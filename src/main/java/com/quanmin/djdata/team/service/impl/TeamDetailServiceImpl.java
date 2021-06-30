package com.quanmin.djdata.team.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.team.TeamDetailPlayersVO;
import com.quanmin.djdata.pojo.team.TeamDetailVO;
import com.quanmin.djdata.pojo.team.TeamVO;
import com.quanmin.djdata.team.dao.ITeamDao;
import com.quanmin.djdata.team.dao.ITeamDetailDao;
import com.quanmin.djdata.team.dao.ITeamDetailPlayersDao;
import com.quanmin.djdata.team.service.ITeamDetailService;
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
 * @CreateDate: 2019-11-14 14:11
 * @ClassName: com.quanmin.djdata.team.service.impl.TeamDetailServiceImpl
 */
@Service
public class TeamDetailServiceImpl implements ITeamDetailService {

    private static final Logger logger = LoggerFactory.getLogger(TeamDetailServiceImpl.class);

    @Autowired
    private ITeamDao iTeamDao;
    @Autowired
    private ITeamDetailDao iTeamDetailDao;
    @Autowired
    private ITeamDetailPlayersDao iTeamDetailPlaypersDao;

    /**
     * @author: ate
     * @description: 保存战队详情
     * @date: 2019/11/14 14:14
     * @param: [teamDetailVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(TeamDetailVO teamDetailVO) {
        if (teamDetailVO == null){
            teamDetailVO = new TeamDetailVO();
        }
        try {
            List<TeamVO> list = iTeamDao.list();
            for (TeamVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("team_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/detail?tenant_id=2&team_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null){
                    JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
                    if (parseObject != null) {
                        teamDetailVO.setId(new BigInteger(parseObject.getString("id"), 10));
                        teamDetailVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                        teamDetailVO.setGame_type(Integer.valueOf(parseObject.getString("game_type")));
                        teamDetailVO.setAlias(parseObject.getString("alias"));
                        teamDetailVO.setName(parseObject.getString("name"));
                        teamDetailVO.setShort_name(parseObject.getString("short_name"));
                        teamDetailVO.setName_english(parseObject.getString("name_english"));
                        teamDetailVO.setLogo(parseObject.getString("logo"));
                        teamDetailVO.setLocal(parseObject.getString("local"));
                        teamDetailVO.setCountry(parseObject.getString("country"));
                        teamDetailVO.setArea(parseObject.getString("area"));
                        teamDetailVO.setArea(JSONArray.parseArray(parseObject.getString("alias")).get(0).toString());
                        // 战队详情玩家
                        JSONArray players = JSONArray.parseArray(parseObject.getString("players"));
                        if (players != null){
                            for (int j=0; j<players.size(); j++){
                                JSONObject plyersItem = (JSONObject) players.get(j);
                                TeamDetailPlayersVO teamDetailPlayersVO = new TeamDetailPlayersVO();
                                teamDetailPlayersVO.setId(new BigInteger(plyersItem.getString("id"), 10));
                                teamDetailPlayersVO.setTeam_detail_id(teamDetailVO.getId());
                                teamDetailPlayersVO.setName(plyersItem.getString("name"));
                                teamDetailPlayersVO.setName_english(plyersItem.getString("name_english"));
                                teamDetailPlayersVO.setCountry(plyersItem.getString("country"));
                                teamDetailPlayersVO.setTeam_player_position(plyersItem.getString("team_player_position"));
                                String alias = plyersItem.getString("alias");
                                if (alias != null){
                                    teamDetailPlayersVO.setAlias(JSONArray.parseArray(alias).get(0).toString());
                                }
                                // 查询是否存在
                                if (iTeamDetailPlaypersDao.get(teamDetailPlayersVO) == null) {
                                    iTeamDetailPlaypersDao.insert(teamDetailPlayersVO);
                                }
                            }
                        }
                        // 是否存在
                        if (iTeamDetailDao.get(teamDetailVO) == null) {
                            iTeamDetailDao.insert(teamDetailVO);
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
     * @description: 分页战队详情
     * @date: 2019/11/30 12:18
     * @param: [teamDetailVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(TeamDetailVO teamDetailVO, PageVO pageVO) {
        if (StringUtils.isEmpty(teamDetailVO.getGame_id())){
            throw new DescribeException("游戏ID不能为空", -1);
        }
        if (StringUtils.isEmpty(teamDetailVO.getId())){
            throw new DescribeException("战队ID不能为空", -1);
        }
        Page<TeamDetailVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        return iTeamDetailDao.page(page, teamDetailVO);
    }

    /**
     * @author: ate
     * @description: 获取战队详情
     * @date: 2019/11/30 15:15
     * @param: [teamDetailVO]
     * @return: java.lang.Object
     */
    @Override
    public Object get(TeamDetailVO teamDetailVO) {
        if (StringUtils.isEmpty(teamDetailVO.getId())){
            throw new DescribeException("战队ID不能为空", -1);
        }
        return iTeamDetailDao.get(teamDetailVO);
    }
}
