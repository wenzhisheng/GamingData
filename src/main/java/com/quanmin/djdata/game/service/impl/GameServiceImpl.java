package com.quanmin.djdata.game.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.controller.GameController;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.game.service.IGameService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameVO;
import com.quanmin.djdata.pojo.team.TeamVO;
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
import java.util.TreeMap;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-08 15:50
 * @ClassName: com.quanmin.djdata.csgo.service.impl.CsgoServiceImpl
 */
@Service
public class GameServiceImpl implements IGameService {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private IGameDao iGameDao;

    /**
     * @author: ate
     * @description: 保存游戏列表
     * @date: 2019/11/11 16:52
     * @param: [gamesVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(GameVO gamesVO) {
        if (gamesVO == null){
            gamesVO = new GameVO();
        }
        try {
            TreeMap<String, String> treeMap = CommonUtil.treeMap;
            treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
            String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
            String url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/games?tenant_id=2&request_time={0}\"", treeMap.get("request_time"));
            JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
            JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
            if (result != null){
                JSONArray jsonArray = JSONArray.parseArray(result.getString("items"));
                if (jsonArray != null){
                    for (int i=0; i<jsonArray.size(); i++){
                        JSONObject parseObject = (JSONObject) jsonArray.get(i);
                        gamesVO.setId(new BigInteger(parseObject.getString("id"), 10));
                        gamesVO.setName(parseObject.getString("name"));
                        gamesVO.setShort_name(parseObject.getString("short_name"));
                        gamesVO.setCode(parseObject.getString("code"));
                        // 根据ID和名称查询游戏是否存在
                        if (iGameDao.get(gamesVO) == null){
                            iGameDao.insert(gamesVO);
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
     * @description: 根据ID和名称查询游戏是否存在
     * @date: 2019/11/12 10:23
     * @param: [gamesVO]
     * @return: com.quanmin.djdata.pojo.game.GamesVO
     */
    @Override
    public GameVO get(GameVO gamesVO) {
        return iGameDao.get(gamesVO);
    }

    /**
     * @author: ate
     * @description: 分页游戏
     * @date: 2019/11/22 16:26
     * @param: [gamesVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(GameVO gamesVO, PageVO pageVO) {
        Page<GameVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        return iGameDao.page(page, gamesVO);
    }

}
