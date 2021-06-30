package com.quanmin.djdata.pojo.course;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: 赛程对象
 * @CreateDate: 2019-11-13 12:04
 * @ClassName: com.quanmin.djdata.pojo.course.CourseVO
 */
@Setter
@Getter
@ApiModel(value = "CourseVO", description = "赛程对象")
public class CourseVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -222999939768726581L;

    @ApiModelProperty(value = "赛事名称")
    private String name;
    @ApiModelProperty(value = "奖金")
    private BigInteger bonus;
    @ApiModelProperty(value = "奖金单位")
    private BigInteger bonus_type;
    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "联赛ID")
    private BigInteger league_id;
    @ApiModelProperty(value = "赛区ID")
    private BigInteger area_id;
    @ApiModelProperty(value = "描述简介")
    private String description;
    @ApiModelProperty(value = "开始时间")
    private BigInteger start_time;
    @ApiModelProperty(value = "结束时间")
    private BigInteger end_time;

}
