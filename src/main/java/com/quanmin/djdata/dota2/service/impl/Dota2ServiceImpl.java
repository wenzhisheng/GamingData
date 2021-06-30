package com.quanmin.djdata.dota2.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.dota2.dao.*;
import com.quanmin.djdata.dota2.service.IDota2Service;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameEquipmentsDao;
import com.quanmin.djdata.game.dao.IGameRoleDao;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.csgo.CsgoVO;
import com.quanmin.djdata.pojo.dota2.Dota2PlayersVO;
import com.quanmin.djdata.pojo.dota2.Dota2VO;
import com.quanmin.djdata.pojo.finish.FinishSeriesVO;
import com.quanmin.djdata.pojo.game.GameEquipmentVO;
import com.quanmin.djdata.pojo.game.GameRoleVO;
import com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO;
import com.quanmin.djdata.pojo.lol.LolTeamRoleVO;
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:30
 * @ClassName: com.quanmin.djdata.dota2.service.impl.Dota2ServiceImpl
 */
@Service
public class Dota2ServiceImpl implements IDota2Service {

    private static final Logger logger = LoggerFactory.getLogger(Dota2ServiceImpl.class);

    @Autowired
    private IDota2Dao iDota2Dao;
    @Autowired
    private ITeamDao iTeamDao;
    @Autowired
    private ITeamDetailDao iTeamDetailDao;
    @Autowired
    private IGameRoleDao iGameRoleDao;
    @Autowired
    private IDota2PlayersDao iDota2PlayersDao;
    @Autowired
    private ISeriesDao iSeriesDao;
    @Autowired
    private IFinishSeriesDota2Dao iFinishSeriesDota2Dao;
    @Autowired
    private IDota2DireBansDao iDota2DireBansDao;
    @Autowired
    private IDota2DirePicksDao iDota2DirePicksDao;
    @Autowired
    private IDota2RadiantBansDao iDota2RadiantBansDao;
    @Autowired
    private IDota2RadiantPicksDao iDota2RadiantPicksDao;
    @Autowired
    private IGameEquipmentsDao iGameEquipmentsDao;
    @Autowired
    private IDota2PlayersEquipmentDao iDota2PlayersEquipmentDao;

