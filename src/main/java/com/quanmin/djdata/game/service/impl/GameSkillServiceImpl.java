package com.quanmin.djdata.game.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.exception.DescribeException;
import com.quanmin.djdata.game.dao.IGameDao;
import com.quanmin.djdata.game.dao.IGameSkillAliasDao;
import com.quanmin.djdata.game.dao.IGameSkillDao;
import com.quanmin.djdata.game.service.IGameSkillService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameRoleVO;
import com.quanmin.djdata.pojo.game.GameSkillAliasVO;
import com.quanmin.djdata.pojo.game.GameSkillVO;
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
 * @CreateDate: 2019-11-13 16:28
 * @ClassName: com.quanmin.djdata.game.service.impl.GameSkillServiceImpl
 */
@Service
public class GameSkillServiceImpl implements IGameSkillService {

    private static final Logger logger = LoggerFactory.getLogger(GameSkillServiceImpl.class);

    @Autowired
    private IGameSkillDao iGameSkillDao;
    @Autowired
    private IGameSkillAliasDao iGameSkillAliasDao;
    @Autowired
    private IGameDao iGameDao;

    /**
     * @author: ate
     * @description: 保存游戏技能
     * @date: 2019/11/13 16:36
     * @param: [gameSkillVO]
     * @return: java.lang.Object
     */
    @Override
    public Object insert(GameSkillVO gameSkillVO) {
        if (gameSkillVO == null){
            gameSkillVO = new GameSkillVO();
        }
        try {
            List<GameVO> list = iGameDao.list();
            for (GameVO vo: list){
                TreeMap<String, String> treeMap = CommonUtil.treeMap;
                treeMap.put("game_id", vo.getId().toString());
                treeMap.put("request_time", String.valueOf(new Date().getTime() / 1000-10));
                String url;
                if (StringUtils.isEmpty(gameSkillVO.getOffset()) && StringUtils.isEmpty(gameSkillVO.getLimit())) {
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/skills?tenant_id=2&game_id={0}&request_time={1}\"", vo.getId().toString(), treeMap.get("request_time"));
                } else {
                    treeMap.put("offset", gameSkillVO.getOffset().toString());
                    treeMap.put("limit", gameSkillVO.getLimit().toString());
                    url = MessageFormat.format("\"http://bapi.stage.risewinter.cn/api/v1/game/skills?tenant_id=2&game_id={0}&request_time={1}&offset={2}&limit={3}\"",
                            vo.getId().toString(), treeMap.get("request_time"), gameSkillVO.getOffset(), gameSkillVO.getLimit());
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
                                gameSkillVO.setId(new BigInteger(parseObject.getString("id"), 10));
                                gameSkillVO.setGame_id(new BigInteger(parseObject.getString("game_id"), 10));
                                gameSkillVO.setGame_role_id(new BigInteger(parseObject.getString("game_role_id"), 10));
                                gameSkillVO.setGame_equipment_id(new BigInteger(parseObject.getString("game_equipment_id"), 10));
                                gameSkillVO.setName(parseObject.getString("name"));
                                gameSkillVO.setName_english(parseObject.getString("name_english"));
                                gameSkillVO.setInfo(parseObject.getString("info"));
                                gameSkillVO.setDescription(parseObject.getString("description"));
                                gameSkillVO.setIcon(parseObject.getString("icon"));
                                // 游戏技能别名
                                JSONArray alias = JSONArray.parseArray(parseObject.getString("alias"));
                                if (alias != null){
                                    GameSkillAliasVO gameSkillAliasVO = new GameSkillAliasVO();
                                    gameSkillAliasVO.setGame_skill_id(gameSkillVO.getId());
                                    if (alias.size() == 2){
                                        gameSkillAliasVO.setName(alias.get(0).toString());
                                        gameSkillAliasVO.setCode(alias.get(1).toString());
                                    } else if (alias.size() ==1){
                                        gameSkillAliasVO.setName(alias.get(0).toString());
                                    }
                                    // 查询是否存在
                                    if (iGameSkillAliasDao.get(gameSkillAliasVO) == null) {
                                        iGameSkillAliasDao.insert(gameSkillAliasVO);
                                    }
                                }
                                // 根据ID和名称查询是否存在
                                if (iGameSkillDao.get(gameSkillVO) == null) {
                                    iGameSkillDao.insert(gameSkillVO);
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
     * @description: 分页游戏技能
     * @date: 2019/11/29 14:27
     * @param: [gameSkillVO, pageVO]
     * @return: java.lang.Object
     */
    @Override
    public Object page(GameSkillVO gameSkillVO, PageVO pageVO) {
        if (StringUtils.isEmpty(gameSkillVO.getGame_id()) || StringUtils.isEmpty(gameSkillVO.getGame_role_id())){
            throw new DescribeException("游戏ID或游戏角色ID不能为空", -1);
        }
        Page<GameRoleVO> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        IPage<GameSkillVO> ipage = iGameSkillDao.page(page, gameSkillVO);
        List<GameSkillVO> records = ipage.getRecords();
        if (records.size() > 0){
            for (int i=0; i<records.size(); i++) {
                GameSkillVO gameSkillVO1 = records.get(i);
                // 游戏技能别名
                GameSkillAliasVO gameSkillAliasVO = new GameSkillAliasVO();
                gameSkillAliasVO.setGame_skill_id(gameSkillVO1.getId());
                gameSkillVO1.setAlias(iGameSkillAliasDao.list(gameSkillAliasVO));
            }
        }
        return ipage.setRecords(records);
    }

}
