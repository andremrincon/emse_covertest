package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Prod-Req-" + java.util.UUID.randomUUID().toString();
        String feat1 = "Feat1-" + java.util.UUID.randomUUID().toString();
        String feat2 = "Feat2-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feat1)
            .formParam("requiredFeature", feat2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Prod-Excl-" + java.util.UUID.randomUUID().toString();
        String feat1 = "Feat1-" + java.util.UUID.randomUUID().toString();
        String feat2 = "Feat2-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feat1)
            .formParam("excludedFeature", feat2)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraints() {
        String productName = "Prod-Get-" + java.util.UUID.randomUUID().toString();
        String feat1 = "Feat1-" + java.util.UUID.randomUUID().toString();
        String feat2 = "Feat2-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feat1)
            .formParam("requiredFeature", feat2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }
}