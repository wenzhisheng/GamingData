package com.quanmin.djdata.team.dao;

import com.quanmin.djdata.pojo.team.TeamDetailPlayersVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 14:10
 * @ClassName: com.quanmin.djdata.team.dao.ITeamDetailPlaypersDao
 */
@Repository
public interface ITeamDetailPlayersDao {

    /**
     * @author: ate
     * @description: 保存战队详情玩家
     * @date: 2019/11/14 14:34
     * @param: [teamDetailPlayersVO]
     * @return: int
     */
    int insert(@Param("vo") TeamDetailPlayersVO teamDetailPlayersVO);

    /**
     * @author: ate
     * @description: 查询战队详情玩家
     * @date: 2019/11/14 14:34
     * @param: [teamDetailPlayersVO]
     * @return: com.quanmin.djdata.pojo.team.TeamDetailPlayersVO
     */
    TeamDetailPlayersVO get(@Param("vo") TeamDetailPlayersVO teamDetailPlayersVO);
}
