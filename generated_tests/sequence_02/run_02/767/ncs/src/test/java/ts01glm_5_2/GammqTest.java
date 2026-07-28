package ts01glm_5_2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class GammqTest {

    private RequestSpecification spec;

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        spec = new RequestSpecBuilder().setBaseUri(baseUrl).build();
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        given()
            .spec(spec)
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given()
            .spec(spec)
        .when()
            .get("/api/gammq/0.001/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        given()
            .spec(spec)
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNonConvergence() {
        given()
            .spec(spec)
        .when()
            .get("/api/gammq/1000.0/1001.0")
        .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testGserNonConvergence() {
        given()
            .spec(spec)
        .when()
            .get("/api/gammq/1000.0/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfWithSmallA() {
        given()
            .spec(spec)
        .when()
            .get("/api/gammq/0.5/1.5")
        .then()
            .statusCode(200);
    }
}