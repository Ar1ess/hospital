package com.softlab.hospital.core.model;

import lombok.Data;

/**
 * Created by LiXiwen on 2019/7/3 17:25.
 **/
@Data
public class Doctor {
    /**
     * 系统id
     */
    private Integer systemId;

    /**
     * 省份
     */
    private String docProvince;

    /**
     * 市
     */
    private String docCity;

    /**
     * 医院
     */
    private String docHospital;

    /**
     * 科室
     */
    private String docRoom;

    /**
     * 姓名
     */
    private String docName;

    /**
     * 添加日期
     */
    private String docDate;

    /**
     * 电话
     */
    private String docPhone;

    /**
     * 总人数
     */
    private Long docSumPatient;

    /**
     * 月增人数
     */
    private Integer docIncPatient;

    /**
     * 总金额
     */
    private Double docSumMoney;

    /**
     * 月增金额
     */
    private Double docIncMoney;

    /**
     * 医生信息文件
     */
    private String docFile;

    /**
     * 所属人员
     */
    private String docTag;

}
