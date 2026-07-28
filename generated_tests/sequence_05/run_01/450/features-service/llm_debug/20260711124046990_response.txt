package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.config.DecoderConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
        RestAssured.config = RestAssured.config().decoderConfig(DecoderConfig.decoderConfig().contentDecoders(io.restassured.config.DecoderConfig.ContentDecoder.GZIP));
    }

    @Test(timeout = 60000)
    public void getConfigurationWithViolatedExcludesConstraintTriggersWrongProductConfigurationException_1() {
        String productName = "TestProduct-Excl-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB).then().statusCode(500);

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void getConfigurationWithViolatedRequiresConstraintTriggersWrongProductConfigurationException_2() {
        String productName = "TestProduct-Req-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "ReqFeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "ReqFeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "ReqTestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesWithViolatedExcludesConstraintTriggersWrongProductConfigurationException_3() {
        String productName = "TestProduct-CF-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "CFFeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "CFFeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "CFTestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB).then().statusCode(500);

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then()
                .statusCode(lessThan(300));
    }
}