    /**
     * @author: ate
     * @description: 保存dota2赛果
     * @date: 2019/11/14 18:04
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(Dota2VO dota2VO) {
        if (dota2VO == null){
            dota2VO = new Dota2VO();
        }
        try {
            SeriesVO seriesVO = new SeriesVO();
            seriesVO.setGame_id(new BigInteger("651841432110167838"));
            // 传值则单个抓取
            List<SeriesVO> list = new ArrayList<SeriesVO>();
            if (StringUtils.isEmpty(dota2VO.getSeries_id())){
                list = iSeriesDao.list(seriesVO);
            } else {
                seriesVO.setId(dota2VO.getSeries_id());
                list.add(seriesVO);
            }
            // 否则全部抓取
            for (SeriesVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("series_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/match/dota2/finish?tenant_id=2&series_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null) {
                    JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                    // 返回数据为空则不处理
                    if (result != null){
                        JSONArray seriesArray = JSONArray.parseArray(result.getString("series"));
                        // 查询出数据，遍历保存json数组数据到数据库
                        for (int i=0; i<seriesArray.size(); i++){
                            JSONObject series = (JSONObject) seriesArray.get(i);
                            FinishSeriesVO finishSeriesVO = new FinishSeriesVO();
                            finishSeriesVO.setSeries_id(vo.getId());
                            finishSeriesVO.setScore(Integer.valueOf(series.getString("score")));
                            finishSeriesVO.setTeam_id(new BigInteger(series.getString("team_id"), 10));
                            if (iFinishSeriesDota2Dao.get(finishSeriesVO) == null) {
                                iFinishSeriesDota2Dao.insert(finishSeriesVO);
                            }
                        }
                    }
                    JSONArray matchArray = JSONArray.parseArray(result.getString("match"));
                    // 返回数据为空则不处理
                    if (matchArray != null){
                        // 查询出数据，遍历保存json数组数据到数据库
                        for (int ii=0; ii<matchArray.size(); ii++){
                            JSONObject match = (JSONObject) matchArray.get(ii);
                            dota2VO.setSeries_id(new BigInteger(match.getString("series_id"), 10));
                            dota2VO.setMatch_id(new BigInteger(match.getString("match_id"), 10));
                            dota2VO.setGame_id(new BigInteger(match.getString("game_id"), 10));
                            dota2VO.setCourse_name(match.getString("course_name"));
                            dota2VO.setStart_time(match.getString("start_time"));
                            dota2VO.setFinished(match.getString("finished").equals("true") ? true : false);
                            dota2VO.setValid(match.getString("valid").equals("true") ? true : false);
                            dota2VO.setGame_time(new BigInteger(match.getString("game_time"), 10));
                            dota2VO.setGame_mode(new BigInteger(match.getString("game_mode"), 10));
                            dota2VO.setFirst_pick(match.getString("first_pick"));
                            dota2VO.setFirst_blood_time(Integer.valueOf(match.getString("first_blood_time")));
                            dota2VO.setWin_side(match.getString("win_side"));
                            dota2VO.setFirst_blood(match.getString("first_blood"));
                            dota2VO.setFive_kills(match.getString("five_kills"));
                            dota2VO.setTen_kills(match.getString("ten_kills"));
                            dota2VO.setTen_kills_score(Integer.valueOf(match.getString("ten_kills_score")));
                            dota2VO.setFirst_tower(match.getString("first_tower"));
                            dota2VO.setRadiant_gold_lead(Integer.valueOf(match.getString("radiant_gold_lead")));
                            /*dota2VO.setRadiant_picks(match.getString("radiant_picks"));
                            dota2VO.setRadiant_bans(match.getString("radiant_bans"));
                            dota2VO.setDire_picks(match.getString("dire_picks"));
                            dota2VO.setDire_bans(match.getString("dire_bans"));*/
                            // 天辉选择英雄
                            JSONArray picks = JSONArray.parseArray(match.getString("radiant_picks"));
                            radiantPicks(dota2VO, picks);
                            // 天辉禁用英雄
                            JSONArray bans = JSONArray.parseArray(match.getString("radiant_bans"));
                            radiantBans(dota2VO, bans);
                            // 夜魇选择英雄
                            picks = JSONArray.parseArray(match.getString("radiant_picks"));
                            direPicks(dota2VO, picks);
                            // 夜魇禁用英雄
                            bans = JSONArray.parseArray(match.getString("radiant_bans"));
                            direBans(dota2VO, bans);
                            // 玩家
                            JSONArray players = JSONArray.parseArray(match.getString("players"));
                            if (players.size() != 0){
                                for (int w=0; w<players.size(); w++){
                                    JSONObject playersItem = (JSONObject) players.get(w);
                                    Dota2PlayersVO dota2PlayersVO = new Dota2PlayersVO();
                                    dota2PlayersVO.setFinish_match_dota2_id(dota2VO.getMatch_id());
                                    dota2PlayersVO.setAccount(new BigInteger(playersItem.getString("account"), 10));
                                    dota2PlayersVO.setTeam(playersItem.getString("team"));
                                    dota2PlayersVO.setHero(new BigInteger(playersItem.getString("hero"), 10));
                                    dota2PlayersVO.setKills(Integer.valueOf(playersItem.getString("kills")));
                                    dota2PlayersVO.setDeath(Integer.valueOf(playersItem.getString("death")));
                                    dota2PlayersVO.setAssists(Integer.valueOf(playersItem.getString("assists")));
                                    dota2PlayersVO.setLast_hits(Integer.valueOf(playersItem.getString("last_hits")));
                                    dota2PlayersVO.setDenies(Integer.valueOf(playersItem.getString("denies")));
                                    dota2PlayersVO.setGold(Integer.valueOf(playersItem.getString("gold")));
                                    dota2PlayersVO.setLevel(Integer.valueOf(playersItem.getString("level")));
                                    dota2PlayersVO.setGold_per_min(Integer.valueOf(playersItem.getString("gold_per_min")));
                                    dota2PlayersVO.setXp_per_min(Integer.valueOf(playersItem.getString("xp_per_min")));
                                    dota2PlayersVO.setUltimate_state(Integer.valueOf(playersItem.getString("ultimate_state")));
                                    /*dota2PlayersVO.setItem_0(new BigInteger(playersItem.getString("item_0"), 10));
                                    dota2PlayersVO.setItem_1(new BigInteger(playersItem.getString("item_1"), 10));
                                    dota2PlayersVO.setItem_2(new BigInteger(playersItem.getString("item_2"), 10));
                                    dota2PlayersVO.setItem_3(new BigInteger(playersItem.getString("item_3"), 10));
                                    dota2PlayersVO.setItem_4(new BigInteger(playersItem.getString("item_4"), 10));
                                    dota2PlayersVO.setItem_5(new BigInteger(playersItem.getString("item_5"), 10));*/
                                    // 玩家装备
                                    String item_0 = playersItem.getString("item_0");
                                    playersEquipment(dota2PlayersVO, item_0, 0);
                                    String item_1 = playersItem.getString("item_1");
                                    playersEquipment(dota2PlayersVO, item_1, 1);
                                    String item_2 = playersItem.getString("item_2");
                                    playersEquipment(dota2PlayersVO, item_2, 2);
                                    String item_3 = playersItem.getString("item_3");
                                    playersEquipment(dota2PlayersVO, item_3, 3);
                                    String item_4 = playersItem.getString("item_4");
                                    playersEquipment(dota2PlayersVO, item_4, 4);
                                    String item_5 = playersItem.getString("item_5");
                                    playersEquipment(dota2PlayersVO, item_5, 5);
                                    dota2PlayersVO.setHero_damage(Integer.valueOf(playersItem.getString("hero_damage")));
                                    dota2PlayersVO.setTower_damage(Integer.valueOf(playersItem.getString("tower_damage")));
                                    dota2PlayersVO.setHero_healing(Integer.valueOf(playersItem.getString("hero_healing")));
                                    dota2PlayersVO.setRespawn_timer(Integer.valueOf(playersItem.getString("respawn_timer")));
                                    dota2PlayersVO.setPosition_x(new BigDecimal(playersItem.getString("position_x")));
                                    dota2PlayersVO.setPosition_y(new BigDecimal(playersItem.getString("position_y")));
                                    dota2PlayersVO.setNet_worth(Integer.valueOf(playersItem.getString("net_worth")));
                                    dota2PlayersVO.setAbility_upgrades(playersItem.getString("ability_upgrades"));
                                    if (iDota2PlayersDao.get(dota2PlayersVO) == null){
                                        iDota2PlayersDao.insert(dota2PlayersVO);
                                    }
                                }
                            }
                            dota2VO.setStream_delay_s(new BigInteger(match.getString("stream_delay_s"), 10));
                            dota2VO.setRoshan_respawn_timer(new BigInteger(match.getString("roshan_respawn_timer"), 10));
                            // radiant
                            JSONObject radiant = JSONObject.parseObject(match.getString("radiant"));
                            dota2VO.setRadiant_score(new BigInteger(radiant.getString("score"), 10));
                            dota2VO.setRadiant_invalid_score(new BigInteger(radiant.getString("invalid_score"), 10));
                            dota2VO.setRadiant_xp(new BigInteger(radiant.getString("xp"), 10));
                            dota2VO.setRadiant_gold(new BigInteger(radiant.getString("gold"), 10));
                            /*JSONArray parseArray1 = JSONArray.parseArray(radiant.getString("towers"));
                            if (parseArray1.size() != 0){
                                StringBuilder sb = new StringBuilder();
                                for (int a=0; a<parseArray1.size(); a++){
                                    if (a < parseArray1.size()-1) {
                                        sb.append(parseArray1.get(a)+ ",");
                                    } else {
                                        sb.append(parseArray1.get(a));
                                    }
                                }
                                dota2VO.setRadiant_towers(sb.toString());
                            }*/
                            dota2VO.setRadiant_towers(radiant.getString("towers"));
                            dota2VO.setRadiant_barracks(radiant.getString("barracks"));
                            dota2VO.setRadiant_last_hits(Integer.valueOf(radiant.getString("last_hits")));
                            dota2VO.setRadiant_damage(Integer.valueOf(radiant.getString("damage")));
                            // dire
                            JSONObject dire = JSONObject.parseObject(match.getString("dire"));
                            dota2VO.setDire_score(new BigInteger(dire.getString("score"), 10));
                            dota2VO.setDire_invalid_score(new BigInteger(dire.getString("invalid_score"), 10));
                            dota2VO.setDire_xp(new BigInteger(dire.getString("xp"), 10));
                            dota2VO.setDire_gold(new BigInteger(dire.getString("gold"), 10));
                            dota2VO.setDire_towers(dire.getString("towers"));
                            dota2VO.setDire_barracks(dire.getString("barracks"));
                            dota2VO.setDire_last_hits(Integer.valueOf(dire.getString("last_hits")));
                            dota2VO.setDire_damage(Integer.valueOf(dire.getString("damage")));
                            // radiant_team
                            JSONObject radiantTeam = JSONObject.parseObject(match.getString("radiant_team"));
                            dota2VO.setRadiant_team_tag(radiantTeam.getString("tag"));
                            /*dota2VO.setRadiant_team_logo(radiantTeam.getString("logo"));
                            dota2VO.setRadiant_team_name(radiantTeam.getString("name"));*/
                            dota2VO.setRadiant_team_id(new BigInteger(radiantTeam.getString("id"), 10));
                            BigInteger radiant_team_id = dota2VO.getRadiant_team_id();
                            if (radiant_team_id != null){
                                TeamVO teamVO = new TeamVO();
                                teamVO.setId(radiant_team_id);
                                TeamVO teamVO1 = iTeamDao.get(teamVO);
                                if (teamVO1 != null){
                                    dota2VO.setRadiant_team_logo(teamVO1.getLogo());
                                    dota2VO.setRadiant_team_name(teamVO1.getName());
                                }
                            }
                            dota2VO.setRadiant_team_score(new BigInteger(radiantTeam.getString("score"), 10));
                            // dire_team
                            JSONObject direTeam = JSONObject.parseObject(match.getString("dire_team"));
                            dota2VO.setDire_team_tag(direTeam.getString("tag"));
                            /*dota2VO.setDire_team_logo(direTeam.getString("logo"));
                            dota2VO.setDire_team_name(direTeam.getString("name"));*/
                            dota2VO.setDire_team_id(new BigInteger(direTeam.getString("id"), 10));
                            BigInteger dire_team_id = dota2VO.getDire_team_id();
                            if (dire_team_id != null){
                                TeamVO teamVO = new TeamVO();
                                teamVO.setId(dire_team_id);
                                TeamVO teamVO1 = iTeamDao.get(teamVO);
                                if (teamVO1 != null){
                                    dota2VO.setDire_team_logo(teamVO1.getLogo());
                                    dota2VO.setDire_team_name(teamVO1.getName());
                                }
                            }
                            dota2VO.setDire_team_score(new BigInteger(direTeam.getString("score"), 10));
                            if (iDota2Dao.get(dota2VO) == null) {
                                iDota2Dao.insert(dota2VO);
                            }
                        }
                    }
                }
            }
        }catch (Exception ex) {
            logger.error("insert games error ={}", ex);
            throw new DescribeException("fail", -1);
        }
        return "success";
    }

    private void playersEquipment(Dota2PlayersVO dota2PlayersVO, String item_0, int sort) {
        if (item_0 != null) {
            LolTeamPlayersEquipmentVO equipmentVO = new LolTeamPlayersEquipmentVO();
            equipmentVO.setFinish_match_players_account(dota2PlayersVO.getAccount());
            equipmentVO.setFinish_match_players_id(dota2PlayersVO.getFinish_match_dota2_id());
            equipmentVO.setEquipment_sort(sort);
            equipmentVO.setEquipment_id(new BigInteger(item_0, 10));
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
            if (iDota2PlayersEquipmentDao.isExist(equipmentVO) == null) {
                iDota2PlayersEquipmentDao.insert(equipmentVO);
            }
        }
    }

    private void radiantBans(Dota2VO dota2VO, JSONArray bans) {
        if (bans != null){
            for (int iiii=0; iiii<bans.size(); iiii++){
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_id(dota2VO.getMatch_id());
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
                if (iDota2RadiantBansDao.get(lolTeamRoleVO) == null) {
                    iDota2RadiantBansDao.insert(lolTeamRoleVO);
                }
            }
        }
    }

    private void direBans(Dota2VO dota2VO, JSONArray bans) {
        if (bans != null){
            for (int iiii=0; iiii<bans.size(); iiii++){
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_id(dota2VO.getMatch_id());
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
                if (iDota2DireBansDao.get(lolTeamRoleVO) == null) {
                    iDota2DireBansDao.insert(lolTeamRoleVO);
                }
            }
        }
    }

    private void radiantPicks(Dota2VO dota2VO, JSONArray picks) {
        if (picks != null){
            for (int iiii=0; iiii<picks.size(); iiii++){
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_id(dota2VO.getMatch_id());
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
                if (iDota2RadiantPicksDao.get(lolTeamRoleVO) == null) {
                    iDota2RadiantPicksDao.insert(lolTeamRoleVO);
                }
            }
        }
    }

    private void direPicks(Dota2VO dota2VO, JSONArray picks) {
        if (picks != null){
            for (int iiii=0; iiii<picks.size(); iiii++){
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_id(dota2VO.getMatch_id());
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
                if (iDota2DirePicksDao.get(lolTeamRoleVO) == null) {
                    iDota2DirePicksDao.insert(lolTeamRoleVO);
                }
            }
        }
    }

    /**
     * @author: ate
     * @description: 分页dota2VO赛果
     * @date: 2019/11/19 15:53
     * @param: [dota2VO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(Dota2VO dota2VO, PageVO pageVO) {
        if (StringUtils.isEmpty(dota2VO.getSeries_id())){
            throw new DescribeException("系列赛ID不能为空", -1);
        }
        Page<Dota2VO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        IPage<Dota2VO> ipage = iDota2Dao.page(page, dota2VO);
        List<Dota2VO> records = ipage.getRecords();
        if (records.size() > 0){
            for (int i=0; i<records.size(); i++) {
                Dota2VO dota2VO1 = records.get(i);
                // 天辉选择英雄
                LolTeamRoleVO lolTeamRoleVO = new LolTeamRoleVO();
                lolTeamRoleVO.setFinish_match_id(dota2VO1.getMatch_id());
                dota2VO1.setRadiant_picks(iDota2RadiantPicksDao.list(lolTeamRoleVO));
                // 天辉禁用英雄
                dota2VO1.setRadiant_bans(iDota2RadiantBansDao.list(lolTeamRoleVO));
                // 夜魇选择英雄
                dota2VO1.setDire_picks(iDota2DirePicksDao.list(lolTeamRoleVO));
                // 夜魇禁用英雄
                dota2VO1.setDire_bans(iDota2DireBansDao.list(lolTeamRoleVO));
                // 玩家信息
                Dota2PlayersVO dota2PlayersVO = new Dota2PlayersVO();
                dota2PlayersVO.setFinish_match_dota2_id(dota2VO1.getMatch_id());
                List<Dota2PlayersVO> playersVOList = iDota2PlayersDao.list(dota2PlayersVO);
                if (playersVOList != null) {
                    // 玩家装备
                    for (int ii=0; ii<playersVOList.size(); ii++) {
                        Dota2PlayersVO dota2PlayersVO1 = playersVOList.get(ii);
                        LolTeamPlayersEquipmentVO equipmentVO = new LolTeamPlayersEquipmentVO();
                        equipmentVO.setFinish_match_players_account(dota2PlayersVO1.getAccount());
                        equipmentVO.setFinish_match_players_id(dota2VO1.getMatch_id());
                        dota2PlayersVO1.setItems(iDota2PlayersEquipmentDao.list(equipmentVO));
                    }
                }
                dota2VO1.setPlayers(playersVOList);
            }
        }
        return ipage.setRecords(records);
    }

    /**
     * @author: ate
     * @description: 保存dota2VO赛果战队信息
     * @date: 2019/11/30 12:48
     * @param: [dota2VO]
     * @return: java.lang.Object
     */
    @Override
    public Object insertTeamInfo(Dota2VO dota2VO) {
        List<Dota2VO> list = iDota2Dao.list(dota2VO);
        for (Dota2VO vo: list){
            if (StringUtils.isEmpty(vo.getRadiant_team_name()) || StringUtils.isEmpty(vo.getRadiant_team_logo())) {
                TeamVO teamVO = new TeamVO();
                teamVO.setId(vo.getRadiant_team_id());
                TeamVO teamVO1 = iTeamDao.get(teamVO);
                if (teamVO1 != null) {
                    vo.setRadiant_team_name(teamVO1.getName());
                    vo.setRadiant_team_logo(teamVO1.getLogo());
                    iDota2Dao.updateByTeamId(vo);
                }
            }
            if (StringUtils.isEmpty(vo.getDire_team_name()) || StringUtils.isEmpty(vo.getDire_team_logo())) {
                TeamVO teamVO = new TeamVO();
                teamVO.setId(vo.getDire_team_id());
                TeamVO teamVO1 = iTeamDao.get(teamVO);
                if (teamVO1 != null) {
                    vo.setDire_team_name(teamVO1.getName());
                    vo.setDire_team_logo(teamVO1.getLogo());
                    iDota2Dao.updateByTeamId(vo);
                }
            }
        }
        return "success";
    }

    /**
     * @author: ate
     * @description: 保存dota2赛果依据第三方
     * @date: 2019/11/30 14:34
     * @param: [dota2VO]
     * @return: java.lang.Object
     */
    @Override
    public Object insertTeamInfo2(Dota2VO dota2VO) {
        try {
            List<Dota2VO> list = iDota2Dao.list(dota2VO);
            if (list != null || list.size() > 0) {
                for (Dota2VO vo: list){
                    // 战队天辉
                    updateTeamRadiant(vo);
                    // 战队夜魇
                    updateTeamDire(vo);
                }
            }
        } catch (Exception ex) {
            logger.error("insert games error ={}", ex);
            throw new DescribeException("fail", -1);
        }
        return "success";
    }

    private void updateTeamRadiant(Dota2VO vo) throws Exception {
        TreeMap<String, String> treeMap = CommonUtil.treeMap;
        treeMap.put("team_id", vo.getRadiant_team_id().toString());
        treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
        String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
        String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/detail?tenant_id=2&team_id={0}&request_time={1}\"", vo.getRadiant_team_id().toString(), treeMap.get("request_time"));
        JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
        if (jsonObject != null){
            JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
            if (parseObject != null) {
                String logo = parseObject.getString("logo");
                if (!StringUtils.isEmpty(logo)) {
                    // 更新赛果信息
                    vo.setRadiant_team_logo(logo);
                    vo.setRadiant_team_name(parseObject.getString("name"));
                    vo.setDire_team_logo(null);
                    vo.setDire_team_name(null);
                    iDota2Dao.updateByTeamId(vo);
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

    private void updateTeamDire(Dota2VO vo) throws Exception {
        TreeMap<String, String> treeMap = CommonUtil.treeMap;
        treeMap.put("team_id", vo.getDire_team_id().toString());
        treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
        String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
        String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/detail?tenant_id=2&team_id={0}&request_time={1}\"", vo.getDire_team_id().toString(), treeMap.get("request_time"));
        JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
        if (jsonObject != null){
            JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
            if (parseObject != null) {
                String logo = parseObject.getString("logo");
                if (!StringUtils.isEmpty(logo)) {
                    // 更新赛果信息
                    vo.setDire_team_logo(logo);
                    vo.setDire_team_name(parseObject.getString("name"));
                    vo.setRadiant_team_logo(null);
                    vo.setRadiant_team_name(null);
                    iDota2Dao.updateByTeamId(vo);
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
