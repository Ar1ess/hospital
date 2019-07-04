package com.softlab.hospital.web.api;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.common.util.VerifyUtil;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.vo.UpList;
import com.softlab.hospital.service.ManagerService;
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

    private final ManagerService managerService;

    @Autowired
    public ManagerApi(ManagerService managerService) {
        this.managerService = managerService;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RestData update(@RequestBody UpList upList, HttpServletRequest request) throws HosExection{

        if (VerifyUtil.VerifyLogin(request)){
            return new RestData(1, ErrorMessage.PLEASE_RELOGIN);
        }

        return new RestData(managerService.updatePatientAndMoney(upList.getAl()));
    }




}
