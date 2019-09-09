package com.ift.imywork.view;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import com.ift.imywork.session.AbstractAction;
import com.ift.slip.model.TbCustomerId;



@ManagedBean
@ViewScoped
public class AccountAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(AccountAction.class.getName());
	
	
	//Parameter Register
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private boolean confirmRegister;
	private boolean disabledBTRegister;
	private TbCustomerId tbCustomerId;
	
	@PostConstruct
	public void preRenderView(){
		try {
			if(!FacesContext.getCurrentInstance().isPostback()){
				ClearData();
				logger.info("Is Not PostBack");
				
			}
		} catch (Exception e) {
			logger.warning("Error AccountAction preRenderView :"+e.getMessage());
		}
	}
	
	public void actionRegister() {
		logger.info("Start actionRegister");
        try {
        	
        	
		} catch (Exception e) {
			logger.warning("Exception Page AccountAction method actionRegister :"+e.getMessage());
		}
        
    }
	public void actionConfirmRegister(ValueChangeEvent e){
		logger.info("Start actionConfirmRegister");
        try {
        	if(this.isConfirmRegister()) {
        		this.disabledBTRegister = true;
        	}else {
        		this.disabledBTRegister = false;
        	}
        	
		} catch (Exception e2) {
			logger.warning("Exception Page AccountAction method actionConfirmRegister :"+e2.getMessage());
		}
	}
	
	public void ClearData(){
		this.email = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.confirmRegister = false;
		this.disabledBTRegister = true;
		this.tbCustomerId = new TbCustomerId();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isConfirmRegister() {
		return confirmRegister;
	}
	public void setConfirmRegister(boolean confirmRegister) {
		this.confirmRegister = confirmRegister;
	}
	public boolean isDisabledBTRegister() {
		return disabledBTRegister;
	}
	public void setDisabledBTRegister(boolean disabledBTRegister) {
		this.disabledBTRegister = disabledBTRegister;
	}
	public TbCustomerId getTbCustomerId() {
		return tbCustomerId;
	}
	public void setTbCustomerId(TbCustomerId tbCustomerId) {
		this.tbCustomerId = tbCustomerId;
	}
}
