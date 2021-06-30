package com.quanmin.djdata.lol.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.lol.LolVO;
import com.quanmin.djdata.pojo.series.SeriesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:32
 * @ClassName: com.quanmin.djdata.lol.dao.ILolDao
 */
@Repository
public interface ILolDao {

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果
     * @date: 2019/11/15 12:59
     * @param: [lolVO]
     * @return: int
     */
    int insert(@Param("vo") LolVO lolVO);

    /**
     * @author: ate
     * @description: 查询lol赛果数据
     * @date: 2019/11/15 13:40
     * @param: [lolVO]
     * @return: boolean
     */
    LolVO get(@Param("vo") LolVO lolVO);

    /**
     * @author: ate
     * @description: 分页王者荣耀赛果
     * @date: 2019/11/20 11:49
     * @param: [page, lolVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.dota2.Dota2VO>
     */
    IPage<LolVO> page(Page<LolVO> page, @Param("vo") LolVO lolVO);

    /**
     * @author: ate
     * @description: 列表
     * @date: 2019/11/25 17:47
     * @param: [lolVO]
     * @return: com.quanmin.djdata.pojo.lol.LolVO
     */
    List<LolVO> list(@Param("vo") LolVO lolVO);

    /**
     * @author: ate
     * @description: 列表-系列赛
     * @date: 2019/11/26 15:51
     * @param: [lolVO, seriesVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.lol.LolVO>
     */
    List<LolVO> listBySeries(@Param("vo") LolVO lolVO, @Param("vo1") SeriesVO seriesVO);

    /**
     * @author: ate
     * @description: 更新赛果战队信息
     * @date: 2019/11/30 13:06
     * @param: [lolVO]
     * @return: int
     */
    int updateByTeamId(@Param("vo") LolVO lolVO);
}
