package com.ift.imywork.session;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ift.imywork.model.FormPageCodeDTO;
import com.ift.imywork.model.UserloginInfo;
import com.ift.imywork.util.FacesUtil;





@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {
	private final static Logger logger = Logger.getLogger(SessionBean.class.getName());
	
	private FormPageCodeDTO menuAuthen;
	private UserloginInfo userLogin;
	
	public void keepSessionAlive() {
		logger.info("-------------- keepSessionAlive ---------------");
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc
				.getExternalContext().getRequest();
		request.getSession();

	}
	public void logout() {
		try {
			logger.info("-------------- logout ---------------");
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession httpSession = (HttpSession) facesContext
					.getExternalContext().getSession(false);
			httpSession.invalidate();
			FacesUtil.redirect("/pages/sign-in.jsf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FormPageCodeDTO getMenuAuthen() {
		return menuAuthen;
	}

	public void setMenuAuthen(FormPageCodeDTO menuAuthen) {
		this.menuAuthen = menuAuthen;
	}
	public UserloginInfo getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(UserloginInfo userLogin) {
		this.userLogin = userLogin;
	}
	

	
	
}
