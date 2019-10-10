package test.display.portfolio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Portfolio {

	public static void main(String[] args) throws IOException {

		getExchangeRate();
		System.out.println(getPortfolioValue());

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

		Boolean IsCookieFileExist = Files.exists(Paths.get("C:\\workspace\\vrcookie.txt"), LinkOption.NOFOLLOW_LINKS);
		Map<String, String> cookieContent = new HashMap<String, String>();

		if (IsCookieFileExist) {

			File existingCookieFile = new File("C:\\workspace\\vrcookie.txt");

			Properties properties = new Properties();
			properties.load(new FileInputStream(existingCookieFile));

			for (String key : properties.stringPropertyNames()) {
				cookieContent.put(key, properties.get(key).toString());
			}

		} else {

			Response response = Jsoup.connect("https://www.valueresearchonline.com/registration/loginprocess.asp")
					.userAgent("Mozilla/5.0").timeout(10 * 1000).method(Method.POST).data("ref", "%2F%3F")
					.data("username", "vinesh178").data("password", "Summer11").data("btn_submit", "Log In")
					.followRedirects(true).execute();

			cookieContent = response.cookies();

			// save cookie to file
			File cookieFile = new File("C:\\workspace\\vrcookie.txt");
			Properties properties = new Properties();

			for (Map.Entry<String, String> entry : cookieContent.entrySet()) {
				properties.put(entry.getKey(), entry.getValue());
			}

			properties.store(new FileOutputStream(cookieFile), null);

		}

		Document valueResearch = Jsoup.connect("https://www.valueresearchonline.com/port/default.asp?")
				.cookies(cookieContent).get();

		String portValue = valueResearch.getElementsByClass("Portfolio-value").get(0).childNode(0).toString();
		String portValueChange = valueResearch.getElementsByClass("Portfolio-value-change").get(0).childNode(0)
				.toString();

		return "Value Research portfolio details : " + portValue.trim().concat(" | ").concat(portValueChange.trim());
	}

}