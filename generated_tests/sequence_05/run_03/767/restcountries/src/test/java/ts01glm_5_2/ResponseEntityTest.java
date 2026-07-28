package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    private RequestSpecification requestSpec;

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .build();
    }

    @Test(timeout = 60000)
    public void testGetMessageOnNotFoundResponse() {
        given()
                .spec(requestSpec)
        .when()
                .get("/v1/name/123")
        .then()
                .statusCode(404)
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatusOnNotFoundResponse() {
        given()
                .spec(requestSpec)
        .when()
                .get("/v1/capital/123")
        .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageOnServerErrorResponse() {
        given()
                .spec(requestSpec)
        .when()
                .get("/v1/name/True")
        .then()
                .statusCode(404)
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatusOnServerErrorResponse() {
        given()
                .spec(requestSpec)
        .when()
                .get("/v1/region/True")
        .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageOnAlphaNotFoundResponse() {
        given()
                .spec(requestSpec)
        .when()
                .get("/v1/alpha/XYZ")
        .then()
                .statusCode(404)
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatusOnCallingCodeNotFoundResponse() {
        given()
                .spec(requestSpec)
        .when()
                .get("/v1/callingcode/99999")
        .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }
}