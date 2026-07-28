package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("API_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("api.base.url");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraintTriggersFeatureConstraintSetId() {
        String productName = "test-product-requires-" + java.util.UUID.randomUUID();
        String sourceFeature = "src-feature-" + java.util.UUID.randomUUID();
        String requiredFeature = "req-feature-" + java.util.UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraintTriggersFeatureConstraintSetId() {
        String productName = "test-product-excludes-" + java.util.UUID.randomUUID();
        String sourceFeature = "src-excl-" + java.util.UUID.randomUUID();
        String excludedFeature = "excl-feature-" + java.util.UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void retrieveProductWithConstraintsTriggersFeatureConstraintSetIdOnLoad() {
        String productName = "test-product-load-" + java.util.UUID.randomUUID();
        String sourceFeature = "src-load-" + java.util.UUID.randomUUID();
        String requiredFeature = "req-load-" + java.util.UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
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