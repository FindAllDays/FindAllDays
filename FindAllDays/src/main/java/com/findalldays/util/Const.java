package com.findalldays.util;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;


public class Const {
	private final static Logger logger = Logger.getLogger(Const.class.getName());
	public static class ESessionBean {		
		public static String SESSION_BEAN = "sessionBean";
	}
	
	
	public static boolean isNotNullAndEmpty(String msg){
		if(msg != null && !"".equals(msg)){
			return true;
		}else{
			return false;
		}
	}
   
}
