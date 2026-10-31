package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Prod-Req-" + System.currentTimeMillis();
        String feat1 = "Feat1-" + System.currentTimeMillis();
        String feat2 = "Feat2-" + System.currentTimeMillis();

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
        String productName = "Prod-Exc-" + System.currentTimeMillis();
        String feat1 = "Feat1-" + System.currentTimeMillis();
        String feat2 = "Feat2-" + System.currentTimeMillis();

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
}