package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintSuccessfully() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithNonExistentFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "NonExistentSource-" + UUID.randomUUID().toString();
        String requiredFeature = "NonExistentRequired-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }
}