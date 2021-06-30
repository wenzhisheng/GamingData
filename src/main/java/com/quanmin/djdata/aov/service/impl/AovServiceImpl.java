package com.quanmin.djdata.aov.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.aov.dao.IAovDao;
import com.quanmin.djdata.aov.dao.IFinishSeriesAovDao;
import com.quanmin.djdata.aov.service.IAovService;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.pojo.aov.AovVO;
import com.quanmin.djdata.pojo.base.PageVO;
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
 * @CreateDate: 2019-11-14 16:26
 * @ClassName: com.quanmin.djdata.aov.service.AovServiceImpl
 */
@Service
public class AovServiceImpl implements IAovService {

    private static final Logger logger = LoggerFactory.getLogger(AovServiceImpl.class);

    @Autowired
    private IAovDao iAovDao;
    @Autowired
    private ISeriesDao iSeriesDao;
    @Autowired
    private IFinishSeriesAovDao iFinishSeriesDao;
    @Autowired
    private ITeamDao iTeamDao;
    @Autowired
    private ITeamDetailDao iTeamDetailDao;

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果
     * @date: 2019/11/14 22:19
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(AovVO aovVO) {
        if (aovVO == null){
            aovVO = new AovVO();
        }
        try {
            SeriesVO seriesVO = new SeriesVO();
            seriesVO.setGame_id(new BigInteger("651841432110167828"));
            // 传值则单个抓取
            List<SeriesVO> list = new ArrayList<SeriesVO>();
            if (StringUtils.isEmpty(aovVO.getSeries_id())){
                list = iSeriesDao.list(seriesVO);
            } else {
                seriesVO.setId(aovVO.getSeries_id());
                list.add(seriesVO);
            }
            // 否则全部抓取
            for (SeriesVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("series_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/match/aov/finish?tenant_id=2&series_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
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
                                aovVO.setSeries_id(new BigInteger(match.getString("series_id"), 10));
                                aovVO.setMatch_id(new BigInteger(match.getString("match_id"), 10));
                                aovVO.setGame_id(new BigInteger(match.getString("game_id"), 10));
                                aovVO.setGame_no(Integer.valueOf(match.getString("game_no")));
                                // flag
                                JSONObject flag = JSONObject.parseObject(match.getString("flag"));
                                aovVO.setFirst_blood(Integer.valueOf(flag.getString("first_blood")));
                                aovVO.setFirst_dominator(Integer.valueOf(flag.getString("first_dominator")));
                                aovVO.setFirst_tower(Integer.valueOf(flag.getString("first_tower")));
                                aovVO.setFirst_tyrant(Integer.valueOf(flag.getString("first_tyrant")));
                                aovVO.setFive_kill(Integer.valueOf(flag.getString("five_kill")));
                                aovVO.setFive_kill(Integer.valueOf(flag.getString("ten_kill")));
                                aovVO.setWin(Integer.valueOf(flag.getString("win")));
                                // team_a
                                JSONObject team_a = JSONObject.parseObject(match.getString("team_a"));
                                aovVO.setTeam_a_id(new BigInteger(team_a.getString("id"), 10));
                                aovVO.setTeam_a_site(new BigInteger(team_a.getString("site"), 10));
                                BigInteger team_a_id = aovVO.getTeam_a_id();
                                if (team_a_id != null){
                                    TeamVO teamVO = new TeamVO();
                                    teamVO.setId(team_a_id);
                                    TeamVO teamVO1 = iTeamDao.get(teamVO);
                                    if (teamVO1 != null){
                                        aovVO.setTeam_a_logo(teamVO1.getLogo());
                                        aovVO.setTeam_a_name(teamVO1.getName());
                                    }
                                }
                                // team_b
                                JSONObject team_b = JSONObject.parseObject(match.getString("team_b"));
                                aovVO.setTeam_b_id(new BigInteger(team_b.getString("id"), 10));
                                aovVO.setTeam_b_site(new BigInteger(team_b.getString("site"), 10));
                                BigInteger team_b_id = aovVO.getTeam_b_id();
                                if (team_b_id != null){
                                    TeamVO teamVO = new TeamVO();
                                    teamVO.setId(team_b_id);
                                    TeamVO teamVO2 = iTeamDao.get(teamVO);
                                    if (teamVO2 != null){
                                        aovVO.setTeam_b_logo(teamVO2.getLogo());
                                        aovVO.setTeam_b_name(teamVO2.getName());
                                    }
                                }
                                if (iAovDao.get(aovVO) == null) {
                                    iAovDao.insert(aovVO);
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
     * @description: 分页王者荣耀赛果
     * @date: 2019/11/19 10:23
     * @param: [aovVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(AovVO aovVO, PageVO pageVO) {
        if (StringUtils.isEmpty(aovVO.getSeries_id())){
            throw new DescribeException("系列赛ID不能为空", -1);
        }
        Page<AovVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        return iAovDao.page(page, aovVO);
    }

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果战队信息
     * @date: 2019/11/27 18:27
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insertTeamInfo(AovVO aovVO) {
//        List<AovVO> newList = new ArrayList<>();
        List<AovVO> list = iAovDao.list(aovVO);
        for (AovVO vo: list){
            if (StringUtils.isEmpty(vo.getTeam_a_name()) || StringUtils.isEmpty(vo.getTeam_a_logo())) {
                TeamVO teamVO = new TeamVO();
                teamVO.setId(vo.getTeam_a_id());
                TeamVO teamVO1 = iTeamDao.get(teamVO);
                if (teamVO1 != null) {
                    vo.setTeam_a_name(teamVO1.getName());
                    vo.setTeam_a_logo(teamVO1.getLogo());
                    iAovDao.updateByTeamId(vo);
                }
//                newList.add(vo);
            }
            if (StringUtils.isEmpty(vo.getTeam_b_name()) || StringUtils.isEmpty(vo.getTeam_b_logo())) {
                TeamVO teamVO = new TeamVO();
                teamVO.setId(vo.getTeam_b_id());
                TeamVO teamVO1 = iTeamDao.get(teamVO);
                if (teamVO1 != null) {
                    vo.setTeam_b_name(teamVO1.getName());
                    vo.setTeam_b_logo(teamVO1.getLogo());
                    iAovDao.updateByTeamId(vo);
                }
//                newList.add(vo);
            }
        }
        // 批量更新
//        return iAovDao.batchUpdate(newList);
        return "success";
    }

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果战队信息依据第三方
     * @date: 2019/11/30 13:10
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insertTeamInfo2(AovVO aovVO) {
        try {
            List<AovVO> list = iAovDao.list(aovVO);
            if (list != null || list.size() > 0) {
                for (AovVO vo: list){
                    // 战队A
                    updateTeamA(vo);
                    // 战队B
                    updateTeamB(vo);
                }
            }
        } catch (Exception ex) {
            logger.error("insert games error ={}", ex);
            throw new DescribeException("fail", -1);
        }
        return "success";
    }

    private void updateTeamA(AovVO vo) throws Exception {
        TreeMap<String, String> treeMap = CommonUtil.treeMap;
        treeMap.put("team_id", vo.getTeam_a_id().toString());
        treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
        String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
        String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/detail?tenant_id=2&team_id={0}&request_time={1}\"", vo.getTeam_a_id().toString(), treeMap.get("request_time"));
        JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
        if (jsonObject != null){
            JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
            if (parseObject != null) {
                String logo = parseObject.getString("logo");
                if (!StringUtils.isEmpty(logo)) {
                    // 更新赛果信息
                    vo.setTeam_a_logo(logo);
                    vo.setTeam_a_name(parseObject.getString("name"));
                    vo.setTeam_b_logo(null);
                    vo.setTeam_b_name(null);
                    iAovDao.updateByTeamId(vo);
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

    private void updateTeamB(AovVO vo) throws Exception {
        TreeMap<String, String> treeMap = CommonUtil.treeMap;
        treeMap.put("team_id", vo.getTeam_b_id().toString());
        treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
        String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
        String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/teams/detail?tenant_id=2&team_id={0}&request_time={1}\"", vo.getTeam_b_id().toString(), treeMap.get("request_time"));
        JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
        if (jsonObject != null){
            JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("result"));
            if (parseObject != null) {
                String logo = parseObject.getString("logo");
                if (!StringUtils.isEmpty(logo)) {
                    // 更新赛果信息
                    vo.setTeam_b_logo(logo);
                    vo.setTeam_b_name(parseObject.getString("name"));
                    vo.setTeam_a_logo(null);
                    vo.setTeam_a_name(null);
                    iAovDao.updateByTeamId(vo);
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
