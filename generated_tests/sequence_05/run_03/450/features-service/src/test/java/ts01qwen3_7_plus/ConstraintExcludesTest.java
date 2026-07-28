package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            io.restassured.RestAssured.baseURI = baseUrl;
        } else {
            io.restassured.RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "TestProduct-" + System.currentTimeMillis();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "SourceFeature1")
            .formParam("excludedFeature", "ExcludedFeature1")
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then()
            .statusCode(201);
    }
}