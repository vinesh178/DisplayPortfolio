package test.display.portfolio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Portfolio {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        getExchangeRateFromTransferwise();
        getSensexData();
        getVasData();
        getIooData();
        getIAFData();
        getIEMData();
        getGoldData();

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