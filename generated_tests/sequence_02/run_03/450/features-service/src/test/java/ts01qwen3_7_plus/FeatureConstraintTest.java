package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "ProdReq_" + UUID.randomUUID().toString();
        String f1 = "F1_" + UUID.randomUUID().toString();
        String f2 = "F2_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(Matchers.lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "ProdExc_" + UUID.randomUUID().toString();
        String f1 = "F1_" + UUID.randomUUID().toString();
        String f2 = "F2_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(Matchers.lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("excludedFeature", f2)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraints() {
        String productName = "ProdGet_" + UUID.randomUUID().toString();
        String f1 = "F1_" + UUID.randomUUID().toString();
        String f2 = "F2_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(Matchers.lessThan(300));
        given().formParam("sourceFeature", f1).formParam("requiredFeature", f2).when().post("/products/" + productName + "/constraints/requires").then().statusCode(Matchers.lessThan(300));

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }
}