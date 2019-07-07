package com.softlab.hospital.service;

import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.User;
import com.softlab.hospital.core.model.vo.DoctorVo;

/**
 * Created by LiXiwen on 2019/7/3 18:00.
 **/
public interface SuperManagerService {

    /**
     *条件查询
     *
     * @param doctorVo
     * @return
     * @throws HosExection
     */
    RestData selectDoctorByCondition(DoctorVo doctorVo) throws HosExection;

    /**
     * 添加管理员
     *
     * @param user
     * @return
     * @throws HosExection
     */
    RestData postAddManager(User user) throws HosExection;

    /**
     * 删除管理员
     *
     * @param systemId
     * @return
     * @throws HosExection
     */
    RestData deleteManager(Integer systemId) throws HosExection;

    /**
     * 修改管理员信息
     *
     * @param user
     * @return
     * @throws HosExection
     */
    RestData updateManager(User user) throws HosExection;

    /**
     * 显示所有管理员
     *
     * @return
     * @throws HosExection
     */
    RestData selectAllManager() throws HosExection;

    /**
     * 按条件查找管理员
     *
     * @param user
     * @return
     * @throws HosExection
     */
    RestData selectPartManager(User user) throws HosExection;

}
