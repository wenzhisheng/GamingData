package com.quanmin.djdata.pojo.dota2;

import com.quanmin.djdata.pojo.base.BaseVO;
import com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: ate
 * @Description: dota2赛果玩家对象
 * @CreateDate: 2019-11-14 16:22
 * @ClassName: com.quanmin.djdata.pojo.dota2.Dota2PlayersVO
 */
@Setter
@Getter
@ApiModel(value = "Dota2PlayersVO", description = "dota2赛果玩家对象")
public class Dota2PlayersVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 4529694525012504691L;

    /** match */
    @ApiModelProperty(value = "系列赛ID")
    private BigInteger finish_match_dota2_id;
    @ApiModelProperty(value = "比赛ID")
    private BigInteger account;
    @ApiModelProperty(value = "战队")
    private String team;
    @ApiModelProperty(value = "英雄")
    private BigInteger hero;
    @ApiModelProperty(value = "技能")
    private Integer kills;
    @ApiModelProperty(value = "死亡数")
    private Integer death;
    @ApiModelProperty(value = "助攻")
    private Integer assists;
    @ApiModelProperty(value = "最后一击")
    private Integer last_hits;
    @ApiModelProperty(value = "拒绝")
    private Integer denies;
    @ApiModelProperty(value = "金币")
    private Integer gold;
    @ApiModelProperty(value = "等级")
    private Integer level;
    @ApiModelProperty(value = "每分钟金币")
    private Integer gold_per_min;
    @ApiModelProperty(value = "每分钟经验")
    private Integer xp_per_min;
    @ApiModelProperty(value = "极限状态")
    private Integer ultimate_state;
    @ApiModelProperty(value = "战队0")
    private BigInteger item_0;
    @ApiModelProperty(value = "战队1")
    private BigInteger item_1;
    @ApiModelProperty(value = "战队2")
    private BigInteger item_2;
    @ApiModelProperty(value = "战队3")
    private BigInteger item_3;
    @ApiModelProperty(value = "战队4")
    private BigInteger item_4;
    @ApiModelProperty(value = "战队5")
    private BigInteger item_5;
    @ApiModelProperty(value = "英雄的伤害")
    private Integer hero_damage;
    @ApiModelProperty(value = "塔的伤害")
    private Integer tower_damage;
    @ApiModelProperty(value = "英雄愈合")
    private Integer hero_healing;
    @ApiModelProperty(value = "复位定时器")
    private Integer respawn_timer;
    @ApiModelProperty(value = "位置x")
    private BigDecimal position_x;
    @ApiModelProperty(value = "位置y")
    private BigDecimal position_y;
    @ApiModelProperty(value = "净值")
    private Integer net_worth;
    @ApiModelProperty(value = "升级的能力")
    private String ability_upgrades;

    /** 装备 */
    @ApiModelProperty(value = "装备")
    private List<LolTeamPlayersEquipmentVO> items;

}
