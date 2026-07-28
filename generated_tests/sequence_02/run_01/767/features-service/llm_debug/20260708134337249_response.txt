package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_Success() {
        String productName = "Prod-Req-" + UUID.randomUUID().toString();
        String sourceFeature = "Src-" + UUID.randomUUID().toString();
        String requiredFeature = "Req-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post(baseUrl + "/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_Success() {
        String productName = "Prod-Exc-" + UUID.randomUUID().toString();
        String sourceFeature = "Src-" + UUID.randomUUID().toString();
        String excludedFeature = "Exc-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post(baseUrl + "/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }
}