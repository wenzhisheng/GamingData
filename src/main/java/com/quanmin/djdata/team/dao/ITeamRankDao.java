package com.quanmin.djdata.team.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.team.TeamRankVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 13:17
 * @ClassName: com.quanmin.djdata.team.dao.ITeamRankDao
 */
@Repository
public interface ITeamRankDao {

    /**
     * @author: ate
     * @description: 保存战队排名
     * @date: 2019/11/14 13:35
     * @param: [teamRankVO]
     * @return: int
     */
    int insert(@Param("vo") TeamRankVO teamRankVO);

    /**
     * @author: ate
     * @description: 查询战队排名
     * @date: 2019/11/14 13:36
     * @param: [teamRankVO]
     * @return: com.quanmin.djdata.pojo.team.TeamRankVO
     */
    TeamRankVO get(@Param("vo") TeamRankVO teamRankVO);

    /**
     * @author: ate
     * @description: 分页战队排名
     * @date: 2019/11/22 16:38
     * @param: [page, teamRankVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.team.TeamRankVO>
     */
    IPage<TeamRankVO> page(Page<TeamRankVO> page, @Param("vo") TeamRankVO teamRankVO);
}
