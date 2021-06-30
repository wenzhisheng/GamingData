package com.quanmin.djdata.team.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.team.TeamDetailVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 14:10
 * @ClassName: com.quanmin.djdata.team.service.ITeanDetailService
 */
public interface ITeamDetailService {

    /**
     * @author: ate
     * @description: 保存战队详情
     * @date: 2019/11/14 14:14
     * @param: [teamDetailVO]
     * @return: java.lang.Object
     */
    Object insert(TeamDetailVO teamDetailVO);

    /**
     * @author: ate
     * @description: 分页战队详情
     * @date: 2019/11/30 12:18
     * @param: [teamDetailVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(TeamDetailVO teamDetailVO, PageVO pageVO);

    /**
     * @author: ate
     * @description: 获取战队详情
     * @date: 2019/11/30 15:15
     * @param: [teamDetailVO]
     * @return: java.lang.Object
     */
    Object get(TeamDetailVO teamDetailVO);
}
