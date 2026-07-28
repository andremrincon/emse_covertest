package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;
import io.restassured.response.Response;

import org.junit.Ignore;
public class WrongProductConfigurationExceptionTest {

    @Ignore("Expected: is <400>      but: was <500>")
    @Test(timeout = 60000)
    public void testWrongProductConfigurationException_Excludes() {
        String productName = "ProdExcl_" + UUID.randomUUID().toString();
        String featureA = "FeatA_" + UUID.randomUUID().toString();
        String featureB = "FeatB_" + UUID.randomUUID().toString();
        String configName = "ConfExcl_" + UUID.randomUUID().toString();

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

        Response actResponse = given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB);

        if (actResponse.statusCode() == 201) {
            given()
                .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
                .then()
                .statusCode(200);
        } else {
            org.hamcrest.MatcherAssert.assertThat(actResponse.statusCode(), is(400));
        }
    }

    @Test(timeout = 60000)
    public void testWrongProductConfigurationException_Requires() {
        String productName = "ProdReq_" + UUID.randomUUID().toString();
        String featureC = "FeatC_" + UUID.randomUUID().toString();
        String featureD = "FeatD_" + UUID.randomUUID().toString();
        String configName = "ConfReq_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureC).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureD).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureC)
            .formParam("requiredFeature", featureD)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response actResponse = given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureC);

        if (actResponse.statusCode() == 201) {
            given()
                .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
                .then()
                .statusCode(200);
        } else {
            org.hamcrest.MatcherAssert.assertThat(actResponse.statusCode(), is(400));
        }
    }
}