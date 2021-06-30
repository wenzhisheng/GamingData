package com.quanmin.djdata.series.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.aov.dao.IAovDao;
import com.quanmin.djdata.course.dao.ICourseDao;
import com.quanmin.djdata.csgo.dao.ICsgoDao;
import com.quanmin.djdata.dota2.dao.IDota2Dao;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.lol.dao.ILolDao;
import com.quanmin.djdata.lol.dao.ILolTeamBlueDao;
import com.quanmin.djdata.lol.dao.ILolTeamRedDao;
import com.quanmin.djdata.pojo.aov.AovVO;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.course.CourseVO;
import com.quanmin.djdata.pojo.csgo.CsgoVO;
import com.quanmin.djdata.pojo.dota2.Dota2VO;
import com.quanmin.djdata.pojo.game.GameVO;
import com.quanmin.djdata.pojo.lol.LolTeamVO;
import com.quanmin.djdata.pojo.lol.LolVO;
import com.quanmin.djdata.pojo.series.SeriesTeamVO;
import com.quanmin.djdata.pojo.series.SeriesVO;
import com.quanmin.djdata.pojo.team.TeamVO;
import com.quanmin.djdata.series.dao.ISeriesDao;
import com.quanmin.djdata.series.dao.ISeriesTeamDao;
import com.quanmin.djdata.series.service.ISeriesService;
import com.quanmin.djdata.team.dao.ITeamDao;
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
import java.util.*;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 12:58
 * @ClassName: com.quanmin.djdata.series.service.impl.SeriesServiceImpl
 */
@Service
public class SeriesServiceImpl implements ISeriesService {

    private static final Logger logger = LoggerFactory.getLogger(SeriesServiceImpl.class);

    private static final String BLUE = "blue";
    private static final String RED = "red";

    @Autowired
    private ISeriesDao iSeriesDao;
    @Autowired
    private ISeriesTeamDao iSeriesTeamDao;
    @Autowired
    private IGameDao iGameDao;
    @Autowired
    private ICourseDao iCourseDao;
    @Autowired
    private ITeamDao iTeamDao;
    @Autowired
    private ILolDao iLolDao;
    @Autowired
    private ILolTeamBlueDao iLolTeamBlueDao;
    @Autowired
    private ILolTeamRedDao iLolTeamRedDao;
    @Autowired
    private IAovDao iAovDao;
    @Autowired
    private IDota2Dao iDota2Dao;
    @Autowired
    private ICsgoDao iCsgoDao;

