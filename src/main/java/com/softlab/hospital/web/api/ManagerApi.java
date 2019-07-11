package com.softlab.hospital.web.api;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.common.util.VerifyUtil;
import com.softlab.hospital.core.model.User;
import com.softlab.hospital.core.model.vo.UpList;
import com.softlab.hospital.service.ManagerService;
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
public class ManagerApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final ManagerService managerService;

    @Autowired
    public ManagerApi(UserService userService, ManagerService managerService) {
        this.userService = userService;
        this.managerService = managerService;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RestData update(@RequestBody UpList upList, HttpServletRequest request) throws HosExection{
        logger.info("updatePatientAndMoney upList: " + JsonUtil.getJsonString(upList));

        if (3 == VerifyUtil.verifyType(request) ){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        return managerService.updatePatientAndMoney(upList.getAl());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public RestData getAllUser(HttpServletRequest request){
        logger.info("getAllUser: ");

        if (3 == VerifyUtil.verifyType(request) ){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try{
            return new RestData(managerService.selectAllUser());
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }

    }

    @RequestMapping(value = "/user/{systemId}", method = RequestMethod.DELETE)
    public RestData deleteUser(@PathVariable Integer systemId, HttpServletRequest request){
        logger.info("deleteUser systemId = " + systemId);

        if (3 == VerifyUtil.verifyType(request) ){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try{
            return managerService.deleteUser(systemId);
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public RestData updateUser(@RequestBody User user, HttpServletRequest request){
        logger.info("updateUser user : " + JsonUtil.getJsonString(user));

        if (3 == VerifyUtil.verifyType(request) ){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try{
            return managerService.updateUser(user);
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public RestData insertUser(@RequestBody User user, HttpServletRequest request){
        logger.info("insertUser user : " + JsonUtil.getJsonString(user));

        if (3 == VerifyUtil.verifyType(request) ){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try{
            return managerService.insertUser(user);
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }


    @RequestMapping(value = "/doctor-all", method = RequestMethod.GET)
    public RestData getAllDcotor(HttpServletRequest request){
        logger.info("getAllDoctor : ");

        if (3 == VerifyUtil.verifyType(request) ){
            return new RestData(1, ErrorMessage.OPERATIOND_ENIED);
        }

        try{
            return new RestData(managerService.selectAllDoctor());
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }




}
