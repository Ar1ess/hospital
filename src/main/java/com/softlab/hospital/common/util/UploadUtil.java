package com.softlab.hospital.common.util;

import com.softlab.hospital.common.RestData;
import com.softlab.hospital.core.mapper.WjMapper;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.Wj;
import com.softlab.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author : Ar1es
 * @date : 2019/7/4
 * @since : Java 8
 */
@Component
public class UploadUtil {

    public static final String uploadDir = "C:\\Users\\HP\\Desktop\\a\\";

    private static WjMapper wjMapper;
    private static UserService userService;

    @Autowired
    public void setDoctorMapper(WjMapper wjMapper, UserService userService) {
        UploadUtil.wjMapper = wjMapper;
        UploadUtil.userService = userService;
    }

    /**
     * 上传文件
     *
     * @param files
     * @return
     * @throws Exception
     */
    public static boolean upload(MultipartFile[] files,Integer id) throws Exception {
        return up(files, id);
    }

    public static boolean update(MultipartFile[] files, Doctor doctor) throws Exception {
        int a = wjMapper.deleteByDocId(doctor);
        boolean aFlag = up(files, doctor.getSystemId());
        if(0 < a && aFlag) {
            return true;
        }
        return false;
    }


    private static boolean up(MultipartFile[] files,Integer id) throws Exception {
        boolean flag = false;
        for(MultipartFile file : files) {
            //文件后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //上传文件名
            String filename = UUID.randomUUID() + suffix;
            //服务器端保存的文件对象
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if(!dir.exists())
            {
                dir.mkdir();
            }
            File serverFile = new File( UploadUtil.uploadDir+ filename);
            //将上传的文件写入到服务器端文件内
            file.transferTo(serverFile);
            Wj wj = new Wj();
            wj.setDocId((long)id);
            wj.setDocFile(filename);
            if(0 < wjMapper.insert(wj)) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

}
