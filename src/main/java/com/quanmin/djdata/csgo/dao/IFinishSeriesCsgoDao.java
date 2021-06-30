package com.quanmin.djdata.csgo.dao;

import com.quanmin.djdata.pojo.finish.FinishSeriesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 21:34
 * @ClassName: com.quanmin.djdata.csgo.dao.IFinishSeriesCsgoDao
 */
@Repository
public interface IFinishSeriesCsgoDao {

    /**
     * @author: ate
     * @description: 保存赛果系列赛
     * @date: 2019/11/14 21:35
     * @param: [finishSeriesVO]
     * @return: int
     */
    int insert(@Param("vo") FinishSeriesVO finishMatchAovVO);

    /**
     * @author: ate
     * @description: 查询赛果系列赛
     * @date: 2019/11/14 21:36
     * @param: [finishSeriesVO]
     * @return: com.quanmin.djdata.pojo.finish.FinishSeriesVO
     */
    FinishSeriesVO get(@Param("vo") FinishSeriesVO finishSeriesVO);
}
