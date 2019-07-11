package com.softlab.hospital.service.impl;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.core.mapper.DoctorMapper;
import com.softlab.hospital.core.mapper.UserMapper;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.User;
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
    private final UserMapper userMapper;

    @Autowired
    public ManagerServiceImpl(DoctorMapper doctorMapper, UserMapper userMapper) {
        this.doctorMapper = doctorMapper;
        this.userMapper = userMapper;
    }


    @Override
    public List<Map<String, Object>> selectAllUser() throws HosExection{
        logger.info("selectAllUser:");
        List<Map<String, Object>> al = new ArrayList<>();
        List<User> list = userMapper.selectAllUser();
        if (null != list){
            for (User list0 : list){
                Map<String, Object> map = new HashMap<>(8);
                map.put("systemId", list0.getSystemId());
                map.put("userId", list0.getUserId());
                map.put("userPassword", list0.getUserPassword());
                map.put("userName", list0.getUserName());
                map.put("userType", list0.getUserType());
                al.add(map);
            }
        } else {
            throw new HosExection(ErrorMessage.NONE_DATA);
        }
        return al;
    }

    @Override
    public RestData updateUser(User user) throws HosExection {
        logger.info("updateUserBySystemId" + JsonUtil.getJsonString(user));
        int success = userMapper.updateByPrimaryKey(user);
        if (0 < success){
            return new RestData(0, "修改成功");
        } else{
            throw new HosExection("修改失败");
        }
    }

    @Override
    public RestData insertUser(User user) throws HosExection {
        logger.info("insertUser" + JsonUtil.getJsonString(user));
        int success = userMapper.insert(user);
        if (0 < success){
            return new RestData(0, "添加成功");
        } else{
            throw new HosExection("添加失败");
        }
    }

    @Override
    public RestData deleteUser(Integer systemId) throws HosExection {
        logger.info("deleteUserBySystemId" + systemId);
        int success = userMapper.deleteByPrimaryKey(systemId);
        if (0 < success){
            return new RestData(0, "删除成功");
        } else{
            throw new HosExection("删除失败");
        }
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

    @Override
    public List<Map<String, Object>> selectAllDoctor() throws HosExection {
        logger.info("selectAllDoctor");
        List<Map<String, Object>> al = new ArrayList<>();
        List<Doctor> list = doctorMapper.selectAll();
        if (null != list) {
            for (Doctor list0 : list){
                Map<String, Object> map = new HashMap<>(16);
                map.put("systemId", list0.getSystemId());
                map.put("province", list0.getDocProvince());
                map.put("city", list0.getDocCity());
                map.put("hospital", list0.getDocHospital());
                map.put("room", list0.getDocRoom());
                map.put("name", list0.getDocName());
                map.put("phone", list0.getDocPhone());
                map.put("date", list0.getDocDate());
                map.put("sumPatient", list0.getDocSumPatient());
                map.put("incPatient", list0.getDocIncPatient());
                map.put("sumMoney", list0.getDocSumMoney());
                map.put("incMoney", list0.getDocIncMoney());
                map.put("file", list0.getDocFile());
                map.put("tag", list0.getDocTag());
                al.add(map);
            }
        } else {
            throw new HosExection(ErrorMessage.NONE_DATA);
        }
        return al;

    }
}
