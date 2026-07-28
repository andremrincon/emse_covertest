package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testInsertConstraintRequires() {
        String productName = "Prod-Req-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post(baseUrl + "/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintExcludes() {
        String productName = "Prod-Exc-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post(baseUrl + "/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductViaProductDeletion() {
        String productName = "Prod-Del-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post(baseUrl + "/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .delete(baseUrl + "/products/" + productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductViaFeatureDeletion() {
        String productName = "Prod-Feat-Del-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post(baseUrl + "/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .delete(baseUrl + "/products/" + productName + "/features/" + feature1)
        .then()
            .statusCode(204);
    }
}