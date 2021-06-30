package com.quanmin.djdata.pojo.csgo;

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
 * @Description: csgo赛果对象
 * @CreateDate: 2019-11-14 16:21
 * @ClassName: com.quanmin.djdata.pojo.csgo.CsgoVO
 */
@Setter
@Getter
@ApiModel(value = "CsgoVO", description = "csgo赛果对象")
public class CsgoVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 2443833449705167387L;

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
    @ApiModelProperty(value = "第几局")
    private Integer match_index;
    @ApiModelProperty(value = "开始时间")
    private String start_time;
    @ApiModelProperty(value = "是否结束")
    private boolean finished;
    @ApiModelProperty(value = "使用地图")
    private String map;
    @ApiModelProperty(value = "左队得分")
    private Integer left_score;
    @ApiModelProperty(value = "右队得分")
    private Integer right_score;
    @ApiModelProperty(value = "手枪局获胜队伍（左、右）")
    private String flag_r1;
    @ApiModelProperty(value = "领先5回合的队伍（左、右）")
    private String flag_w5;
    @ApiModelProperty(value = "第16局获胜队伍（左、右）")
    private String flag_r16;
    @ApiModelProperty(value = "左队回合历史")
    private String left_round_history;
    @ApiModelProperty(value = "右队回合历史")
    private String right_round_history;
    @ApiModelProperty(value = "加时赛左队得分")
    private Integer overtime_left_score;
    @ApiModelProperty(value = "加时赛右队得分")
    private Integer overtime_right_score;
    /** match-left_team */
    @ApiModelProperty(value = "左队名字")
    private String left_team_name;
    @ApiModelProperty(value = "左队标识")
    private String left_team_tag;
    @ApiModelProperty(value = "左队logo")
    private String left_team_logo;
    @ApiModelProperty(value = "左队ID")
    private BigInteger left_team_id;
    @ApiModelProperty(value = "左队得分")
    private BigInteger left_team_score;
    /** match-right_team */
    @ApiModelProperty(value = "右队名字")
    private String right_team_name;
    @ApiModelProperty(value = "右队标识")
    private String right_team_tag;
    @ApiModelProperty(value = "右队logo")
    private String right_team_logo;
    @ApiModelProperty(value = "右队ID")
    private BigInteger right_team_id;
    @ApiModelProperty(value = "右队得分")
    private BigInteger right_team_score;
    /** match-first_half */
    @ApiModelProperty(value = "上半场左队得分")
    private BigInteger first_half_left_score;
    @ApiModelProperty(value = "上半场左队角色（ct反恐精英，t恐怖份子）")
    private String first_half_left_side;
    @ApiModelProperty(value = "上半场右队得分")
    private BigInteger first_half_right_score;
    @ApiModelProperty(value = "上半场右队角色（ct反恐精英，t恐怖份子）")
    private String first_half_right_side;
    /** match-second_half */
    @ApiModelProperty(value = "下半场左队得分")
    private BigInteger second_half_left_score;
    @ApiModelProperty(value = "下半场左队角色（ct反恐精英，t恐怖份子）")
    private String second_half_left_side;
    @ApiModelProperty(value = "下半场右队得分")
    private BigInteger second_half_right_score;
    @ApiModelProperty(value = "下半场右队角色（ct反恐精英，t恐怖份子）")
    private String second_half_right_side;

    /** 结果参数 */
    @ApiModelProperty(value = "左队玩家")
    private List<CsgoPlayersVO> left_players;
    @ApiModelProperty(value = "右队玩家")
    private List<CsgoPlayersVO> right_players;

}
