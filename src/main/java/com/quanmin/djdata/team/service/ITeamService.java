package com.quanmin.djdata.team.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.team.TeamVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 23:04
 * @ClassName: com.quanmin.djdata.team.service.ITeamService
 */
public interface ITeamService {

    /**
     * @author: ate
     * @description: 保存战队列表
     * @date: 2019/11/13 23:11
     * @param: [teamVO]
     * @return: java.lang.Object
     */
    Object insert(TeamVO teamVO);

    /**
     * @author: ate
     * @description: 分页战队
     * @date: 2019/11/22 16:02
     * @param: [teamVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(TeamVO teamVO, PageVO pageVO);
}
