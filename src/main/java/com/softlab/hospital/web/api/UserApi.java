package com.softlab.hospital.web.api;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.common.util.TokenUtil;
import com.softlab.hospital.common.util.VerifyUtil;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.User;
import com.softlab.hospital.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.NoRouteToHostException;


/**
 * Created by LiXiwen on 2019/7/3 17:59.
 **/
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class UserApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add-doctor", method = RequestMethod.POST)
    public RestData postAddDoctor(@RequestBody Doctor doctor, HttpServletRequest request) {
        logger.info("POST postAddDoctor" + JsonUtil.getJsonString(doctor));
        if(!VerifyUtil.VerifyLogin(request)){
            return new RestData(2, ErrorMessage.PLEASE_RELOGIN);
        }
        /*try {

        } catch (HosExection e) {

        }*/
        return null;
    }
}
