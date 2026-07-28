package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.requestSpecification = new RequestSpecBuilder().setBaseUri(baseUrl).build();
    }

    @Test(timeout = 60000)
    public void testSayHelloWithDefaultName() {
        given()
            .when()
                .get("/")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSayHelloWithExplicitName() {
        given()
            .queryParam("name", "John%20Smith")
            .when()
                .get("/")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSlowApiWithDefaultDelay() {
        given()
            .when()
                .get("/slowApi")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSlowApiWithExplicitDelay() {
        given()
            .queryParam("delay", 1)
            .when()
                .get("/slowApi")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSlowApiWithInvalidDelayType() {
        given()
            .queryParam("delay", "abc")
            .when()
                .get("/slowApi")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSlowApiWithNegativeDelay() {
        given()
            .queryParam("delay", -1)
            .when()
                .get("/slowApi")
            .then()
                .statusCode(200);
    }
}