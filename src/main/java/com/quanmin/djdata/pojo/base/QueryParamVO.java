package com.quanmin.djdata.pojo.base;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: ate
 * @Description: 查询参数对象
 * @CreateDate: 2019-11-22 13:45
 * @ClassName: com.quanmin.djdata.pojo.base.QueryParamVO
 */
@Getter
@Setter
@ApiModel(value = "QueryParamVO", description = "查询参数对象")
public class QueryParamVO implements Serializable {

    private static final long serialVersionUID = -8613538542172054042L;



}