    /**
     * @author: ate
     * @description: 保存系列赛列表
     * @date: 2019/11/13 13:07
     * @param: [seriesVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(SeriesVO seriesVO) {
        if (seriesVO == null){
            seriesVO = new SeriesVO();
        }
        try {
            List<GameVO> list = iGameDao.list();
            // 根据游戏ID获取每个游戏的数据
            for (GameVO vo: list){
                // 比赛状态（1未开始 2进行中 3结束）
                for (int status=1; status<4; status++){
                    TreeMap<String, String> treeMap = CommonUtil.treeMap;
                    treeMap.put("game_id", vo.getId().toString());
                    treeMap.put("status", String.valueOf(status));
                    treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                    String url;
                    /*if (!StringUtils.isEmpty(seriesVO.getBegin_time()) && !StringUtils.isEmpty(seriesVO.getEnd_time()) &&
                            !StringUtils.isEmpty(seriesVO.getOffset()) && !StringUtils.isEmpty(seriesVO.getLimit())) {
                        treeMap.put("begin_time", String.valueOf(seriesVO.getBegin_time()));
                        treeMap.put("end_time", String.valueOf(seriesVO.getEnd_time()));
                        treeMap.put("offset", seriesVO.getOffset().toString());
                        treeMap.put("limit", seriesVO.getLimit().toString());
                        url = String.format("\"http://bapi.stage.risewinter.cn/api/v1/match/series?tenant_id=2&game_id=%s&status=%d&request_time=%s&begin_time=%d&end_time=%d&offset=%s&limit=%s\"",
                                vo.getId().toString(), status, treeMap.get("request_time"), seriesVO.getBegin_time(), seriesVO.getEnd_time(), treeMap.get("offset"), treeMap.get("limit"));
                    } else */if (!StringUtils.isEmpty(seriesVO.getBegin_time()) && !StringUtils.isEmpty(seriesVO.getEnd_time())){
                        treeMap.put("begin_time", String.valueOf(seriesVO.getBegin_time()));
                        treeMap.put("end_time", String.valueOf(seriesVO.getEnd_time()));
                        url = String.format("\"http://bapi.stage.risewinter.cn/api/v1/match/series?tenant_id=2&game_id=%s&status=%d&request_time=%s&begin_time=%d&end_time=%d\"", vo.getId().toString(), status, treeMap.get("request_time"), seriesVO.getBegin_time(), seriesVO.getEnd_time());
                    } else if (!StringUtils.isEmpty(seriesVO.getBegin_time())){
                        treeMap.put("begin_time", String.valueOf(seriesVO.getBegin_time()));
                        url = String.format("\"http://bapi.stage.risewinter.cn/api/v1/match/series?tenant_id=2&game_id=%s&status=%d&request_time=%s&begin_time=%d\"", vo.getId().toString(), status, treeMap.get("request_time"), seriesVO.getBegin_time());
                    } else {
                        url = String.format("\"http://bapi.stage.risewinter.cn/api/v1/match/series?tenant_id=2&game_id=%s&status=%d&request_time=%s\"", vo.getId().toString(), status, treeMap.get("request_time"));
                    }
                    String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                    JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                    if (jsonObject != null) {
                        JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                        if (result != null) {
                            JSONArray jsonArray = JSONArray.parseArray(result.getString("items"));
                            // 返回数据为空则不处理
                            if (jsonArray != null) {
                                // 查询出数据，遍历保存json数组数据到数据库
                                for (int i = 0; i < jsonArray.size(); i++) {
                                    JSONObject parseObject = (JSONObject) jsonArray.get(i);
                                    seriesVO.setId(new BigInteger(parseObject.getString("id"), 10));
                                    seriesVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                                    seriesVO.setLeague_id(new BigInteger(parseObject.getString("league_id"), 10));
                                    seriesVO.setArea_id(new BigInteger(parseObject.getString("area_id"), 10));
                                    seriesVO.setCourse_id(new BigInteger(parseObject.getString("course_id"), 10));
                                    // 系列赛名称
                                    CourseVO courseVO = new CourseVO();
                                    courseVO.setId(seriesVO.getCourse_id());
                                    CourseVO courseVO1 = iCourseDao.get(courseVO);
                                    if (courseVO1 != null){
                                        seriesVO.setCourse_name(courseVO1.getName());
                                    }
                                    seriesVO.setStatus(Integer.valueOf(parseObject.getString("status")));
                                    seriesVO.setSeason_info(parseObject.getString("season_info"));
                                    seriesVO.setDescription(parseObject.getString("description"));
                                    seriesVO.setStart_time(new BigInteger(parseObject.getString("start_time"), 10));
                                    seriesVO.setEnd_time(new BigInteger(parseObject.getString("end_time"), 10));
                                    // 系列赛队伍
                                    JSONArray teams = JSONArray.parseArray(parseObject.getString("teams"));
                                    if (teams != null) {
                                        for (int j = 0; j < teams.size(); j++) {
                                            JSONObject team = (JSONObject) teams.get(j);
                                            SeriesTeamVO seriesTeamVO = new SeriesTeamVO();
                                            seriesTeamVO.setSeries_id(new BigInteger(parseObject.getString("id"), 10));
                                            seriesTeamVO.setTeam_id(new BigInteger(team.getString("team_id"), 10));
                                            // 战队名称与logo
                                            BigInteger radiant_team_id = seriesTeamVO.getTeam_id();
                                            if (radiant_team_id != null){
                                                TeamVO teamVO = new TeamVO();
                                                teamVO.setId(radiant_team_id);
                                                TeamVO teamVO1 = iTeamDao.get(teamVO);
                                                if (teamVO1 != null){
                                                    seriesTeamVO.setTeam_logo(teamVO1.getLogo());
                                                    seriesTeamVO.setTeam_name(teamVO1.getName());
                                                }
                                            }
                                            /*// 战队类型、比分、比赛数据
                                            BigInteger gameId = seriesVO.getGame_id();
                                            // 英雄联盟
                                            if (new BigInteger("651841432110167818", 10).compareTo(gameId) == 0) {
                                                LolVO lolVO = new LolVO();
                                                lolVO.setSeries_id(seriesVO.getId());
                                                List<LolVO> lolVOList = iLolDao.list(lolVO);
                                                if (lolVOList != null && lolVOList.size() !=0 ){
                                                    LolVO lolVO1 = lolVOList.get(lolVOList.size()-1);
                                                    // 多少局
                                                    if (lolVO1.getGame_no() != 1){
                                                        seriesVO.setGame_no("第"+lolVO1.getGame_no()+"局");
                                                    }
                                                    // 类型、比分
                                                    if (lolVO1.getBlue_team_name() != null){
                                                        if (lolVO1.getBlue_team_name().equals(seriesTeamVO.getTeam_name())){
                                                            seriesTeamVO.setTeam_type(BLUE);
                                                            seriesTeamVO.setTeam_score(lolVO1.getBlue_team_score());
                                                        } else {
                                                            seriesTeamVO.setTeam_type(RED);
                                                            seriesTeamVO.setTeam_score(lolVO1.getRed_team_score());
                                                        }
                                                    }
                                                    // 比赛数据
                                                    if (BLUE.equals(lolVO1.getFirst_blood())){
                                                        seriesTeamVO.setFirst_blood(BLUE);
                                                    } else {
                                                        seriesTeamVO.setFirst_blood(RED);
                                                    }
                                                    if (BLUE.equals(lolVO1.getFirst_tower())){
                                                        seriesTeamVO.setFirst_tower(BLUE);
                                                    } else {
                                                        seriesTeamVO.setFirst_tower(RED);
                                                    }
                                                    if (BLUE.equals(lolVO1.getFive_kills())){
                                                        seriesTeamVO.setFive_kills(BLUE);
                                                    } else {
                                                        seriesTeamVO.setFive_kills(RED);
                                                    }
                                                    if (BLUE.equals(lolVO1.getTen_kills())){
                                                        seriesTeamVO.setTen_kills(BLUE);
                                                    } else {
                                                        seriesTeamVO.setTen_kills(RED);
                                                    }
                                                    if (BLUE.equals(lolVO1.getFirst_drakes())){
                                                        seriesTeamVO.setFirst_drakes(BLUE);
                                                    } else {
                                                        seriesTeamVO.setFirst_drakes(RED);
                                                    }
                                                    if (BLUE.equals(lolVO1.getFirst_nashors())){
                                                        seriesTeamVO.setFirst_nashors(BLUE);
                                                    } else {
                                                        seriesTeamVO.setFirst_nashors(RED);
                                                    }
                                                    if (BLUE.equals(lolVO1.getFirst_inhibitor())){
                                                        seriesTeamVO.setFirst_inhibitor(BLUE);
                                                    } else {
                                                        seriesTeamVO.setFirst_inhibitor(RED);
                                                    }
                                                    if (BLUE.equals(lolVO1.getHerald())){
                                                        seriesTeamVO.setHerald(BLUE);
                                                    } else {
                                                        seriesTeamVO.setHerald(RED);
                                                    }
                                                }
                                            }
                                            // 王者荣耀
                                            if (new BigInteger("651841432110167828", 10).compareTo(gameId) == 0) {
                                                AovVO aovVO = new AovVO();
                                                aovVO.setSeries_id(seriesVO.getId());
                                                List<AovVO> aovVOList = iAovDao.list(aovVO);
                                                if (aovVOList != null && aovVOList.size() != 0 ){
                                                    AovVO aovVO1 = aovVOList.get(aovVOList.size() - 1);
                                                    // 多少局
                                                    if (aovVO1.getGame_no() != 1){
                                                        seriesVO.setGame_no("第"+aovVO1.getGame_no()+"局");
                                                    }
                                                    seriesTeamVO.setFirst_bloods(aovVO1.getFirst_blood());
                                                    seriesTeamVO.setFirst_dominator(aovVO1.getFirst_dominator());
                                                    seriesTeamVO.setFirst_towers(aovVO1.getFirst_tower());
                                                    seriesTeamVO.setFirst_tyrant(aovVO1.getFirst_tyrant());
                                                    seriesTeamVO.setFive_kill(aovVO1.getFive_kill());
                                                    seriesTeamVO.setTen_kill(aovVO1.getTen_kill());
                                                    seriesTeamVO.setWin(aovVO1.getWin());
                                                }
                                            }
                                            // 刀塔2
                                            if (new BigInteger("651841432110167838", 10).compareTo(gameId) == 0) {
                                                Dota2VO dota2VO = new Dota2VO();
                                                dota2VO.setSeries_id(seriesVO.getId());
                                                List<Dota2VO> dota2VOList = iDota2Dao.list(dota2VO);
                                                if (dota2VOList != null && dota2VOList.size() != 0 ){
                                                    Dota2VO dota2VO1 = dota2VOList.get(dota2VOList.size() - 1);
                                                }
                                            }
                                            // 反恐精英
                                            if (new BigInteger("651841432110167848", 10).compareTo(gameId) == 0) {
                                                CsgoVO csgoVO = new CsgoVO();
                                                csgoVO.setSeries_id(seriesVO.getId());
                                                List<CsgoVO> csgoVOList = iCsgoDao.list(csgoVO);
                                                if (csgoVOList != null && csgoVOList.size() != 0 ){
                                                    CsgoVO csgoVO1 = csgoVOList.get(csgoVOList.size() - 1);

                                                }
                                            }
                                            // 根据ID和名称查询游戏是否存在
                                            if (iSeriesTeamDao.get(seriesTeamVO) == null) {
                                                iSeriesTeamDao.insert(seriesTeamVO);
                                            }*/
                                        }
                                    }
                                    // 根据ID和名称查询游戏是否存在
                                    if (iSeriesDao.isExist(seriesVO) == null) {
                                        iSeriesDao.insert(seriesVO);
                                    }
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
     * @description: 系列赛分页
     * @date: 2019/11/18 16:15
     * @param: [seriesVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public IPage<SeriesVO> page(SeriesVO seriesVO, PageVO pageVO) {
        if (StringUtils.isEmpty(seriesVO.getGame_id())){
            throw new DescribeException("游戏ID不能为空", -1);
        }
        // 英雄联盟
        String gameId = String.valueOf(seriesVO.getGame_id());
        Page<SeriesVO> page = new Page<SeriesVO>(pageVO.getPageNo(), pageVO.getPageSize());
        if ("651841432110167818".equals(gameId)) {
            IPage<SeriesVO> ipage = iSeriesDao.pageLol(page, seriesVO);
            List<SeriesVO> records = ipage.getRecords();
            for (int i=0; i<records.size(); i++){
                SeriesVO seriesVO1 = records.get(i);
                List<LolVO> list = new ArrayList<LolVO>();
                // 状态（1：未开始，2：进行中，3：结束）
                if (seriesVO1.getStatus() != null && seriesVO1.getStatus() == 3){
                    // 系列赛赛果
                    LolVO lolVO = new LolVO();
                    lolVO.setSeries_id(seriesVO1.getId());
                    lolVO.setGame_id(seriesVO.getGame_id());
                    //List<LolVO> list = iLolDao.listBySeries(lolVO, seriesVO);
                    list = iLolDao.list(lolVO);
                    // lol蓝队红队
                    for (int ii=0; ii<list.size(); ii++){
                        LolVO lolVO1 = list.get(ii);
                        LolTeamVO lolTeamVO = new LolTeamVO();
                        lolTeamVO.setTeam_id(lolVO1.getBlue_team_id());
                        lolTeamVO.setFinish_match_lol_id(lolVO1.getMatch_id());
                        List<LolTeamVO> list1 = iLolTeamBlueDao.list(lolTeamVO);
                        lolVO1.setBlue(list1);
                        lolTeamVO.setTeam_id(lolVO1.getRed_team_id());
                        List<LolTeamVO> list2 = iLolTeamRedDao.list(lolTeamVO);
                        lolVO1.setRed(list2);
                    }
                } else {
                    // 战队信息
                    seriesVO1.setTeams(iSeriesTeamDao.list(seriesVO1));
                }
                seriesVO1.setList(list);
            }
            // 赛果为空则剔除，如果赛事未开始的不要过滤
            if (seriesVO.getStatus() != null && seriesVO.getStatus() == 3){
                removeNullList(records);
            }
            return ipage.setRecords(records);
        }
        // 王者荣耀
        if ("651841432110167828".equals(gameId)) {
            IPage<SeriesVO> ipage = iSeriesDao.pageAov(page, seriesVO);
            List<SeriesVO> records = ipage.getRecords();
            for (int i=0; i<records.size(); i++){
                SeriesVO seriesVO1 = records.get(i);
                List<AovVO> list = new ArrayList<AovVO>();
                // 状态（1：未开始，2：进行中，3：结束）
                if (seriesVO1.getStatus() != null && seriesVO1.getStatus() == 3){
                    // 系列赛赛果
                    AovVO aovVO = new AovVO();
                    aovVO.setSeries_id(seriesVO1.getId());
                    aovVO.setGame_id(seriesVO.getGame_id());
                    list = iAovDao.list(aovVO);
                } else {
                    // 战队信息
                    seriesVO1.setTeams(iSeriesTeamDao.list(seriesVO1));
                }
                seriesVO1.setList(list);
            }
            // 赛果为空则剔除，如果赛事未开始的不要过滤
            if (seriesVO.getStatus() != null && seriesVO.getStatus() == 3){
                removeNullList(records);
            }
            return ipage.setRecords(records);
        }
        // Dota2
        if ("651841432110167838".equals(gameId)) {
            IPage<SeriesVO> ipage = iSeriesDao.pageDota2(page, seriesVO);
            List<SeriesVO> records = ipage.getRecords();
            for (int i=0; i<records.size(); i++){
                SeriesVO seriesVO1 = records.get(i);
                List<Dota2VO> list = new ArrayList<Dota2VO>();
                // 状态（1：未开始，2：进行中，3：结束）
                if (seriesVO1.getStatus() != null && seriesVO1.getStatus() == 3){
                    // 系列赛赛果
                    Dota2VO dota2VO = new Dota2VO();
                    dota2VO.setSeries_id(seriesVO1.getId());
                    dota2VO.setGame_id(seriesVO.getGame_id());
                    //List<Dota2VO> list = iDota2Dao.listBySeries(dota2VO, seriesVO);
                    list = iDota2Dao.list(dota2VO);
                } else {
                    // 系列赛战队
                    seriesVO1.setTeams(iSeriesTeamDao.list(seriesVO1));
                }
                seriesVO1.setList(list);
            }
            // 赛果为空则剔除，如果赛事未开始的不要过滤
            if (seriesVO.getStatus() != null && seriesVO.getStatus() == 3){
                removeNullList(records);
            }
            return ipage.setRecords(records);
        }
        // Csgo
        if ("651841432110167848".equals(gameId)) {
            IPage<SeriesVO> ipage = iSeriesDao.pageCsgo(page, seriesVO);
            List<SeriesVO> records = ipage.getRecords();
            for (int i=0; i<records.size(); i++){
                SeriesVO seriesVO1 = records.get(i);
                List<CsgoVO> list = new ArrayList<CsgoVO>();
                // 状态（1：未开始，2：进行中，3：结束）
                if (seriesVO1.getStatus() != null && seriesVO1.getStatus() == 3){
                    // 系列赛赛果
                    CsgoVO csgoVO = new CsgoVO();
                    csgoVO.setSeries_id(seriesVO1.getId());
                    csgoVO.setGame_id(seriesVO.getGame_id());
                    //List<CsgoVO> list = iCsgoDao.listBySeries(csgoVO, seriesVO);
                    list = iCsgoDao.list(csgoVO);
                } else {
                    // 系列赛战队
                    seriesVO1.setTeams(iSeriesTeamDao.list(seriesVO1));
                }
                seriesVO1.setList(list);
            }
            // 赛果为空则剔除，如果赛事未开始的不要过滤
            if (seriesVO.getStatus() != null && seriesVO.getStatus() == 3){
                removeNullList(records);
            }
            return ipage.setRecords(records);
        }
        return null;
    }

    /**
     * @author: ate
     * @description: 移除自身为空的list
     * @date: 2019/11/27 15:30
     * @param: [records]
     * @return: void
     */
    private void removeNullList(List<SeriesVO> records) {
        ListIterator<SeriesVO> iterator = records.listIterator();
        while (iterator.hasNext()){
            SeriesVO next = iterator.next();
            if (next.getList() == null || next.getList().size() == 0) {
                iterator.remove();
            }
        }
    }

}
