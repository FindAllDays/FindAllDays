package com.ift.imywork.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.ift.slip.connect.url.HttpURLService;
import com.ift.slip.model.User;

@ManagedBean
public class TestBean {

	@PostConstruct
	public void init() {
		List<String[]> params = null;
		
		List<User> u = HttpURLService.GET_SERVICE_LIST(User.class, params, "getUser/1");
		try {
		
			System.out.println(u.get(0).getUsername());
			
			User u1 = HttpURLService.GET_SERVICE(User.class, params, "getOneUser/1");
			System.out.println(u1.getUserId());
			
			User save = new User();
			save.setUserId(5);
			save.setPassword("TT");
			save.setUsername("User");
			//Object obj = (Object)save;
			User uu = HttpURLService.POST_SERVICE(User.class, params, "SaveUser", save);
			System.out.println(uu.getUsername());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	  
		
	}
}
