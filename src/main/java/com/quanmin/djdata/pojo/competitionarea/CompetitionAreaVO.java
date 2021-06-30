package com.quanmin.djdata.pojo.competitionarea;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: ate
 * @Description: 赛区对象
 * @CreateDate: 2019-11-12 10:53
 * @ClassName: com.quanmin.djdata.pojo.competitionarea.CompetitionAreaVO
 */
@Setter
@Getter
@ApiModel(value = "CompetitionAreaVO", description = "赛区对象")
public class CompetitionAreaVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 439321242810042081L;

    @ApiModelProperty(value = "赛区名称")
    private String name;
    @ApiModelProperty(value = "赛区区域")
    private String area;
    @ApiModelProperty(value = "游戏ID")
    private String game_id;
    @ApiModelProperty(value = "描述简介")
    private String description;

}
