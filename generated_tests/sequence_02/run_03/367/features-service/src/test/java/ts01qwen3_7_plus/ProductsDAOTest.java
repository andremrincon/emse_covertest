package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInsertConstraintRequires() {
        String productName = "Prod-Req-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        RequestSpecification req = RestAssured.given();
        req.formParam("sourceFeature", feature1);
        req.formParam("requiredFeature", feature2);
        req.when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintExcludes() {
        String productName = "Prod-Excl-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        RequestSpecification req = RestAssured.given();
        req.formParam("sourceFeature", feature1);
        req.formParam("excludedFeature", feature2);
        req.when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProduct() {
        String productName = "Prod-Del-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        RequestSpecification req = RestAssured.given();
        req.formParam("sourceFeature", feature1);
        req.formParam("requiredFeature", feature2);
        req.when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(lessThan(300));

        RestAssured.given()
            .when()
            .delete("/products/" + productName)
            .then()
            .statusCode(204);
    }
}