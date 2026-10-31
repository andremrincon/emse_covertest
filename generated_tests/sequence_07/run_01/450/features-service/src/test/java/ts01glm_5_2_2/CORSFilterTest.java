package ts01glm_5_2_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testDoFilterGetRequestSetsCorsHeadersAndContinuesChain() {
        String productName = "AeroBook-Pro-15-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/features", productName)
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
                .header("Access-Control-Allow-Headers", "x-requested-with")
                .header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void testDoFilterOptionsRequestSetsCorsHeadersWithoutContinuingChain() {
        given()
                .when()
                .options("/products")
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(204), equalTo(404)))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
                .header("Access-Control-Allow-Headers", "x-requested-with")
                .header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void testDoFilterPostRequestSetsCorsHeaders() {
        String productName = "Smartwatch-Series-8-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, "Blood-Oxygen-Sensor")
                .then()
                .statusCode(201)
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterDeleteRequestSetsCorsHeaders() {
        String productName = "SmartWatch-Pro-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, "heart-rate-monitor")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/features/{featureName}", productName, "heart-rate-monitor")
                .then()
                .statusCode(204)
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterPutRequestSetsCorsHeaders() {
        String productName = "AeroBook-Pro-15-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "backlit-keyboard";

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("description", "RGB backlit keyboard with customizable zones and per-key lighting.")
                .when()
                .put("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterGetProductsListSetsCorsHeaders() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(404)))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
                .header("Access-Control-Allow-Headers", "x-requested-with")
                .header("Access-Control-Max-Age", "3600");
    }
}