package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionExcludes() {
        String productName = "ProdExcl-" + UUID.randomUUID();
        String configName = "ConfExcl-" + UUID.randomUUID();
        String featA = "FeatA-" + UUID.randomUUID();
        String featB = "FeatB-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featB).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featA).formParam("excludedFeature", featB).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featA).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featB).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionRequires() {
        String productName = "ProdReq-" + UUID.randomUUID();
        String configName = "ConfReq-" + UUID.randomUUID();
        String featA = "FeatA-" + UUID.randomUUID();
        String featB = "FeatB-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featB).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featA).formParam("requiredFeature", featB).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featA).then().statusCode(201);
    }
}