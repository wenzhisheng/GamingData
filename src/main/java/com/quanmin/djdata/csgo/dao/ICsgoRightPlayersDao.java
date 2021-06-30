package com.quanmin.djdata.csgo.dao;

import com.quanmin.djdata.pojo.csgo.CsgoPlayersVO;
import com.quanmin.djdata.pojo.csgo.CsgoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-08 15:51
 * @ClassName: com.quanmin.djdata.csgo.dao.CsgoDao
 */
@Repository
public interface ICsgoRightPlayersDao {

    /**
     * @author: ate
     * @description: 保存csgo赛果
     * @date: 2019/11/15 15:32
     * @param: [csgoPlayersVO]
     * @return: int
     */
    int insert(@Param("vo") CsgoPlayersVO csgoPlayersVO);

    /**
     * @author: ate
     * @description: 查询csgo赛果
     * @date: 2019/11/15 15:32
     * @param: [csgoPlayersVO]
     * @return: com.quanmin.djdata.pojo.csgo.CsgoPlayersVO
     */
    CsgoPlayersVO get(@Param("vo") CsgoPlayersVO csgoPlayersVO);

    /**
     * @author: ate
     * @description: csgo赛果玩家
     * @date: 2019/11/29 11:49
     * @param: [csgoPlayersVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.csgo.CsgoPlayersVO>
     */
    List<CsgoPlayersVO> list(@Param("vo") CsgoPlayersVO csgoPlayersVO);
}
