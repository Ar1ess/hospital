package com.softlab.hospital.web.api;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.common.util.VerifyUtil;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.service.UserService;
import org.apache.tomcat.jni.Multicast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


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

    @RequestMapping(value = "/doctor/{systemId}", method = RequestMethod.DELETE)
    public RestData deleteDoctor(@PathVariable Integer systemId, HttpServletRequest request) throws HosExection {
        logger.info("delete doctor systemId = " + systemId);

        if (!VerifyUtil.VerifyLogin(request)){
            return new RestData(1, ErrorMessage.PLEASE_RELOGIN);
        }

        return userService.deleteBySystemId(systemId);
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.PUT)
    public RestData updateDoctor(@RequestBody Doctor doctor, HttpServletRequest request) throws HosExection{
        logger.info("update doctor: " + JsonUtil.getJsonString(doctor));

        if (VerifyUtil.VerifyLogin(request)){
            return new RestData(1, ErrorMessage.PLEASE_RELOGIN);
        }

        return userService.updateDoctor(doctor);
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST)
    public RestData insertDoctor(@RequestParam MultipartFile file, @RequestBody Doctor doctor, HttpServletRequest request) throws HosExection{
        logger.info("insert doctor: " + JsonUtil.getJsonString(doctor));

        if (VerifyUtil.VerifyLogin(request)){
            return new RestData(1, ErrorMessage.PLEASE_RELOGIN);
        }

        return userService.insertDoctor(doctor);
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public RestData getAllDoctor(HttpServletRequest request){
        logger.info("getAllDoctor: " );

        if (VerifyUtil.VerifyLogin(request)){
            return new RestData(1, ErrorMessage.PLEASE_RELOGIN);
        }

        return new RestData(userService.selectAll());
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public RestData searchDoctor(@RequestBody Doctor doctor, HttpServletRequest request){
        logger.info("searchDoctor: " + JsonUtil.getJsonString(doctor));

        if (VerifyUtil.VerifyLogin(request)){
            return new RestData(1, ErrorMessage.PLEASE_RELOGIN);
        }

        return new RestData(userService.selectByContidion(doctor));
    }
}
