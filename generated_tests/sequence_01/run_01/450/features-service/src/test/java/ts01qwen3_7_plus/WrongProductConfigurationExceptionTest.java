package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionOnConflictingFeature() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String featureA = "FeatureA_" + UUID.randomUUID().toString();
        String featureB = "FeatureB_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB)
            .then()
            .statusCode(500);
    }
}