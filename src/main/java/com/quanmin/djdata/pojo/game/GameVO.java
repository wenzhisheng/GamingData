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
 * @Description: 游戏对象
 * @CreateDate: 2019-11-11 16:35
 * @ClassName: com.quanmin.djdata.pojo.games.GamesVO
 */
@Setter
@Getter
@ApiModel(value = "GamesVO", description = "游戏对象")
public class GameVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -7433929818256466533L;

    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "短名称")
    private String short_name;
    @ApiModelProperty(value = "编码")
    private String code;

}
