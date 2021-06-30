package com.quanmin.djdata.league.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.league.LeagueVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-12 18:27
 * @ClassName: com.quanmin.djdata.league.dao.ILeagueDao
 */
@Repository
public interface ILeagueDao {

    /**
     * @author: ate
     * @description: TODO
     * @date: 2019/11/12 20:44
     * @param: [leagueVO]
     * @return: int
     */
    int insert(@Param("vo") LeagueVO leagueVO);

    /**
     * @author: ate
     * description: 查询是否存在
     * @date: 2019/11/12 20:51
     * @param: [leagueVO]
     * @return: com.quanmin.djdata.pojo.league.LeagueVO
     */
    LeagueVO get(@Param("vo") LeagueVO leagueVO);

    /**
     * @author: ate
     * @description: 分页联赛
     * @date: 2019/11/30 14:56
     * @param: [page, leagueVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.league.LeagueVO>
     */
    IPage<LeagueVO> page(Page<LeagueVO> page, @Param("vo") LeagueVO leagueVO);
}
