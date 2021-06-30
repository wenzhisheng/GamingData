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
 * @Description: 英雄联盟赛果战队对象
 * @CreateDate: 2019-11-14 16:19
 * @ClassName: com.quanmin.djdata.pojo.lol.LolVO
 */
@Setter
@Getter
@ApiModel(value = "LolTeamVO", description = "英雄联盟赛果战队对象")
public class LolTeamVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 4215212621090830772L;

    @ApiModelProperty(value = "lol赛果ID")
    private BigInteger finish_match_lol_id;
    @ApiModelProperty(value = "战队ID")
    private BigInteger team_id;
    @ApiModelProperty(value = "击杀")
    private Integer kills;
    @ApiModelProperty(value = "死亡")
    private Integer deaths;
    @ApiModelProperty(value = "助攻")
    private Integer assists;
    @ApiModelProperty(value = "经济")
    private Integer gold;
    @ApiModelProperty(value = "补刀")
    private Integer cs;
    @ApiModelProperty(value = "小龙")
    private Integer drakes;
    @ApiModelProperty(value = "等级")
    private Integer level;
    @ApiModelProperty(value = "伤害")
    private Integer damage_taken;
    @ApiModelProperty(value = "造成伤害")
    private Integer damage;
    @ApiModelProperty(value = "男爵")
    private Integer nahsor_barons;
    @ApiModelProperty(value = "水晶")
    private Integer inhibitors;
    @ApiModelProperty(value = "塔")
    private Integer towers;

    /** 选择-禁用 */
    @ApiModelProperty(value = "蓝队")
    private List<LolTeamRoleVO> picks;
    @ApiModelProperty(value = "红队")
    private List<LolTeamRoleVO> bans;
    /** 玩家 */
    @ApiModelProperty(value = "玩家")
    private List<LolTeamPlayersVO> players;

}
