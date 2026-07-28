package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Prod-Req-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "Feat1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "Feat2-" + UUID.randomUUID().toString().substring(0, 8);

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
        String productName = "Prod-Exc-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "Feat1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "Feat2-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testDeleteConstraint() {
        String productName = "Prod-Del-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "Feat1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "Feat2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        String response = given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(lessThan(300))
            .extract().asString();

        String constraintId = null;
        if (response != null && !response.isEmpty()) {
            try {
                constraintId = io.restassured.path.json.JsonPath.from(response).getString("id");
            } catch (Exception e) {
                constraintId = null;
            }
        }

        if (constraintId == null || constraintId.isEmpty() || constraintId.equals("null")) {
            constraintId = given()
                .when().get("/products/" + productName)
                .then().statusCode(lessThan(300))
                .extract().jsonPath().getString("constraints[0].id");
        }

        given()
        .when()
            .delete("/products/" + productName + "/constraints/" + constraintId)
        .then()
            .statusCode(204);
    }
}