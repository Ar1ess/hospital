package com.softlab.hospital.web.api;

import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.common.util.UploadUtil;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    public RestData deleteDoctor(@PathVariable Integer systemId) {
        logger.info("delete doctor systemId = " + systemId);

        try{
            return userService.deleteBySystemId(systemId);
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.PUT)
    public RestData updateDoctor(@RequestBody Doctor doctor) {
        logger.info("update doctor: " + JsonUtil.getJsonString(doctor));

        try{
            return userService.updateDoctor(doctor);
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }

    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST)
    public RestData insertDoctor(@RequestParam("file") MultipartFile file, @RequestBody Doctor doctor) throws Exception {
        logger.info("insert doctor: " + JsonUtil.getJsonString(doctor));

        //文件上传
        String fileName = UploadUtil.upload(file);
        logger.info("filename: " + fileName);
        doctor.setDocFile(fileName);

        //日期上传
        Date d = new Date();
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String s = f.format(d);
        doctor.setDocDate(s);
        logger.info("date:" + s);

        try{
            return userService.insertDoctor(doctor);
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public RestData getAllDoctor(@RequestParam(value = "userId") String userId){
        logger.info("getAllDoctorByUserId userId = "  + userId);

        try {
            return new RestData(userService.selectAll(userId));
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public RestData searchDoctor(@RequestBody Doctor doctor){
        logger.info("searchDoctor: " + JsonUtil.getJsonString(doctor));

        try {
            return new RestData(userService.selectByContidion(doctor));
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

}
