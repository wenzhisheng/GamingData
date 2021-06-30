package com.quanmin.djdata.game.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.game.dao.IGameEquipmentsDao;
import com.quanmin.djdata.game.service.IGameEquipmentsService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameEquipmentVO;
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
public class GameEquipmentsServiceImpl implements IGameEquipmentsService {

    private static final Logger logger = LoggerFactory.getLogger(GameEquipmentsServiceImpl.class);

    @Autowired
    private IGameEquipmentsDao iGameEquipmentsDao;
    @Autowired
    private IGameDao iGameDao;

    /**
     * @author: ate
     * @description: 保存游戏装备
     * @date: 2019/11/13 15:20
     * @param: [gameEquipmentVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(GameEquipmentVO gameEquipmentVO) {
        if (gameEquipmentVO == null){
            gameEquipmentVO = new GameEquipmentVO();
        }
        try {
            List<GameVO> list = iGameDao.list();
            for (GameVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("game_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String url;
                if (StringUtils.isEmpty(gameEquipmentVO.getOffset()) && StringUtils.isEmpty(gameEquipmentVO.getLimit())) {
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/equipments?tenant_id=2&game_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                } else {
                    treeMap.put("offset", gameEquipmentVO.getOffset().toString());
                    treeMap.put("limit", gameEquipmentVO.getLimit().toString());
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/equipments?tenant_id=2&game_id={0}&request_time={1}&offset={2}&limit={3}\"",
                            vo.getId().toString(), treeMap.get("request_time"), gameEquipmentVO.getOffset(), gameEquipmentVO.getLimit());
                }
                String sign = MessageFormat.format("\"sign:{0}\"", RSAEncrypt.signParam(treeMap));
                JSONObject jsonObject = CurlUtil.readGETJson(sign, "", url);
                if (jsonObject != null){
                    JSONObject result = JSONObject.parseObject(jsonObject.getString("result"));
                    if (result != null) {
                        JSONArray jsonArray = JSONArray.parseArray(result.getString("items"));
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JSONObject parseObject = (JSONObject) jsonArray.get(i);
                                gameEquipmentVO.setId(new BigInteger(parseObject.getString("id"), 10));
                                gameEquipmentVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                                gameEquipmentVO.setName(parseObject.getString("name"));
                                gameEquipmentVO.setName_english(parseObject.getString("name_english"));
                                gameEquipmentVO.setAlias(parseObject.getString("alias"));
                                gameEquipmentVO.setInfo(parseObject.getString("info"));
                                gameEquipmentVO.setDescription(parseObject.getString("description"));
                                gameEquipmentVO.setIcon(parseObject.getString("icon"));
                                // 根据ID和名称查询是否存在
                                if (iGameEquipmentsDao.get(gameEquipmentVO) == null) {
                                    iGameEquipmentsDao.insert(gameEquipmentVO);
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
     * @description: 分页游戏装备
     * @date: 2019/11/22 16:31
     * @param: [gameEquipmentVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(GameEquipmentVO gameEquipmentVO, PageVO pageVO) {
        if (StringUtils.isEmpty(gameEquipmentVO.getGame_id())) {
            throw new DescribeException("游戏ID不能为空", -1);
        }
        Page<GameEquipmentVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        return iGameEquipmentsDao.page(page, gameEquipmentVO);
    }
}
