package com.quanmin.djdata.csgo.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.csgo.CsgoVO;
import com.quanmin.djdata.pojo.series.SeriesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-08 15:51
 * @ClassName: com.quanmin.djdata.csgo.dao.CsgoDao
 */
@Repository
public interface ICsgoDao {

    /**
     * @author: ate
     * @description: 保存csgo赛果
     * @date: 2019/11/15 15:32
     * @param: [csgoVO]
     * @return: int
     */
    int insert(@Param("vo") CsgoVO csgoVO);

    /**
     * @author: ate
     * @description: 查询csgo赛果
     * @date: 2019/11/15 15:32
     * @param: [csgoVO]
     * @return: com.quanmin.djdata.pojo.csgo.CsgoVO
     */
    CsgoVO get(@Param("vo") CsgoVO csgoVO);

    /**
     * @author: ate
     * @description: 列表
     * @date: 2019/11/26 10:40
     * @param: [csgoVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.csgo.CsgoVO>
     */
    List<CsgoVO> list(@Param("vo") CsgoVO csgoVO);

    /**
     * @author: ate
     * @description: 列表-系列赛
     * @date: 2019/11/26 16:11
     * @param: [csgoVO, seriesVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.csgo.CsgoVO>
     */
    List<CsgoVO> listBySeries(@Param("vo") CsgoVO csgoVO, @Param("vo1") SeriesVO seriesVO);

    /**
     * @author: ate
     * @description: 分页csgo赛果
     * @date: 2019/11/29 10:49
     * @param: [page, csgoVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.csgo.CsgoVO>
     */
    IPage<CsgoVO> page(Page<CsgoVO> page, @Param("vo") CsgoVO csgoVO);

    /**
     * @author: ate
     * @description: 更新赛果战队信息
     * @date: 2019/11/30 12:43
     * @param: [csgoVO]
     * @return: int
     */
    int updateByTeamId(@Param("vo") CsgoVO csgoVO);
}
