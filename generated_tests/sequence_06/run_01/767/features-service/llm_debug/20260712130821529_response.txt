package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
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
    public void testCreateRequiresConstraintWithInvalidProductName() {
        String invalidProductName = "Invalid/Product/Name-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + invalidProductName + "/constraints/requires")
        .then()
            .statusCode(404);
    }
}