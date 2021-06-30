package com.quanmin.djdata.aov.service;

import com.quanmin.djdata.pojo.aov.AovVO;
import com.quanmin.djdata.pojo.base.PageVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:25
 * @ClassName: com.quanmin.djdata.aov.service.IAovService
 */
public interface IAovService {

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果
     * @date: 2019/11/14 16:38
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    Object insert(AovVO aovVO);

    /**
     * @author: ate
     * @description: 分页王者荣耀赛果
     * @date: 2019/11/19 10:22
     * @param: [aovVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(AovVO aovVO, PageVO pageVO);

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果战队信息
     * @date: 2019/11/27 18:27
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    Object insertTeamInfo(AovVO aovVO);

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果战队信息依据第三方
     * @date: 2019/11/30 13:10
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    Object insertTeamInfo2(AovVO aovVO);
}
