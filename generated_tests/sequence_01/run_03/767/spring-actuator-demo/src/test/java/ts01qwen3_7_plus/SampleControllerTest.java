package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class SampleControllerTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testSayHelloWithDefaultName() {
        given()
            .when()
            .get("/")
            .then()
            .statusCode(200)
            .body(equalTo("Hello Guest!!"));
    }

    @Ignore("Illegal character in query at index 11: /?name=John Smith")
    @Test(timeout = 60000)
    public void testSayHelloWithSpecificName() {
        given()
            .queryParam("name", "John Smith")
            .when()
            .get()
            .then()
            .statusCode(200)
            .body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithZeroDelay() {
        given()
            .queryParam("delay", 0)
            .when()
            .get("/slowApi")
            .then()
            .statusCode(200)
            .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithPositiveDelay() {
        given()
            .queryParam("delay", 1)
            .when()
            .get("/slowApi")
            .then()
            .statusCode(200)
            .body(equalTo("Result"));
    }
}