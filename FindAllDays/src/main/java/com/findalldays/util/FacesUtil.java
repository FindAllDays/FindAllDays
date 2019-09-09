package com.findalldays.util;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Named
public class FacesUtil {
	private static FacesUtil instance = new FacesUtil();
	public void clearContext() {
		HttpSession session = getHttpSession(false);
		try {
			session.invalidate();
		} catch(Exception ex) {
			// do nothing
		}
		
	}
	public static void redirect(final String urlRequest) throws IOException {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()+urlRequest);
	}
	public HttpServletRequest getHttpRequest(){   
        return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();   
    }   
    
	
    public HttpServletResponse getHttpResponse(){   
        return(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();   
    } 
    public HttpSession getHttpSession(final boolean create) {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(create);
	}
	public Object getSessionMapValue(final String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}
	public static FacesUtil getInstance() {
		return instance;
	}
	public void setSessionMapValue(final String key, final Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}
	public static void session(){
		FacesContext context = FacesContext.getCurrentInstance();
		  HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	}
	public static void addMessage(String detail,boolean status) {
		if(status){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, detail, "");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, detail, "");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
        
    }
	
}
