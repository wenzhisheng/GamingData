package com.quanmin.djdata.pojo.league;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: 联赛对象
 * @CreateDate: 2019-11-12 20:09
 * @ClassName: com.quanmin.djdata.pojo.league.LeagueVO
 */
@Setter
@Getter
@ApiModel(value = "LeagueVO", description = "联赛对象")
public class LeagueVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -7518865248185289321L;

    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "短名称")
    private String short_name;
    @ApiModelProperty(value = "图标")
    private String logo;
    @ApiModelProperty(value = "组织者")
    private String organizer;
    @ApiModelProperty(value = "等级")
    private String level;
    @ApiModelProperty(value = "地区")
    private String local;
    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "赛区ID")
    private BigInteger area_id;
    @ApiModelProperty(value = "队伍数量")
    private int limit_team;
    @ApiModelProperty(value = "描述简介")
    private String description;

}
