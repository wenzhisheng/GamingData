package com.quanmin.djdata.competitionarea.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.competitionarea.CompetitionAreaVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-12 10:46
 * @ClassName: com.quanmin.djdata.competitionarea.dao.ICompetitionAreaDao
 */
@Repository
public interface ICompetitionAreaDao {

    /**
     * @author: ate
     * @description: 保存赛区列表
     * @date: 2019/11/12 17:49
     * @param: [competitionAreaVO]
     * @return: int
     */
    int insert(@Param("vo") CompetitionAreaVO competitionAreaVO);

    /**
     * @author: ate
     * @description: 查询赛区是否存在
     * @date: 2019/11/12 17:52
     * @param: [competitionAreaVO]
     * @return: com.quanmin.djdata.pojo.competitionarea.CompetitionAreaVO
     */
    CompetitionAreaVO get(@Param("vo") CompetitionAreaVO competitionAreaVO);

    /**
     * @author: ate
     * @description: 分页赛区
     * @date: 2019/11/30 15:04
     * @param: [page, competitionAreaVO]
     * @return: java.lang.Object
     */
    IPage<CompetitionAreaVO> page(Page<CompetitionAreaVO> page, @Param("vo") CompetitionAreaVO competitionAreaVO);
}
