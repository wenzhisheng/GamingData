package com.quanmin.djdata.csgo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.csgo.dao.ICsgoDao;
import com.quanmin.djdata.csgo.dao.ICsgoLeftPlayersDao;
import com.quanmin.djdata.csgo.dao.ICsgoRightPlayersDao;
import com.quanmin.djdata.csgo.service.ICsgoService;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.lol.dao.IFinishSeriesLolDao;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.csgo.CsgoPlayersVO;
import com.quanmin.djdata.pojo.csgo.CsgoVO;
import com.quanmin.djdata.pojo.finish.FinishSeriesVO;
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
 * @CreateDate: 2019-11-08 15:50
 * @ClassName: com.quanmin.djdata.csgo.service.impl.CsgoServiceImpl
 */
@Service
public class CsgoServiceImpl implements ICsgoService {

    private static final Logger logger = LoggerFactory.getLogger(CsgoServiceImpl.class);

    @Autowired
    private ICsgoDao iCsgoDao;
    @Autowired
    private ISeriesDao iSeriesDao;
    @Autowired
    private ICsgoLeftPlayersDao iCsgoLeftPlayersDao;
    @Autowired
    private ICsgoRightPlayersDao iCsgoRightPlayersDao;
    @Autowired
    private IFinishSeriesLolDao iFinishSeriesDao;
    @Autowired
    private ITeamDao iTeamDao;
    @Autowired
    private ITeamDetailDao iTeamDetailDao;

