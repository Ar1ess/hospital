package com.softlab.hospital.core.mapper;

import java.util.List;
import com.softlab.hospital.core.model.Info;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InfoMapper {

    int deleteByPrimaryKey(Long systemId);

    int insert(Info record);

    Info selectByPrimaryKey(Long systemId);

    List<Info> selectAll();

    int updateByPrimaryKey(Info record);

    List<String> selectAllProvince();

    List<String> selectAllCity(Info record);

    List<String> selectAllHospital(Info record);

    List<String> selectAllRoom(Info record);


}