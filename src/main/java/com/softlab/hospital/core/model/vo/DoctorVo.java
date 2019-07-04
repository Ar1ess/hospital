package com.softlab.hospital.core.model.vo;

import com.softlab.hospital.core.model.Doctor;
import lombok.Data;

/**
 * @author : Ar1es
 * @date : 2019/7/4
 * @since : Java 8
 */
@Data
public class DoctorVo extends Doctor {

    private String createTime;

    private String endTime;
}
