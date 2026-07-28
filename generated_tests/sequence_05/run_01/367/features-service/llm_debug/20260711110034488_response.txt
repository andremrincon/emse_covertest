package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.http.ContentType;

public class FeatureConstraintTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String product = "Prod-Req-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-Req-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-Req-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + product).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + product + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + product + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post(baseUrl + "/products/" + product + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String product = "Prod-Exc-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-Exc-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-Exc-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + product).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + product + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + product + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post(baseUrl + "/products/" + product + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraints() {
        String product = "Prod-Get-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-Get-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-Get-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + product).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + product + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + product + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post(baseUrl + "/products/" + product + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get(baseUrl + "/products/" + product)
        .then()
            .statusCode(200);
    }
}