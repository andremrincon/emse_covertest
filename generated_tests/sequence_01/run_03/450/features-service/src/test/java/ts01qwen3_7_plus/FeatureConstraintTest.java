package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Product-Req-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Product-Excl-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraints() {
        String productName = "Product-Get-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
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

    @Test(timeout = 60000)
    public void testDeleteConstraint() {
        String productName = "Product-Del-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        int constraintId = given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(lessThan(300))
            .extract().jsonPath().getInt("constraints[0].id");

        given()
        .when()
            .delete("/products/" + productName + "/constraints/" + constraintId)
        .then()
            .statusCode(204);
    }
}