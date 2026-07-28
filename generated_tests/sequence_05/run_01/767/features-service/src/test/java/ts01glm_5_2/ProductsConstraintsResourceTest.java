package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_returnsCreated() {
        String productName = "TestProduct-Req-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_returnsCreated() {
        String productName = "TestProduct-Excl-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ExclSource-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExclTarget-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_nonExistentProduct_returnsServerError() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_nonExistentProduct_returnsServerError() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ExclSource-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExclTarget-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_nonExistentSourceFeature_returnsServerError() {
        String productName = "TestProduct-ReqNF-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", "NonExistentSourceFeature")
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_nonExistentExcludedFeature_returnsServerError() {
        String productName = "TestProduct-ExclNF-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ExclSource-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", "NonExistentExcludedFeature")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }
}