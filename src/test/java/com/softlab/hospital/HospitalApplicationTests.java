package com.softlab.hospital;

import com.softlab.hospital.common.HosExection;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.core.mapper.DoctorMapper;
import com.softlab.hospital.core.model.Doctor;
import com.softlab.hospital.core.model.vo.DoctorVo;
import com.softlab.hospital.web.api.UserApi;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Reader;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalApplicationTests {

	@Autowired
	private UserApi userApi;

	@Test
	public void testSelectByCondition() throws HosExection {
		Doctor doctor = new Doctor();
		doctor.setSystemId(3);
		RestData restData = userApi.up(doctor);
		System.out.println(JsonUtil.getJsonString(restData));
	}

}
