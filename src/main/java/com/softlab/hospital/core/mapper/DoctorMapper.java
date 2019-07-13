package com.softlab.hospital.core.mapper;


import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.vo.DoctorVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DoctorMapper {

    int deleteByPrimaryKey(Integer systemId);

    int insert(Doctor record);

    Doctor selectByPrimaryKey(Integer systemId);

    List<Doctor> selectAllDoctor(String userId);

    int updateByPrimaryKey(Doctor record);

    List<Doctor> selectByCondition(DoctorVo record);

    int updatePatientAndMoney(Integer SystemId);

    DoctorVo selectDoctorByCondition(DoctorVo doctorVo);

    List<Doctor> selectAll();
}