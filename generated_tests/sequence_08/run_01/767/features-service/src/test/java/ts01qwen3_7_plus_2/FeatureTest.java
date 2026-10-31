package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSetNameAndSetProduct() {
        String productName = "Prod-Name-Set-" + System.currentTimeMillis();
        String featureName = "Feat-Name-Set-" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProduct() {
        String productName = "Prod-Get-Prod-" + System.currentTimeMillis();
        String featureName = "Feat-Get-Prod-" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEqualsViaConfigurationFeature() {
        String productName = "Prod-Eq-Cfg-" + System.currentTimeMillis();
        String featureName = "Feat-Eq-Cfg-" + System.currentTimeMillis();
        String configName = "Cfg-Eq-" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEqualsViaUpdateFeature() {
        String productName = "Prod-Eq-Upd-" + System.currentTimeMillis();
        String featureName = "Feat-Eq-Upd-" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Updated description")
            .when()
            .put("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductViaConfigFeatures() {
        String productName = "Prod-Get-Cfg-" + System.currentTimeMillis();
        String featureName = "Feat-Get-Cfg-" + System.currentTimeMillis();
        String configName = "Cfg-Get-" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }
}