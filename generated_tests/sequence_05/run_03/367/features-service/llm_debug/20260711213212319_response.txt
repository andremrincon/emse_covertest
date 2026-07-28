package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().baseUri(getBaseUrl()).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(getBaseUrl()).when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().baseUri(getBaseUrl()).when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().baseUri(getBaseUrl()).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(getBaseUrl()).when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().baseUri(getBaseUrl()).when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }
}