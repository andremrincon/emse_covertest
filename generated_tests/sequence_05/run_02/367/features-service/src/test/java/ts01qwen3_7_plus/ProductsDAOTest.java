package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.Matchers;

import java.util.UUID;

public class ProductsDAOTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInsertConstraintRequires() {
        String productName = "Enterprise-Server-X1-" + UUID.randomUUID().toString();
        String sourceFeature = "RAID-Controller-Card-" + UUID.randomUUID().toString();
        String requiredFeature = "128GB-ECC-RAM-" + UUID.randomUUID().toString();

        RestAssured.given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(Matchers.lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintExcludes() {
        String productName = "Laptop-Pro-15-" + UUID.randomUUID().toString();
        String sourceFeature = "CPU-i9-13900H-" + UUID.randomUUID().toString();
        String excludedFeature = "Integrated-Graphics-Only-" + UUID.randomUUID().toString();

        RestAssured.given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(Matchers.lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProduct() {
        String productName = "UltraPhoneX-" + UUID.randomUUID().toString();
        String sourceFeature = "FeatureA-" + UUID.randomUUID().toString();
        String requiredFeature = "FeatureB-" + UUID.randomUUID().toString();

        RestAssured.given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(Matchers.lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(Matchers.lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(Matchers.lessThan(300));

        RestAssured.given()
        .when()
            .delete("/products/" + productName)
        .then()
            .statusCode(204);
    }
}