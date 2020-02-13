package test.display.portfolio;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Portfolio {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        getTransferWiseFor2000Aud();
        getExchangeRateFromTransferwise();
        getSensexData();
        getVasData();
        getIooData();
        getIAFData();
        getIEMData();
        getGoldData();
    }

    public static void getTransferWiseFor2000Aud() throws IOException {
        HttpPost post = new HttpPost("https://transferwise.com/gateway/v2/quotes/");

        JSONObject request = new JSONObject();
        request.put("sourceAmount",2000);
        request.put("sourceCurrency","AUD");
        request.put("targetCurrency","INR");
        request.put("preferredPayIn",null);
        request.put("guaranteedTargetAmount",false);

        post.addHeader("Content-type", "application/json");

        post.setEntity(new StringEntity(request.toString()));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            JSONParser parse = new JSONParser();
            JSONObject jobj = (JSONObject) parse.parse(EntityUtils.toString(response.getEntity()));

            JSONArray jsonarr_1 = (JSONArray) jobj.get("paymentOptions");

            JSONObject jobj2 = (JSONObject) jsonarr_1.get(0);
            System.out.println("Transfering 2000 AUD in Transferwise: " + jobj2.get("targetAmount"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void getSensexData() {

        Document sensexDoc = null;
        try {
            sensexDoc = Jsoup.connect("https://www.moneycontrol.com").get();
        } catch (IOException e) {
            System.out.println("Error in getting sensex doc: " + e.getMessage());
        }

        Elements table = sensexDoc.getElementsByClass("rhsglTbl").eq(1);

        Elements rows = table.select("tr");

        System.out.println(rows.get(0).getElementsByTag("tr").get(0).text());
        System.out.println(rows.get(1).getElementsByTag("tr").get(0).text());
    }


    public static void getExchangeRateFromTransferwise() {

        Document doc;
        StringBuilder s = new StringBuilder();
        try {
            doc = Jsoup.connect("https://transferwise.com/au/currency-converter/aud-to-inr-rate").get();
            s.append("Aud To Inr: ");
            s.append(doc.getElementsByClass("text-success").first().text());
            System.out.println(s.toString());
        } catch (IOException e) {
            System.out.println("Error getting exchange rate");
        }
    }

    private static void getVasData() {

        Document doc;
        StringBuilder s = new StringBuilder();
        try {
            doc = Jsoup.connect("https://www.morningstar.com.au/ETFs/NewsAndQuotes/VAS").get();
            s.append("VAS:  ");
            s.append(doc.getElementsByClass("N_QPriceLeft").text());
            System.out.println(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getIooData() {

        Document doc;
        StringBuilder s = new StringBuilder();
        try {
            doc = Jsoup.connect("https://www.morningstar.com.au/ETFs/NewsAndQuotes/IOO").get();
            s.append("GLB:  ");
            s.append(doc.getElementsByClass("N_QPriceLeft").text());
            System.out.println(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getIEMData() {

        Document doc;
        StringBuilder s = new StringBuilder();
        try {
            doc = Jsoup.connect("https://www.morningstar.com.au/ETFs/NewsAndQuotes/IEM").get();
            s.append("EMR:  ");
            s.append(doc.getElementsByClass("N_QPriceLeft").text());
            System.out.println(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getIAFData() {

        Document doc;
        StringBuilder s = new StringBuilder();
        try {
            doc = Jsoup.connect("https://www.morningstar.com.au/ETFs/NewsAndQuotes/IAF").get();
            s.append("BND:  ");
            s.append(doc.getElementsByClass("N_QPriceLeft").text());
            System.out.println(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getGoldData() {

        Document doc;
        StringBuilder s = new StringBuilder();
        try {
            doc = Jsoup.connect("https://www.morningstar.com.au/ETFs/NewsAndQuotes/GOLD").get();
            s.append("GLD:  ");
            s.append(doc.getElementsByClass("N_QPriceLeft").text());
            System.out.println(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}