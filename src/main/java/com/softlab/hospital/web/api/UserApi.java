package com.softlab.hospital.web.api;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.common.util.UploadUtil;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.vo.DoctorVo;
import com.softlab.hospital.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    public RestData updateDoctor(@RequestPart(value = "file", required = false) MultipartFile[] files, @RequestPart(value = "doctor") Doctor doctor) throws Exception {
        logger.info("PUT updateDoctor : " + JsonUtil.getJsonString(doctor));

        try {
            return userService.updateDoctor(files, doctor);
        } catch (Exception e) {
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST)
    public RestData insertDoctor(@RequestPart(value = "file") MultipartFile[] files, @RequestPart(value = "doctor", required = false) Doctor doctor)  {
        logger.info("insert doctor: " + JsonUtil.getJsonString(doctor));

        logger.info("date:" + doctor.getDocDate());
        logger.info("files length: " + files.length);
        try {
            return userService.insertDoctor(files, doctor);
        } catch (Exception e) {
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
    public RestData searchDoctor(@RequestBody DoctorVo doctorVo){
        logger.info("searchDoctor: " + JsonUtil.getJsonString(doctorVo));

        try {
            return new RestData(userService.selectByContidion(doctorVo));
        } catch (HosExection e){
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/downFile", method = RequestMethod.GET)
    public String downFile(@RequestParam("fileName") String fileName, HttpServletResponse response){
        //String fileId = "5d2c15fd7974d22c8046ae78";//这里可以通过参数取代
        logger.info("download : " + fileName);

        String path = UploadUtil.uploadDir + fileName;

        File imageFile = new File (path);
        if (!imageFile.exists()){
            return "未查询到此文件";
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        response.setContentType("application/octet-stream");

        try{
            FileInputStream fis = new FileInputStream(path);
            byte[] content = new byte[fis.available()];
            fis.read(content);
            ServletOutputStream sos = response.getOutputStream();
            sos.write(content);
            sos.flush();
            sos.close();
            fis.close();
        } catch (Exception e) {
            return "下载失败";
        }
        return null;
    }

}
