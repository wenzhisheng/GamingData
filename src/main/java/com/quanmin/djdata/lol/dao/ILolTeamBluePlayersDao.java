package com.quanmin.djdata.lol.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.lol.LolTeamPlayersVO;
import com.quanmin.djdata.pojo.lol.LolTeamRoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:32
 * @ClassName: com.quanmin.djdata.lol.dao.ILolTeamBluePlayersDao
 */
@Repository
public interface ILolTeamBluePlayersDao {

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果战队玩家
     * @date: 2019/11/20 12:59
     * @param: [lolTeamVO]
     * @return: int
     */
    int insert(@Param("vo") LolTeamPlayersVO lolTeamPlayersVO);

    /**
     * @author: ate
     * @description: 查询英雄联盟赛果战队玩家
     * @date: 2019/11/20 13:40
     * @param: [lolVO]
     * @return: boolean
     */
    LolTeamPlayersVO get(@Param("vo") LolTeamPlayersVO lolTeamPlayersVO);

    /**
     * @author: ate
     * @description: 分页英雄联盟赛果战队玩家
     * @date: 2019/11/20 13:16
     * @param: [page, lolTeamPlayersVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.lol.LolTeamPlayersVO>
     */
    IPage<LolTeamPlayersVO> page(Page<LolTeamPlayersVO> page, @Param("vo") LolTeamPlayersVO lolTeamPlayersVO);

    /**
     * @author: ate
     * @description: 蓝队队员
     * @date: 2019/11/21 19:23
     * @param: [lolTeamPlayersVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.lol.LolTeamPlayersVO>
     */
    List<LolTeamPlayersVO> list(@Param("vo") LolTeamPlayersVO lolTeamPlayersVO);
}
