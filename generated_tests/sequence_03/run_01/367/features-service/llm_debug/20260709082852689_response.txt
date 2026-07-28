package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String f1 = "F1-" + UUID.randomUUID().toString();
        String f2 = "F2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintAndRetrieve() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String f1 = "F1-" + UUID.randomUUID().toString();
        String f2 = "F2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("excludedFeature", f2)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }
}