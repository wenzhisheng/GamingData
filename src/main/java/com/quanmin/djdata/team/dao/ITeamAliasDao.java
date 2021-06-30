package com.quanmin.djdata.team.dao;

import com.quanmin.djdata.pojo.team.TeamAliasVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 12:15
 * @ClassName: com.quanmin.djdata.team.dao.ITeamAliasDao
 */
@Repository
public interface ITeamAliasDao {

    /**
     * @author: ate
     * @description: 保存战队别名
     * @date: 2019/11/14 12:16
     * @param: [teamAliasVO]
     * @return: int
     */
    int insert(@Param("vo") TeamAliasVO teamAliasVO);

    /**
     * @author: ate
     * @description: 查询战队别名
     * @date: 2019/11/14 12:17
     * @param: [teamAliasVO]
     * @return: com.quanmin.djdata.pojo.team.TeamAliasVO
     */
    TeamAliasVO get(@Param("vo") TeamAliasVO teamAliasVO);

    /**
     * @author: ate
     * @description: 列表战队别名
     * @date: 2019/11/30 12:10
     * @param: [teamAliasVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.team.TeamAliasVO>
     */
    List<TeamAliasVO> list(@Param("vo") TeamAliasVO teamAliasVO);
}
