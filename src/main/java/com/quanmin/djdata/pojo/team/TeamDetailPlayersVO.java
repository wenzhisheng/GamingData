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
 * @Description: 战队详情玩家对象
 * @CreateDate: 2019-11-14 14:05
 * @ClassName: com.quanmin.djdata.pojo.team.TeamDetailVO
 */
@Setter
@Getter
@ApiModel(value = "TeamDetailPlaypersVO", description = "战队详情玩家对象")
public class TeamDetailPlayersVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1478267424709481445L;

    @ApiModelProperty(value = "战队详情ID")
    private BigInteger team_detail_id;
    @ApiModelProperty(value = "别名")
    private String alias;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "英文名")
    private String name_english;
    @ApiModelProperty(value = "国家编码")
    private String country;
    @ApiModelProperty(value = "战队玩家位置")
    private String team_player_position;

}
