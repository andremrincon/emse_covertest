package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithDefaultName() {
        Response response = given()
                .when()
                .get("/");
        response.then()
                .statusCode(200)
                .body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithCustomName() throws Exception {
        String encodedName = URLEncoder.encode("John Smith", "UTF-8");
        Response response = given()
                .queryParam("name", encodedName)
                .when()
                .get("/");
        response.then()
                .statusCode(200)
                .body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithDefaultDelay() {
        Response response = given()
                .when()
                .get("/slowApi");
        response.then()
                .statusCode(200)
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithExplicitDelay() {
        Response response = given()
                .queryParam("delay", 1)
                .when()
                .get("/slowApi");
        response.then()
                .statusCode(200)
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithLargeDelay() {
        Response response = given()
                .queryParam("delay", 1)
                .when()
                .get("/slowApi");
        response.then()
                .statusCode(200)
                .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithSpecialCharactersName() throws Exception {
        String encodedName = URLEncoder.encode("María-José O'Connor-Smith III", "UTF-8");
        Response response = given()
                .queryParam("name", encodedName)
                .when()
                .get("/");
        response.then()
                .statusCode(200)
                .body(equalTo("Hello María-José O'Connor-Smith III!!"));
    }
}