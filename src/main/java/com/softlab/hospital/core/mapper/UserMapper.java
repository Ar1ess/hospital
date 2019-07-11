package com.softlab.hospital.core.mapper;

import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LiXiwen on 2019/7/3 17:26.
 **/
@Repository
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer systemId);

    int insert(User record);

    User selectByPrimaryKey(Integer systemId);

    List<User> selectAllUser();

    int updateByPrimaryKey(User record);

    List<User> selectByCondition(User user);

    int updateTokenBySystemId(User user);

    List<User> selectAllManager();

    User selectByToken(User user);



}
