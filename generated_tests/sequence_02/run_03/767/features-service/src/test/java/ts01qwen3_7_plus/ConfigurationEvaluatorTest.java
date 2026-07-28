package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.specification.RequestSpecification;

public class ConfigurationEvaluatorTest {

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithDerivedFeatures() {
        String productName = "Prod1_" + UUID.randomUUID().toString();
        String configName = "Conf1_" + UUID.randomUUID().toString();

        given().post("/products/" + productName).then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/features/F1").then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/features/F2").then().statusCode(lessThan(300));
        RequestSpecification req1 = given();
        req1.formParam("sourceFeature", "F1").formParam("requiredFeature", "F2").post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/configurations/" + configName + "/features/F1").then().statusCode(lessThan(300));

        given().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithConstraintsNoDerivedFeatures() {
        String productName = "Prod2_" + UUID.randomUUID().toString();
        String configName = "Conf2_" + UUID.randomUUID().toString();

        given().post("/products/" + productName).then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/features/F3").then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/features/F4").then().statusCode(lessThan(300));
        RequestSpecification req2 = given();
        req2.formParam("sourceFeature", "F3").formParam("excludedFeature", "F4").post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/configurations/" + configName + "/features/F3").then().statusCode(lessThan(300));

        given().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithNoConstraints() {
        String productName = "Prod3_" + UUID.randomUUID().toString();
        String configName = "Conf3_" + UUID.randomUUID().toString();

        given().post("/products/" + productName).then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/features/F5").then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().post("/products/" + productName + "/configurations/" + configName + "/features/F5").then().statusCode(lessThan(300));

        given().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }
}