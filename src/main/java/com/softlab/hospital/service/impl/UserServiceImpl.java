package com.softlab.hospital.service.impl;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.common.util.TokenUtil;
import com.softlab.hospital.common.util.UploadUtil;
import com.softlab.hospital.core.mapper.DoctorMapper;
import com.softlab.hospital.core.mapper.UserMapper;
import com.softlab.hospital.core.mapper.WjMapper;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.User;
import com.softlab.hospital.core.model.Wj;
import com.softlab.hospital.core.model.vo.DoctorVo;
import com.softlab.hospital.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author LiXiwen
 * @date 2019/7/3 17:59
 **/
@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;
    private final DoctorMapper doctorMapper;
    private final WjMapper wjMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, DoctorMapper doctorMapper, WjMapper wjMapper) {
        this.userMapper = userMapper;
        this.doctorMapper = doctorMapper;
        this.wjMapper = wjMapper;
    }

    @Override
    public Map<String, Object> postLogin(User user) throws HosExection {
        Map<String,Object> rtv = null;
        List<User> users = userMapper.selectByCondition(user);
        if(null != users && 1 == users.size()) {
            user = users.get(0);
            user.setUserToken(TokenUtil.getToken());
            if(0 < userMapper.updateTokenBySystemId(user)) {
                rtv = new HashMap<>(3);
                user = users.get(0);
                rtv.put("systemId", user.getSystemId());
                rtv.put("token", user.getUserToken());
                rtv.put("type", user.getUserType());
            }
        } else {
            throw new HosExection("用户名或密码不正确!");
        }
        return rtv;
    }

    @Override
    public RestData deleteBySystemId(Integer systemId) throws HosExection{
        logger.info("deleteBySystemId : " + systemId);
        int success = doctorMapper.deleteByPrimaryKey(systemId);
        Doctor t = new Doctor();
        t.setSystemId(systemId);
        int a = wjMapper.deleteByDocId(t);
        if (0 < success && 0 < a){
            return new RestData(0, "删除成功");
        } else{
            throw new HosExection("删除失败");
        }
    }

    @Override
    public RestData updateDoctor(MultipartFile[] files, Doctor doctor) throws Exception {
        logger.info("updateDoctorBySystemId" + JsonUtil.getJsonString(doctor));
        if (null != files) {
            if (!UploadUtil.update(files, doctor)) {
                return new RestData(1, ErrorMessage.UPLOAD_ERROR);
            }
        }
        int success = doctorMapper.updateByPrimaryKey(doctor);
        if (0 < success){
            return new RestData(0, "修改成功");
        } else {
            throw new HosExection("修改失败");
        }
    }

    @Override
    public RestData insertDoctor(MultipartFile[] files, Doctor doctor) throws Exception {
        logger.info("insertDoctor : " + JsonUtil.getJsonString(doctor));
        int success = doctorMapper.insert(doctor);

        if (0 < success && UploadUtil.upload(files, doctor.getSystemId())){
            return new RestData(0, "添加成功");
        } else{
            throw new HosExection("添加失败");
        }
    }

    @Override
    public List<Map<String, Object>> selectAll(String userId) throws HosExection{
        logger.info("selectAll");
        List<Map<String, Object>> al = new ArrayList<>();
        List<Doctor> list = doctorMapper.selectAllDoctor(userId);
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
                Set<String> set = wjMapper.selectFilesByPrimaryKey(list0.getSystemId());
                int i = 1;
                for(String s : set) {
                    map.put("file"+i, s);
                }
                map.put("tag", list0.getDocTag());
                al.add(map);
            }
        } else {
            throw new HosExection(ErrorMessage.NONE_DATA);
        }

        return al;

    }

    @Override
    public List<Map<String, Object>> selectByContidion(DoctorVo doctorVo) throws HosExection{
        logger.info("selectByCondition" + JsonUtil.getJsonString(doctorVo));
        List<Map<String, Object>> al = new ArrayList<>();
        List<Doctor> list = doctorMapper.selectByCondition(doctorVo);
        if (null != list) {
            for (Doctor list0 : list) {
                Map<String, Object> map = new HashMap<>(16);
                map.put("systemId", list0.getSystemId());
                map.put("tag", list0.getDocTag());
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
                Set<String> set = wjMapper.selectFilesByPrimaryKey(list0.getSystemId());
                int i = 1;
                for(String s : set) {
                    map.put("file"+i, s);
                }
                al.add(map);
            }
        } else {
            throw new HosExection(ErrorMessage.NONE_DATA);
        }
        return al;
    }


}
