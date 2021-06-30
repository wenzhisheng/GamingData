package com.quanmin.djdata.csgo.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.csgo.CsgoVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-08 15:50
 * @ClassName: com.quanmin.djdata.csgo.service.ICsgoService
 */
public interface ICsgoService {

    /**
     * @author: ate
     * @description: 保存csgo赛果
     * @date: 2019/11/15 15:25
     * @param: [csgoVO]
     * @return: java.lang.Object
     */
    Object insert(CsgoVO csgoVO);

    /**
     * @author: ate
     * @description: 分页
     * @date: 2019/11/29 10:45
     * @param: [csgoVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(CsgoVO csgoVO, PageVO pageVO);

    /**
     * @author: ate
     * @description: 保存csgo赛果战队信息
     * @date: 2019/11/30 12:39
     * @param: [csgoVO]
     * @return: java.lang.Object
     */
    Object insertTeamInfo(CsgoVO csgoVO);

    /**
     * @author: ate
     * @description: 保存csgo赛果战队信息依据第三方
     * @date: 2019/11/30 14:27
     * @param: [csgoVO]
     * @return: java.lang.Object
     */
    Object insertTeamInfo2(CsgoVO csgoVO);
}
