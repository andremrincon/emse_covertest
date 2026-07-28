package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NcsRestTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjN3X0() {
        Response response = given()
                .when()
                .get("/api/bessj/3/0");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testBessjN3XVerySmall() {
        Response response = given()
                .when()
                .get("/api/bessj/3/0.0000000001");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testBessjN3X2_5() {
        Response response = given()
                .when()
                .get("/api/bessj/3/2.5");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testBessjN1000X1() {
        Response response = given()
                .when()
                .get("/api/bessj/1000/1.0");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testFisherM10N5X0_75() {
        Response response = given()
                .when()
                .get("/api/fisher/10/5/0.75");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testFisherM1N1X0() {
        Response response = given()
                .when()
                .get("/api/fisher/1/1/0.0");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testFisherM1N1X0_5() {
        Response response = given()
                .when()
                .get("/api/fisher/1/1/0.5");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testGammqA5_5X2_3() {
        Response response = given()
                .when()
                .get("/api/gammq/5.5/2.3");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testGammqA0_001X1000() {
        Response response = given()
                .when()
                .get("/api/gammq/0.001/1000.0");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testGammqA1X0_5() {
        Response response = given()
                .when()
                .get("/api/gammq/1.0/0.5");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testRemainderA17B5() {
        Response response = given()
                .when()
                .get("/api/remainder/17/5");
        response.then().statusCode(200);
        assertEquals(Integer.valueOf(2), response.jsonPath().get("resultAsInt"));
    }

    @Test(timeout = 60000)
    public void testRemainderNeg9B4() {
        Response response = given()
                .when()
                .get("/api/remainder/-9/4");
        response.then().statusCode(200);
        assertNotNull(response.jsonPath().get("resultAsInt"));
    }
}