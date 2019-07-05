package com.softlab.hospital.core.mapper;

import java.util.List;
import com.softlab.hospital.core.model.Info;

public interface InfoMapper {

    int deleteByPrimaryKey(Long systemId);

    int insert(Info record);

    Info selectByPrimaryKey(Long systemId);

    List<Info> selectAll();

    int updateByPrimaryKey(Info record);
}