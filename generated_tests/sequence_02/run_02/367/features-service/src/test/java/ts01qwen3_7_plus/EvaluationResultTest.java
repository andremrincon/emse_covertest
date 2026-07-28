package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @Test(timeout = 60000)
    public void testEvaluationResultInstantiation() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }
}