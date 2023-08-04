import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PositiveCurrencyTest {
    private static Response response;

    @Test
    public void getAllRecentCurrenciesResponseCodeTest(){
        response = given().header("apikey", Consts.API_KEY).contentType("application/json").get(Consts.URL+Consts.RECENT_RATE_ENDPOINT);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("success", equalTo(true));
        response.then().body("timestamp", notNullValue());
        response.then().body("source", equalTo("USD"));
        response.then().body("quotes", notNullValue());


    }

    @Test
    public void getAllCurrenciesPerformanceTest(){
        response = given().header("apikey", Consts.API_KEY).contentType("application/json").get(Consts.URL+Consts.RECENT_RATE_ENDPOINT);
        response.then().time(lessThan(2000l));
    }

    @Test
    public void getAllAvailableCurrenciesCodeTest(){
        response = given().header("apikey", Consts.API_KEY).contentType("application/json").get(Consts.URL+Consts.ALL_CURRENCIES_LIST_ENDPOINT);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("success", equalTo(true));
    }

    @Test
    public void getCADCurrencyResponseCodeTest(){
        response = given().header("apikey", Consts.API_KEY).contentType("application/json")
                .get(Consts.URL + Consts.RECENT_RATE_ENDPOINT + "&source=USD&currencies=CAD");
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("quotes.USDCAD", notNullValue());
    }

    @Test
    public void getEURCurrencyResponseCodeTest(){
        response = given().header("apikey", Consts.API_KEY).contentType("application/json")
                .get(Consts.URL + Consts.RECENT_RATE_ENDPOINT + "&source=USD&currencies=EUR");
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("quotes.USDEUR", notNullValue());
    }

    @Test
    public void getRUBCurrencyResponseCodeTest(){
        response = given().header("apikey", Consts.API_KEY).contentType("application/json")
                .get(Consts.URL + Consts.RECENT_RATE_ENDPOINT + "&source=USD&currencies=RUB");
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("quotes.USDRUB", notNullValue());
    }

    @Test
    public void getSeveralCurrenciesResponseCodeTest(){
        response = given().header("apikey", Consts.API_KEY).contentType("application/json")
                .get(Consts.URL + Consts.RECENT_RATE_ENDPOINT + "&&source=USD&currencies=CAD,EUR,RUB");
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("quotes.USDCAD", notNullValue());
        response.then().body("quotes.USDEUR", notNullValue());
        response.then().body("quotes.USDRUB", notNullValue());
    }

    @Test
    public void getHistoricalCurrencyResponseCodeTest(){
        response = given().header("apikey", Consts.API_KEY).contentType("application/json")
                .get(Consts.URL + Consts.HISTORICAL_RATE_ENDPOINT + "?date=2018-01-01&source=USD&currencies=CAD,EUR,RUB");
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("historical", equalTo(true));
        response.then().body("date", equalTo("2018-01-01"));
    }
}
