package com.softlab.hospital.common.util;

import com.softlab.hospital.core.mapper.UserMapper;
import com.softlab.hospital.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @author : Ar1es
 * @date : 2019/7/5
 * @since : Java 8
 */
@Component
public class VerifyUtil {

    private static UserMapper userMapper;

    @Autowired
    public VerifyUtil(UserMapper userMapper) {
        VerifyUtil.userMapper = userMapper;
    }

    public static Integer verifyType(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = new User();
        user.setUserToken(token);
        User user1 = userMapper.selectByToken(user);
        return user1.getUserType();
    }




}
