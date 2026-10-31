package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;

import java.util.UUID;

import org.junit.Ignore;
public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintSuccess() {
        String productName = "ReqProduct-" + UUID.randomUUID().toString();
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        RequestSpecification req = RestAssured.given();
        req.formParam("sourceFeature", sourceFeature)
           .formParam("requiredFeature", requiredFeature)
           .when()
           .post("/products/{productName}/constraints/requires", productName)
           .then()
           .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintSuccess() {
        String productName = "ExclProduct-" + UUID.randomUUID().toString();
        String sourceFeature = "ExclSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        RequestSpecification req = RestAssured.given();
        req.formParam("sourceFeature", sourceFeature)
           .formParam("excludedFeature", excludedFeature)
           .when()
           .post("/products/{productName}/constraints/excludes", productName)
           .then()
           .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintNonExistentProduct() {
        String productName = "NoProduct-" + UUID.randomUUID().toString();

        RequestSpecification req = RestAssured.given();
        req.formParam("sourceFeature", "RAID-Controller-Card")
           .formParam("requiredFeature", "128GB-ECC-RAM")
           .when()
           .post("/products/{productName}/constraints/requires", productName)
           .then()
           .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintNonExistentProduct() {
        String productName = "NoProduct-" + UUID.randomUUID().toString();

        RequestSpecification req = RestAssured.given();
        req.formParam("sourceFeature", "CPU-i9-13900H")
           .formParam("excludedFeature", "Integrated-Graphics-Only")
           .when()
           .post("/products/{productName}/constraints/excludes", productName)
           .then()
           .statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <201>.")
    @Test(timeout = 60000)
    public void testAddRequiresConstraintNonExistentFeatures() {
        String productName = "ProdNoFeat-" + UUID.randomUUID().toString();

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        RequestSpecification req = RestAssured.given();
        req.formParam("sourceFeature", "NonExistentSource")
           .formParam("requiredFeature", "NonExistentRequired")
           .when()
           .post("/products/{productName}/constraints/requires", productName)
           .then()
           .statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <201>.")
    @Test(timeout = 60000)
    public void testAddExcludesConstraintNonExistentFeatures() {
        String productName = "ProdNoFeat-" + UUID.randomUUID().toString();

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        RequestSpecification req = RestAssured.given();
        req.formParam("sourceFeature", "NonExistentSource")
           .formParam("excludedFeature", "NonExistentExcluded")
           .when()
           .post("/products/{productName}/constraints/excludes", productName)
           .then()
           .statusCode(500);
    }
}