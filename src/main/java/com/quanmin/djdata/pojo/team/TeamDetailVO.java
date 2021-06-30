package com.quanmin.djdata.pojo.team;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: 战队详情对象
 * @CreateDate: 2019-11-14 14:05
 * @ClassName: com.quanmin.djdata.pojo.team.TeamDetailVO
 */
@Setter
@Getter
@ApiModel(value = "TeamDetailVO", description = "战队详情对象")
public class TeamDetailVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1478267424709481445L;

    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "游戏类型")
    private Integer game_type;
    @ApiModelProperty(value = "别名")
    private String alias;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "简称")
    private String short_name;
    @ApiModelProperty(value = "英文名称")
    private String name_english;
    @ApiModelProperty(value = "图标")
    private String logo;
    @ApiModelProperty(value = "归属地")
    private String local;
    @ApiModelProperty(value = "国家编码")
    private String country;
    @ApiModelProperty(value = "地区")
    private String area;

}
