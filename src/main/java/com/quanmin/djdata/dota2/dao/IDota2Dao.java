package com.quanmin.djdata.dota2.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.aov.AovVO;
import com.quanmin.djdata.pojo.dota2.Dota2VO;
import com.quanmin.djdata.pojo.series.SeriesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:28
 * @ClassName: com.quanmin.djdata.dota2.dao.IDota2Dao
 */
@Repository
public interface IDota2Dao {

    /**
     * @author: ate
     * @description: 保存dota2赛果
     * @date: 2019/11/16 15:46
     * @param: [dota2VO]
     * @return: int
     */
    int insert(@Param("vo") Dota2VO dota2VO);

    /**
     * @author: ate
     * @description: 查询dota2赛果
     * @date: 2019/11/16 15:47
     * @param: [dota2VO]
     * @return: com.quanmin.djdata.pojo.dota2.Dota2VO
     */
    Dota2VO get(@Param("vo") Dota2VO dota2VO);

    /**
     * @author: ate
     * @description: 分页dota2VO赛果
     * @date: 2019/11/19 15:53
     * @param: [page, dota2VO]
     * @return: java.lang.Object
     */
    IPage<Dota2VO> page(Page<Dota2VO> page, @Param("vo") Dota2VO dota2VO);

    /**
     * @author: ate
     * @description: 列表
     * @date: 2019/11/26 10:37
     * @param: [dota2VO]
     * @return: java.util.List<com.quanmin.djdata.pojo.dota2.Dota2VO>
     */
    List<Dota2VO> list(@Param("vo") Dota2VO dota2VO);

    /**
     * @author: ate
     * @description: 列表-系列赛
     * @date: 2019/11/26 16:15
     * @param: [dota2VO, seriesVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.dota2.Dota2VO>
     */
    List<Dota2VO> listBySeries(@Param("vo") Dota2VO dota2VO, @Param("vo1") SeriesVO seriesVO);

    /**
     * @author: ate
     * @description: 更新赛果战队信息
     * @date: 2019/11/30 12:51
     * @param: [dota2VO]
     * @return: int
     */
    int updateByTeamId(@Param("vo") Dota2VO dota2VO);
}
