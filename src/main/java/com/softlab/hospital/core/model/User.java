package com.softlab.hospital.core.model;

import lombok.Data;


/**
 * Created by LiXiwen on 2019/7/3 17:25.
 **/
@Data
public class User {
    /**
     * 系统自增id
     */
    private Integer systemId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户名
     */
    private String userName;

    /**
     * token
     */
    private String userToken;

    /**
     * 用户类型
     * 0 = 市场人员
     * 1 = 管理员
     * 2 = 超级管理员
     */
    private Integer userType;

}
