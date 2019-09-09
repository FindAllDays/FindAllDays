package com.findalldays.session;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.findalldays.util.FacesUtil;




@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {
	private final static Logger logger = Logger.getLogger(SessionBean.class.getName());
	

	private String sessionId;
	
	
	@PostConstruct
	public void keepSessionAlive() {
		logger.info("-------------- keepSessionAlive ---------------");
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc
				.getExternalContext().getRequest();
		request.getSession();
		
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
		sessionId = session.getId();
		
	}
	
	public void logout() {
		try {
			logger.info("-------------- logout ---------------");
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession httpSession = (HttpSession) facesContext
					.getExternalContext().getSession(false);
			httpSession.invalidate();
			FacesUtil.redirect("/index");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isMobile() {
		if(FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("User-Agent").indexOf("Mobile") != -1) {
			return true;
		 } else {
			 return false;
		 }
	}

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
