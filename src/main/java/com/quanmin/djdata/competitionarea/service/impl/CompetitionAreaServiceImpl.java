package com.quanmin.djdata.competitionarea.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.competitionarea.dao.ICompetitionAreaDao;
import com.quanmin.djdata.competitionarea.service.ICompetitionAreaService;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.competitionarea.CompetitionAreaVO;
import com.quanmin.djdata.pojo.game.GameVO;
import com.quanmin.djdata.util.CommonUtil;
import com.quanmin.djdata.util.CurlUtil;
import com.quanmin.djdata.util.RSAEncrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-12 10:47
 * @ClassName: com.quanmin.djdata.competitionarea.service.impl.CompetitionAreaServiceImpl
 */
@Service
public class CompetitionAreaServiceImpl implements ICompetitionAreaService {

    private static final Logger logger = LoggerFactory.getLogger(CompetitionAreaServiceImpl.class);

    @Autowired
    private ICompetitionAreaDao iCompetitionAreaDao;
    @Autowired
    private IGameDao iGameDao;

    /**
     * @author: ate
     * @description: 保存赛区列表
     * @date: 2019/11/12 17:33
     * @param: [competitionAreaVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(CompetitionAreaVO competitionAreaVO) {
        if (competitionAreaVO == null){
            competitionAreaVO = new CompetitionAreaVO();
        }
        try {
            List<GameVO> list = iGameDao.list();
            for (GameVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("game_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/areas?tenant_id=2&game_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null){
                    JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                    if (result != null){
                        JSONArray jsonArray = JSONArray.parseArray(result.getString("items"));
                        for (int i=0; i<jsonArray.size(); i++){
                            JSONObject parseObject = (JSONObject) jsonArray.get(i);
                            competitionAreaVO.setId(new BigInteger(parseObject.getString("id"), 10));
                            competitionAreaVO.setName(parseObject.getString("name"));
                            competitionAreaVO.setArea(parseObject.getString("area"));
                            competitionAreaVO.setGame_id(parseObject.getString("game_id"));
                            competitionAreaVO.setDescription(parseObject.getString("description"));
                            // 根据ID和名称查询赛区是否存在
                            if (iCompetitionAreaDao.get(competitionAreaVO) == null){
                                iCompetitionAreaDao.insert(competitionAreaVO);
                            }
                        }
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
     * @description: 分页赛区
     * @date: 2019/11/30 15:03
     * @param: [competitionAreaVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(CompetitionAreaVO competitionAreaVO, PageVO pageVO) {
        Page<CompetitionAreaVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        return iCompetitionAreaDao.page(page, competitionAreaVO);
    }

}
