package com.quanmin.djdata.game.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.game.dao.IGameRoleDao;
import com.quanmin.djdata.game.service.IGameRoleService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameRoleVO;
import com.quanmin.djdata.pojo.game.GameVO;
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
 * @CreateDate: 2019-11-13 15:10
 * @ClassName: com.quanmin.djdata.game.service.impl.GameRoleServiceImpl
 */
@Service
public class GameRoleServiceImpl implements IGameRoleService {

    private static final Logger logger = LoggerFactory.getLogger(GameRoleServiceImpl.class);

    @Autowired
    private IGameRoleDao iGameRoleDao;
    @Autowired
    private IGameDao iGameDao;

    /**
     * @author: ate
     * @description: 保存游戏角色
     * @date: 2019/11/13 15:20
     * @param: [gameRoleVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(GameRoleVO gameRoleVO) {
        if (gameRoleVO == null){
            gameRoleVO = new GameRoleVO();
        }
        try {
            List<GameVO> list = iGameDao.list();
            for (GameVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("game_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String url;
                if (StringUtils.isEmpty(gameRoleVO.getOffset()) && StringUtils.isEmpty(gameRoleVO.getLimit())) {
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/roles?tenant_id=2&game_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                } else {
                    treeMap.put("offset", gameRoleVO.getOffset().toString());
                    treeMap.put("limit", gameRoleVO.getLimit().toString());
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/roles?tenant_id=2&game_id={0}&request_time={1}&offset={2}&limit={3}\"", vo.getId().toString(), treeMap.get("request_time"), gameRoleVO.getOffset(), gameRoleVO.getLimit());
                }
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null) {
                    JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                    if (result != null) {
                        JSONArray jsonArray = JSONArray.parseArray(result.getString("items"));
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JSONObject parseObject = (JSONObject) jsonArray.get(i);
                                gameRoleVO.setId(new BigInteger(parseObject.getString("id"), 10));
                                gameRoleVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                                gameRoleVO.setName(parseObject.getString("name"));
                                gameRoleVO.setName_english(parseObject.getString("name_english"));
                                gameRoleVO.setAlias(parseObject.getString("alias"));
                                gameRoleVO.setInfo(parseObject.getString("info"));
                                gameRoleVO.setDescription(parseObject.getString("description"));
                                gameRoleVO.setIcon(parseObject.getString("icon"));
                                // 根据ID和名称查询是否存在
                                if (iGameRoleDao.get(gameRoleVO) == null) {
                                    iGameRoleDao.insert(gameRoleVO);
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
     * @description: 分页游戏英雄
     * @date: 2019/11/29 14:13
     * @param: [gameRoleVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(GameRoleVO gameRoleVO, PageVO pageVO) {
        if (StringUtils.isEmpty(gameRoleVO.getGame_id())) {
            throw new DescribeException("游戏ID不能为空", -1);
        }
        Page<GameRoleVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        return iGameRoleDao.page(page, gameRoleVO);
    }
}
