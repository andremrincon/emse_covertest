package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String p = "prod-" + UUID.randomUUID().toString();
        String f1 = "f1-" + UUID.randomUUID().toString();
        String f2 = "f2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + p).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/features/" + f2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
        .when()
            .post("/products/" + p + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveRequiredInactive() {
        String p = "prod-" + UUID.randomUUID().toString();
        String f1 = "f1-" + UUID.randomUUID().toString();
        String f2 = "f2-" + UUID.randomUUID().toString();
        String c = "c-" + UUID.randomUUID().toString();

        given().when().post("/products/" + p).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/features/" + f2).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/configurations/" + c).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
        .when()
            .post("/products/" + p + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given().when().post("/products/" + p + "/configurations/" + c + "/features/" + f1).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + p + "/configurations/" + c + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceInactive() {
        String p = "prod-" + UUID.randomUUID().toString();
        String f1 = "f1-" + UUID.randomUUID().toString();
        String f2 = "f2-" + UUID.randomUUID().toString();
        String c = "c-" + UUID.randomUUID().toString();

        given().when().post("/products/" + p).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/features/" + f2).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/configurations/" + c).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
        .when()
            .post("/products/" + p + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + p + "/configurations/" + c + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothActive() {
        String p = "prod-" + UUID.randomUUID().toString();
        String f1 = "f1-" + UUID.randomUUID().toString();
        String f2 = "f2-" + UUID.randomUUID().toString();
        String c = "c-" + UUID.randomUUID().toString();

        given().when().post("/products/" + p).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/features/" + f2).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/configurations/" + c).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
        .when()
            .post("/products/" + p + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given().when().post("/products/" + p + "/configurations/" + c + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + p + "/configurations/" + c + "/features/" + f2).then().statusCode(500);

        given()
        .when()
            .get("/products/" + p + "/configurations/" + c + "/features")
        .then()
            .statusCode(200);
    }
}