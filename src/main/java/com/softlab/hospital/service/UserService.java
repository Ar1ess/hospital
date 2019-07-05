package com.softlab.hospital.service;

import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LiXiwen on 2019/7/3 18:01.
 **/
public interface UserService {

    /**
     * 登陆
     *
     * @param user
     * @return
     * @throws HosExection
     */
    Map<String, Object> postLogin(User user) throws HosExection;

    /**
     * 删除医生信息
     *
     * @param systemId
     * @return
     * @throws HosExection
     */
    RestData deleteBySystemId(Integer systemId) throws HosExection;

    /**
     * 修改医生信息
     *
     * @param doctor
     * @return
     * @throws HosExection
     */
    RestData updateDoctor(Doctor doctor) throws HosExection;

    /**
     * 增加医生信息
     *
     * @param doctor
     * @return
     * @throws HosExection
     */
    RestData insertDoctor(Doctor doctor) throws HosExection;

    /**
     * 查看所有医生信息
     *
     * @return
     */
    List<Map<String, Object>> selectAll(String userId) throws HosExection;

    /**
     * 按照条件查询医生
     *
     * @param doctor
     * @return
     */
    List<Map<String, Object>> selectByContidion(Doctor doctor) throws HosExection;


}
