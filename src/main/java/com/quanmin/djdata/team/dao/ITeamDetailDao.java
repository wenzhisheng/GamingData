package com.quanmin.djdata.team.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.team.TeamDetailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 14:10
 * @ClassName: com.quanmin.djdata.team.dao.ITeanDetailDao
 */
@Repository
public interface ITeamDetailDao {

    /**
     * @author: ate
     * @description: 保存战队详情
     * @date: 2019/11/14 14:34
     * @param: [teamDetailVO]
     * @return: int
     */
    int insert(@Param("vo") TeamDetailVO teamDetailVO);

    /**
     * @author: ate
     * @description: 查询战队详情
     * @date: 2019/11/14 14:34
     * @param: [teamDetailVO]
     * @return: com.quanmin.djdata.pojo.team.TeamDetailVO
     */
    TeamDetailVO get(@Param("vo") TeamDetailVO teamDetailVO);

    /**
     * @author: ate
     * @description: 分页战队详情
     * @date: 2019/11/30 12:20
     * @param: [page, teamDetailVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.team.TeamDetailVO>
     */
    IPage<TeamDetailVO> page(Page<TeamDetailVO> page, @Param("vo") TeamDetailVO teamDetailVO);

    /**
     * @author: ate
     * @description: 更新战队详情
     * @date: 2019/11/30 13:43
     * @param: [teamDetailVO]
     * @return: int
     */
    int update(@Param("vo") TeamDetailVO teamDetailVO);
}
