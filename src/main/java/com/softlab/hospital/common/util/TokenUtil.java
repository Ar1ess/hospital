package com.softlab.hospital.common.util;

import com.softlab.hospital.core.mapper.UserMapper;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.attribute.DocAttribute;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LiXiwen on 2019/7/3 17:54.
 **/
@Component
public class TokenUtil {

    private static UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        TokenUtil.userMapper = userMapper;
    }

    public static User getUserByToken(HttpServletRequest request){
        User user = null;
        String token = request.getHeader("token");

        if(null != token) {
            User userCondition = new User();
            userCondition.setUserToken(token);
            List<User> users = userMapper.selectByCondition(userCondition);
            if(1 == users.size()){
                user = users.get(0);
            }
        }
        return user;
    }


    public static String getToken(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
