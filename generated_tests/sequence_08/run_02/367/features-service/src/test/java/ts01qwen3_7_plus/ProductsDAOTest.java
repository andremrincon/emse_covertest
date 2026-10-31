package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testInsertConstraintRequires() {
        String productName = "Product-Req-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatureA").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatureB").then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", "FeatureA")
                .formParam("requiredFeature", "FeatureB")
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintExcludes() {
        String productName = "Product-Excl-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatureC").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatureD").then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", "FeatureC")
                .formParam("excludedFeature", "FeatureD")
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithConstraints() {
        String productName = "Product-Del-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatureE").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatureF").then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", "FeatureE")
                .formParam("requiredFeature", "FeatureF")
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(lessThan(300));

        given().when().delete("/products/" + productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithoutConstraints() {
        String productName = "Product-Del-Empty-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName).then().statusCode(204);
    }
}