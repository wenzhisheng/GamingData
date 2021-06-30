package com.quanmin.djdata.team.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameVO;
import com.quanmin.djdata.pojo.team.TeamAliasVO;
import com.quanmin.djdata.pojo.team.TeamRankVO;
import com.quanmin.djdata.team.dao.ITeamRankDao;
import com.quanmin.djdata.team.service.ITeamRankService;
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
 * @CreateDate: 2019-11-14 13:18
 * @ClassName: com.quanmin.djdata.team.service.impl.TeamRankServiceImpl
 */
@Service
public class TeamRankServiceImpl implements ITeamRankService {

    private static final Logger logger = LoggerFactory.getLogger(TeamRankServiceImpl.class);

    @Autowired
    private ITeamRankDao iTeamRankDao;
    @Autowired
    private IGameDao iGameDao;

    /**
     * @author: ate
     * @description: 保存战队排名
     * @date: 2019/11/14 13:26
     * @param: [teamRankVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(TeamRankVO teamRankVO) {
        if (teamRankVO == null){
            teamRankVO = new TeamRankVO();
        }
        try {
            List<GameVO> list = iGameDao.list();
            // 根据游戏ID获取每个游戏的数据
            for (GameVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("game_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String url;
                if (StringUtils.isEmpty(teamRankVO.getOffset()) && StringUtils.isEmpty(teamRankVO.getLimit())) {
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/rank?tenant_id=2&game_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                } else {
                    treeMap.put("offset", teamRankVO.getOffset().toString());
                    treeMap.put("limit", teamRankVO.getLimit().toString());
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/rank?tenant_id=2&game_id={0}&request_time={1}&offset={2}&limit={3}\"",
                            vo.getId().toString(), treeMap.get("request_time"), teamRankVO.getOffset(), teamRankVO.getLimit());
                }
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null) {
                    JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                    // 返回数据为空则不处理
                    if (result != null){
                        JSONArray jsonArray = JSONArray.parseArray(result.getString("items"));
                        if (jsonArray != null) {
                            // 查询出数据，遍历保存json数组数据到数据库
                            for (int i=0; i<jsonArray.size(); i++){
                                JSONObject parseObject = (JSONObject) jsonArray.get(i);
                                teamRankVO.setTeam_id(new BigInteger(parseObject.getString("team_id"), 10));
                                teamRankVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                                teamRankVO.setShort_name(parseObject.getString("short_name"));
                                teamRankVO.setRanks(Integer.valueOf(parseObject.getString("rank")));
                                teamRankVO.setSort(Integer.valueOf(parseObject.getString("sort")));
                                // 是否存在
                                if (iTeamRankDao.get(teamRankVO) == null){
                                    iTeamRankDao.insert(teamRankVO);
                                }
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
     * @description: 分页战队排名
     * @date: 2019/11/22 16:36
     * @param: [teamRankVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(TeamRankVO teamRankVO, PageVO pageVO) {
        if (StringUtils.isEmpty(teamRankVO.getGame_id())) {
            throw new DescribeException("游戏ID不能为空", -1);
        }
        Page<TeamRankVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        return iTeamRankDao.page(page, teamRankVO);
    }
}
