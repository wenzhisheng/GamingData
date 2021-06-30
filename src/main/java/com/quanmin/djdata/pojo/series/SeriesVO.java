package com.quanmin.djdata.pojo.series;

import com.quanmin.djdata.pojo.aov.AovVO;
import com.quanmin.djdata.pojo.base.BaseVO;
import com.quanmin.djdata.pojo.csgo.CsgoVO;
import com.quanmin.djdata.pojo.dota2.Dota2VO;
import com.quanmin.djdata.pojo.lol.LolVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 13:01
 * @ClassName: com.quanmin.djdata.pojo.series.SeriesVO
 */
@Setter
@Getter
@ApiModel(value = "SeriesVO", description = "系列赛对象")
public class SeriesVO<T> extends BaseVO implements Serializable {

    private static final long serialVersionUID = -3020279129255038469L;

    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "联赛ID")
    private BigInteger league_id;
    @ApiModelProperty(value = "赛区ID")
    private BigInteger area_id;
    @ApiModelProperty(value = "赛事ID")
    private BigInteger course_id;
    @ApiModelProperty(value = "赛事名称")
    private String course_name;
    @ApiModelProperty(value = "状态（1：未开始，2：进行中，3：结束）")
    private Integer status;
    @ApiModelProperty(value = "赛季信息")
    private String season_info;
    @ApiModelProperty(value = "描述简介")
    private String description;
    @ApiModelProperty(value = "开始时间")
    private BigInteger start_time;
    @ApiModelProperty(value = "结束时间")
    private BigInteger end_time;

    /** 查询参数 */
    @ApiModelProperty(value = "开始时间")
    private BigInteger begin_time;
    /** 请求参数 */
    @ApiModelProperty(value = "页码")
    private Integer offset;
    @ApiModelProperty(value = "每页条数")
    private Integer limit;

    /** 结果参数 */
    @ApiModelProperty(value = "系列赛战队")
    private List<SeriesTeamVO> teams;
    /** lol-flag
    @ApiModelProperty(value = "一血")
    private String first_blood;
    @ApiModelProperty(value = "一塔")
    private String first_tower;
    @ApiModelProperty(value = "杀五个")
    private String five_kills;
    @ApiModelProperty(value = "杀十个")
    private String ten_kills;
    @ApiModelProperty(value = "首杀小龙")
    private String first_drakes;
    @ApiModelProperty(value = "首个男爵")
    private String first_nashors;
    @ApiModelProperty(value = "首个水晶")
    private String first_inhibitor;
    @ApiModelProperty(value = "先锋")
    private String herald;
    * aov-flag
    @ApiModelProperty(value = "一血")
    private Integer first_blood;
    @ApiModelProperty(value = "一塔")
    private Integer first_tower;
    @ApiModelProperty(value = "主宰")
    private Integer first_dominator;
    @ApiModelProperty(value = "暴君")
    private Integer first_tyrant;
    @ApiModelProperty(value = "五杀")
    private Integer five_kill;
    @ApiModelProperty(value = "十杀")
    private Integer ten_kill;
    @ApiModelProperty(value = "获胜")
    private Integer win;
    * dota2
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
    private String first_tower;*/

    @ApiModelProperty(value = "多少局")
    private String game_no;
    @ApiModelProperty(value = "游戏时间")
    private String game_time;

    /*@ApiModelProperty(value = "系列赛赛果lol")
    private List<LolVO> lolList;
    @ApiModelProperty(value = "系列赛赛果aov")
    private List<AovVO> aovList;
    @ApiModelProperty(value = "系列赛赛果doat2")
    private List<Dota2VO> dota2List;
    @ApiModelProperty(value = "系列赛赛果csgo")
    private List<CsgoVO> csgoList;*/
    @ApiModelProperty(value = "系列赛赛果列表")
    private List<T> list;

}
