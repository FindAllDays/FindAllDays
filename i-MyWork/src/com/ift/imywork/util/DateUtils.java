package com.ift.imywork.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {

	static String[] Month ={"","มกราคม","กุมภาพันธ์","มีนาคม","เมษายน","พฤษภาคม","มิถุนายน","กรกฎาคม","สิงหาคม","กันยายน","ตุลาคม","พฤศจิกายน","ธันวาคม"};
	
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}
public static String getyyyyMMddToDateText(String date){
		
		if(StringUtils.isEmpty(date)){
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", new Locale("th", "TH"));
		try{
			return getdd_MM_yyyyToDateText(getDateToStringTh(df.parse(date),"dd/MM/yyyy"));
		}
		catch(Exception ex){
			return "";
		}
	}
	
	
	public static String getDateToStringTh(Date da,String fomat) {
		if(da==null)return "";
		SimpleDateFormat df = new SimpleDateFormat(fomat, new Locale("th", "TH"));
		return df.format(da);
	}
	public static String getDateToString(Date da,String fomat) {
		if(da==null)return "";
		SimpleDateFormat df = new SimpleDateFormat(fomat);
		return df.format(da);
	}

	public static Date getDateSplitTime(Date da,String time) {
		if(da==null)return null;
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date daOut = null; 
		try{
			daOut=df2.parse(df.format(da)+" "+time);
		}
		catch(Exception ex){
			
		}
		return daOut;  
	}
	public static String getdd_MM_yyyyToyyyyMMdd(String date){
		String[] da=date.split("/");
		if(da.length!=3)return "";  
		return da[2]+da[1]+da[0];
	}
	public static String getdd_MM_yyyyToyyyyMMddUS(String date){
		String[] da=date.split("/");
		if(da.length!=3)return "";  
		return (Integer.parseInt(da[2])-543)+da[1]+da[0];
	}
	public static String getdd_MM_yyyyTodd_MM_yyyyUS(String date){
		String[] da=date.split("/");
		if(da.length!=3)return "";
		return da[0]+"/"+da[1]+"/"+(Integer.parseInt(da[2])-543);
	}
	public static Date getdd_MM_yyyyToDate(String date){
		String[] da=date.split("/");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		System.out.println("length :"+da.length);
		Date daOut = null;
		if(da.length!=3)return null;
//		System.out.println("convert ... "); 
		try{
			daOut=df.parse(da[0]+"/"+da[1]+"/"+(Integer.parseInt(da[2])-543));
		}
		catch(Exception ex){
			
		}
		return daOut;  
		
	}
	
	public static Date getFomatToDate(String date,String fomat){
		
		SimpleDateFormat df = new SimpleDateFormat(fomat);
		Date daOut = null; 
		try{
			daOut=df.parse(date);
		}
		catch(Exception ex){
			
		}
		return daOut;  
		
	}
	public static Date getFomatThToDate(String date,String fomat){
		
		SimpleDateFormat df = new SimpleDateFormat(fomat, new Locale("th", "TH"));
		
		Date daOut = null;

		try{
			daOut=df.parse(date);
		}
		catch(Exception ex){
			
		}
		return daOut;  
		
	}
	
	
	public static String getMM_yyyyToDateUS(String month){
		String outDate="";
		String[] da1=month.split("/");
		if(da1.length!=2)return "";
		outDate=(Integer.parseInt(da1[1])-543)+""+da1[0];
		return outDate;
	}
	public static String getMM_yyyyToDateTH(String month){
		String outDate="";
		String[] da1=month.split("/");
		if(da1.length!=2)return "";
		outDate=(da1[1])+""+da1[0];
		return outDate;
	}
	public static Date getMM_yyyyToDate(String date){
		
		String[] da=date.split("/");
		SimpleDateFormat df = new SimpleDateFormat("MM/yyyy");
		
		Date daOut = null;
		if(da.length!=2)return null;
		
		try{
			daOut=df.parse(da[0]+"/"+(Integer.parseInt(da[1])-543));
		}
		catch(Exception ex){
			
		}
		return daOut;  
		
	}
	
	public static String getdd_MM_yyyyToDateText(String date1){
		String outDate="";
		String[] da1=date1.split("/");
		if(da1.length!=3)return "";
		int day1=Integer.parseInt(da1[0]);
		int mon1=Integer.parseInt(da1[1]);
		outDate=day1+" "+Month[mon1]+" "+da1[2];
		return outDate;
	}
	public static String getdd_MM_yyyyUSToDateText(String date1){
		String outDate="";
		String[] da1=date1.split("/");
		if(da1.length!=3)return "";
		int day1=Integer.parseInt(da1[0]);
		int mon1=Integer.parseInt(da1[1]);
		int year1=Integer.parseInt(da1[1])+543;
		outDate=day1+" "+Month[mon1]+" "+year1;
		return outDate;
	}
	public static String getdd_MM_yyyyToDateText(String date1,String date2){
		String outDate="";
		String[] da1=date1.split("/");
		String[] da2=date2.split("/");
		if(da1.length!=3||da2.length!=3)return "";
		
		int day1=Integer.parseInt(da1[0]);
		int mon1=Integer.parseInt(da1[1]);
		int day2=Integer.parseInt(da2[0]);
		int mon2=Integer.parseInt(da2[1]);
		outDate=day1+" "+Month[mon1]+" "+da1[2]+"  ถึงวันที่  "+day2+" "+Month[mon2]+" "+da2[2];
		
		return outDate;
	}
	public static String getMM_yyyyToDateText(String date1){
		String outDate="";
		String[] da1=date1.split("/");
		if(da1.length!=2)return "";
		int mon1=Integer.parseInt(da1[0]);
		outDate=Month[mon1]+" "+da1[1];
		return outDate;
	}
	public static String getMM_yyyyToDateText(String date1,String date2){
		String outDate="";
		String[] da1=date1.split("/");
		String[] da2=date2.split("/");
		if(da1.length!=2||da2.length!=2)return "";
		
		
		int mon1=Integer.parseInt(da1[0]);
		int mon2=Integer.parseInt(da2[0]);
		
		outDate=Month[mon1]+" "+da1[1]+"  ถึงเดือน   "+Month[mon2]+" "+da2[1];
		
		return outDate;
	}
	public static String getFormatToFormat(String date,String inputFormat,String outFormat) throws Exception {// 
		
		SimpleDateFormat dfI = new SimpleDateFormat(inputFormat);
		SimpleDateFormat dfO = new SimpleDateFormat(outFormat);
		Date da = dfI.parse(date);
		return dfO.format(da);
	}
	public static String customFormat(String pattern, int value ) {
		  		// customFormat("###,###.###", 123456.789);
		      DecimalFormat myFormatter = new DecimalFormat(pattern);
		      String output = myFormatter.format(value);
		      return output;
		     
   }
	public static String customFormat(String pattern, BigDecimal value ) {
  		// customFormat("###,###.###", 123456.789);
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      String output = myFormatter.format(value);
      return output;
     
}
	public static String customFormat(String pattern, double value ) {
  		// customFormat("###,###.###", 123456.789);
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      String output = myFormatter.format(value);
      return output;
     
}
	public static int calDay(Date date1,Date date2) {
		return (int)( ( date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
}
