package com.softlab.hospital.service.impl;

import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.core.mapper.DoctorMapper;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiXiwen on 2019/7/3 18:01.
 **/
@Service
public class ManagerServiceImpl implements ManagerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DoctorMapper doctorMapper;

    @Autowired
    public ManagerServiceImpl(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    @Override
    public RestData updatePatientAndMoney(ArrayList<Integer> al) throws HosExection{
        logger.info("updatePatientAndMoney" + JsonUtil.getJsonString(al));
        for (Integer a : al){
            int success = doctorMapper.updatePatientAndMoney(a);
            if (0 < success){
                continue;
            } else{
                throw new HosExection("修改失败");
            }
        }
        return new RestData(0,"批量维护成功");
    }
}