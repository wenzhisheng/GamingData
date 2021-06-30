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
 * @Description: 英雄联盟赛果对象
 * @CreateDate: 2019-11-14 16:19
 * @ClassName: com.quanmin.djdata.pojo.lol.LolVO
 */
@Setter
@Getter
@ApiModel(value = "LolVO", description = "英雄联盟赛果对象")
public class LolVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 4211312621090830772L;

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
    @ApiModelProperty(value = "游戏场次")
    private Integer game_no;
    @ApiModelProperty(value = "赛程名称")
    private String course_name;
    @ApiModelProperty(value = "蓝方黄金比分")
    private Integer blue_gold_diff;
    @ApiModelProperty(value = "开始时间")
    private String start_time;
    @ApiModelProperty(value = "创建时间")
    private String createdAt;
    @ApiModelProperty(value = "游戏时间")
    private Integer game_time;
    @ApiModelProperty(value = "比赛是否完成")
    private Boolean finished;
    @ApiModelProperty(value = "比赛是否有效")
    private Boolean valid;
    @ApiModelProperty(value = "获胜队伍")
    private String winner;
    /** match-flag */
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
    /** match-blue */
    @ApiModelProperty(value = "蓝色方战队名称")
    private String blue_team_name;
    @ApiModelProperty(value = "蓝色方战队标识")
    private String blue_team_tag;
    @ApiModelProperty(value = "蓝色方战队logo")
    private String blue_team_logo;
    @ApiModelProperty(value = "蓝色方战队ID")
    private BigInteger blue_team_id;
    @ApiModelProperty(value = "蓝色方战队得分")
    private int blue_team_score;
    /** match-red */
    @ApiModelProperty(value = "红色方战队名称")
    private String red_team_name;
    @ApiModelProperty(value = "红色方战队标识")
    private String red_team_tag;
    @ApiModelProperty(value = "红色方战队logo")
    private String red_team_logo;
    @ApiModelProperty(value = "红色方战队ID")
    private BigInteger red_team_id;
    @ApiModelProperty(value = "红色方战队得分")
    private int red_team_score;

    /** 结果参数-战队 */
    @ApiModelProperty(value = "蓝队")
    private List<LolTeamVO> blue;
    @ApiModelProperty(value = "红队")
    private List<LolTeamVO> red;

}
