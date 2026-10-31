package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("BASE_URL");
        }
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintViolationOnAddFeatureToConfiguration() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;
        String configName = "Config-" + uuid;

        int status;

        status = given().when().post("/products/" + productName).then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/features/" + featureA)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/features/" + featureB)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB)
            .then().extract().statusCode();
        Assert.assertEquals(500, status);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintViolationOnGetConfiguration() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct2-" + uuid;
        String featureA = "ReqA-" + uuid;
        String featureB = "ReqB-" + uuid;
        String configName = "Config2-" + uuid;

        int status;

        status = given().when().post("/products/" + productName).then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/features/" + featureA)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/features/" + featureB)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .get("/products/" + productName + "/configurations/" + configName)
            .then().extract().statusCode();
        Assert.assertEquals(500, status);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintViolationOnGetConfigurationFeatures() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct3-" + uuid;
        String featureA = "ExclA-" + uuid;
        String featureB = "ExclB-" + uuid;
        String configName = "Config3-" + uuid;

        int status;

        status = given().when().post("/products/" + productName).then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/features/" + featureA)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/features/" + featureB)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB)
            .then().extract().statusCode();
        Assert.assertTrue(status < 300);

        status = given().when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().extract().statusCode();
        Assert.assertEquals(500, status);
    }
}