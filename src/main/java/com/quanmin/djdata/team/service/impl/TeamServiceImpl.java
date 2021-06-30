package com.quanmin.djdata.team.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameVO;
import com.quanmin.djdata.pojo.team.TeamAliasVO;
import com.quanmin.djdata.pojo.team.TeamVO;
import com.quanmin.djdata.team.dao.ITeamAliasDao;
import com.quanmin.djdata.team.dao.ITeamDao;
import com.quanmin.djdata.team.service.ITeamService;
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
 * @CreateDate: 2019-11-13 23:05
 * @ClassName: com.quanmin.djdata.team.service.impl.TeamServiceImpl
 */
@Service
public class TeamServiceImpl implements ITeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Autowired
    private ITeamDao iTeamDao;
    @Autowired
    private IGameDao iGameDao;
    @Autowired
    private ITeamAliasDao iTeamAliasDao;

    /**
     * @author: ate
     * @description: 保存战队列表
     * @date: 2019/11/13 23:12
     * @param: [teamVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(TeamVO teamVO) {
        if (teamVO == null){
            teamVO = new TeamVO();
        }
        try {
            List<GameVO> list = iGameDao.list();
            // 根据游戏ID获取每个游戏的数据
            for (GameVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("game_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String url;
                if (StringUtils.isEmpty(teamVO.getOffset()) && StringUtils.isEmpty(teamVO.getLimit())) {
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams?tenant_id=2&game_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                } else {
                    treeMap.put("offset", teamVO.getOffset().toString());
                    treeMap.put("limit", teamVO.getLimit().toString());
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams?tenant_id=2&game_id={0}&request_time={1}&offset={2}&limit={3}\"",
                            vo.getId().toString(), treeMap.get("request_time"), teamVO.getOffset(), teamVO.getLimit());
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
                                teamVO.setId(new BigInteger(parseObject.getString("id"), 10));
                                teamVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                                teamVO.setGame_type(Integer.valueOf(parseObject.getString("game_type")));
                                teamVO.setName(parseObject.getString("name"));
                                teamVO.setShort_name(parseObject.getString("short_name"));
                                teamVO.setName_english(parseObject.getString("name_english"));
                                teamVO.setLocal(parseObject.getString("local"));
                                teamVO.setCountry(parseObject.getString("country"));
                                teamVO.setArea(parseObject.getString("area"));
                                teamVO.setLogo(parseObject.getString("logo"));
                                // 战队队伍
                                JSONArray alias = JSONArray.parseArray(parseObject.getString("alias"));
                                if (alias != null){
                                    TeamAliasVO teamAliasVO = new TeamAliasVO();
                                    teamAliasVO.setTeam_id(teamVO.getId());
                                    if (alias.size() == 2){
                                        teamAliasVO.setName(alias.get(0).toString());
                                        teamAliasVO.setCode(alias.get(1).toString());
                                    } else if (alias.size() ==1){
                                        teamAliasVO.setName(alias.get(0).toString());
                                    }
                                    // 查询是否存在
                                    if (iTeamAliasDao.get(teamAliasVO) == null) {
                                        iTeamAliasDao.insert(teamAliasVO);
                                    }
                                }
                                // 是否存在
                                if (iTeamDao.get(teamVO) == null){
                                    iTeamDao.insert(teamVO);
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
     * @description: 分页战队
     * @date: 2019/11/22 16:02
     * @param: [teamVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(TeamVO teamVO, PageVO pageVO) {
        Page<TeamVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        IPage<TeamVO> ipage = iTeamDao.page(page, teamVO);
        List<TeamVO> records = ipage.getRecords();
        if (records.size() > 0){
            for(int i=0; i<records.size(); i++){
                TeamVO teamVO1 = records.get(i);
                TeamAliasVO teamAliasVO = new TeamAliasVO();
                teamAliasVO.setTeam_id(teamVO1.getId());
                teamVO1.setAlias(iTeamAliasDao.list(teamAliasVO));
            }
        }
        return ipage.setRecords(records);
    }

}
