package com.quanmin.djdata.pojo.dota2;

import com.quanmin.djdata.pojo.base.BaseVO;
import com.quanmin.djdata.pojo.lol.LolTeamRoleVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: ate
 * @Description: dota2赛果对象
 * @CreateDate: 2019-11-14 16:22
 * @ClassName: com.quanmin.djdata.pojo.dota2.DotaVO
 */
@Setter
@Getter
@ApiModel(value = "Dota2VO", description = "dota2赛果对象")
public class Dota2VO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 4529698245012504691L;

    /** series */
    @ApiModelProperty(value = "战队ID")
    private BigInteger team_id;
    @ApiModelProperty(value = "得分")
    private Integer score;
    /** match */
    @ApiModelProperty(value = "系列赛ID")
    private BigInteger series_id;
    @ApiModelProperty(value = "比赛ID")
    private BigInteger match_id;
    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "赛程名称")
    private String course_name;
    @ApiModelProperty(value = "开始时间")
    private String start_time;
    @ApiModelProperty(value = "是否结束")
    private boolean finished;
    @ApiModelProperty(value = "是否结束")
    private boolean valid;
    @ApiModelProperty(value = "比赛总时长")
    private BigInteger game_time;
    @ApiModelProperty(value = "游戏模式（0为正规比赛）")
    private BigInteger game_mode;
    @ApiModelProperty(value = "优先bp队伍")
    private String first_pick;
    @ApiModelProperty(value = "一血发生时间（秒）")
    private Integer first_blood_time;
    @ApiModelProperty(value = "获胜队伍")
    private String win_side;
    @ApiModelProperty(value = "一血队伍")
    private String first_blood;
    @ApiModelProperty(value = "首先五杀队伍")
    private String five_kills;
    @ApiModelProperty(value = "首先十杀队伍")
    private String ten_kills;
    @ApiModelProperty(value = "十杀时领先人头数")
    private Integer ten_kills_score;
    @ApiModelProperty(value = "一塔队伍")
    private String first_tower;
    @ApiModelProperty(value = "天辉经济领先（可为负数）")
    private Integer radiant_gold_lead;
    /*@ApiModelProperty(value = "天辉picks英雄")
    private String radiant_picks;
    @ApiModelProperty(value = "天辉ban英雄")
    private String radiant_bans;
    @ApiModelProperty(value = "夜魇pick英雄")
    private String dire_picks;
    @ApiModelProperty(value = "夜魇ban英雄")
    private String dire_bans;*/
    @ApiModelProperty(value = "数据延迟（单位毫秒）")
    private BigInteger stream_delay_s;
    @ApiModelProperty(value = "肉山复活倒计时（秒）")
    private BigInteger roshan_respawn_timer;
    /** radiant */
    @ApiModelProperty(value = "天辉击杀数")
    private BigInteger radiant_score;
    @ApiModelProperty(value = "天辉无效击杀数（自杀）")
    private BigInteger radiant_invalid_score;
    @ApiModelProperty(value = "天辉经验值")
    private BigInteger radiant_xp;
    @ApiModelProperty(value = "天辉经济值")
    private BigInteger radiant_gold;
    @ApiModelProperty(value = "天辉塔状态")
    private String radiant_towers;
    @ApiModelProperty(value = "天辉兵营状态")
    private String radiant_barracks;
    @ApiModelProperty(value = "天辉补刀")
    private Integer radiant_last_hits;
    @ApiModelProperty(value = "天辉总伤害输出")
    private Integer radiant_damage;
    /** dire */
    @ApiModelProperty(value = "夜魇击杀数")
    private BigInteger dire_score;
    @ApiModelProperty(value = "夜魇无效击杀数（自杀）")
    private BigInteger dire_invalid_score;
    @ApiModelProperty(value = "夜魇经验值")
    private BigInteger dire_xp;
    @ApiModelProperty(value = "夜魇经济值")
    private BigInteger dire_gold;
    @ApiModelProperty(value = "夜魇塔状态")
    private String dire_towers;
    @ApiModelProperty(value = "夜魇兵营状态")
    private String dire_barracks;
    @ApiModelProperty(value = "夜魇补刀")
    private Integer dire_last_hits;
    @ApiModelProperty(value = "夜魇总伤害输出")
    private Integer dire_damage;
    /** radiant_team */
    @ApiModelProperty(value = "天辉名称")
    private String radiant_team_name;
    @ApiModelProperty(value = "天辉编写")
    private String radiant_team_tag;
    @ApiModelProperty(value = "天辉logo")
    private String radiant_team_logo;
    @ApiModelProperty(value = "天辉队伍ID")
    private BigInteger radiant_team_id;
    @ApiModelProperty(value = "天辉得分")
    private BigInteger radiant_team_score;
    /** dire_team */
    @ApiModelProperty(value = "夜魇名称")
    private String dire_team_name;
    @ApiModelProperty(value = "夜魇编写")
    private String dire_team_tag;
    @ApiModelProperty(value = "夜魇logo")
    private String dire_team_logo;
    @ApiModelProperty(value = "夜魇队伍ID")
    private BigInteger dire_team_id;
    @ApiModelProperty(value = "夜魇得分")
    private BigInteger dire_team_score;

    /** 结果参数 */
    @ApiModelProperty(value = "玩家列表")
    private List<Dota2PlayersVO> players;
    @ApiModelProperty(value = "天辉选择英雄")
    private List<LolTeamRoleVO> radiant_picks;
    @ApiModelProperty(value = "天辉禁用英雄")
    private List<LolTeamRoleVO> radiant_bans;
    @ApiModelProperty(value = "夜魇选择英雄")
    private List<LolTeamRoleVO> Dire_picks;
    @ApiModelProperty(value = "夜魇禁用英雄")
    private List<LolTeamRoleVO> Dire_bans;

}
