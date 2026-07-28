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
        String baseUrl = System.getProperty("base.url");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInsertConstraintRequires() {
        String productName = "Enterprise-Server-X1-" + UUID.randomUUID().toString();
        String sourceFeature = "RAID-Controller-Card-" + UUID.randomUUID().toString();
        String requiredFeature = "128GB-ECC-RAM-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
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

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProduct() {
        String productName = "Product-Del-" + UUID.randomUUID().toString();
        String sourceFeature = "Feature-Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Feature-Required-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName)
        .then()
            .statusCode(204);
    }
}