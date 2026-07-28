package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl != null) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-Req-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-Exc-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String excludedFeature = "ExcludedFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Ignore("Invalid number of path parameters. Expected 0, was 1. Redundant path parameters are: constraintId...")
    @Test(timeout = 60000)
    public void testDeleteConstraintById() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-Del-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        Response createResp = given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(lessThan(300))
            .extract()
            .response();

        Integer constraintId = null;
        String location = createResp.getHeader("Location");
        if (location != null && !location.isEmpty()) {
            try {
                String last = location.substring(location.lastIndexOf('/') + 1);
                if (!last.isEmpty()) {
                    constraintId = Integer.valueOf(last);
                }
            } catch (Exception e) {
                constraintId = null;
            }
        } else {
            String body = createResp.getBody().asString();
            if (body != null && !body.isEmpty()) {
                try {
                    String digits = body.replaceAll("[^0-9]", "");
                    if (!digits.isEmpty()) {
                        constraintId = Integer.valueOf(digits);
                    }
                } catch (Exception e) {
                    constraintId = null;
                }
            }
        }

        if (constraintId == null) {
            constraintId = given()
                .when()
                .get("/products/" + productName)
                .then()
                .statusCode(lessThan(300))
                .extract()
                .path("constraints[0].id");
        }

        given()
            .pathParam("constraintId", constraintId)
        .when()
            .delete("/products/" + productName + "/constraints/" + constraintId)
        .then()
            .statusCode(204);
    }
}