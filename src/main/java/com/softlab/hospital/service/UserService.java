package com.softlab.hospital.service;

import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.core.model.User;

import java.util.Map;

/**
 * Created by LiXiwen on 2019/7/3 18:01.
 **/
public interface UserService {
    Map<String, Object> postLogin(User user) throws HosExection;
}
