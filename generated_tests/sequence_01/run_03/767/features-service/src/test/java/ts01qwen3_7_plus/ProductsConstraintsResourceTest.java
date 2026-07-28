package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.UUID;

public class ProductsConstraintsResourceTest {

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        String productName = "Prod-Req-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-Feat-" + UUID.randomUUID().toString();
        String requiredFeature = "Req-Feat-" + UUID.randomUUID().toString();

        RestAssured.given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(Matchers.lessThan(300));

        RestAssured.given()
            .baseUri(baseUrl)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        String productName = "Prod-Excl-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-Feat-" + UUID.randomUUID().toString();
        String excludedFeature = "Excl-Feat-" + UUID.randomUUID().toString();

        RestAssured.given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(Matchers.lessThan(300));

        RestAssured.given()
            .baseUri(baseUrl)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }
}