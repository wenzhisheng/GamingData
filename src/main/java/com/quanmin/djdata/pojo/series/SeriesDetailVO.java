package com.quanmin.djdata.pojo.series;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: 系列赛详情对象
 * @CreateDate: 2019-11-13 13:01
 * @ClassName: com.quanmin.djdata.pojo.series.SeriesVO
 */
@Setter
@Getter
@ApiModel(value = "SeriesDetailVO", description = "系列赛详情对象")
public class SeriesDetailVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -3020279129255038469L;

    @ApiModelProperty(value = "系列赛ID")
    private BigInteger series_id;
    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "联赛ID")
    private BigInteger league_id;
    @ApiModelProperty(value = "赛区ID")
    private BigInteger area_id;
    @ApiModelProperty(value = "赛事ID")
    private BigInteger course_id;
    @ApiModelProperty(value = "状态（1：未开始，2：进行中，3：结束）")
    private Integer status;
    @ApiModelProperty(value = "描述简介")
    private String description;
    @ApiModelProperty(value = "开始时间")
    private BigInteger start_time;
    @ApiModelProperty(value = "结束时间")
    private BigInteger end_time;

}
