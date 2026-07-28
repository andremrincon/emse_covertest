package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintActivatesDerivedFeature() {
        String productName = "Product-Requires-Test-" + System.currentTimeMillis();
        String feature1 = "Feature-Source";
        String feature2 = "Feature-Required";
        String configName = "Config-Requires";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
            .when().post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given()
            .when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200)
            .body("$", hasItem(feature2));
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintPreventsExcludedFeature() {
        String productName = "Product-Excludes-Test-" + System.currentTimeMillis();
        String feature1 = "Feature-Source-Excl";
        String feature2 = "Feature-Excluded";
        String configName = "Config-Excludes";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
            .when().post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(500);

        given()
            .when().get("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(200)
            .body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testNoConstraintsActivatesFeature() {
        String productName = "Product-NoConstraints-Test-" + System.currentTimeMillis();
        String feature1 = "Feature-Solo";
        String configName = "Config-Solo";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given()
            .when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200)
            .body("$", hasItem(feature1));
    }
}