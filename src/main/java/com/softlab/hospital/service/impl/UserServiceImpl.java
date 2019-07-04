package com.softlab.hospital.service.impl;

import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.util.TokenUtil;
import com.softlab.hospital.core.mapper.UserMapper;
import com.softlab.hospital.core.model.User;
import com.softlab.hospital.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiXiwen on 2019/7/3 17:59.
 **/
@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Map<String, Object> postLogin(User user) throws HosExection {
        Map<String,Object> rtv = null;
        List<User> users = userMapper.selectByCondition(user);
        if(null != users && 1 == users.size()) {
            user = users.get(0);
            user.setUserToken(TokenUtil.getToken());
            if(0 < userMapper.updateTokenBySystemId(user)) {
                rtv = new HashMap<>(3);
                user = users.get(0);
                rtv.put("systemId", user.getSystemId());
                rtv.put("token", user.getUserToken());
                rtv.put("type", user.getUserType());
            }
        } else {
            throw new HosExection("用户名或密码不正确!");
        }
        return rtv;
    }

}
