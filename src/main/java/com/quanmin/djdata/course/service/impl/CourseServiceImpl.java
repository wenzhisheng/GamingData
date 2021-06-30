package com.quanmin.djdata.course.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.course.dao.ICourseDao;
import com.quanmin.djdata.course.service.ICourseService;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.league.service.impl.LeagueServiceImpl;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.course.CourseVO;
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
 * @CreateDate: 2019-11-13 12:00
 * @ClassName: com.quanmin.djdata.course.service.impl.CourseServiceImpl
 */
@Service
public class CourseServiceImpl implements ICourseService {

    private static final Logger logger = LoggerFactory.getLogger(LeagueServiceImpl.class);

    @Autowired
    private ICourseDao iCourseDao;
    @Autowired
    private IGameDao iGameDao;

    /**
     * @author: ate
     * @description: 保存赛程列表
     * @date: 2019/11/13 12:07
     * @param: [courseVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(CourseVO courseVO) {
        if (courseVO == null){
            courseVO = new CourseVO();
        }
        try {
            List<GameVO> list = iGameDao.list();
            for (GameVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("game_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/courses?tenant_id=2&game_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null) {
                    JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                    if (result != null){
                        JSONArray jsonArray = JSONArray.parseArray(result.getString("items"));
                        if (jsonArray == null){
                            for (int i=0; i<jsonArray.size(); i++){
                                JSONObject parseObject = (JSONObject) jsonArray.get(i);
                                courseVO.setId(new BigInteger(parseObject.getString("id"), 10));
                                courseVO.setName(parseObject.getString("name"));
                                courseVO.setBonus(new BigInteger(parseObject.getString("bonus"), 10));
                                courseVO.setBonus_type(new BigInteger(parseObject.getString("bonus_type"), 10));
                                courseVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                                courseVO.setLeague_id(new BigInteger(parseObject.getString("league_id"), 10));
                                courseVO.setArea_id(new BigInteger(parseObject.getString("area_id"), 10));
                                courseVO.setDescription(parseObject.getString("description"));
                                courseVO.setStart_time(new BigInteger(parseObject.getString("start_time"), 10));
                                // 根据ID和名称查询游戏是否存在
                                if (iCourseDao.get(courseVO) == null){
                                    iCourseDao.insert(courseVO);
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
     * @description: 分页联赛
     * @date: 2019/11/30 15:10
     * @param: [courseVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(CourseVO courseVO, PageVO pageVO) {
        Page<CourseVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        return iCourseDao.page(page, courseVO);
    }

}
