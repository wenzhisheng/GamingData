package com.quanmin.djdata.lol.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameEquipmentsDao;
import com.quanmin.djdata.game.dao.IGameRoleDao;
import com.quanmin.djdata.lol.dao.*;
import com.quanmin.djdata.lol.service.ILolService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.finish.FinishSeriesVO;
import com.quanmin.djdata.pojo.game.GameEquipmentVO;
import com.quanmin.djdata.pojo.game.GameRoleVO;
import com.quanmin.djdata.pojo.lol.*;
import com.quanmin.djdata.pojo.series.SeriesVO;
import com.quanmin.djdata.pojo.team.TeamDetailVO;
import com.quanmin.djdata.pojo.team.TeamVO;
import com.quanmin.djdata.series.dao.ISeriesDao;
import com.quanmin.djdata.team.dao.ITeamDao;
import com.quanmin.djdata.team.dao.ITeamDetailDao;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:33
 * @ClassName: com.quanmin.djdata.lol.service.LolServiceImpl
 */
@Service
public class LolServiceImpl implements ILolService {

    private static final Logger logger = LoggerFactory.getLogger(LolServiceImpl.class);

    @Autowired
    private ILolDao iLolDao;
    @Autowired
    private ITeamDao iTeamDao;
    @Autowired
    private ITeamDetailDao iTeamDetailDao;
    @Autowired
    private ISeriesDao iSeriesDao;
    @Autowired
    private IFinishSeriesLolDao iFinishSeriesDao;
    @Autowired
    private ILolTeamBlueDao iLolTeamBlueDao;
    @Autowired
    private ILolTeamBlueBansDao iLolTeamBlueBansDao;
    @Autowired
    private ILolTeamBluePicksDao iLolTeamBluePicksDao;
    @Autowired
    private ILolTeamRedDao iLolTeamRedDao;
    @Autowired
    private ILolTeamRedBansDao iLolTeamRedBansDao;
    @Autowired
    private ILolTeamRedPicksDao iLolTeamRedPicksDao;
    @Autowired
    private ILolTeamRedPlayersDao iLolTeamRedPlayersDao;
    @Autowired
    private ILolTeamRedPlayersEquipmentDao iLolTeamRedPlayersEquipmentDao;
    @Autowired
    private ILolTeamBluePlayersDao iLolTeamBluePlayersDao;
    @Autowired
    private ILolTeamBluePlayersEquipmentDao iLolTeamBluePlayersEquipmentDao;
    @Autowired
    private IGameEquipmentsDao iGameEquipmentsDao;
    @Autowired
    private IGameRoleDao iGameRoleDao;

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果
     * @date: 2019/11/14 23:09
     * @param: [lolVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(LolVO lolVO) {
        if (lolVO == null){
            lolVO = new LolVO();
        }
        try {
            SeriesVO seriesVO = new SeriesVO();
            seriesVO.setGame_id(new BigInteger("651841432110167818"));
            // 传值则单个抓取
            List<SeriesVO> list = new ArrayList<SeriesVO>();
            if (StringUtils.isEmpty(lolVO.getSeries_id())){
                list = iSeriesDao.list(seriesVO);
            } else {
                seriesVO.setId(lolVO.getSeries_id());
                list.add(seriesVO);
            }
            // 否则全部抓取
            for (SeriesVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("series_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/match/lol/finish?tenant_id=2&series_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null){
                    JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                    if (result != null){
                        JSONArray seriesArray = JSONArray.parseArray(result.getString("series"));
                        // 返回数据为空则不处理
                        if (seriesArray != null){
                            // 查询出数据，遍历保存json数组数据到数据库
                            for (int i=0; i<seriesArray.size(); i++){
                                JSONObject series = (JSONObject) seriesArray.get(i);
                                FinishSeriesVO finishSeriesVO = new FinishSeriesVO();
                                finishSeriesVO.setSeries_id(vo.getId());
                                finishSeriesVO.setScore(Integer.valueOf(series.getString("score")));
                                finishSeriesVO.setTeam_id(new BigInteger(series.getString("team_id"), 10));
                                if (iFinishSeriesDao.get(finishSeriesVO) == null) {
                                    iFinishSeriesDao.insert(finishSeriesVO);
                                }
                            }
                        }
                        JSONArray matchArray = JSONArray.parseArray(result.getString("match"));
                        // 返回数据为空则不处理
                        if (matchArray != null){
                            // 查询出数据，遍历保存json数组数据到数据库
                            for (int ii=0; ii<matchArray.size(); ii++){
                                JSONObject match = (JSONObject) matchArray.get(ii);
                                // base
                                saveLolBase(lolVO, match);
                                // flag
                                saveLolFlag(lolVO, match);
                                // blue_team
                                saveLolBlueTeam(lolVO, match);
                                // red_team
                                saveLolRedTeam(lolVO, match);
                                // blue
                                saveLolBlue(lolVO, match);
                                // red
                                saveLolRed(lolVO, match);
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

    private void saveLolRed(LolVO lolVO, JSONObject match) {
        JSONObject red = JSONObject.parseObject(match.getString("red"));
        LolTeamVO lolTeamVO1 = new LolTeamVO();
        lolTeamVO1.setFinish_match_lol_id(lolVO.getMatch_id());
        lolTeamVO1.setTeam_id(new BigInteger(red.getString("team_id"), 10));
        lolTeamVO1.setKills(Integer.valueOf(red.getString("kills")));
        lolTeamVO1.setDeaths(Integer.valueOf(red.getString("deaths")));
        lolTeamVO1.setAssists(Integer.valueOf(red.getString("assists")));
        lolTeamVO1.setGold(Integer.valueOf(red.getString("gold")));
        lolTeamVO1.setCs(Integer.valueOf(red.getString("cs")));
        lolTeamVO1.setDrakes(Integer.valueOf(red.getString("drakes")));
        lolTeamVO1.setLevel(Integer.valueOf(red.getString("level")));
        lolTeamVO1.setDamage_taken(Integer.valueOf(red.getString("damage_taken")));
        lolTeamVO1.setDamage(Integer.valueOf(red.getString("damage")));
        lolTeamVO1.setNahsor_barons(Integer.valueOf(red.getString("nahsor_barons")));
        lolTeamVO1.setInhibitors(Integer.valueOf(red.getString("inhibitors")));
        lolTeamVO1.setTowers(Integer.valueOf(red.getString("towers")));
        // picks
        JSONArray picks1 = JSONArray.parseArray(red.getString("picks"));
        if (picks1 != null){
            for (int iiii=0; iiii<picks1.size(); iiii++){
                String role_id = picks1.get(iiii).toString();
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_lol_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamRoleVO.setRole_id(new BigInteger(role_id, 10));
                // 查询英雄
                GameRoleVO gameRoleVO = new GameRoleVO();
                gameRoleVO.setId(lolTeamRoleVO.getRole_id());
                GameRoleVO gameRoleVO1 = iGameRoleDao.get(gameRoleVO);
                if (gameRoleVO1 != null){
                    lolTeamRoleVO.setRole_logo(gameRoleVO1.getIcon());
                    lolTeamRoleVO.setRole_name(gameRoleVO1.getName());
                    lolTeamRoleVO.setRole_name_english(gameRoleVO1.getName_english());
                    lolTeamRoleVO.setRole_alias(gameRoleVO1.getAlias());
                    lolTeamRoleVO.setRole_info(gameRoleVO1.getInfo());
                    lolTeamRoleVO.setRole_description(gameRoleVO1.getDescription());
                }
                // 保存选择英雄
                if (iLolTeamRedPicksDao.get(lolTeamRoleVO) == null) {
                    iLolTeamRedPicksDao.insert(lolTeamRoleVO);
                }
            }
        }
        // bans
        JSONArray bans1 = JSONArray.parseArray(red.getString("bans"));
        if (bans1 != null){
            for (int iiii=0; iiii<bans1.size(); iiii++){
                String role_id = bans1.get(iiii).toString();
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_lol_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamRoleVO.setRole_id(new BigInteger(role_id, 10));
                // 查询玩家装备
                GameRoleVO gameRoleVO = new GameRoleVO();
                gameRoleVO.setId(lolTeamRoleVO.getRole_id());
                GameRoleVO gameRoleVO1 = iGameRoleDao.get(gameRoleVO);
                if (gameRoleVO1 != null){
                    lolTeamRoleVO.setRole_logo(gameRoleVO1.getIcon());
                    lolTeamRoleVO.setRole_name(gameRoleVO1.getName());
                    lolTeamRoleVO.setRole_name_english(gameRoleVO1.getName_english());
                    lolTeamRoleVO.setRole_alias(gameRoleVO1.getAlias());
                    lolTeamRoleVO.setRole_info(gameRoleVO1.getInfo());
                    lolTeamRoleVO.setRole_description(gameRoleVO1.getDescription());
                }
                // 保存禁用英雄
                if (iLolTeamRedBansDao.get(lolTeamRoleVO) == null) {
                    iLolTeamRedBansDao.insert(lolTeamRoleVO);
                }
            }
        }
        // 保存lol赛果战队
        if (iLolTeamRedDao.get(lolTeamVO1) == null) {
            iLolTeamRedDao.insert(lolTeamVO1);
        }
        // players
        JSONArray players2 = JSONArray.parseArray(red.getString("players"));
        if (players2 != null){
            for (int iii=0; iii<players2.size(); iii++){
                JSONObject playersItem = JSONObject.parseObject(players2.get(iii).toString());
                LolTeamPlayersVO lolTeamPlayersVO = new LolTeamPlayersVO();
                lolTeamPlayersVO.setFinish_match_lol_team_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamPlayersVO.setKda(playersItem.getString("kda"));
                lolTeamPlayersVO.setCs(Integer.valueOf(playersItem.getString("cs")));
                lolTeamPlayersVO.setLevel(Integer.valueOf(playersItem.getString("level")));
                lolTeamPlayersVO.setKills(Integer.valueOf(playersItem.getString("kills")));
                lolTeamPlayersVO.setDeaths(Integer.valueOf(playersItem.getString("deaths")));
                lolTeamPlayersVO.setAssists(Integer.valueOf(playersItem.getString("assists")));
                lolTeamPlayersVO.setGold(Integer.valueOf(playersItem.getString("gold")));
                lolTeamPlayersVO.setDamage_taken(Integer.valueOf(playersItem.getString("damage_taken")));
                lolTeamPlayersVO.setDamage(Integer.valueOf(playersItem.getString("damage")));
                lolTeamPlayersVO.setHeal(Integer.valueOf(playersItem.getString("heal")));
                lolTeamPlayersVO.setHp(Integer.valueOf(playersItem.getString("hp")));
                lolTeamPlayersVO.setRole(playersItem.getString("role"));
                lolTeamPlayersVO.setPlayer(new BigInteger(playersItem.getString("player"),10));
                lolTeamPlayersVO.setChampion(new BigInteger(playersItem.getString("champion"),10));
                lolTeamPlayersVO.setGold_rate(playersItem.getString("gold_rate"));
                lolTeamPlayersVO.setRelative_gold_diff(Integer.valueOf(playersItem.getString("relative_gold_diff")));
                lolTeamPlayersVO.setDamage_taken_rate(playersItem.getString("damage_taken_rate"));
                lolTeamPlayersVO.setDamage_taken_min(playersItem.getString("damage_taken_min"));
                lolTeamPlayersVO.setDamage_taken_death(playersItem.getString("damage_taken_death"));
                lolTeamPlayersVO.setDamage_rate(playersItem.getString("damage_rate"));
                lolTeamPlayersVO.setDamage_min(playersItem.getString("damage_min"));
                lolTeamPlayersVO.setDamage_kill(playersItem.getString("damage_kill"));
                lolTeamPlayersVO.setSummoner_spells(playersItem.getString("summoner_spells"));
                // 玩家装备
                JSONArray items1 = JSONArray.parseArray(playersItem.getString("items"));
                if (items1 != null){
                    for (int iiii=0; iiii<items1.size(); iiii++){
                        String equipment_id = items1.get(iiii).toString();
                        LolTeamPlayersEquipmentVO equipmentVO = new LolTeamPlayersEquipmentVO();
                        equipmentVO.setFinish_match_id(lolVO.getMatch_id());
                        equipmentVO.setFinish_match_team_id(lolTeamVO1.getTeam_id());
                        equipmentVO.setFinish_match_players_id(lolTeamPlayersVO.getPlayer());
                        equipmentVO.setEquipment_id(new BigInteger(equipment_id, 10));
                        // 查询英雄
                        GameEquipmentVO gameEquipmentVO = new GameEquipmentVO();
                        gameEquipmentVO.setId(equipmentVO.getEquipment_id());
                        GameEquipmentVO gameEquipmentVO1 = iGameEquipmentsDao.get(gameEquipmentVO);
                        if (gameEquipmentVO1 != null){
                            equipmentVO.setEquipment_logo(gameEquipmentVO1.getIcon());
                            equipmentVO.setEquipment_name(gameEquipmentVO1.getName());
                            equipmentVO.setEquipment_name_english(gameEquipmentVO1.getName_english());
                            equipmentVO.setEquipment_alias(gameEquipmentVO1.getAlias());
                            equipmentVO.setEquipment_alias(gameEquipmentVO1.getInfo());
                            equipmentVO.setEquipment_description(gameEquipmentVO1.getDescription());
                        }
                        // 保存玩家装备
                        if (iLolTeamRedPlayersEquipmentDao.get(equipmentVO) == null) {
                            iLolTeamRedPlayersEquipmentDao.insert(equipmentVO);
                        }
                    }
                }
                // 保存lol赛果战队玩家
                if (iLolTeamRedPlayersDao.get(lolTeamPlayersVO) == null) {
                    iLolTeamRedPlayersDao.insert(lolTeamPlayersVO);
                }
            }
        }
        // 保存lol赛果数据
        if (iLolDao.get(lolVO) == null) {
            iLolDao.insert(lolVO);
        }
    }

    private JSONObject saveLolBlue(LolVO lolVO, JSONObject match) {
        JSONObject blue = JSONObject.parseObject(match.getString("blue"));
        LolTeamVO lolTeamVO1 = new LolTeamVO();
        lolTeamVO1.setFinish_match_lol_id(lolVO.getMatch_id());
        lolTeamVO1.setTeam_id(new BigInteger(blue.getString("team_id"), 10));
        lolTeamVO1.setKills(Integer.valueOf(blue.getString("kills")));
        lolTeamVO1.setDeaths(Integer.valueOf(blue.getString("deaths")));
        lolTeamVO1.setAssists(Integer.valueOf(blue.getString("assists")));
        lolTeamVO1.setGold(Integer.valueOf(blue.getString("gold")));
        lolTeamVO1.setCs(Integer.valueOf(blue.getString("cs")));
        lolTeamVO1.setDrakes(Integer.valueOf(blue.getString("drakes")));
        lolTeamVO1.setLevel(Integer.valueOf(blue.getString("level")));
        lolTeamVO1.setDamage_taken(Integer.valueOf(blue.getString("damage_taken")));
        lolTeamVO1.setDamage(Integer.valueOf(blue.getString("damage")));
        lolTeamVO1.setNahsor_barons(Integer.valueOf(blue.getString("nahsor_barons")));
        lolTeamVO1.setInhibitors(Integer.valueOf(blue.getString("inhibitors")));
        lolTeamVO1.setTowers(Integer.valueOf(blue.getString("towers")));
        // picks
        JSONArray picks = JSONArray.parseArray(blue.getString("picks"));
        if (picks != null){
            for (int iiii=0; iiii<picks.size(); iiii++){
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_lol_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamRoleVO.setRole_id(new BigInteger(picks.get(iiii).toString(), 10));
                // 查询英雄
                GameRoleVO gameRoleVO = new GameRoleVO();
                gameRoleVO.setId(lolTeamRoleVO.getRole_id());
                GameRoleVO gameRoleVO1 = iGameRoleDao.get(gameRoleVO);
                if (gameRoleVO1 != null){
                    lolTeamRoleVO.setRole_logo(gameRoleVO1.getIcon());
                    lolTeamRoleVO.setRole_name(gameRoleVO1.getName());
                    lolTeamRoleVO.setRole_name_english(gameRoleVO1.getName_english());
                    lolTeamRoleVO.setRole_alias(gameRoleVO1.getAlias());
                    lolTeamRoleVO.setRole_info(gameRoleVO1.getInfo());
                    lolTeamRoleVO.setRole_description(gameRoleVO1.getDescription());
                }
                // 保存选择英雄
                if (iLolTeamBluePicksDao.get(lolTeamRoleVO) == null) {
                    iLolTeamBluePicksDao.insert(lolTeamRoleVO);
                }
            }
        }
        // bans
        JSONArray bans = JSONArray.parseArray(blue.getString("bans"));
        if (bans != null){
            for (int iiii=0; iiii<bans.size(); iiii++){
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_lol_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamRoleVO.setRole_id(new BigInteger(bans.get(iiii).toString(), 10));
                // 查询英雄
                GameRoleVO gameRoleVO = new GameRoleVO();
                gameRoleVO.setId(lolTeamRoleVO.getRole_id());
                GameRoleVO gameRoleVO1 = iGameRoleDao.get(gameRoleVO);
                if (gameRoleVO1 != null){
                    lolTeamRoleVO.setRole_logo(gameRoleVO1.getIcon());
                    lolTeamRoleVO.setRole_name(gameRoleVO1.getName());
                    lolTeamRoleVO.setRole_name_english(gameRoleVO1.getName_english());
                    lolTeamRoleVO.setRole_alias(gameRoleVO1.getAlias());
                    lolTeamRoleVO.setRole_info(gameRoleVO1.getInfo());
                    lolTeamRoleVO.setRole_description(gameRoleVO1.getDescription());
                }
                // 保存禁用英雄
                if (iLolTeamBlueBansDao.get(lolTeamRoleVO) == null) {
                    iLolTeamBlueBansDao.insert(lolTeamRoleVO);
                }
            }
        }
        // 保存lol赛果战队
        if (iLolTeamBlueDao.get(lolTeamVO1) == null) {
            iLolTeamBlueDao.insert(lolTeamVO1);
        }
        // players
        JSONArray players1 = JSONArray.parseArray(blue.getString("players"));
        if (players1 != null){
            for (int iii=0; iii<players1.size(); iii++){
                JSONObject playersItem = JSONObject.parseObject(players1.get(iii).toString());
                LolTeamPlayersVO lolTeamPlayersVO = new LolTeamPlayersVO();
                lolTeamPlayersVO.setFinish_match_lol_team_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamPlayersVO.setKda(playersItem.getString("kda"));
                lolTeamPlayersVO.setCs(Integer.valueOf(playersItem.getString("cs")));
                lolTeamPlayersVO.setLevel(Integer.valueOf(playersItem.getString("level")));
                lolTeamPlayersVO.setKills(Integer.valueOf(playersItem.getString("kills")));
                lolTeamPlayersVO.setDeaths(Integer.valueOf(playersItem.getString("deaths")));
                lolTeamPlayersVO.setAssists(Integer.valueOf(playersItem.getString("assists")));
                lolTeamPlayersVO.setGold(Integer.valueOf(playersItem.getString("gold")));
                lolTeamPlayersVO.setDamage_taken(Integer.valueOf(playersItem.getString("damage_taken")));
                lolTeamPlayersVO.setDamage(Integer.valueOf(playersItem.getString("damage")));
                lolTeamPlayersVO.setHeal(Integer.valueOf(playersItem.getString("heal")));
                lolTeamPlayersVO.setHp(Integer.valueOf(playersItem.getString("hp")));
                lolTeamPlayersVO.setRole(playersItem.getString("role"));
                lolTeamPlayersVO.setPlayer(new BigInteger(playersItem.getString("player"),10));
                lolTeamPlayersVO.setChampion(new BigInteger(playersItem.getString("champion"),10));
                lolTeamPlayersVO.setGold_rate(playersItem.getString("gold_rate"));
                lolTeamPlayersVO.setRelative_gold_diff(Integer.valueOf(playersItem.getString("relative_gold_diff")));
                lolTeamPlayersVO.setDamage_taken_rate(playersItem.getString("damage_taken_rate"));
                lolTeamPlayersVO.setDamage_taken_min(playersItem.getString("damage_taken_min"));
                lolTeamPlayersVO.setDamage_taken_death(playersItem.getString("damage_taken_death"));
                lolTeamPlayersVO.setDamage_rate(playersItem.getString("damage_rate"));
                lolTeamPlayersVO.setDamage_min(playersItem.getString("damage_min"));
                lolTeamPlayersVO.setDamage_kill(playersItem.getString("damage_kill"));
                lolTeamPlayersVO.setSummoner_spells(playersItem.getString("summoner_spells"));
                // 玩家装备
                JSONArray items = JSONArray.parseArray(playersItem.getString("items"));
                if (items != null){
                    for (int iiii=0; iiii<items.size(); iiii++){
                        String equipment_id = items.get(iiii).toString();
                        LolTeamPlayersEquipmentVO equipmentVO = new LolTeamPlayersEquipmentVO();
                        equipmentVO.setFinish_match_id(lolVO.getMatch_id());
                        equipmentVO.setFinish_match_team_id(lolTeamVO1.getTeam_id());
                        equipmentVO.setFinish_match_players_id(lolTeamPlayersVO.getPlayer());
                        equipmentVO.setEquipment_id(new BigInteger(equipment_id, 10));
                        // 查询玩家装备
                        GameEquipmentVO gameEquipmentVO = new GameEquipmentVO();
                        gameEquipmentVO.setId(equipmentVO.getEquipment_id());
                        GameEquipmentVO gameEquipmentVO1 = iGameEquipmentsDao.get(gameEquipmentVO);
                        if (gameEquipmentVO1 != null){
                            equipmentVO.setEquipment_logo(gameEquipmentVO1.getIcon());
                            equipmentVO.setEquipment_name(gameEquipmentVO1.getName());
                            equipmentVO.setEquipment_name_english(gameEquipmentVO1.getName_english());
                            equipmentVO.setEquipment_alias(gameEquipmentVO1.getAlias());
                            equipmentVO.setEquipment_alias(gameEquipmentVO1.getInfo());
                            equipmentVO.setEquipment_description(gameEquipmentVO1.getDescription());
                        }
                        // 保存玩家装备
                        if (iLolTeamBluePlayersEquipmentDao.get(equipmentVO) == null) {
                            iLolTeamBluePlayersEquipmentDao.insert(equipmentVO);
                        }
                    }
                }
                // 保存lol赛果战队玩家
                if (iLolTeamBluePlayersDao.get(lolTeamPlayersVO) == null) {
                    iLolTeamBluePlayersDao.insert(lolTeamPlayersVO);
                }
            }
        }
        return blue;
    }

    private void saveLolRedTeam(LolVO lolVO, JSONObject match) {
        JSONObject red_team = JSONObject.parseObject(match.getString("red_team"));
        lolVO.setRed_team_tag(red_team.getString("tag"));
        lolVO.setRed_team_id(new BigInteger(red_team.getString("id"), 10));
        BigInteger red_team_id = lolVO.getRed_team_id();
        if (red_team_id != null){
            TeamVO teamVO = new TeamVO();
            teamVO.setId(red_team_id);
            TeamVO teamVO1 = iTeamDao.get(teamVO);
            if (teamVO1 != null){
                lolVO.setRed_team_logo(teamVO1.getLogo());
                lolVO.setRed_team_name(teamVO1.getName());
            } else {
                lolVO.setRed_team_logo(red_team.getString("logo"));
                lolVO.setRed_team_name(red_team.getString("name"));
            }
        }
        lolVO.setRed_team_score(Integer.valueOf(red_team.getString("score")));
    }

    private void saveLolBlueTeam(LolVO lolVO, JSONObject match) {
        JSONObject blue_team = JSONObject.parseObject(match.getString("blue_team"));
        lolVO.setBlue_team_tag(blue_team.getString("tag"));
        lolVO.setBlue_team_id(new BigInteger(blue_team.getString("id"), 10));
        BigInteger blue_team_id = lolVO.getBlue_team_id();
        if (blue_team_id != null){
            TeamVO teamVO = new TeamVO();
            teamVO.setId(blue_team_id);
            TeamVO teamVO1 = iTeamDao.get(teamVO);
            if (teamVO1 != null){
                lolVO.setBlue_team_logo(teamVO1.getLogo());
                lolVO.setBlue_team_name(teamVO1.getName());
            } else {
                lolVO.setBlue_team_logo(blue_team.getString("logo"));
                lolVO.setBlue_team_name(blue_team.getString("name"));
            }
        }
        lolVO.setBlue_team_score(Integer.valueOf(blue_team.getString("score")));
    }

    private void saveLolFlag(LolVO lolVO, JSONObject match) {
        JSONObject flag = JSONObject.parseObject(match.getString("flag"));
        lolVO.setFirst_blood(flag.getString("first_blood"));
        lolVO.setFirst_tower(flag.getString("first_tower"));
        lolVO.setFive_kills(flag.getString("five_kills"));
        lolVO.setFive_kills(flag.getString("ten_kills"));
        lolVO.setFirst_drakes(flag.getString("first_drakes"));
        lolVO.setFirst_nashors(flag.getString("first_nashors"));
        lolVO.setFirst_inhibitor(flag.getString("first_inhibitor"));
        lolVO.setHerald(flag.getString("herald"));
    }

    private void saveLolBase(LolVO lolVO, JSONObject match) {
        lolVO.setSeries_id(new BigInteger(match.getString("series_id"), 10));
        lolVO.setMatch_id(new BigInteger(match.getString("match_id"), 10));
        lolVO.setGame_id(new BigInteger(match.getString("game_id"), 10));
        lolVO.setGame_no(Integer.valueOf(match.getString("game_no")));
        lolVO.setCourse_name(match.getString("course_name"));
        lolVO.setBlue_gold_diff(Integer.valueOf(match.getString("blue_gold_diff")));
        lolVO.setStart_time(match.getString("start_time"));
        lolVO.setCreatedAt(match.getString("createdAt"));
        lolVO.setGame_time(Integer.valueOf(match.getString("game_time")));
        lolVO.setFinished(match.getString("finished").equals("true") ? true : false);
        lolVO.setValid(match.getString("valid").equals("true") ? true : false);
        lolVO.setWinner(match.getString("winner"));
    }

    /**
     * @author: ate
     * @description: 分页王者荣耀赛果
     * @date: 2019/11/20 11:48
     * @param: [lolVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(LolVO lolVO, PageVO pageVO) {
        if (StringUtils.isEmpty(lolVO.getSeries_id())){
            throw new DescribeException("系列赛ID不能为空", -1);
        }
        Page<LolVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        IPage<LolVO> ipage = iLolDao.page(page, lolVO);
        List<LolVO> records = ipage.getRecords();
        if (records.size() > 0){
            for (int i=0; i<records.size(); i++) {
                // 蓝队
                blueTeam(records, i);
                // 红队
                redTeam(records, i);
            }
        }
        return ipage.setRecords(records);
    }

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果战队信息
     * @date: 2019/11/30 13:03
     * @param: [lolVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insertTeamInfo(LolVO lolVO) {
        List<LolVO> list = iLolDao.list(lolVO);
        for (LolVO vo: list){
            if (StringUtils.isEmpty(vo.getBlue_team_name()) || StringUtils.isEmpty(vo.getBlue_team_logo())) {
                TeamVO teamVO = new TeamVO();
                teamVO.setId(vo.getBlue_team_id());
                TeamVO teamVO1 = iTeamDao.get(teamVO);
                if (teamVO1 != null) {
                    vo.setBlue_team_name(teamVO1.getName());
                    vo.setBlue_team_logo(teamVO1.getLogo());
                    iLolDao.updateByTeamId(vo);
                }
            }
            if (StringUtils.isEmpty(vo.getRed_team_name()) || StringUtils.isEmpty(vo.getRed_team_logo())) {
                TeamVO teamVO = new TeamVO();
                teamVO.setId(vo.getRed_team_id());
                TeamVO teamVO1 = iTeamDao.get(teamVO);
                if (teamVO1 != null) {
                    vo.setRed_team_name(teamVO1.getName());
                    vo.setRed_team_logo(teamVO1.getLogo());
                    iLolDao.updateByTeamId(vo);
                }
            }
        }
        return "success";
    }

    private void redTeam(List<LolVO> records, int i) {
        LolVO lolVO1 = records.get(i);
        LolTeamVO lolTeamVO = new LolTeamVO();
        lolTeamVO.setFinish_match_lol_id(lolVO1.getMatch_id());
        List<LolTeamVO> teamVOList = iLolTeamRedDao.list(lolTeamVO);
        if (teamVOList != null) {
            for (int ii = 0; ii < teamVOList.size(); ii++) {
                // 选择英雄
                LolTeamVO lolTeamVO1 = teamVOList.get(ii);
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_lol_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamVO1.setPicks(iLolTeamRedPicksDao.list(lolTeamRoleVO));
                // 禁用英雄
                lolTeamRoleVO.setFinish_match_lol_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamVO1.setBans(iLolTeamRedBansDao.list(lolTeamRoleVO));
                // 队员
                LolTeamPlayersVO lolTeamPlayersVO = new LolTeamPlayersVO();
                lolTeamPlayersVO.setFinish_match_lol_team_id(lolTeamVO1.getFinish_match_lol_id());
                List<LolTeamPlayersVO> lolTeamPlayersVOList = iLolTeamRedPlayersDao.list(lolTeamPlayersVO);
                if (lolTeamPlayersVOList != null) {
                    for (int iii = 0; iii < lolTeamPlayersVOList.size(); iii++) {
                        LolTeamPlayersVO lolTeamPlayersVO1 = lolTeamPlayersVOList.get(iii);
                        // 队员装备
                        LolTeamPlayersEquipmentVO equipmentVO = new LolTeamPlayersEquipmentVO();
                        equipmentVO.setFinish_match_id(lolVO1.getMatch_id());
                        equipmentVO.setFinish_match_team_id(lolTeamVO1.getTeam_id());
                        equipmentVO.setFinish_match_players_id(lolTeamPlayersVO1.getPlayer());
                        lolTeamPlayersVO1.setItems(iLolTeamRedPlayersEquipmentDao.list(equipmentVO));
                    }
                }
                lolTeamVO1.setPlayers(lolTeamPlayersVOList);
            }
        }
        lolVO1.setRed(teamVOList);
    }

    private void blueTeam(List<LolVO> records, int i) {
        LolVO lolVO1 = records.get(i);
        LolTeamVO lolTeamVO = new LolTeamVO();
        lolTeamVO.setFinish_match_lol_id(lolVO1.getMatch_id());
        List<LolTeamVO> teamVOList = iLolTeamBlueDao.list(lolTeamVO);
        if (teamVOList != null) {
            for (int ii = 0; ii < teamVOList.size(); ii++) {
                // 选择英雄
                LolTeamVO lolTeamVO1 = teamVOList.get(ii);
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_lol_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamVO1.setPicks(iLolTeamBluePicksDao.list(lolTeamRoleVO));
                // 禁用英雄
                lolTeamRoleVO.setFinish_match_lol_id(lolTeamVO1.getFinish_match_lol_id());
                lolTeamVO1.setBans(iLolTeamBlueBansDao.list(lolTeamRoleVO));
                // 队员
                LolTeamPlayersVO lolTeamPlayersVO = new LolTeamPlayersVO();
                lolTeamPlayersVO.setFinish_match_lol_team_id(lolTeamVO1.getFinish_match_lol_id());
                List<LolTeamPlayersVO> lolTeamPlayersVOList = iLolTeamBluePlayersDao.list(lolTeamPlayersVO);
                if (lolTeamPlayersVOList != null) {
                    for (int iii = 0; iii < lolTeamPlayersVOList.size(); iii++) {
                        LolTeamPlayersVO lolTeamPlayersVO1 = lolTeamPlayersVOList.get(iii);
                        // 队员装备
                        LolTeamPlayersEquipmentVO equipmentVO = new LolTeamPlayersEquipmentVO();
                        equipmentVO.setFinish_match_id(lolVO1.getMatch_id());
                        equipmentVO.setFinish_match_team_id(lolTeamVO1.getTeam_id());
                        equipmentVO.setFinish_match_players_id(lolTeamPlayersVO1.getPlayer());
                        lolTeamPlayersVO1.setItems(iLolTeamBluePlayersEquipmentDao.list(equipmentVO));
                    }
                }
                lolTeamVO1.setPlayers(lolTeamPlayersVOList);
            }
        }
        lolVO1.setBlue(teamVOList);
    }

    @Override
    public Object insertTeamInfo2(LolVO lolVO) {
        try {
            List<LolVO> list = iLolDao.list(lolVO);
            if (list != null || list.size() > 0) {
                for (LolVO vo: list){
                    // 战队蓝队
                    updateTeamBlue(vo);
                    // 战队红队
                    updateTeamRed(vo);
                }
            }
        } catch (Exception ex) {
            logger.error("insert games error ={}", ex);
            throw new DescribeException("fail", -1);
        }
        return "success";
    }

    private void updateTeamBlue(LolVO vo) throws Exception {
        TreeMap<String, String> treeMap = CommonUtil.treeMap;
        treeMap.put("team_id", vo.getBlue_team_id().toString());
        treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
        String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
        String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/detail?tenant_id=2&team_id={0}&request_time={1}\"", vo.getBlue_team_id().toString(), treeMap.get("request_time"));
        JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
        if (jsonObject != null){
            JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
            if (parseObject != null) {
                String logo = parseObject.getString("logo");
                if (!StringUtils.isEmpty(logo)) {
                    // 更新赛果信息
                    vo.setBlue_team_logo(logo);
                    vo.setBlue_team_name(parseObject.getString("name"));
                    vo.setRed_team_logo(null);
                    vo.setRed_team_name(null);
                    iLolDao.updateByTeamId(vo);
                    // 更新战队信息
                    TeamVO teamVO = new TeamVO();
                    teamVO.setId(new BigInteger(parseObject.getString("id"), 10));
                    teamVO.setLogo(parseObject.getString("logo"));
                    teamVO.setName(parseObject.getString("name"));
                    teamVO.setGame_type(Integer.valueOf(parseObject.getString("game_type")));
                    iTeamDao.update(teamVO);
                    // 更新战队详情信息
                    TeamDetailVO teamDetailVO = new TeamDetailVO();
                    teamDetailVO.setId(new BigInteger(parseObject.getString("id"), 10));
                    teamDetailVO.setLogo(parseObject.getString("logo"));
                    teamDetailVO.setName(parseObject.getString("name"));
                    teamDetailVO.setGame_type(Integer.valueOf(parseObject.getString("game_type")));
                    iTeamDetailDao.update(teamDetailVO);
                }
            }
        }
    }

    private void updateTeamRed(LolVO vo) throws Exception {
        TreeMap<String, String> treeMap = CommonUtil.treeMap;
        treeMap.put("team_id", vo.getRed_team_id().toString());
        treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
        String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
        String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/detail?tenant_id=2&team_id={0}&request_time={1}\"", vo.getRed_team_id().toString(), treeMap.get("request_time"));
        JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
        if (jsonObject != null){
            JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
            if (parseObject != null) {
                String logo = parseObject.getString("logo");
                if (!StringUtils.isEmpty(logo)) {
                    // 更新赛果信息
                    vo.setRed_team_logo(logo);
                    vo.setRed_team_name(parseObject.getString("name"));
                    vo.setBlue_team_logo(null);
                    vo.setBlue_team_name(null);
                    iLolDao.updateByTeamId(vo);
                    // 更新战队信息
                    TeamVO teamVO = new TeamVO();
                    teamVO.setId(new BigInteger(parseObject.getString("id"), 10));
                    teamVO.setLogo(parseObject.getString("logo"));
                    teamVO.setName(parseObject.getString("name"));
                    teamVO.setGame_type(Integer.valueOf(parseObject.getString("game_type")));
                    iTeamDao.update(teamVO);
                    // 更新战队详情信息
                    TeamDetailVO teamDetailVO = new TeamDetailVO();
                    teamDetailVO.setId(new BigInteger(parseObject.getString("id"), 10));
                    teamDetailVO.setLogo(parseObject.getString("logo"));
                    teamDetailVO.setName(parseObject.getString("name"));
                    teamDetailVO.setGame_type(Integer.valueOf(parseObject.getString("game_type")));
                    iTeamDetailDao.update(teamDetailVO);
                }
            }
        }
    }

}
