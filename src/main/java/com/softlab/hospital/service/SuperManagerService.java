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
     * @param doctorVo
     * @return
     * @throws HosExection
     */
    RestData selectDoctorByCondition(DoctorVo doctorVo) throws HosExection;

    RestData postAddManager(User user) throws HosExection;

    RestData deleteManager(Integer systemId) throws HosExection;

    RestData updateManager(User user) throws HosExection;

    RestData selectAllManager() throws HosExection;

    RestData selectPartManager(User user) throws HosExection;

}
