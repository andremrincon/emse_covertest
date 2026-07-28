package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void doFilter_setsCorsHeadersOnGetRequest() {
        String productName = "CORS-Test-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/products/{productName}", productName);

        response.then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", containsString("GET"));
    }

    @Test(timeout = 60000)
    public void doFilter_handlesOptionsRequestWithoutChaining() {
        Response response = given()
            .when()
            .options("/products");

        response.then()
            .statusCode(lessThan(500))
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", containsString("OPTIONS"));
    }

    @Test(timeout = 60000)
    public void doFilter_setsCorsHeadersOnPostRequest() {
        String productName = "CORS-Post-" + UUID.randomUUID().toString().substring(0, 8);

        Response response = given()
            .when()
            .post("/products/{productName}", productName);

        response.then()
            .statusCode(201)
            .header("Access-Control-Allow-Origin", "*");
    }
}