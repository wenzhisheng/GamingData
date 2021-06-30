package com.quanmin.djdata.competitionarea.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.competitionarea.CompetitionAreaVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-12 10:46
 * @ClassName: com.quanmin.djdata.competitionarea.service.ICompetitionAreaService
 */
public interface ICompetitionAreaService {

    /**
     * @author: ate
     * @description: 保存赛区列表
     * @date: 2019/11/12 17:48
     * @param: [competitionAreaVO]
     * @return: java.lang.Object
     */
    Object insert(CompetitionAreaVO competitionAreaVO);

    /**
     * @author: ate
     * @description: 分页赛区
     * @date: 2019/11/30 15:03
     * @param: [competitionAreaVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(CompetitionAreaVO competitionAreaVO, PageVO pageVO);
}
