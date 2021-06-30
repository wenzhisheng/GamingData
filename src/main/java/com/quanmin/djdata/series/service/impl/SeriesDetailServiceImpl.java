package com.quanmin.djdata.series.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.pojo.series.SeriesDetailInfoVO;
import com.quanmin.djdata.pojo.series.SeriesDetailVO;
import com.quanmin.djdata.pojo.series.SeriesVO;
import com.quanmin.djdata.series.dao.ISeriesDao;
import com.quanmin.djdata.series.dao.ISeriesDetailDao;
import com.quanmin.djdata.series.dao.ISeriesDetailInfoDao;
import com.quanmin.djdata.series.service.ISeriesDetailService;
import com.quanmin.djdata.util.CommonUtil;
import com.quanmin.djdata.util.CurlUtil;
import com.quanmin.djdata.util.RSAEncrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 12:58
 * @ClassName: com.quanmin.djdata.series.service.impl.SeriesServiceImpl
 */
@Service
public class SeriesDetailServiceImpl implements ISeriesDetailService {

    private static final Logger logger = LoggerFactory.getLogger(SeriesDetailServiceImpl.class);

    @Autowired
    private ISeriesDetailDao iSeriesDetailDao;
    @Autowired
    private ISeriesDetailInfoDao iSeriesDetailInfoDao;
    @Autowired
    private ISeriesDao iSeriesDao;

    /**
     * @author: ate
     * @description: 保存系列赛详情
     * @date: 2019/11/13 18:07
     * @param: [seriesDetailVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(SeriesDetailVO seriesDetailVO) {
        if (seriesDetailVO == null){
            seriesDetailVO = new SeriesDetailVO();
        }
        try {
            List<SeriesVO> list = iSeriesDao.list(new SeriesVO());
            // 根据游戏ID获取每个游戏的数据
            for (SeriesVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("series_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/match/series/detail?tenant_id=2&series_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
                // 返回数据为空则不处理
                if (parseObject != null){
                    seriesDetailVO.setId(new BigInteger(parseObject.getString("id"), 10));
                    seriesDetailVO.setSeries_id(new BigInteger(vo.getId().toString(), 10));
                    seriesDetailVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                    seriesDetailVO.setLeague_id(new BigInteger(parseObject.getString("league_id"), 10));
                    seriesDetailVO.setArea_id(new BigInteger(parseObject.getString("area_id"), 10));
                    seriesDetailVO.setCourse_id(new BigInteger(parseObject.getString("course_id"), 10));
                    seriesDetailVO.setStatus(Integer.valueOf(parseObject.getString("status")));
                    seriesDetailVO.setDescription(parseObject.getString("description"));
                    seriesDetailVO.setStart_time(new BigInteger(parseObject.getString("start_time"), 10));
                    seriesDetailVO.setEnd_time(new BigInteger(parseObject.getString("end_time"), 10));
                    // 系列赛详情队伍
                    JSONArray info = JSONArray.parseArray(parseObject.getString("info"));
                    if (info != null){
                        for (int j=0; j<info.size(); j++){
                            JSONObject infoTeam = (JSONObject) info.get(j);
                            SeriesDetailInfoVO seriesDetailInfoVO = new SeriesDetailInfoVO();
                            seriesDetailInfoVO.setSeries_detail_id(seriesDetailVO.getId());
                            seriesDetailInfoVO.setScore(new BigInteger(infoTeam.getString("score"), 10));
                            seriesDetailInfoVO.setTeam_id(new BigInteger(infoTeam.getString("team_id"), 10));
                            // 是否存在
                            if (iSeriesDetailInfoDao.get(seriesDetailInfoVO) == null){
                                iSeriesDetailInfoDao.insert(seriesDetailInfoVO);
                            }
                        }
                    }
                    // 是否存在
                    if (iSeriesDetailDao.get(seriesDetailVO) == null){
                        iSeriesDetailDao.insert(seriesDetailVO);
                    }
                }
            }
        }catch (Exception ex){
            logger.error("insert games error ={}", ex);
            throw new DescribeException("fail", -1);
        }
        return "success";
    }

    /**
     * @author: ate
     * @description: 获取系列赛详情
     * @date: 2019/11/30 14:49
     * @param: [seriesDetailVO]
     * @return: java.lang.Object
     */
    @Override
    public Object get(SeriesDetailVO seriesDetailVO) {
        if (StringUtils.isEmpty(seriesDetailVO.getSeries_id())){
            throw new DescribeException("系列赛ID不能为空", -1);
        }
        return iSeriesDetailDao.get(seriesDetailVO);
    }

}
