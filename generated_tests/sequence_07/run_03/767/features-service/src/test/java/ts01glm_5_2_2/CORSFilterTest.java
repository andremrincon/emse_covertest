package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void corsHeadersPresentOnGetRequest() {
        String productName = "AeroBook-Pro-15-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/features")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void corsHeadersPresentOnOptionsPreflight() {
        given()
            .when()
                .options("/products/AeroBook-Pro-15")
            .then()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
                .header("Access-Control-Allow-Headers", "x-requested-with")
                .header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void corsHeadersPresentOnPostRequest() {
        String productName = "Smartwatch-Series-8-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/Blood-Oxygen-Sensor")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }
}