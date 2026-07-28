package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String baseUrl = getBaseUrl();
        String productName = "Prod-Req-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        Response response = given()
                .baseUri(baseUrl)
                .formParam("sourceFeature", feature1)
                .formParam("requiredFeature", feature2)
                .when()
                .post("/products/" + productName + "/constraints/requires");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String baseUrl = getBaseUrl();
        String productName = "Prod-Excl-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        Response response = given()
                .baseUri(baseUrl)
                .formParam("sourceFeature", feature1)
                .formParam("excludedFeature", feature2)
                .when()
                .post("/products/" + productName + "/constraints/excludes");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraints() {
        String baseUrl = getBaseUrl();
        String productName = "Prod-Get-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given()
                .baseUri(baseUrl)
                .formParam("sourceFeature", feature1)
                .formParam("requiredFeature", feature2)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then().statusCode(lessThan(300));

        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/products/" + productName);

        response.then().statusCode(200);
    }
}