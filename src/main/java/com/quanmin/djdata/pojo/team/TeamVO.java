package com.quanmin.djdata.pojo.team;

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
 * @Description: 战队对象
 * @CreateDate: 2019-11-13 22:56
 * @ClassName: com.quanmin.djdata.pojo.teams.TeamsVO
 */
@Setter
@Getter
@ApiModel(value = "TeamVO", description = "战队对象")
public class TeamVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 8304832683128338393L;

    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "游戏类型")
    private Integer game_type;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "短名称")
    private String short_name;
    @ApiModelProperty(value = "英文名")
    private String name_english;
    @ApiModelProperty(value = "地区")
    private String local;
    @ApiModelProperty(value = "国家编码")
    private String country;
    @ApiModelProperty(value = "国家编码")
    private String area;
    @ApiModelProperty(value = "图标")
    private String logo;

    /** 请求参数 */
    @ApiModelProperty(value = "页码")
    private Integer offset;
    @ApiModelProperty(value = "每页条数")
    private Integer limit;

    /** 结果参数 */
    @ApiModelProperty(value = "战队别名")
    private List<TeamAliasVO> alias;

}
