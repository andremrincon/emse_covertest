package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Enterprise-Server-X1-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/RAID-Controller-Card").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/128GB-ECC-RAM").then().statusCode(lessThan(300));

        Response response = given()
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when()
                .post("/products/" + productName + "/constraints/requires");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Laptop-Pro-15-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/CPU-i9-13900H").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Integrated-Graphics-Only").then().statusCode(lessThan(300));

        Response response = given()
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/" + productName + "/constraints/excludes");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraintsToCoverGetId() {
        String productName = "Mobile-App-Basic-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Push-Notifications").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/User-Authentication").then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", "Push-Notifications")
                .formParam("requiredFeature", "User-Authentication")
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName);

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraint() {
        String productName = "UltraPhoneX-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Feature1").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Feature2").then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", "Feature1")
                .formParam("excludedFeature", "Feature2")
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then().statusCode(lessThan(300));

        Response productResponse = given().when().get("/products/" + productName);
        productResponse.then().statusCode(lessThan(300));

        Long constraintId = productResponse.jsonPath().getLong("constraints[0].id");

        Response response = given().when().delete("/products/" + productName + "/constraints/" + constraintId);

        response.then().statusCode(204);
    }
}