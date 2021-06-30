package com.quanmin.djdata.league.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.league.dao.ILeagueDao;
import com.quanmin.djdata.league.service.ILeagueService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameVO;
import com.quanmin.djdata.pojo.league.LeagueVO;
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
 * @CreateDate: 2019-11-12 18:28
 * @ClassName: com.quanmin.djdata.league.service.impl.LeagueServiceImpl
 */
@Service
public class LeagueServiceImpl implements ILeagueService {

    private static final Logger logger = LoggerFactory.getLogger(LeagueServiceImpl.class);

    @Autowired
    private ILeagueDao iLeagueDao;
    @Autowired
    private IGameDao iGameDao;

    /**
     * @author: ate
     * @description: 保存联赛列表
     * @date: 2019/11/12 20:39
     * @param: [leagueVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(LeagueVO leagueVO) {
        if (leagueVO == null){
            leagueVO = new LeagueVO();
        }
        try {
            List<GameVO> list = iGameDao.list();
            for (GameVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("game_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/leagues?tenant_id=2&game_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null){
                    JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                    if (result != null){
                        JSONArray jsonArray = JSONArray.parseArray(result.getString("items"));
                        for (int i=0; i<jsonArray.size(); i++){
                            JSONObject parseObject = (JSONObject) jsonArray.get(i);
                            leagueVO.setId(new BigInteger(parseObject.getString("id"), 10));
                            leagueVO.setName(parseObject.getString("name"));
                            leagueVO.setShort_name(parseObject.getString("short_name"));
                            leagueVO.setLogo(parseObject.getString("logo"));
                            leagueVO.setOrganizer(parseObject.getString("organizer"));
                            leagueVO.setLevel(parseObject.getString("level"));
                            leagueVO.setLocal(parseObject.getString("local"));
                            leagueVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                            leagueVO.setArea_id(new BigInteger(parseObject.getString("area_id"), 10));
                            leagueVO.setLimit_team(Integer.valueOf(parseObject.getString("limit_team")));
                            leagueVO.setDescription(parseObject.getString("description"));
                            // 根据ID和名称查询游戏是否存在
                            if (iLeagueDao.get(leagueVO) == null){
                                iLeagueDao.insert(leagueVO);
                            }
                        }
                    }
                }
            }
        }catch (Exception ex){
            logger.error("insert games error ={}", ex);
            return new DescribeException("fail", -1);
        }
        return "success";
    }

    /**
     * @author: ate
     * @description: 分页联赛
     * @date: 2019/11/30 14:55
     * @param: [leagueVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(LeagueVO leagueVO, PageVO pageVO) {
        Page<LeagueVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        return iLeagueDao.page(page, leagueVO);
    }

}
