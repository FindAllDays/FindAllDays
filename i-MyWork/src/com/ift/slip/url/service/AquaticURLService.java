package com.ift.slip.url.service;


import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ift.slip.model.User;


@Path("/wPayURL")
public class AquaticURLService  {
	
		
		@GET
		@Path("/login/{username}/{password}")
		@Produces("application/json")
		public User  loginMember(@PathParam(value = "username")String username,@PathParam(value = "password")String password) {
			User out = new User(); //accountDAO.logInUser(username,password);
			if(out==null) {
				out = new User();
				out.setUserId(-1);
			}
			return out;
		}
		
		@GET
		@Path("/topup/{money}/{userId}")
		@Produces("application/json")
		public User  topup(@PathParam(value = "money")String money,@PathParam(value = "userId")String userId) {
			BigDecimal m = new BigDecimal(money);
		User out = new User(); //accountDAO.topup(m, Integer.parseInt(userId));
			if(out==null) {
				out = new User();
				out.setUserId(-1);
			}
			return out;
		}
		
		@GET
		@Path("/widthdraw/{money}/{userId}")
		@Produces("application/json")
		public User  widthdraw(@PathParam(value = "money")String money,@PathParam(value = "userId")String userId) {
			BigDecimal m = new BigDecimal(money);
			User out = new User(); //accountDAO.widthdraw(m, Integer.parseInt(userId));
			if(out==null) {
				out = new User();
				out.setUserId(-1);
			}
			return out;
		}
		
		@GET
		@Path("/buyItem/{money}/{userId}")
		@Produces("application/json")
		public User  buyItem(@PathParam(value = "money")String money,@PathParam(value = "userId")String userId) {
			BigDecimal m = new BigDecimal(money);
			User out = new User(); //accountDAO.buyItem(m, Integer.parseInt(userId));
			if(out==null) {
				out = new User();
				out.setUserId(-1);
			}
			return out;
		}
		
		@GET
		@Path("/sell/{money}/{userId}")
		@Produces("application/json")
		public User  sell(@PathParam(value = "money")String money,@PathParam(value = "userId")String userId) {
			BigDecimal m = new BigDecimal(money);
			User out = new User(); //accountDAO.sell(m, Integer.parseInt(userId));
			if(out==null) {
				out = new User();
				out.setUserId(-1);
			}
			return out;
		}
		
		@GET
		@Path("/transfer/{money}/{userId}")
		@Produces("application/json")
		public User  transfer(@PathParam(value = "money")String money,@PathParam(value = "userId")String userId) {
			BigDecimal m = new BigDecimal(money);
			User out = new User(); //accountDAO.Transfer(m, Integer.parseInt(userId));
			if(out==null) {
				out = new User();
				out.setUserId(-1);
			}
			return out;
		}

}
