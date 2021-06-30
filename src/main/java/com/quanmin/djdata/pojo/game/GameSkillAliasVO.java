package com.quanmin.djdata.pojo.game;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: 游戏技能别名对象
 * @CreateDate: 2019-11-13 15:11
 * @ClassName: com.quanmin.djdata.pojo.game.GameSkillAliasVO
 */
@Setter
@Getter
@ApiModel(value = "GameSkillAliasVO", description = "游戏技能别名对象")
public class GameSkillAliasVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -5393088114040622452L;

    @ApiModelProperty(value = "游戏技能ID")
    private BigInteger game_skill_id;
    @ApiModelProperty(value = "技能名称")
    private String name;
    @ApiModelProperty(value = "技能编码")
    private String code;

}
