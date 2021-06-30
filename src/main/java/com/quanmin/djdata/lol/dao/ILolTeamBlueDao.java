package com.quanmin.djdata.lol.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.lol.LolTeamVO;
import com.quanmin.djdata.pojo.lol.LolVO;
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
public interface ILolTeamBlueDao {

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果战队蓝队
     * @date: 2019/11/20 12:59
     * @param: [lolTeamVO]
     * @return: int
     */
    int insert(@Param("vo") LolTeamVO lolTeamVO);

    /**
     * @author: ate
     * @description: 查询英雄联盟赛果战队蓝队
     * @date: 2019/11/20 13:40
     * @param: [lolVO]
     * @return: boolean
     */
    LolTeamVO get(@Param("vo") LolTeamVO lolTeamVO);

    /**
     * @author: ate
     * @description: 分页英雄联盟赛果战队蓝队
     * @date: 2019/11/20 13:14
     * @param: [page, lolTeamVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.lol.LolTeamVO>
     */
    IPage<LolTeamVO> page(Page<LolTeamVO> page, @Param("vo") LolTeamVO lolTeamVO);

    /**
     * @author: ate
     * @description: 列表战队
     * @date: 2019/11/21 19:08
     * @param: [lolTeamVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.lol.LolTeamVO>
     */
    List<LolTeamVO> list(@Param("vo") LolTeamVO lolTeamVO);
}
