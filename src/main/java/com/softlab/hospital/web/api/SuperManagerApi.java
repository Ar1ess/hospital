package com.softlab.hospital.web.api;

import com.softlab.hospital.service.SuperManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiXiwen on 2019/7/3 17:58.
 **/
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class SuperManagerApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SuperManagerService superManagerService;

    @Autowired
    public SuperManagerApi(SuperManagerService superManagerService) {
        this.superManagerService = superManagerService;
    }



}
