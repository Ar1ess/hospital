package com.softlab.hospital.common.util;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.core.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LiXiwen on 2019/7/3 17:54.
 **/
public class VerifyUtil {

    public static boolean VerifyLogin(HttpServletRequest request) {
        User currentUser = TokenUtil.getUserByToken(request);
        if(null == currentUser) {
            return false;
        }
        return true;
    }
}
