package com.quanmin.djdata.lol.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.lol.LolVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:32
 * @ClassName: com.quanmin.djdata.lol.service.ILolService
 */
public interface ILolService {

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果
     * @date: 2019/11/14 23:08
     * @param: [lolVO]
     * @return: java.lang.Object
     */
    Object insert(LolVO lolVO);

    /**
     * @author: ate
     * @description: 分页王者荣耀赛果
     * @date: 2019/11/20 11:48
     * @param: [lolVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(LolVO lolVO, PageVO pageVO);

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果战队信息
     * @date: 2019/11/30 13:03
     * @param: [lolVO]
     * @return: java.lang.Object
     */
    Object insertTeamInfo(LolVO lolVO);

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果依据第三方
     * @date: 2019/11/30 14:40
     * @param: [lolVO]
     * @return: java.lang.Object
     */
    Object insertTeamInfo2(LolVO lolVO);
}
