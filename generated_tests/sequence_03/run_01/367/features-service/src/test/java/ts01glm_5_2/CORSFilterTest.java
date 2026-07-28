package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static String getBaseUrl() {
        String host = System.getenv("APP_HOST");
        if (host == null || host.isEmpty()) {
            host = "localhost";
        }
        String port = System.getenv("APP_PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }
        return "http://" + host + ":" + port;
    }

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = getBaseUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void corsFilterSetsHeadersOnGetRequest() {
        String productName = "AeroBook-Pro-15";

        given()
                .when()
                .get("/products/{productName}/features", productName)
                .then()
                .statusCode(anyOf(is(200), is(500)))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
                .header("Access-Control-Allow-Headers", "x-requested-with")
                .header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void corsFilterHandlesOptionsRequestWithoutChaining() {
        given()
                .when()
                .options("/products")
                .then()
                .statusCode(anyOf(is(200), is(204)))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void corsFilterSetsHeadersOnPostRequest() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(anyOf(is(201), is(500)))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "x-requested-with")
                .header("Access-Control-Max-Age", "3600");
    }
}