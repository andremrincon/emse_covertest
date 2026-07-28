package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Enterprise-Server-X1-" + UUID.randomUUID().toString();
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";

        given().when().post("/products/" + productName).then().statusCode(lessThan(Integer.valueOf(300)));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(Integer.valueOf(300)));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(Integer.valueOf(300)));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "Laptop-Pro-15-" + UUID.randomUUID().toString();
        String sourceFeature = "CPU-i9-13900H";
        String excludedFeature = "Integrated-Graphics-Only";

        given().when().post("/products/" + productName).then().statusCode(lessThan(Integer.valueOf(300)));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(Integer.valueOf(300)));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(Integer.valueOf(300)));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }
}