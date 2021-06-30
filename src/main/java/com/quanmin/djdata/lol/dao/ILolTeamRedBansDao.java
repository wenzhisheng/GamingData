package com.quanmin.djdata.lol.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.lol.LolTeamRoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:32
 * @ClassName: com.quanmin.djdata.lol.dao.ILolDao
 */
@Repository
public interface ILolTeamRedBansDao {

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果战队红队禁用英雄
     * @date: 2019/11/20 12:59
     * @param: [lolTeamRoleVO]
     * @return: int
     */
    int insert(@Param("vo") LolTeamRoleVO lolTeamRoleVO);

    /**
     * @author: ate
     * @description: 查询英雄联盟赛果战队红队禁用英雄
     * @date: 2019/11/20 13:40
     * @param: [lolTeamRoleVO]
     * @return: boolean
     */
    LolTeamRoleVO get(@Param("vo") LolTeamRoleVO lolTeamRoleVO);

    /**
     * @author: ate
     * @description: 分页英雄联盟赛果战队红队禁用英雄
     * @date: 2019/11/20 18:02
     * @param: [page, lolTeamRoleVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.lol.LolTeamRoleVO>
     */
    IPage<LolTeamRoleVO> page(Page<LolTeamRoleVO> page, @Param("vo") LolTeamRoleVO lolTeamRoleVO);

    /**
     * @author: ate
     * @description: 红队禁用选择
     * @date: 2019/11/21 19:30
     * @param: [lolTeamRoleVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.lol.LolTeamRoleVO>
     */
    List<LolTeamRoleVO> list(@Param("vo") LolTeamRoleVO lolTeamRoleVO);
}
