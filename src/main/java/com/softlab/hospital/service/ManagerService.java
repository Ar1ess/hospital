package com.softlab.hospital.service;


import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.core.model.Info;
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

    /**
     * 查找所有省
     * @return
     * @throws HosExection
     */
    RestData selectAllProvince() throws HosExection;

    /**
     * 查找该省下所有已添加的城市
     * @param record
     * @return
     * @throws HosExection
     */
    RestData selectAllcity(Info record) throws HosExection;

    /**
     * 查找该省下，和市下所有的医院
     * @param record
     * @return
     * @throws HosExection
     */
    RestData selectAllHospital(Info record) throws HosExection;

    /**
     * 查找医院下所有的科室
     * @param info
     * @return
     * @throws HosExection
     */
    RestData selectAllRoom(Info info) throws HosExection;

    /**
     * 添加省市医院
     * @param info
     * @return
     * @throws HosExection
     */
    RestData postAdd(Info info) throws HosExection;

    /**
     * 查询全部
     * @return
     * @throws HosExection
     */
    RestData selectAll() throws HosExection;

    /**
     * 按主键查询
     * @param systemId
     * @return
     * @throws HosExection
     */
    RestData selectByPrimaryKey(Long systemId) throws HosExection;

    /**
     * 按主键删除
     * @param systemId
     * @return
     * @throws HosExection
     */
    RestData deleteByPrimaryKey(Long systemId) throws HosExection;

    /**
     * 按主键更新
     * @param info
     * @return
     * @throws HosExection
     */
    RestData updateByPrimaryKey(Info info) throws HosExection;

}
