package com.quanmin.djdata.pojo.series;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 14:13
 * @ClassName: com.quanmin.djdata.pojo.series.SeriesTeamVO
 */
@Setter
@Getter
@ApiModel(value = "SeriesTeamVO", description = "系列赛团队对象")
public class SeriesTeamVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -7869430476357675567L;

    @ApiModelProperty(value = "系列赛ID")
    private BigInteger series_id;
    @ApiModelProperty(value = "系列赛战队ID")
    private BigInteger team_id;
    @ApiModelProperty(value = "系列赛战队图标")
    private String team_logo;
    @ApiModelProperty(value = "系列赛战队名称")
    private String team_name;

    @ApiModelProperty(value = "系列赛战队类型")
    private String team_type;
    @ApiModelProperty(value = "系列赛战队比分")
    private int team_score;

//    /** match-flag-lol */
//    @ApiModelProperty(value = "一血")
//    private String first_blood;
//    @ApiModelProperty(value = "一塔")
//    private String first_tower;
//    @ApiModelProperty(value = "杀五个")
//    private String five_kills;
//    @ApiModelProperty(value = "杀十个")
//    private String ten_kills;
//    @ApiModelProperty(value = "首杀小龙")
//    private String first_drakes;
//    @ApiModelProperty(value = "首个男爵")
//    private String first_nashors;
//    @ApiModelProperty(value = "首个水晶")
//    private String first_inhibitor;
//    @ApiModelProperty(value = "先锋")
//    private String herald;
//    /** match-flag-aov */
//    @ApiModelProperty(value = "一血")
//    private Integer first_bloods;
//    @ApiModelProperty(value = "主宰")
//    private Integer first_dominator;
//    @ApiModelProperty(value = "一塔")
//    private Integer first_towers;
//    @ApiModelProperty(value = "暴君")
//    private Integer first_tyrant;
//    @ApiModelProperty(value = "五杀")
//    private Integer five_kill;
//    @ApiModelProperty(value = "十杀")
//    private Integer ten_kill;
//    @ApiModelProperty(value = "获胜")
//    private Integer win;

}
