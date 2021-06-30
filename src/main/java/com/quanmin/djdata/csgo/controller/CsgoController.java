package com.quanmin.djdata.csgo.controller;

import com.quanmin.djdata.csgo.service.ICsgoService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.csgo.CsgoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: csgo赛果
 * @CreateDate: 2019-11-08 14:33
 * @ClassName: com.quanmin.djdata.csgo.controller.CsgoController
 */
@RestController
@RequestMapping("/csgo")
@Api(value = "CsgoController", tags = "csgo赛果")
public class CsgoController {

    private static final Logger logger = LoggerFactory.getLogger(CsgoController.class);

    @Autowired
    private ICsgoService iCsgoService;

    /**
     * @author: ate
     * @description: 保存csgo赛果
     * @date: 2019/11/15 15:24
     * @param: [csgoVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存csgo赛果", notes = "可选参数：series_id=系列赛ID")
    public Object insert(CsgoVO csgoVO){
        return iCsgoService.insert(csgoVO);
    }

    /**
     * @author: ate
     * @description: 保存csgo赛果战队信息
     * @date: 2019/11/30 12:38
     * @param: [csgoVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/insertTeamInfo")
    @ApiOperation(value = "保存csgo赛果战队信息", notes = "必传参数：无")
    public Object insertTeamInfo(CsgoVO csgoVO){
        return iCsgoService.insertTeamInfo(csgoVO);
    }

    /**
     * @author: ate
     * @description: 保存csgo赛果战队信息依据第三方
     * @date: 2019/11/30 14:09
     * @param: [csgoVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/insertTeamInfo2")
    @ApiOperation(value = "保存csgo赛果战队信息依据第三方", notes = "必传参数：无")
    public Object insertTeamInfo2(CsgoVO csgoVO){
        return iCsgoService.insertTeamInfo2(csgoVO);
    }

    /**
     * @author: ate
     * @description: 分页
     * @date: 2019/11/29 10:44
     * @param: [csgoVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页csgo赛果", notes = "必传参数：pageNo=页码，pageSize=每页条数，series_id=系列赛ID")
    public Object page(CsgoVO csgoVO, PageVO pageVO){
        return iCsgoService.page(csgoVO, pageVO);
    }

}
