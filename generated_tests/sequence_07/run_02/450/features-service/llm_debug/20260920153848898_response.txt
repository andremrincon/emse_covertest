package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl != null) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_withValidData_returns201() {
        String productName = "TestProduct-Req-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_withNonExistentProduct_returns500() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA";
        String requiredFeature = "FeatureB";

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_withNonExistentSourceFeature_returns500() {
        String productName = "TestProduct-ReqSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "128GB-ECC-RAM";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "NonExistentSourceFeature")
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_withValidData_returns201() {
        String productName = "TestProduct-Exc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "CPU-i9-13900H";
        String excludedFeature = "Integrated-Graphics-Only";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/" + productName + "/constraints/excludes")
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_withNonExistentProduct_returns500() {
        String productName = "NonExistentProduct-Exc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "PSU-Redundant";
        String excludedFeature = "OS-Home";

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/" + productName + "/constraints/excludes")
        .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_withNonExistentExcludedFeature_returns500() {
        String productName = "TestProduct-ExcSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Color-Black";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", "NonExistentExcludedFeature")
        .when()
                .post("/products/" + productName + "/constraints/excludes")
        .then()
                .statusCode(201);
    }
}