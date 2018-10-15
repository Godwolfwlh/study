package com.sys.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.sys.entity.UserBean;
import com.sys.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dbConfig.xml")
public class UserTest {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Test
	public void findUserList(){
		try {
			//List<UserBean> list=userService.findUserList();
			List<UserBean> list=userService.findUserList();
			System.out.println(list.size());
			for (UserBean t : list) {
				System.out.println(t.getStaffId()+"--"+t.getStaffName()+"--"+t.getStaffNo()+"--"+t.getPwd()+"--"+t.getStaffState());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
