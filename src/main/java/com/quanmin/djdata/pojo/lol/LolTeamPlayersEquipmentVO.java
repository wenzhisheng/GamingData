package com.quanmin.djdata.pojo.lol;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: 英雄联盟赛果战队玩家装备对象
 * @CreateDate: 2019-11-20 16:19
 * @ClassName: com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO
 */
@Setter
@Getter
@ApiModel(value = "LolTeamPlayersVO", description = "英雄联盟赛果战队玩家装备对象")
public class LolTeamPlayersEquipmentVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 4215212471090830772L;

    @ApiModelProperty(value = "赛果系列ID")
    private BigInteger finish_match_id;
    @ApiModelProperty(value = "赛果战队ID")
    private BigInteger finish_match_team_id;
    @ApiModelProperty(value = "赛果战队玩家ID")
    private BigInteger finish_match_players_id;

    /** dota2 */
    @ApiModelProperty(value = "赛果战队玩家账号")
    private BigInteger finish_match_players_account;
    @ApiModelProperty(value = "装备ID")
    private BigInteger equipment_id;
    @ApiModelProperty(value = "装备logo")
    private String equipment_logo;
    @ApiModelProperty(value = "装备名称")
    private String equipment_name;
    @ApiModelProperty(value = "装备英文名")
    private String equipment_name_english;
    @ApiModelProperty(value = "装备别名")
    private String equipment_alias;
    @ApiModelProperty(value = "装备信息")
    private String equipment_info;
    @ApiModelProperty(value = "装备描述")
    private String equipment_description;

    /** dota2使用 */
    @ApiModelProperty(value = "装备排序")
    private int equipment_sort;

}
