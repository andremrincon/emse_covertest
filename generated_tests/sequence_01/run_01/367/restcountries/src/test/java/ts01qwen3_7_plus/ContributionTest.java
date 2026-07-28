package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @Test(timeout = 60000)
    public void testContributeValidPayload() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"amount\": 100, \"token\": \"tok_123\"}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingFields() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }
}