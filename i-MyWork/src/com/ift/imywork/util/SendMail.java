package com.ift.imywork.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeUtility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class SendMail {
	
	public static void main(String args[]) throws Exception
	{
		
//		StringBuffer Detail = new StringBuffer();
//		Detail.append("<p>เรียนบริษัทไอ เฟิร์สเทค (i-Firsttech)</p>");
		
//		sendmail("bpa_1921@hotmail.com","กกกกกก",Detail.toString());
		
		StringBuffer Detail = new StringBuffer();
		Detail.append("<p style='font-weight: bold;'>เรียนคุณ  </p>");
		Detail.append("<p>ตามที่ได้ลงทะเบียนเพื่อขอใช้บริการ OTOP EXPRODUCT</p>");
		Detail.append("<p>กรุณายืนยันการลงทะเบียนสมัครใช้บริการ ");
		Detail.append("<p>หากไม่ได้เป็นผู้สมัครหรือไม่มีเจตตนาในการสมัครใช้บริการนี้ ทาง OTOP EXPRODUCT จะลบอีเมล์นี้ได้อย่างปลอดภัย </p>");
		Detail.append("<p>&nbsp;</p>");
		Detail.append("<p>ขอแสดงความนับถือ</p>");
		Detail.append("<p>แผนกบริการลูกค้า </p>");
		sendmail("ิbasz.worapol@gmail.com","กกกกกก",Detail.toString());
	}
	public static void sendmail(String To,String Subject,String Detail) {
		 
		 Runnable r = new Runnable() {
	         public void run() {
	        	 sendMailBGProcess6(To,Subject,Detail);
	         }
	     };
	     new Thread(r).start();
	}
	
	private static void sendMailBGProcess(String To,String Subject,String Detail){
//		 final String host = Constant.Email_Host;
//		 final int port = Constant.Email_Port;
//		 final String username = Constant.Email_Username;
//		 final String password = Constant.Email_Password;
//		 
//		 Properties props = new Properties();
//		 props.put("mail.smtp.auth", "true");
//		 props.put("mail.smtp.starttls.enable", "true");
//
//		 Session session = Session.getInstance(props,
//		     new javax.mail.Authenticator() {
//		         protected PasswordAuthentication getPasswordAuthentication() {
//		         return new PasswordAuthentication(username, password);
//		       }
//		   });
//		 session.setDebug(true);
//
//		    try {
//
//		        Message message = new MimeMessage(session);
//		        message.setFrom(new InternetAddress(username,"fisherie.mail"));
//		        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(To));
//		        message.setSubject(MimeUtility.encodeText(Subject, "UTF-8", "Q"));
//		        message.setContent(Detail, "text/html; charset=utf-8");
//		        Transport transport = session.getTransport("smtp");
//		        transport.connect (host, port, username, password);
//		        transport.sendMessage(message, message.getAllRecipients());
//		        transport.close();  
//		        System.out.println("Done");
//
//		    } catch (MessagingException e) {
//		        throw new RuntimeException(e);
//		    } catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}
	private static boolean sendMailService(String To,String Subject,String Detail) {
		HashMap<String,String> params = new HashMap<String,String>();
		String result = "";
		
		try {
			URL url = new URL(Constant.Email_Username);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			
//			params.put("acCode" ,SendMailClazz.unicodeEscaped(mailObject.getAcCode()));
//			params.put("approvedName" ,SendMailClazz.unicodeEscaped(mailObject.getApprovedName()));
//			params.put("footer" ,SendMailClazz.unicodeEscaped(mailObject.getFooter()));
			params.put("mailContent" ,SendMail.unicodeEscaped(Detail));
			params.put("mailSubject" ,SendMail.unicodeEscaped(Subject));
			params.put("toAddress" ,SendMail.unicodeEscaped(To));
			 
			Gson gson = new GsonBuilder().create(); 
			String json = gson.toJson(params);

			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes("UTF-8"));
//			System.err.println("Acc JOSON2 : " + json);
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			
			while ((output = br.readLine()) != null) {
				result = output;
			}
			
			conn.disconnect();
			
			if("Success".equals(result)) {
				return true;
			} else {
				return false;
			}
		} catch(MalformedURLException e) {
			e.printStackTrace();
			
			return false;
		} catch(IOException e) {
			e.printStackTrace();
			
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	private static  void sendMailBGProcess2(String To,String Subject,String Detail) {
//		 try{
//
////		        Properties props = new Properties();
////		        props.put("mail.smtp.host", Constant.Email_Host); // for gmail use smtp.gmail.com
////		        props.put("mail.smtp.auth", "true");
////		        props.put("mail.smtp.starttls.enable", "true");
////		        props.put("mail.smtp.port", Constant.Email_Port);
////		        props.put("mail.smtp.socketFactory.port", Constant.Email_Port);
////
////		        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
////
////		            protected PasswordAuthentication getPasswordAuthentication() {
////		                return new PasswordAuthentication(Constant.Email_Username, Constant.Email_Password);
////		            }
////		        });
////
////		        mailSession.setDebug(true); // Enable the debug mode
////
////		        Message msg = new MimeMessage( mailSession );
////
////		        //--[ Set the FROM, TO, DATE and SUBJECT fields
////		        msg.setFrom( new InternetAddress( "Fisheries" ) );
////		        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(To) );
////		        msg.setSentDate( new Date());
////		        msg.setSubject( Subject );
////
////		        //--[ Create the body of the mail
////		        msg.setText( Detail );
////
////		        //--[ Ask the Transport class to send our mail message
////		        Transport.send( msg );
////
////		    }catch(Exception E){
////		        System.out.println( "Oops something has gone pearshaped!");
////		        System.out.println( E );
////		    }
	}
	
	public static void sendMailBGProcess6(String To,String Subject,String Detail){
		 final String host = Constant.Email_Host;
		 final int port = Constant.Email_Port;
		 final String username = Constant.Email_Username;
		 final String password = Constant.Email_Password;
		 
		 Properties props = new Properties();
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable", "true");

//		 Session session = Session.getInstance(props,
//		     new javax.mail.Authenticator() {
//		         protected PasswordAuthentication getPasswordAuthentication() {
//		         return new PasswordAuthentication(username, password);
//		       }
//		   });
//		 session.setDebug(true);
//
//		    try {
//
//		        Message message = new MimeMessage(session);
//		        message.setFrom(new InternetAddress(username,"Fisheries"));
//		        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(To));
//		        message.setSubject(MimeUtility.encodeText(Subject, "UTF-8", "Q"));
//		        message.setContent(Detail, "text/html; charset=utf-8");
//		        Transport transport = session.getTransport("smtp");
//		        transport.connect (host, port, username, password);
//		        transport.sendMessage(message, message.getAllRecipients());
//		        transport.close();  
//		        System.out.println("Done");
//
//		    } catch (MessagingException e) {
//		        throw new RuntimeException(e);
//		    } catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	} 
	
	public static String unicodeEscaped(String arg) {
    	String unicodeString = "";
    	
//    	System.out.println("Before : " + arg);
    	
    	for (int i=0; i<arg.length(); i++) {
    		char ch = arg.charAt(i);
    		
    		if (ch < 0x10) {
    			unicodeString += "\\u000" + Integer.toHexString(ch);
        	} else if (ch < 0x100) {
        		unicodeString += "\\u00" + Integer.toHexString(ch);
        	} else if (ch < 0x1000) {
        		unicodeString += "\\u0" + Integer.toHexString(ch);
        	} else {
        		unicodeString += "\\u" + Integer.toHexString(ch);
        	}
    	}
    	
//    	System.out.println("After : "+unicodeString +"\n");
    	
    	return unicodeString;
    }
}
