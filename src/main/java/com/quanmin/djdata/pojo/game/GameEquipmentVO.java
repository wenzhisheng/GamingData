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
 * @Description: TODO
 * @CreateDate: 2019-11-13 15:47
 * @ClassName: com.quanmin.djdata.pojo.game.GameEquipmentVO
 */
@Setter
@Getter
@ApiModel(value = "GameEquipmentVO", description = "游戏装备对象")
public class GameEquipmentVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 863247596597997798L;

    @ApiModelProperty(value = "游戏ID")
    private BigInteger game_id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "别名")
    private String alias;
    @ApiModelProperty(value = "信息")
    private String info;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "英文名称")
    private String name_english;
    @ApiModelProperty(value = "图标")
    private String icon;

    /** 请求参数 */
    @ApiModelProperty(value = "页码")
    private Integer offset;
    @ApiModelProperty(value = "每页条数")
    private Integer limit;

}
