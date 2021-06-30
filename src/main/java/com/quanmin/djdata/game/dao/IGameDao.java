package com.quanmin.djdata.game.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.game.GameVO;
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
public interface IGameDao {

    /**
     * @author: ate
     * @description: 保存游戏列表
     * @date: 2019/11/11 16:48
     * @param: [gamesVO]
     * @return: int
     */
    int insert(@Param("vo") GameVO gamesVO);

    /**
     * @author: ate
     * @description: 根据ID和名称查询游戏是否存在
     * @date: 2019/11/12 10:24
     * @param: [gamesVO]
     * @return: com.quanmin.djdata.pojo.game.GamesVO
     */
    GameVO get(@Param("vo") GameVO gamesVO);

    /**
     * @author: ate
     * @description: 游戏列表
     * @date: 2019/11/12 17:35
     * @param: []
     * @return: java.util.List<com.quanmin.djdata.pojo.game.GamesVO>
     */
    List<GameVO> list();

    /**
     * @author: ate
     * @description: 分页游戏
     * @date: 2019/11/22 16:27
     * @param: [page, gamesVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.game.GameVO>
     */
    IPage<GameVO> page(Page<GameVO> page, @Param("vo") GameVO gamesVO);
}
