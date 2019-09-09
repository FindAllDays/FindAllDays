package com.ift.imywork.util;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.faces.context.FacesContext;





public class ConverFormatUtil {
	

//	public static void main(String[] args) {
//		getLastDay("201711");
//		System.out.println(getCountMonth(new Date() ,getFomatToDate("01052018", "ddMMyyyy")));
//		System.out.println(getDateToString(getNextMonth(new Date(),20),"dd/MM/yyyy"));
		
//	}
	
	static String[] Month ={"","มกราคม","กุมภาพันธ์","มีนาคม","เมษายน","พฤษภาคม","มิถุนายน","กรกฎาคม","สิงหาคม","กันยายน","ตุลาคม","พฤศจิกายน","ธันวาคม"};
	
	public static String  wordWarpLine(String input, int maxChar) {	
		if(input==null)return "";
		if(maxChar==0)return input;
		Locale thaiLocale = new Locale("th");
		BreakIterator boundary = BreakIterator.getWordInstance(thaiLocale);	 
		
		boundary.setText(input);
		StringBuffer strout = new StringBuffer();
		String txtline="";
		int start = boundary.first();
		int i=1;
		for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
			
			 if(input.substring(start, end).contains("\n")){
				 txtline+=input.substring(start, end);
				 strout.append(txtline);
				 txtline="";
			 }
			 else if(countText(txtline+input.substring(start, end))>maxChar){
				 	txtline+=input.substring(start, end);
				 	strout.append(txtline + "\n");
				 	txtline="";
				 	
			 }
			 else txtline+=input.substring(start, end);
			
		}
		if(txtline!="")strout.append(txtline);
	 
	
	 return strout.toString();
	}
	public static int countText(String arg){
		arg=arg.replace("\n", "");
		arg=arg.replace("ิ", "");
		arg=arg.replace("ี", "");
		arg=arg.replace("ื", "");
		arg=arg.replace("ุ", "");
		arg=arg.replace("ู", "");
		arg=arg.replace("ั", "");
		arg=arg.replace("๊", "");
		arg=arg.replace("็", "");
		arg=arg.replace("้", "");
		arg=arg.replace("ึ", "");
		arg=arg.replace("ึ", "");
		arg=arg.replace("์", "");
		return arg.length();
	}
	
	public static boolean checkPersonID(String id13){
		if(id13.length() != 13) return false;
		double sum=0;
		for(int i=0; i < 12; i++)
			sum += Double.parseDouble(id13.charAt(i)+"")*(13-i);
		if((11-sum%11)%10!=Double.parseDouble(id13.charAt(12)+"")) return false;
		return true;
	}
	
	public static Date getNextMonth(Date date,int num){
		Calendar calendar = Calendar.getInstance();         
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,num );
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date nextMonthFirstDay = calendar.getTime();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date nextMonthLastDay = calendar.getTime();
		return nextMonthLastDay;
}
	
	public static int getCountMonth(Date startDate,Date endDate){
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(startDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
	
		return diffMonth;
	}	
	public static String getLastDay(String yyyymm){
//		Date date = ConverFormatUtil.getFomatToDate(yyyy+mm, "yyyyMM");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date convertedDate;
		
		try {
			convertedDate = dateFormat.parse(yyyymm+"01");
			
			Calendar c = Calendar.getInstance();
			c.setTime(convertedDate);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//			System.out.println(getDateToString(c.getTime(),"dd"));
			return	getDateToString(c.getTime(),"dd");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return "30";
		}
		
//		if("01".equals(mm)
//			||"03".equals(mm)
//			||"05".equals(mm)
//			||"07".equals(mm)
//			||"08".equals(mm)
//			||"10".equals(mm)
//			||"12".equals(mm)){
//			return "31";
//		}
//		if("02".equals(mm)){
//			return "28";
//		}
//		return "30";
		
	}
	public static String getCurrentDate(){
	
		String output;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
		output = formatter.format(new Date());
		return output;
	}
	public static String getCurrentMonthTh(){
		
		String output;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy", new Locale("th", "TH"));
		output = formatter.format(new Date());
		return output;
	}


	public static String getCurrentMonth(){
		
		String output;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
		output = formatter.format(new Date());
		return output;
	}
	
	public static String getyyyyMMddToDateText(String date){
		
		if(date==null||date=="")return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", new Locale("th", "TH"));
		try{
			return getdd_MM_yyyyToDateText(getDateToStringTh(df.parse(date),"dd/MM/yyyy"));
		}
		catch(ParseException ex){
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
		catch(ParseException ex){
			
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
		catch(ParseException ex){
			
		}
		return daOut;  
		
	}
	
	public static Date getFomatToDate(String date,String fomat){
		
		SimpleDateFormat df = new SimpleDateFormat(fomat);
		Date daOut = null; 
		try{
			daOut=df.parse(date);
		}
		catch(ParseException ex){
			
		}
		return daOut;  
		
	}
	public static Date getFomatThToDate(String date,String fomat){
		
		SimpleDateFormat df = new SimpleDateFormat(fomat, new Locale("th", "TH"));
		
		Date daOut = null;

		try{
			daOut=df.parse(date);
		}
		catch(ParseException ex){
			
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
		catch(ParseException ex){
			
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
	public static String getFormatToFormat(String date,String inputFormat,String outFormat) throws ParseException {// 
		
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
	public static boolean compareNowTime(String time1,String time2) {
//		Calendar now = Calendar.getInstance();

//	    int hour = now.get(Calendar.HOUR);
//	    int minute = now.get(Calendar.MINUTE);
		SimpleDateFormat inputParser = new SimpleDateFormat("HH:mm", new Locale("th", "TH"));
	    Date now = new Date();
	    Date date =parseDate(inputParser.format(now));
	    Date dateCompareOne = parseDate(time1);
	    Date dateCompareTwo = parseDate(time2);

	    return dateCompareOne.before( date ) && dateCompareTwo.after(date);
	  
	}
	private static  Date parseDate(String date) {
		SimpleDateFormat inputParser = new SimpleDateFormat("HH:mm", new Locale("th", "TH"));
	    try {
	        return inputParser.parse(date);
	    } catch (java.text.ParseException e) {
	        return new Date(0);
	    }
	}
	public static double calHour(String startTime,String endTime) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		if(startTime!=null&&startTime!=""&&endTime!=null&&endTime!=""){
			String[] t1=startTime.split(":");
			String[] t2=endTime.split(":");
			if(t1[0].length()==1)startTime="0"+startTime;
			if(t2[0].length()==1)endTime="0"+endTime;
		
			Date date1;
			Date date2;
			try {
				date1 = format.parse(startTime);
				date2 = format.parse(endTime);
				long difference = date2.getTime() - date1.getTime(); 
				long diffInMilli = difference / (60 * 1000) % 60;
				double diffInHours = difference / (60 * 60 * 1000) % 24;
			
			
//				logger.debug("diffInHours:"+diffInHours+" , diffInMilli:"+diffInMilli);
				
				if(diffInMilli>=45)
					diffInHours=diffInHours+1;
				else if(diffInMilli>=15 && diffInMilli<45)
					diffInHours=diffInHours+0.5;
				if(diffInHours>8.5)
					diffInHours=8.5;
				return diffInHours;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				return 0;
			}
			
			
			
		}
		return 0;
	}
	
	public static Object cloneObject(Object obj){
        try{
            Object clone = obj.getClass().newInstance();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if(field.get(obj) == null || Modifier.isFinal(field.getModifiers())){
                    continue;
                }
                if(field.getType().isPrimitive() || field.getType().equals(String.class)
                        || field.getType().getSuperclass().equals(Number.class)
                        || field.getType().equals(Boolean.class)){
                    field.set(clone, field.get(obj));
                }else{
                    Object childObj = field.get(obj);
                    if(childObj == obj){
                        field.set(clone, clone);
                    }else{
                        field.set(clone, cloneObject(field.get(obj)));
                    }
                }
            }
            return clone;
        }catch(Exception e){
            return null;
        }
    }
	
}
