package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testBessjSuccess() {
        Response response = given()
                .when()
                .get("/api/bessj/3/2.5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherSuccess() {
        Response response = given()
                .when()
                .get("/api/fisher/10/5/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherRuntimeException() {
        Response response = given()
                .when()
                .get("/api/fisher/10/5/1.2");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqSuccess() {
        Response response = given()
                .when()
                .get("/api/gammq/5.5/2.3");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderSuccess() {
        Response response = given()
                .when()
                .get("/api/remainder/17/5");
        response.then().statusCode(200);
    }
}