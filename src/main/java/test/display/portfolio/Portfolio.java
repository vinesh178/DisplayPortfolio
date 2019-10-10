package test.display.portfolio;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Portfolio {
	

	public static void main(String[] args) throws IOException {
		
		//getExchangeRate();
		//getPortfolioValue();

		Document sensexDoc = Jsoup.connect("https://www.moneycontrol.com").get();

		Elements table = sensexDoc.getElementsByClass("rhsglTbl").eq(1);

		Elements rows = table.select("tr");

		System.out.println(rows.get(0).getElementsByTag("tr").get(0).text());
		System.out.println(rows.get(1).getElementsByTag("tr").get(0).text());

	}

	public static String getExchangeRate() {

		Document doc;
		StringBuilder s = new StringBuilder();
		try {
			doc = Jsoup.connect("https://au.investing.com/currencies/aud-inr").get();

			s.append("Aud To Inr: ");
			s.append(doc.getElementById("_last_1493").text());
			s.append(" Change: ");
			s.append(doc.getElementById("_chg_1493").text());
			System.out.println(s.toString());
			return s.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return s.toString();
	}
	
	public static String getPortfolioValue() throws IOException {
		
		
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
		 
		 Map<String, String> cookies =  response.cookies();
		 
		 Document valueResearch = Jsoup.connect("https://www.valueresearchonline.com/port/default.asp?").cookies(cookies).get();
		 
		String portValue = valueResearch.getElementsByClass("Portfolio-value").text();
		String portValueChange = valueResearch.getElementsByClass("Portfolio-value-change").text();
		System.out.println(portValue.concat("\n").concat(portValueChange));
		
		return portValue.concat("\n").concat(portValueChange);
	}

}