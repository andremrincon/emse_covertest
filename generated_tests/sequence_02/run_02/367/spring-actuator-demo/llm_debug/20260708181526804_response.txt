package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void sayHello_withDefaultName_returns200AndHelloGuest() {
        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .body(is("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void sayHello_withExplicitName_returns200AndHelloName() {
        given()
                .queryParam("name", "John%20Smith")
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .body(is("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withDefaultDelay_returns200AndResult() {
        given()
                .when()
                .get("/slowApi")
                .then()
                .statusCode(200)
                .body(is("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withExplicitDelay_returns200AndResult() {
        given()
                .queryParam("delay", 1)
                .when()
                .get("/slowApi")
                .then()
                .statusCode(200)
                .body(is("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNonIntegerDelay_returns500() {
        given()
                .queryParam("delay", "abc")
                .when()
                .get("/slowApi")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNegativeDelay_returns200AndResult() {
        given()
                .queryParam("delay", -1)
                .when()
                .get("/slowApi")
                .then()
                .statusCode(200)
                .body(is("Result"));
    }
}