package com.quanmin.djdata.team.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.team.TeamRankVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 13:17
 * @ClassName: com.quanmin.djdata.team.service.ITeamRankService
 */
public interface ITeamRankService {

    /**
     * @author: ate
     * @description: 保存战队排名
     * @date: 2019/11/14 13:26
     * @param: [teamRankVO]
     * @return: java.lang.Object
     */
    Object insert(TeamRankVO teamRankVO);

    /**
     * @author: ate
     * @description: 分页战队排名
     * @date: 2019/11/22 16:36
     * @param: [teamRankVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(TeamRankVO teamRankVO, PageVO pageVO);
}
