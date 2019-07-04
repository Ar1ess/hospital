package com.softlab.hospital.core.mapper;


import com.softlab.hospital.core.model.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DoctorMapper {

    int deleteByPrimaryKey(Integer systemId);

    int insert(Doctor record);

    Doctor selectByPrimaryKey(Integer systemId);

    List<Doctor> selectAll();

    int updateByPrimaryKey(Doctor record);
}