package com.quanmin.djdata.dota2.controller;

import com.quanmin.djdata.dota2.service.IDota2Service;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.dota2.Dota2VO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: dota2赛果
 * @CreateDate: 2019-11-14 16:27
 * @ClassName: com.quanmin.djdata.dota2.controller.Dota2Controller
 */
@RestController
@RequestMapping("/dota2")
@Api(value = "Dota2Controller", tags = "dota2赛果")
public class Dota2Controller {

    @Autowired
    private IDota2Service iDota2Service;

    /**
     * @author: ate
     * @description: 保存dota2VO赛果
     * @date: 2019/11/16 14:32
     * @param: [dota2VO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存dota2赛果", notes = "可选参数：series_id=系列赛ID")
    public Object insert(Dota2VO dota2VO){
        return iDota2Service.insert(dota2VO);
    }

    /**
     * @author: ate
     * @description: 保存dota2赛果依据第三方
     * @date: 2019/11/30 14:34
     * @param: [dota2VO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/insertTeamInfo2")
    @ApiOperation(value = "保存dota2赛果依据第三方", notes = "必传参数：无")
    public Object insertTeamInfo2(Dota2VO dota2VO){
        return iDota2Service.insertTeamInfo2(dota2VO);
    }

    /**
     * @author: ate
     * @description: 保存dota2VO赛果战队信息
     * @date: 2019/11/30 12:48
     * @param: [csgoVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/insertTeamInfo")
    @ApiOperation(value = "保存dota2VO赛果战队信息", notes = "必传参数：无")
    public Object insertTeamInfo(Dota2VO dota2VO){
        return iDota2Service.insertTeamInfo(dota2VO);
    }

    /**
     * @author: ate
     * @description: 分页dota2VO赛果
     * @date: 2019/11/19 15:52
     * @param: [dota2VO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页dota2VO赛果", notes = "必传参数：pageNo=页码，pageSize=每页条数，series_id=系列赛ID")
    public Object page(Dota2VO dota2VO, PageVO pageVO){
        return iDota2Service.page(dota2VO, pageVO);
    }

}
