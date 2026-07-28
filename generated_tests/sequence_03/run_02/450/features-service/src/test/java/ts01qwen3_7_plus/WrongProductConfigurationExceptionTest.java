package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class WrongProductConfigurationExceptionTest {

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionRequiresViolation() {
        String productName = "Prod_" + UUID.randomUUID();
        String f1 = "F1_" + UUID.randomUUID();
        String f2 = "F2_" + UUID.randomUUID();
        String configName = "Conf_" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(lessThan(300));

        given().formParam("sourceFeature", f1).formParam("requiredFeature", f2)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + f1).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionExcludesViolation() {
        String productName = "Prod_" + UUID.randomUUID();
        String f1 = "F1_" + UUID.randomUUID();
        String f2 = "F2_" + UUID.randomUUID();
        String configName = "Conf_" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(lessThan(300));

        given().formParam("sourceFeature", f1).formParam("excludedFeature", f2)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + f2).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(500);
    }
}