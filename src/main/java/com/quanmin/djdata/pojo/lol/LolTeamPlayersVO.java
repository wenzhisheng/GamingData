package com.quanmin.djdata.pojo.lol;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: ate
 * @Description: 英雄联盟赛果战队玩家对象
 * @CreateDate: 2019-11-14 16:19
 * @ClassName: com.quanmin.djdata.pojo.lol.LolVO
 */
@Setter
@Getter
@ApiModel(value = "LolTeamPlayersVO", description = "英雄联盟赛果战队玩家对象")
public class LolTeamPlayersVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 4215212471090830772L;

    @ApiModelProperty(value = "lol赛果蓝队ID")
    private BigInteger finish_match_lol_team_id;
    @ApiModelProperty(value = "自动")
    private String kda;
    @ApiModelProperty(value = "补刀")
    private Integer cs;
    @ApiModelProperty(value = "等级")
    private Integer level;
    @ApiModelProperty(value = "击杀")
    private Integer kills;
    @ApiModelProperty(value = "死亡")
    private Integer deaths;
    @ApiModelProperty(value = "助攻")
    private Integer assists;
    @ApiModelProperty(value = "经济")
    private Integer gold;
    @ApiModelProperty(value = "伤害")
    private Integer damage_taken;
    @ApiModelProperty(value = "造成伤害")
    private Integer damage;
    @ApiModelProperty(value = "治疗")
    private Integer heal;
    @ApiModelProperty(value = "血量")
    private Integer hp;
    @ApiModelProperty(value = "位置")
    private String role;
    @ApiModelProperty(value = "队员ID")
    private BigInteger player;
    @ApiModelProperty(value = "英雄ID")
    private BigInteger champion;
    @ApiModelProperty(value = "金率")
    private String gold_rate;
    @ApiModelProperty(value = "相对黄金比例")
    private Integer relative_gold_diff;
    @ApiModelProperty(value = "伤害率")
    private String damage_taken_rate;
    @ApiModelProperty(value = "伤害最小")
    private String damage_taken_min;
    @ApiModelProperty(value = "伤害死亡")
    private String damage_taken_death;
    @ApiModelProperty(value = "伤害率")
    private String damage_rate;
    @ApiModelProperty(value = "伤害最小")
    private String damage_min;
    @ApiModelProperty(value = "伤害杀死")
    private String damage_kill;
    @ApiModelProperty(value = "召唤者法术")
    private String summoner_spells;

    /** 装备 */
    @ApiModelProperty(value = "装备")
    private List<LolTeamPlayersEquipmentVO> items;

}
