package com.quanmin.djdata.pojo.lol;

import com.quanmin.djdata.pojo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: 英雄联盟赛果战队英雄对象
 * @CreateDate: 2019-11-20 16:19
 * @ClassName: com.quanmin.djdata.pojo.lol.LolTeamRoleVO
 */
@Setter
@Getter
@ApiModel(value = "LolTeamRoleVO", description = "英雄联盟赛果战队英雄对象")
public class LolTeamRoleVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 4215212621090830772L;

    @ApiModelProperty(value = "lol赛果ID")
    private BigInteger finish_match_lol_id;
    @ApiModelProperty(value = "赛果ID")
    private BigInteger finish_match_id;
    @ApiModelProperty(value = "英雄ID")
    private BigInteger role_id;
    @ApiModelProperty(value = "英雄logo")
    private String role_logo;
    @ApiModelProperty(value = "英雄名称")
    private String role_name;
    @ApiModelProperty(value = "英雄英文名")
    private String role_name_english;
    @ApiModelProperty(value = "英雄别名")
    private String role_alias;
    @ApiModelProperty(value = "英雄信息")
    private String role_info;
    @ApiModelProperty(value = "英雄描述")
    private String role_description;

}
