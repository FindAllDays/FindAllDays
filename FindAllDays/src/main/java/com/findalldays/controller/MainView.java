package com.findalldays.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.util.logging.Logger;
import com.findalldays.session.AbstractAction;
import com.findalldays.session.SessionBean;

@ManagedBean
@ViewScoped
public class MainView extends AbstractAction implements Serializable  {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(MainView.class.getName());
	
	SessionBean sessionBean; 
	private String Test;
	
	@PostConstruct
	public void preRenderView(){	
		
			try {
				sessionBean = super.getSessionBean();
				if(!FacesContext.getCurrentInstance().isPostback()){
					this.Test = "Test";
						
					logger.info("Is Not PostBack");
				}
			} catch (Exception e) {
				logger.info("Error HomeBean :"+e.getMessage());
			}
		
	}

	public String getTest() {
		return Test;
	}

	public void setTest(String test) {
		Test = test;
	}
	
}
