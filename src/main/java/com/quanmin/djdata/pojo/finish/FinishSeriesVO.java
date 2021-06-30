package com.quanmin.djdata.pojo.finish;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: 赛果系列赛对象
 * @CreateDate: 2019-11-14 18:15
 * @ClassName: com.quanmin.djdata.pojo.finish.FinishSeriesVO
 */
@Setter
@Getter
@ApiModel(value = "FinishSeriesVO", description = "赛果系列赛对象")
public class FinishSeriesVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -6947024062634034622L;

    @ApiModelProperty(value = "系列赛ID")
    private BigInteger series_id;
    @ApiModelProperty(value = "战队ID")
    private BigInteger team_id;
    @ApiModelProperty(value = "得分")
    private Integer score;

}
