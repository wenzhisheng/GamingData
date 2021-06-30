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
 * @Description: 战队别名对象
 * @CreateDate: 2019-11-14 12:08
 * @ClassName: com.quanmin.djdata.pojo.team.TeamAliasVO
 */
@Setter
@Getter
@ApiModel(value = "TeamAliasVO", description = "战队别名对象")
public class TeamAliasVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -2006137559891454963L;

    @ApiModelProperty(value = "游戏ID")
    private BigInteger team_id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "编码")
    private String code;

}
