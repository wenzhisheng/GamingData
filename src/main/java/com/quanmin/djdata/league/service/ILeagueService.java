package com.quanmin.djdata.league.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.league.LeagueVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-12 18:28
 * @ClassName: com.quanmin.djdata.league.service.ILeagueService
 */
public interface ILeagueService {

    /**
     * @author: ate
     * @description: 保存联赛列表
     * @date: 2019/11/12 20:39
     * @param: [leagueVO]
     * @return: java.lang.Object
     */
    Object insert(LeagueVO leagueVO);

    /**
     * @author: ate
     * @description: 分页联赛
     * @date: 2019/11/30 14:54
     * @param: [leagueVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(LeagueVO leagueVO, PageVO pageVO);
}
