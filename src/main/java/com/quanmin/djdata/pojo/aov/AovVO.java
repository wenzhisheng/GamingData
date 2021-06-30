package com.quanmin.djdata.pojo.aov;

import com.quanmin.djdata.pojo.base.BaseVO;
import com.quanmin.djdata.pojo.finish.FinishSeriesVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: ate
 * @Description: 王者荣耀赛果对象
 * @CreateDate: 2019-11-14 16:17
 * @ClassName: com.quanmin.djdata.pojo.aov.AovVO
 */
@Setter
@Getter
@ApiModel(value = "AovVO", description = "王者荣耀赛果对象")
public class AovVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -1392599356650025418L;

    /** series */
    /*@ApiModelProperty(value = "战队ID")
    private BigInteger team_id;
    @ApiModelProperty(value = "得分")
    private Integer score;*/
    /** match */
    @ApiModelProperty(value = "系列赛ID")
    private BigInteger series_id;
    @ApiModelProperty(value = "比赛ID")
    private BigInteger match_id;
    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "游戏场次")
    private Integer game_no;
    /** match-flag */
    @ApiModelProperty(value = "一血")
    private Integer first_blood;
    @ApiModelProperty(value = "主宰")
    private Integer first_dominator;
    @ApiModelProperty(value = "一塔")
    private Integer first_tower;
    @ApiModelProperty(value = "暴君")
    private Integer first_tyrant;
    @ApiModelProperty(value = "五杀")
    private Integer five_kill;
    @ApiModelProperty(value = "十杀")
    private Integer ten_kill;
    @ApiModelProperty(value = "获胜")
    private Integer win;
    /** match-team_a-team_b */
    @ApiModelProperty(value = "战队A站点")
    private BigInteger team_a_site;
    @ApiModelProperty(value = "战队A的ID")
    private BigInteger team_a_id;
    @ApiModelProperty(value = "战队A的logo")
    private String team_a_logo;
    @ApiModelProperty(value = "战队A的名字")
    private String team_a_name;
    @ApiModelProperty(value = "战队B站点")
    private BigInteger team_b_site;
    @ApiModelProperty(value = "战队B的ID")
    private BigInteger team_b_id;
    @ApiModelProperty(value = "战队B的logo")
    private String team_b_logo;
    @ApiModelProperty(value = "战队B的名字")
    private String team_b_name;

    /** 结果参数 */
    @ApiModelProperty(value = "系列战队")
    private List<FinishSeriesVO> series;

}
