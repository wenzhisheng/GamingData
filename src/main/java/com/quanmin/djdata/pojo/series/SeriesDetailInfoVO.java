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
 * @Description: 系列赛详情信息对象
 * @CreateDate: 2019-11-13 18:01
 * @ClassName: com.quanmin.djdata.pojo.series.SeriesDetailInfoVO
 */
@Setter
@Getter
@ApiModel(value = "SeriesDetailInfoVO", description = "系列赛详情信息对象")
public class SeriesDetailInfoVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -3020279529255038469L;

    @ApiModelProperty(value = "系列赛详情ID")
    private BigInteger series_detail_id;
    @ApiModelProperty(value = "得分")
    private BigInteger score;
    @ApiModelProperty(value = "团队ID")
    private BigInteger team_id;

}
