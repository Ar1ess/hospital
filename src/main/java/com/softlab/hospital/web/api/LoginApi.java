package com.softlab.hospital.web.api;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.core.model.User;
import com.softlab.hospital.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by LiXiwen on 2019/7/3 17:57.
 * 178415Aa
 **/
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class LoginApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @Autowired
    public LoginApi(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestData postLogin(@RequestBody User user) {
        logger.info("POST postLogin : " + JsonUtil.getJsonString(user));

        if(null == user.getUserId() || 1 > user.getUserId().length() ||
                null == user.getUserPassword()){
            return new RestData(1, ErrorMessage.PARAMATER_ERROR);
        }
        try {
            Map<String, Object> data = userService.postLogin(user);
            return new RestData(data);
        } catch (HosExection e) {
            return new RestData(1, e.getMessage());
        }
    }


}
