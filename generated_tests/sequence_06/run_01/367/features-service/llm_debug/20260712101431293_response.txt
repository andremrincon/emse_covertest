package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        Response response = given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post(baseUrl + "/products/" + productName + "/constraints/requires");

        response.then().statusCode(201);
    }
}