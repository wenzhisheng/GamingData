package com.quanmin.djdata.pojo.team;

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
 * @CreateDate: 2019-11-14 13:10
 * @ClassName: com.quanmin.djdata.pojo.team.TeamRankVO
 */
@Setter
@Getter
@ApiModel(value = "TeamRankVO", description = "战队排名对象")
public class TeamRankVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 2732274988701937424L;

    @ApiModelProperty(value = "团队ID")
    private BigInteger team_id;
    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "短名称")
    private String short_name;
    @ApiModelProperty(value = "排名")
    private Integer ranks;
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /** 请求参数 */
    @ApiModelProperty(value = "页码")
    private Integer offset;
    @ApiModelProperty(value = "每页条数")
    private Integer limit;

}
