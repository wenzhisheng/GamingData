package com.quanmin.djdata.dota2.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.dota2.Dota2PlayersVO;
import com.quanmin.djdata.pojo.dota2.Dota2VO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:28
 * @ClassName: com.quanmin.djdata.dota2.dao.IDota2PlayersDao
 */
@Repository
public interface IDota2PlayersDao {

    /**
     * @author: ate
     * @description: 保存dota2赛果玩家
     * @date: 2019/11/19 15:46
     * @param: [dota2PlayersVO]
     * @return: int
     */
    int insert(@Param("vo") Dota2PlayersVO dota2PlayersVO);

    /**
     * @author: ate
     * @description: 查询dota2赛果玩家
     * @date: 2019/11/19 15:47
     * @param: [dota2PlayersVO]
     * @return: com.quanmin.djdata.pojo.dota2.Dota2PlayersVO
     */
    Dota2PlayersVO get(@Param("vo") Dota2PlayersVO dota2PlayersVO);

    /**
     * @author: ate
     * @description: 分页dota2VO赛果玩家
     * @date: 2019/11/19 15:53
     * @param: [page, dota2PlayersVO]
     * @return: java.lang.Object
     */
    IPage<Dota2PlayersVO> page(Page<Dota2PlayersVO> page, @Param("vo") Dota2PlayersVO dota2PlayersVO);

    /**
     * @author: ate
     * @description: 列表dota2VO赛果玩家
     * @date: 2019/11/20 10:47
     * @param: [dota2PlayersVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.dota2.Dota2PlayersVO>
     */
    List<Dota2PlayersVO> list(@Param("vo") Dota2PlayersVO dota2PlayersVO);
}
