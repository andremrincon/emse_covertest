package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    private static String encode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "TestFeature-" + UUID.randomUUID();
        String body = "description=" + encode("Test description");
        given()
            .contentType(ContentType.URLENC)
            .body(body)
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "TestFeature-" + UUID.randomUUID();
        given()
            .contentType(ContentType.URLENC)
            .body("")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureNonExistent() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/NonExistent-" + UUID.randomUUID())
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameFound() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String sourceFeature = "SourceFeat-" + UUID.randomUUID();
        String requiredFeature = "ReqFeat-" + UUID.randomUUID();

        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/" + sourceFeature)
            .then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/" + requiredFeature)
            .then().statusCode(lessThan(300));

        String body = "sourceFeature=" + encode(sourceFeature) + "&requiredFeature=" + encode(requiredFeature);
        given()
            .contentType(ContentType.URLENC)
            .body(body)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameNotFound() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String body = "sourceFeature=" + encode("NonExistent-" + UUID.randomUUID()) + "&requiredFeature=" + encode("AlsoMissing-" + UUID.randomUUID());
        given()
            .contentType(ContentType.URLENC)
            .body(body)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameCaseInsensitive() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "CaseTest-" + UUID.randomUUID();
        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        String configName = "TestConfig-" + UUID.randomUUID();
        given().when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName.toUpperCase())
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamedTrue() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "ConfigFeat-" + UUID.randomUUID();
        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        String configName = "TestConfig-" + UUID.randomUUID();
        given().when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamedFalse() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String configName = "TestConfig-" + UUID.randomUUID();
        given().when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/NonExistentFeature-" + UUID.randomUUID())
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraintRequires() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String sourceFeature = "ReqSource-" + UUID.randomUUID();
        String requiredFeature = "ReqTarget-" + UUID.randomUUID();

        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/" + sourceFeature)
            .then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/" + requiredFeature)
            .then().statusCode(lessThan(300));

        String body = "sourceFeature=" + encode(sourceFeature) + "&requiredFeature=" + encode(requiredFeature);
        given()
            .contentType(ContentType.URLENC)
            .body(body)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraintExcludes() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String sourceFeature = "ExcSource-" + UUID.randomUUID();
        String excludedFeature = "ExcTarget-" + UUID.randomUUID();

        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/" + sourceFeature)
            .then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/" + excludedFeature)
            .then().statusCode(lessThan(300));

        String body = "sourceFeature=" + encode(sourceFeature) + "&excludedFeature=" + encode(excludedFeature);
        given()
            .contentType(ContentType.URLENC)
            .body(body)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraintExcludesFeatureNotFound() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String body = "sourceFeature=" + encode("NonExistent-" + UUID.randomUUID()) + "&excludedFeature=" + encode("AlsoMissing-" + UUID.randomUUID());
        given()
            .contentType(ContentType.URLENC)
            .body(body)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetProduct() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/FeatureA-" + UUID.randomUUID())
            .then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/FeatureB-" + UUID.randomUUID())
            .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetFeaturesList() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/ListFeature-" + UUID.randomUUID())
            .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = "TestProd-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "ConfigDelFeat-" + UUID.randomUUID();
        given().contentType(ContentType.URLENC).body("").when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        String configName = "TestConfig-" + UUID.randomUUID();
        given().when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }
}