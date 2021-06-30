package com.quanmin.djdata.team.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.game.GameVO;
import com.quanmin.djdata.pojo.team.TeamVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 23:04
 * @ClassName: com.quanmin.djdata.team.dao.ITeamDao
 */
@Repository
public interface ITeamDao {

    /**
     * @author: ate
     * @description: 保存战队列表
     * @date: 2019/11/13 23:17
     * @param: [teamVO]
     * @return: int
     */
    int insert(@Param("vo") TeamVO teamVO);

    /**
     * @author: ate
     * @description: 查询战队
     * @date: 2019/11/13 23:18
     * @param: [teamVO]
     * @return: com.quanmin.djdata.pojo.team.TeamVO
     */
    TeamVO get(@Param("vo") TeamVO teamVO);

    /**
     * @author: ate
     * @description: 战队列表
     * @date: 2019/11/14 14:18
     * @param: []
     * @return: java.util.List<com.quanmin.djdata.pojo.team.TeamVO>
     */
    List<TeamVO> list();

    /**
     * @author: ate
     * @description: TODO
     * @date: 2019/11/22 16:04
     * @param: [page, teamVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.team.TeamVO>
     */
    IPage<TeamVO> page(Page<TeamVO> page, @Param("vo") TeamVO teamVO);

    /**
     * @author: ate
     * @description: 更新战队信息
     * @date: 2019/11/30 13:37
     * @param: [teamVO]
     * @return: int
     */
    int update(@Param("vo") TeamVO teamVO);
}
