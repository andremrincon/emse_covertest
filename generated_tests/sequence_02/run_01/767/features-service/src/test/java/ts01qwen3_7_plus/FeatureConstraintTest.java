package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Product-Req-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feature1)
                .formParam("requiredFeature", feature2)
                .when()
                .post("/products/" + productName + "/constraints/requires");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Product-Excl-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feature1)
                .formParam("excludedFeature", feature2)
                .when()
                .post("/products/" + productName + "/constraints/excludes");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraints() {
        String productName = "Product-Get-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feature1)
                .formParam("requiredFeature", feature2)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName);

        response.then().statusCode(200);
    }
}