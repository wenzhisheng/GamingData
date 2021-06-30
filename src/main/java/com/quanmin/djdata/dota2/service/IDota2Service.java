package com.quanmin.djdata.dota2.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.dota2.Dota2VO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:29
 * @ClassName: com.quanmin.djdata.dota2.service.IDota2Service
 */
public interface IDota2Service {

    /**
     * @author: ate
     * @description: 保存dota2赛果
     * @date: 2019/11/14 18:03
     * @param: [dota2VO]
     * @return: java.lang.Object
     */
    Object insert(Dota2VO dota2VO);

    /**
     * @author: ate
     * @description: 分页dota2VO赛果
     * @date: 2019/11/19 15:52
     * @param: [dota2VO, pageVO]
     * @return: java.lang.Object
     */
    Object page(Dota2VO dota2VO, PageVO pageVO);

    /**
     * @author: ate
     * @description: 保存dota2VO赛果战队信息
     * @date: 2019/11/30 12:48
     * @param: [dota2VO]
     * @return: java.lang.Object
     */
    Object insertTeamInfo(Dota2VO dota2VO);

    /**
     * @author: ate
     * @description: 保存dota2赛果依据第三方
     * @date: 2019/11/30 14:34
     * @param: [dota2VO]
     * @return: java.lang.Object
     */
    Object insertTeamInfo2(Dota2VO dota2VO);
}
