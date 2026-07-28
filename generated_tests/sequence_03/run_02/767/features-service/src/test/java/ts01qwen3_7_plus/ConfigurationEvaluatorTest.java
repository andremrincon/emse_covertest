package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithoutDerivedFeatures() {
        String productName = "Prod1_" + UUID.randomUUID().toString();
        String feature1 = "Feat1_" + UUID.randomUUID().toString();
        String feature2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Conf1_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature2)
            .formParam("requiredFeature", feature1)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithDerivedFeatures() {
        String productName = "Prod2_" + UUID.randomUUID().toString();
        String feature1 = "Feat1_" + UUID.randomUUID().toString();
        String feature2 = "Feat2_" + UUID.randomUUID().toString();
        String feature3 = "Feat3_" + UUID.randomUUID().toString();
        String configName = "Conf2_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature3).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature2)
            .formParam("requiredFeature", feature3)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithInvalidConstraint() {
        String productName = "Prod3_" + UUID.randomUUID().toString();
        String feature1 = "Feat1_" + UUID.randomUUID().toString();
        String feature2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Conf3_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(500);

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }
}