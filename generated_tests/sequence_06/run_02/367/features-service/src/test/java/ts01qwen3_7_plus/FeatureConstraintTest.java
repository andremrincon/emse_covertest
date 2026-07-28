package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testSetIdViaRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post(baseUrl + "/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }
}