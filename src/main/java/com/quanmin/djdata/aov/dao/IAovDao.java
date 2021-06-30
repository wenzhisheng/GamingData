package com.quanmin.djdata.aov.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.aov.AovVO;
import com.quanmin.djdata.pojo.series.SeriesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:24
 * @ClassName: com.quanmin.djdata.aov.dao.IAovDao
 */
@Repository
public interface IAovDao {

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果
     * @date: 2019/11/14 22:19
     * @param: [lolVO]
     * @return: int
     */
    int insert(@Param("vo") AovVO lolVO);

    /**
     * @author: ate
     * @description: 查询王者荣耀赛果
     * @date: 2019/11/14 22:19
     * @param: [aovVO]
     * @return: com.quanmin.djdata.pojo.aov.AovVO
     */
    AovVO get(@Param("vo") AovVO aovVO);

    /**
     * @author: ate
     * @description: 分页王者荣耀赛果
     * @date: 2019/11/19 10:25
     * @param: [page, aovVO]
     * @return: java.lang.Object
     */
    IPage<AovVO> page(Page<AovVO> page, @Param("vo") AovVO aovVO);

    /**
     * @author: ate
     * @description: 列表
     * @date: 2019/11/25 18:34
     * @param: [aovVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.aov.AovVO>
     */
    List<AovVO> list(@Param("vo") AovVO aovVO);

    /**
     * @author: ate
     * @description: 列表系列赛
     * @date: 2019/11/26 16:09
     * @param: [aovVO, seriesVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.aov.AovVO>
     */
    List<AovVO> listBySeries(@Param("vo") AovVO aovVO, @Param("vo1") SeriesVO seriesVO);

    /**
     * @author: ate
     * @description: 批量更新
     * @date: 2019/11/28 10:32
     * @param: [newList]
     * @return: int
     */
    int batchUpdate(@Param("list") List<AovVO> newList);

    /**
     * @author: ate
     * @description: 根据战队ID更新
     * @date: 2019/11/30 11:52
     * @param: [aovVO]
     * @return: int
     */
    int updateByTeamId(@Param("vo") AovVO aovVO);
}