    /**
     * @author: ate
     * @description: 保存csgo赛果
     * @date: 2019/11/15 15:25
     * @param: [lolVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(CsgoVO csgoVO) {
        if (csgoVO == null){
            csgoVO = new CsgoVO();
        }
        try {
            SeriesVO seriesVO = new SeriesVO();
            seriesVO.setGame_id(new BigInteger("651841432110167848"));
            // 传值则单个抓取
            List<SeriesVO> list = new ArrayList<SeriesVO>();
            if (StringUtils.isEmpty(csgoVO.getSeries_id())){
                list = iSeriesDao.list(seriesVO);
            } else {
                seriesVO.setId(csgoVO.getSeries_id());
                list.add(seriesVO);
            }
            // 否则全部抓取
            for (SeriesVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("series_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/match/csgo/finish?tenant_id=2&series_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null){
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
                            if (iFinishSeriesDao.get(finishSeriesVO) == null) {
                                iFinishSeriesDao.insert(finishSeriesVO);
                            }
                        }
                        JSONArray matchArray = JSONArray.parseArray(result.getString("match"));
                        // 返回数据为空则不处理
                        if (matchArray != null){
                            // 查询出数据，遍历保存json数组数据到数据库
                            for (int ii=0; ii<matchArray.size(); ii++){
                                JSONObject match = (JSONObject) matchArray.get(ii);
                                csgoVO.setSeries_id(new BigInteger(match.getString("series_id"), 10));
                                csgoVO.setMatch_id(new BigInteger(match.getString("match_id"), 10));
                                csgoVO.setGame_id(new BigInteger(match.getString("game_id"), 10));
                                csgoVO.setCourse_name(match.getString("course_name"));
                                csgoVO.setMatch_index(Integer.valueOf(match.getString("match_index")));
                                csgoVO.setStart_time(match.getString("start_time"));
                                csgoVO.setFinished(match.getString("finished").equals("true") ? true : false);
                                csgoVO.setMap(match.getString("map"));
                                csgoVO.setLeft_score(Integer.valueOf(match.getString("left_score")));
                                csgoVO.setRight_score(Integer.valueOf(match.getString("right_score")));
                                csgoVO.setFlag_r1(match.getString("flag_r1"));
                                csgoVO.setFlag_w5(match.getString("flag_w5"));
                                csgoVO.setFlag_r16(match.getString("flag_r16"));
                                // round_history
                                //csgoVO.setLeft_round_history(match.getString("left_round_history"));
                                //csgoVO.setRight_round_history(match.getString("right_round_history"));
                                JSONArray leftRoundHistory = JSONArray.parseArray(match.getString("left_round_history"));
                                if (leftRoundHistory != null){
                                    StringBuffer leftBuffer = new StringBuffer();
                                    for (int k=0; k<leftRoundHistory.size(); k++){
                                        if (k < leftRoundHistory.size()-1) {
                                            leftBuffer.append(leftRoundHistory.get(k)+",");
                                        } else {
                                            leftBuffer.append(leftRoundHistory.get(k).toString());
                                        }
                                    }
                                    csgoVO.setLeft_round_history(leftBuffer.toString());
                                }
                                JSONArray rightRoundHistory = JSONArray.parseArray(match.getString("right_round_history"));
                                if (leftRoundHistory != null){
                                    StringBuffer rightBuffer = new StringBuffer();
                                    for (int k=0; k<rightRoundHistory.size(); k++){
                                        if (k < rightRoundHistory.size()-1) {
                                            rightBuffer.append(rightRoundHistory.get(k)+",");
                                        } else {
                                            rightBuffer.append(rightRoundHistory.get(k).toString());
                                        }
                                    }
                                    csgoVO.setRight_round_history(rightBuffer.toString());
                                }
                                // overtime
                                JSONObject overtime = JSONObject.parseObject(match.getString("overtime"));
                                csgoVO.setOvertime_left_score(Integer.valueOf(overtime.getString("left_score")));
                                csgoVO.setOvertime_right_score(Integer.valueOf(overtime.getString("right_score")));
                                // left_team
                                JSONObject leftTeam = JSONObject.parseObject(match.getString("left_team"));
                                csgoVO.setLeft_team_name(leftTeam.getString("name"));
                                csgoVO.setLeft_team_tag(leftTeam.getString("tag"));
                                csgoVO.setLeft_team_logo(leftTeam.getString("logo"));
                                csgoVO.setLeft_team_id(new BigInteger(leftTeam.getString("id"), 10));
                                csgoVO.setLeft_team_score(new BigInteger(leftTeam.getString("score"), 10));
                                // right_team
                                JSONObject rightTeam = JSONObject.parseObject(match.getString("right_team"));
                                csgoVO.setRight_team_name(rightTeam.getString("name"));
                                csgoVO.setRight_team_tag(rightTeam.getString("tag"));
                                csgoVO.setRight_team_logo(rightTeam.getString("logo"));
                                csgoVO.setRight_team_id(new BigInteger(rightTeam.getString("id"), 10));
                                csgoVO.setRight_team_score(new BigInteger(rightTeam.getString("score"), 10));
                                // first_half
                                JSONObject firstHalf = JSONObject.parseObject(match.getString("first_half"));
                                csgoVO.setFirst_half_left_score(new BigInteger(firstHalf.getString("left_score"), 10));
                                csgoVO.setFirst_half_left_side(firstHalf.getString("left_side"));
                                csgoVO.setFirst_half_right_score(new BigInteger(firstHalf.getString("right_score"), 10));
                                csgoVO.setFirst_half_right_side(firstHalf.getString("right_side"));
                                // second_half
                                JSONObject secondHalf = JSONObject.parseObject(match.getString("second_half"));
                                csgoVO.setSecond_half_left_score(new BigInteger(secondHalf.getString("left_score"), 10));
                                csgoVO.setSecond_half_left_side(secondHalf.getString("left_side"));
                                csgoVO.setSecond_half_right_score(new BigInteger(secondHalf.getString("right_score"), 10));
                                csgoVO.setSecond_half_right_side(secondHalf.getString("right_side"));
                                // left_players
                                JSONArray leftPlayers = JSONArray.parseArray(match.getString("left_players"));
                                if (leftPlayers != null){
                                    for (int m=0; m<leftPlayers.size();m++){
                                        JSONObject leftPlayersItem = (JSONObject) leftPlayers.get(m);
                                        CsgoPlayersVO csgoPlayersVO = new CsgoPlayersVO();
                                        csgoPlayersVO.setFinish_match_csgo_id(csgoVO.getMatch_id());
                                        csgoPlayersVO.setId(new BigInteger(leftPlayersItem.getString("id"), 10));
                                        csgoPlayersVO.setName(leftPlayersItem.getString("name"));
                                        csgoPlayersVO.setKills(leftPlayersItem.getString("kills"));
                                        csgoPlayersVO.setHs(leftPlayersItem.getString("hs"));
                                        csgoPlayersVO.setAssists(leftPlayersItem.getString("assists"));
                                        csgoPlayersVO.setDeaths(leftPlayersItem.getString("deaths"));
                                        csgoPlayersVO.setKdratio(leftPlayersItem.getString("kdratio"));
                                        csgoPlayersVO.setKddiff(leftPlayersItem.getString("kddiff"));
                                        csgoPlayersVO.setAdr(leftPlayersItem.getString("adr"));
                                        csgoPlayersVO.setFkdiff(leftPlayersItem.getString("fkdiff"));
                                        csgoPlayersVO.setRating(leftPlayersItem.getString("rating"));
                                        if (iCsgoLeftPlayersDao.get(csgoPlayersVO) == null){
                                            iCsgoLeftPlayersDao.insert(csgoPlayersVO);
                                        }
                                    }
                                }
                                // right_players
                                JSONArray rightPlayers = JSONArray.parseArray(match.getString("right_players"));
                                if (rightPlayers != null){
                                    for (int m=0; m<rightPlayers.size();m++){
                                        JSONObject rightPlayersItem = (JSONObject) rightPlayers.get(m);
                                        CsgoPlayersVO csgoPlayersVO = new CsgoPlayersVO();
                                        csgoPlayersVO.setFinish_match_csgo_id(csgoVO.getMatch_id());
                                        csgoPlayersVO.setId(new BigInteger(rightPlayersItem.getString("id"), 10));
                                        csgoPlayersVO.setName(rightPlayersItem.getString("name"));
                                        csgoPlayersVO.setKills(rightPlayersItem.getString("kills"));
                                        csgoPlayersVO.setHs(rightPlayersItem.getString("hs"));
                                        csgoPlayersVO.setAssists(rightPlayersItem.getString("assists"));
                                        csgoPlayersVO.setDeaths(rightPlayersItem.getString("deaths"));
                                        csgoPlayersVO.setKdratio(rightPlayersItem.getString("kdratio"));
                                        csgoPlayersVO.setKddiff(rightPlayersItem.getString("kddiff"));
                                        csgoPlayersVO.setAdr(rightPlayersItem.getString("adr"));
                                        csgoPlayersVO.setFkdiff(rightPlayersItem.getString("fkdiff"));
                                        csgoPlayersVO.setRating(rightPlayersItem.getString("rating"));
                                        if (iCsgoRightPlayersDao.get(csgoPlayersVO) == null){
                                            iCsgoRightPlayersDao.insert(csgoPlayersVO);
                                        }
                                    }
                                }
                                // 保存csgo赛果数据
                                if (iCsgoDao.get(csgoVO) == null) {
                                    iCsgoDao.insert(csgoVO);
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
     * @description: 分页
     * @date: 2019/11/29 10:45
     * @param: [csgoVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(CsgoVO csgoVO, PageVO pageVO) {
        if (StringUtils.isEmpty(csgoVO.getSeries_id())){
            throw new DescribeException("系列赛ID不能为空", -1);
        }
        Page<CsgoVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        IPage<CsgoVO> iPage = iCsgoDao.page(page, csgoVO);
        List<CsgoVO> records = iPage.getRecords();
        if (records.size() > 0){
            for (int i=0; i<records.size(); i++) {
                CsgoVO csgoVO1 = records.get(i);
                CsgoPlayersVO csgoPlayersVO = new CsgoPlayersVO();
                csgoPlayersVO.setFinish_match_csgo_id(csgoVO1.getMatch_id());
                // 左队玩家
                csgoVO1.setLeft_players(iCsgoLeftPlayersDao.list(csgoPlayersVO));
                // 右队玩家
                csgoVO1.setRight_players(iCsgoRightPlayersDao.list(csgoPlayersVO));
            }
        }
        return iPage.setRecords(records);
    }

    /**
     * @author: ate
     * @description: 保存csgo赛果战队信息
     * @date: 2019/11/30 12:39
     * @param: [csgoVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insertTeamInfo(CsgoVO csgoVO) {
        List<CsgoVO> list = iCsgoDao.list(csgoVO);
        for (CsgoVO vo: list){
            if (StringUtils.isEmpty(vo.getLeft_team_name()) || StringUtils.isEmpty(vo.getLeft_team_logo())) {
                TeamVO teamVO = new TeamVO();
                teamVO.setId(vo.getLeft_team_id());
                TeamVO teamVO1 = iTeamDao.get(teamVO);
                if (teamVO1 != null) {
                    vo.setLeft_team_name(teamVO1.getName());
                    vo.setLeft_team_logo(teamVO1.getLogo());
                    iCsgoDao.updateByTeamId(vo);
                }
            }
            if (StringUtils.isEmpty(vo.getRight_team_name()) || StringUtils.isEmpty(vo.getRight_team_logo())) {
                TeamVO teamVO = new TeamVO();
                teamVO.setId(vo.getRight_team_id());
                TeamVO teamVO1 = iTeamDao.get(teamVO);
                if (teamVO1 != null) {
                    vo.setRight_team_name(teamVO1.getName());
                    vo.setRight_team_logo(teamVO1.getLogo());
                    iCsgoDao.updateByTeamId(vo);
                }
            }
        }
        return "success";
    }

    /**
     * @author: ate
     * @description: 保存csgo赛果战队信息依据第三方
     * @date: 2019/11/30 14:27
     * @param: [csgoVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insertTeamInfo2(CsgoVO csgoVO) {
        try {
            List<CsgoVO> list = iCsgoDao.list(csgoVO);
            if (list != null || list.size() > 0) {
                for (CsgoVO vo: list){
                    // 战队左队
                    updateTeamLeft(vo);
                    // 战队右队
                    updateTeamRight(vo);
                }
            }
        } catch (Exception ex) {
            logger.error("insert games error ={}", ex);
            throw new DescribeException("fail", -1);
        }
        return "success";
    }

    private void updateTeamLeft(CsgoVO vo) throws Exception {
        TreeMap<String, String> treeMap = CommonUtil.treeMap;
        treeMap.put("team_id", vo.getLeft_team_id().toString());
        treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
        String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
        String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/detail?tenant_id=2&team_id={0}&request_time={1}\"", vo.getLeft_team_id().toString(), treeMap.get("request_time"));
        JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
        if (jsonObject != null){
            JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
            if (parseObject != null) {
                String logo = parseObject.getString("logo");
                if (!StringUtils.isEmpty(logo)) {
                    // 更新赛果信息
                    vo.setLeft_team_logo(logo);
                    vo.setLeft_team_name(parseObject.getString("name"));
                    vo.setRight_team_logo(null);
                    vo.setRight_team_name(null);
                    iCsgoDao.updateByTeamId(vo);
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

    private void updateTeamRight(CsgoVO vo) throws Exception {
        TreeMap<String, String> treeMap = CommonUtil.treeMap;
        treeMap.put("team_id", vo.getRight_team_id().toString());
        treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
        String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
        String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/detail?tenant_id=2&team_id={0}&request_time={1}\"", vo.getRight_team_id().toString(), treeMap.get("request_time"));
        JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
        if (jsonObject != null){
            JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
            if (parseObject != null) {
                String logo = parseObject.getString("logo");
                if (!StringUtils.isEmpty(logo)) {
                    // 更新赛果信息
                    vo.setRight_team_logo(logo);
                    vo.setRight_team_name(parseObject.getString("name"));
                    vo.setLeft_team_logo(null);
                    vo.setLeft_team_name(null);
                    iCsgoDao.updateByTeamId(vo);
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
