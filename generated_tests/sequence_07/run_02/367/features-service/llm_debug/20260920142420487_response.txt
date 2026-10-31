package ts01glm_5_2_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.util.UUID;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("port", "8080"));
    }

    @Test(timeout = 60000)
    public void corsFilterPassesGetRequestThroughChain() {
        String productName = "CORS-GET-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
        .when()
            .get("/products/" + productName + "/features")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void corsFilterHandlesOptionsRequestWithoutChain() {
        given()
        .when()
            .options("/products/AeroBook-Pro-15/features")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void corsFilterPassesPostRequestThroughChain() {
        String productName = "CORS-POST-" + UUID.randomUUID().toString().substring(0, 8);
        given()
        .when()
            .post("/products/" + productName)
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void corsFilterPassesDeleteRequestThroughChain() {
        String productName = "CORS-DEL-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
        .when()
            .delete("/products/" + productName)
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void corsFilterPassesPutRequestThroughChain() {
        String productName = "CORS-PUT-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature";
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Updated description")
        .when()
            .put("/products/" + productName + "/features/" + featureName)
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void corsFilterSetsAllCORSHeadersOnPreflightRequest() {
        given()
        .when()
            .options("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
            .header("Access-Control-Allow-Headers", "x-requested-with")
            .header("Access-Control-Max-Age", "3600");
    }
}