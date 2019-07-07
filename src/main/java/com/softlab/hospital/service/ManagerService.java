package com.softlab.hospital.service;


import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.core.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LiXiwen on 2019/7/3 18:01.
 **/
public interface ManagerService {


    /**
     * 查找所有市场人员
     *
     * @return
     */
    List<Map<String, Object>> selectAllUser() throws HosExection;

    /**
     * 修改指定市场人员
     *
     * @param user
     * @return
     * @throws HosExection
     */
    RestData updateUser(User user) throws HosExection;

    /**
     * 添加市场人员
     *
     * @param user
     * @return
     * @throws HosExection
     */
    RestData insertUser(User user) throws HosExection;

    /**
     * 删除市场人员
     *
     * @param systemId
     * @return
     * @throws HosExection
     */
    RestData deleteUser(Integer systemId) throws HosExection;

    /**
     * 批量维护患者总数和总金额
     *
     * @param al
     * @return
     */
    RestData updatePatientAndMoney(ArrayList<Integer> al) throws HosExection;

    /**
     * 查找所有医生信息
     *
     * @return
     * @throws HosExection
     */
    List<Map<String, Object>> selectAllDoctor() throws HosExection;
}
