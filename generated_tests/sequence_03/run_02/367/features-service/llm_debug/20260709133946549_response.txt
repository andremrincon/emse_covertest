package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(lessThan(300));
        Object idObj = given().when().get("/products/{productName}", productName).then().statusCode(lessThan(300)).extract().path("constraints[0].id");
        int constraintId;
        if (idObj instanceof Number) {
            constraintId = ((Number) idObj).intValue();
        } else {
            constraintId = Integer.parseInt(String.valueOf(idObj));
        }
        given().when().delete("/products/{productName}/constraints/{constraintId}", productName, constraintId).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductContainsConstraintIdAfterCreation() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(lessThan(300));
        Object idObj = given().when().get("/products/{productName}", productName).then().statusCode(lessThan(300)).extract().path("constraints[0].id");
        int extractedId;
        if (idObj instanceof Number) {
            extractedId = ((Number) idObj).intValue();
        } else {
            extractedId = Integer.parseInt(String.valueOf(idObj));
        }
        given().when().get("/products/{productName}", productName).then().body("constraints[0].id", equalTo(extractedId));
    }
}