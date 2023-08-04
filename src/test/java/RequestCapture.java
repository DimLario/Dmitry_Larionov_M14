
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.filter.log.RequestLoggingFilter;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class RequestCapture {
    private static Response response;
    private static String capturedRequest;

    @Test
    public void requestCapture() {
        // Create a PrintStream to capture the request logs
        PrintStream requestStream = System.out;

        // Create a custom request specification with a logging filter to capture the request
        RequestSpecification requestSpec = given().header("apikey", Consts.API_KEY).contentType("application/json")
                .filter(new RequestLoggingFilter(requestStream));

        // Build the request without sending it
        capturedRequest = requestSpec.get(Consts.URL + Consts.RECENT_RATE_ENDPOINT).asString();

        // Send the request and capture the response
        response = requestSpec.get(Consts.URL + Consts.RECENT_RATE_ENDPOINT);

        System.out.println(capturedRequest); // Print the captured request text
    }
}
