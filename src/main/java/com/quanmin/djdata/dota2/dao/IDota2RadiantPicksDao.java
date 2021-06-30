package com.quanmin.djdata.dota2.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.lol.LolTeamRoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:28
 * @ClassName: com.quanmin.djdata.dota2.dao.IDota2DireBansDao
 */
@Repository
public interface IDota2RadiantPicksDao {

    /**
     * @author: ate
     * @description: 保存dota2赛果天辉选择英雄
     * @date: 2019/11/23 12:57
     * @param: [lolTeamRoleVO]
     * @return: int
     */
    int insert(@Param("vo") LolTeamRoleVO lolTeamRoleVO);

    /**
     * @author: ate
     * @description: 查询dota2赛果天辉选择英雄
     * @date: 2019/11/23 12:57
     * @param: [lolTeamRoleVO]
     * @return: com.quanmin.djdata.pojo.lol.LolTeamRoleVO
     */
    LolTeamRoleVO get(@Param("vo") LolTeamRoleVO lolTeamRoleVO);

    /**
     * @author: ate
     * @description: 分页dota2VO赛果天辉选择英雄
     * @date: 2019/11/23 12:57
     * @param: [page, lolTeamRoleVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.lol.LolTeamRoleVO>
     */
    IPage<LolTeamRoleVO> page(Page<LolTeamRoleVO> page, @Param("vo") LolTeamRoleVO lolTeamRoleVO);

    /**
     * @author: ate
     * @description: 列表dota2VO赛果天辉选择英雄
     * @date: 2019/11/23 17:54
     * @param: [lolTeamRoleVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.lol.LolTeamRoleVO>
     */
    List<LolTeamRoleVO> list(@Param("vo") LolTeamRoleVO lolTeamRoleVO);
}
