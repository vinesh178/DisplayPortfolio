package test.display.portfolio;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test {

//	public static void main(String[] args) {
//
//		Observable<String> observable = Observable.create(emitter -> {
//
//			Document doc = Jsoup.connect("https://au.investing.com/currencies/aud-inr").get();
//
//			StringBuilder s = new StringBuilder();
//
//			s.append("Aud To Inr: ");
//			s.append(doc.getElementById("_last_1493").text());
//			s.append(" Change: ");
//			s.append(doc.getElementById("_chg_1493").text());
//
//			emitter.onNext(s.toString());
//
//			emitter.onComplete();
//
//		});
//
//		observable.subscribe(System.out::println, System.out::println, () -> System.out.println("Completed"));
//
//	}

//	 public static void main(String[] args) {  
//
//	        Date today = new Date();  
//	        
////	        Calendar c =  Calendar.getInstance();
////	        c.set(2019, 11, 1);
//	        
//	        Calendar calendar = Calendar.getInstance();  
//	        calendar.set(2019, 11, 1);
////	        calendar.setTime(today);  
//
//	      //  calendar.add(Calendar.MONTH, 1);  
//	     //   calendar.set(Calendar.DAY_OF_MONTH, 1);  
//	        calendar.add(Calendar.DATE, -1);  
//
//	        Date lastDayOfMonth = calendar.getTime();  
//
//	        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
//	        System.out.println("Today            : " + sdf.format(today));  
//	        System.out.println("Last Day of Month: " + sdf.format(lastDayOfMonth));  
//	        
//	        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
//	        		calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//	        	
//	        	System.out.println("Weekend");
//	        	calendar.add(Calendar.DATE, -1);
//	        	System.out.println(sdf.format(calendar.getTime()) );
//	        }else {
//	        	System.out.println("Weekday");
//	        }
//	    }  

//	public static void main(String[] args) throws IOException {
//		//https://www.moneycontrol.com/
//		
//		Document doc = Jsoup.connect("https://www.moneycontrol.com").get();
//		
//		Elements table = doc.getElementsByClass("rhsglTbl").eq(1);
//		
//		Elements rows = table.select("tr");
//		
//	    System.out.println(rows.get(0).getElementsByTag("tr").get(0).text());
//	    System.out.println(rows.get(1).getElementsByTag("tr").get(0).text());
//		    
//	}
	
	public static void main(String[] args) throws IOException {
		
	
		
		 Response response = 
                 Jsoup.connect("https://www.valueresearchonline.com/registration/loginprocess.asp")
                 .userAgent("Mozilla/5.0")
                 .timeout(10 * 1000)
                 .method(Method.POST)
                 .data("ref", "%2F%3F")
                 .data("username", "vinesh178")
                 .data("password", "Summer11")
                 .data("btn_submit", "Log In")
                 .followRedirects(true)
                 .execute();
		 
		 Map<String, String> cookie = response.cookies();
		 
		 Document valueResearch = Jsoup.connect("https://www.valueresearchonline.com/port/default.asp?").cookies(cookie).get();
		 
		String portValue = valueResearch.getElementsByClass("Portfolio-value").text();
		String portValueChange = valueResearch.getElementsByClass("Portfolio-value-change").text();
		
		System.out.println(portValue);
		System.out.println(portValueChange);
		
		
//		
	
		
		
         
	}

}
