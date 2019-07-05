package com.softlab.hospital.service.impl;

import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.core.mapper.DoctorMapper;
import com.softlab.hospital.core.mapper.UserMapper;
import com.softlab.hospital.core.model.User;
import com.softlab.hospital.core.model.vo.DoctorVo;
import com.softlab.hospital.service.SuperManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiXiwen on 2019/7/3 18:00.
 **/
@Service
public class SuperManagerServiceImpl implements SuperManagerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DoctorMapper doctorMapper;

    private final UserMapper userMapper;

    @Autowired
    public SuperManagerServiceImpl(DoctorMapper doctorMapper, UserMapper userMapper) {
        this.doctorMapper = doctorMapper;
        this.userMapper = userMapper;
    }

    @Override
    public RestData selectDoctorByCondition(DoctorVo doctorVo) throws HosExection {
        DoctorVo data = doctorMapper.selectDoctorByCondition(doctorVo);
        if(null != data) {
            Map<String, Object> map = new HashMap<>(4);
            map.put("totalMoney", data.getTotalMoney());
            map.put("totalIncMoney", data.getTotalIncMoney());
            map.put("totalPeople", data.getTotalPeople());
            map.put("totalIncPeople", data.getTotalIncPeople());
            return new RestData(map);
        } else {
            throw new HosExection("无数据");
        }
    }

    @Override
    public RestData postAddManager(User user) throws HosExection {
        user.setUserType(2);
        int status = userMapper.insert(user);
        if(0 < status) {
            return new RestData(0,"插入成功");
        } else {
            return new RestData(1,"插入失败");
        }
    }

    @Override
    public RestData deleteManager(Integer systemId) throws HosExection {
        int status = userMapper.deleteByPrimaryKey(systemId);
        if (0 < status) {
            return new RestData(0, "删除成功");
        } else {
            throw new HosExection("删除失败");
        }
    }

    @Override
    public RestData updateManager(User user) throws HosExection {
        int status = userMapper.updateByPrimaryKey(user);
        if (0 < status){
            return new RestData(0, "修改成功");
        } else{
            throw new HosExection("修改失败");
        }
    }

    @Override
    public RestData selectAllManager() throws HosExection {
        List<Map<String, Object>> rtv = new ArrayList<>();
        List<User> data = userMapper.selectAllManager();
        if(null !=data) {
            for (User user1 : data) {
                Map<String, Object> map = new HashMap<>(5);
                map.put("systemId", user1.getSystemId());
                map.put("userId", user1.getUserId());
                map.put("userPassword", user1.getUserPassword());
                map.put("userName", user1.getUserName());
                map.put("userType", user1.getUserType());
                rtv.add(map);
            }
        } else {
            throw new HosExection("无数据");
        }
        return new RestData(rtv);
    }

    @Override
    public RestData selectPartManager(User user) throws HosExection {
        List<Map<String, Object>> rtv = new ArrayList<>();
        List<User> data = userMapper.selectByCondition(user);
        if(null != data) {
            for(User user1 : data){
                Map<String, Object> map = new HashMap<>(5);
                map.put("systemId", user1.getSystemId());
                map.put("userId", user1.getUserId());
                map.put("userPassword", user1.getUserPassword());
                map.put("userName", user1.getUserName());
                map.put("userType", user1.getUserType());
                rtv.add(map);
            }
        } else {
            throw new HosExection("无数据");
        }
        return new RestData(rtv);
    }
}
