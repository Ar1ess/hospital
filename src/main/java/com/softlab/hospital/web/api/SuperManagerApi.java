package com.softlab.hospital.web.api;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.common.util.VerifyUtil;
import com.softlab.hospital.core.model.User;
import com.softlab.hospital.core.model.vo.DoctorVo;
import com.softlab.hospital.service.ManagerService;
import com.softlab.hospital.service.SuperManagerService;
import com.softlab.hospital.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LiXiwen on 2019/7/3 17:58.
 **/
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class SuperManagerApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SuperManagerService superManagerService;
    private final ManagerService managerService;
    private final UserService userService;

    @Autowired
    public SuperManagerApi(SuperManagerService superManagerService, ManagerService managerService, UserService userService) {
        this.superManagerService = superManagerService;
        this.managerService = managerService;
        this.userService = userService;
    }

    @RequestMapping(value = "/super-select", method = RequestMethod.GET)
    public RestData selectDoctorByCondition(@RequestBody DoctorVo doctorVo, HttpServletRequest request) {
        logger.info("GET selectDoctorByCondition : " + JsonUtil.getJsonString(doctorVo));

        if (1 != VerifyUtil.verifyType(request)){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try {
            return superManagerService.selectDoctorByCondition(doctorVo);
        } catch (HosExection e) {
            return new RestData(1, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public RestData postAddManager(@RequestBody User user, HttpServletRequest request) {
        logger.info("POST postAddManager : " + JsonUtil.getJsonString(user));

        if (1 != VerifyUtil.verifyType(request)){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try {
            return superManagerService.postAddManager(user);
        } catch (HosExection e) {
            return new RestData(1, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/manager/{systemId}", method = RequestMethod.DELETE)
    public RestData deleteManagerById(@PathVariable Integer systemId, HttpServletRequest request) {
        logger.info("DELETE deleteManager : " + systemId);

        if (1 != VerifyUtil.verifyType(request)){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try{
            return superManagerService.deleteManager(systemId);
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/manager", method = RequestMethod.PUT)
    public RestData updateManager(@RequestBody User user, HttpServletRequest request) {
        logger.info("DELETE deleteManager : " + JsonUtil.getJsonString(user));

        if (1 != VerifyUtil.verifyType(request)){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try{
            return superManagerService.updateManager(user);
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/manager-all", method = RequestMethod.GET)
    public RestData selectAllManager(HttpServletRequest request) {
        logger.info("GET selectAllManager : ");

        if (1 != VerifyUtil.verifyType(request)){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try{
            return superManagerService.selectAllManager();
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/manager-part", method = RequestMethod.POST)
    public RestData selectAllManager(@RequestBody User user, HttpServletRequest request) {
        logger.info("GET selectPartManager : ");

        if (1 != VerifyUtil.verifyType(request)){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try{
            return superManagerService.selectPartManager(user);
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

}
