import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NegativeCurrencyTest {
    private static Response response;

    @Test
    public void invalidKeyTest(){
        response = given().header("apikey", "blablabla").contentType("application/json").get(Consts.URL+Consts.RECENT_RATE_ENDPOINT);
        System.out.println(response.asString());
        response.then().statusCode(401);
        response.then().body("message", equalTo("Invalid authentication credentials"));
    }

    @Test
    public void emptyKeyTest(){
        response = given().header("apikey", "").contentType("application/json").get(Consts.URL+Consts.RECENT_RATE_ENDPOINT);
        response.then().statusCode(401);
        response.then().body("message", equalTo("No API key found in request"));
    }

    @Test
    public void wrongDateTest(){
        response = given().header("apikey", Consts.API_KEY).contentType("application/json")
                .get(Consts.URL + Consts.HISTORICAL_RATE_ENDPOINT + "?date=2025-01-01&source=USD&currencies=CAD,EUR,NIS,RUB");
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("error.info", equalTo("You have entered an invalid date. [Required format: date=YYYY-MM-DD]"));
    }
}
