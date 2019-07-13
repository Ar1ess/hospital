package com.softlab.hospital.core.mapper;


import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.Wj;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface WjMapper {

    int deleteByPrimaryKey(Long systemId);


    int insert(Wj record);


    Wj selectByPrimaryKey(Long systemId);


    List<Wj> selectAll();


    int updateByPrimaryKey(Wj record);

    Set<String> selectFilesByPrimaryKey(long systemId);

    int updateFileByDocIdAndFileName(Wj w);

    int deleteByDocId(Doctor doctor);
}