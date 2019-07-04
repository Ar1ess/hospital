package com.softlab.hospital.service;


import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;

import java.util.ArrayList;

/**
 * Created by LiXiwen on 2019/7/3 18:01.
 **/
public interface ManagerService {

    /**
     * 批量维护患者总数和总金额
     *
     * @param al
     * @return
     */
    RestData updatePatientAndMoney(ArrayList<Integer> al) throws HosExection;
}
