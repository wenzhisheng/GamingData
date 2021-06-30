package com.quanmin.djdata.pojo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: ate
 * @Description: 基础对象
 * @CreateDate: 2019-11-11 12:22
 * @ClassName: com.quanmin.djdata.pojo.base.BaseVO
 */
@Getter
@Setter
@ApiModel(value = "BaseVO", description = "基础对象")
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 2085914884131758688L;

    /** ID */
    @ApiModelProperty(value = "ID")
    private BigInteger id;

}
