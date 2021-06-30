package com.quanmin.djdata.pojo.csgo;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: csgo赛果玩家对象
 * @CreateDate: 2019-11-14 16:21
 * @ClassName: com.quanmin.djdata.pojo.csgo.CsgoPlayersVO
 */
@Setter
@Getter
@ApiModel(value = "CsgoPlayersVO", description = "csgo赛果玩家对象")
public class CsgoPlayersVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 2443866449705167387L;

    /** match */
    @ApiModelProperty(value = "csgo赛果ID")
    private BigInteger finish_match_csgo_id;
    @ApiModelProperty(value = "名字")
    private String name;
    @ApiModelProperty(value = "击杀")
    private String kills;
    @ApiModelProperty(value = "爆头")
    private String hs;
    @ApiModelProperty(value = "助攻")
    private String assists;
    @ApiModelProperty(value = "死亡")
    private String deaths;
    @ApiModelProperty(value = "击杀/死亡比")
    private String kdratio;
    @ApiModelProperty(value = "击杀死亡差")
    private String kddiff;
    @ApiModelProperty(value = "全场平均生命值")
    private String adr;
    @ApiModelProperty(value = "浮动比")
    private String fkdiff;
    @ApiModelProperty(value = "比率")
    private String rating;

}
