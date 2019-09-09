package com.ift.imywork.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;


public class Constant {
    public Constant() {
        super();
    }
    
    public static int pageSize = 10;
    
    public static String APP_MODE="production";
   

    public static String IMG_PATH = APP_MODE.equals("local")?"/Users/yeanyong/aquaticApp/":"/aquaticApp/";;
    public static String IMG_AQUATIC="Aquatic/";
    public static String IMG_FAMILY="Family/";
    public static final String Email_Host = "smtp.gmail.com";
    public static final int Email_Port = 587; //25
    public static final String Email_Username = "fisherie.mail@gmail.com";
    public static final String Email_Password = "fish@2019";

    public static boolean isNull(String _obj){
    	String obj = _obj;
    	if(obj==null) {
    		obj="";
    	}
    	obj=obj.trim();
		return obj==null||obj==""||"".equals(obj); 
	}
	public static int getRow(Object obj){
		int rowCount = 0;
	if (obj instanceof Integer) {
		rowCount = (Integer) obj;
	} else if (obj instanceof Long) {
		rowCount = Integer.valueOf(obj.toString());
	}
	 else if (obj instanceof BigDecimal) {
			rowCount = Integer.valueOf(obj.toString());
		}
	 else if (obj instanceof BigDecimal) {
			rowCount =  ((BigDecimal) obj).intValue();
		}

	return rowCount;
	}
	public static int calPageCount(int rowCount){
	
	int totalPage = (int) Math.ceil(rowCount * 1.0d
			/ (Constant.pageSize * 1.0d));
	return totalPage;
	}
	public static int calPageCount(int rowCount,int pageSize){
		
		int totalPage = (int) Math.ceil(rowCount * 1.0d
				/ (pageSize* 1.0d));
		return totalPage;
		}
	
	public static void main(String[] args) {
		//sendMailBGProcess2("basz.worapol@gmail.com","test","xxxxxxx");
		try {
			System.out.println(Password.getSaltedHashRev("73fc120cc480d6895816d43c4b65d273"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	  
	public static  void sendMailBGProcess2(String To,String Subject,String Detail) {
		 try{

//		        Properties props = new Properties();
//		        props.put("mail.smtp.host", Constant.Email_Host); // for gmail use smtp.gmail.com
//		        props.put("mail.smtp.auth", "true");
//		        props.put("mail.smtp.starttls.enable", "true");
//		        props.put("mail.smtp.port", Constant.Email_Port);
//		        props.put("mail.smtp.socketFactory.port", Constant.Email_Port);
//
//		        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
//
//		            protected PasswordAuthentication getPasswordAuthentication() {
//		                return new PasswordAuthentication("fisherie.mail@gmail.com", "fish@2019");
//		            }
//		        });
//
//		        mailSession.setDebug(true); // Enable the debug mode
//
//		        Message msg = new MimeMessage( mailSession );
//
//		        //--[ Set the FROM, TO, DATE and SUBJECT fields
//		        msg.setFrom( new InternetAddress( "Fisheries" ) );
//		        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(To) );
//		        msg.setSentDate( new Date());
//		        msg.setSubject( Subject );
//
//		        //--[ Create the body of the mail
//		        msg.setText( Detail );
//
//		        //--[ Ask the Transport class to send our mail message
//		        Transport.send( msg );

		    }catch(Exception E){
		        System.out.println( "Oops something has gone pearshaped!");
		        System.out.println( E );
		    }
	}
	public static void addMassageToMsgUi(String msgId,String msgText,boolean serverity){
		 FacesContext fc = FacesContext.getCurrentInstance();
		 FacesMessage message = new FacesMessage(serverity?FacesMessage.SEVERITY_INFO:FacesMessage.SEVERITY_ERROR, null, msgText);
		 fc.addMessage(msgId, message);
	     fc.renderResponse();
	     fc.validationFailed();
	}
	public static String formatMoney(BigDecimal money) {
		DecimalFormat dec = new DecimalFormat("#,###.00");
		dec.setMinimumFractionDigits(2);
		return dec.format(money);
	}
	
}
