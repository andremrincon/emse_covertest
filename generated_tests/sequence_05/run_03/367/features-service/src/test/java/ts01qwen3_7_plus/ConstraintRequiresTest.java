package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintSuccessfully() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Src-" + UUID.randomUUID().toString();
        String requiredFeature = "Req-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        Response response = given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithDifferentFeatures() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Src-" + UUID.randomUUID().toString();
        String requiredFeature = "Req-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        Response response = given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires");

        response.then().statusCode(201);
    }
}