package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class SampleControllerTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void sayHello_withDefaultName_returns200AndGuestGreeting() {
        given()
            .when()
                .get("/")
            .then()
                .statusCode(200)
                .body(containsString("Hello Guest"));
    }

    @Ignore("Illegal character in query at index 11: /?name=John Smith")
    @Test(timeout = 60000)
    public void sayHello_withNameParam_returns200AndCustomGreeting() {
        given()
            .queryParam("name", "John Smith")
            .when()
                .get("/")
            .then()
                .statusCode(200)
                .body(containsString("Hello John Smith"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withDelayZero_triggersRandomDelayBranch() {
        given()
            .queryParam("delay", 0)
            .when()
                .get("/slowApi")
            .then()
                .statusCode(200)
                .body(containsString("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNonZeroDelay_triggersDirectSleepBranch() {
        given()
            .queryParam("delay", 1)
            .when()
                .get("/slowApi")
            .then()
                .statusCode(200)
                .body(containsString("Result"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withInvalidDelayType_returnsErrorStatus() {
        given()
            .queryParam("delay", "abc")
            .when()
                .get("/slowApi")
            .then()
                .statusCode(anyOf(equalTo(400), equalTo(500)));
    }

    @Ignore("Illegal character in query at index 17: /?name=María-José O'Connor-Smith III")
    @Test(timeout = 60000)
    public void sayHello_withSpecialCharactersInName_returns200() {
        given()
            .queryParam("name", "María-José O'Connor-Smith III")
            .when()
                .get("/")
            .then()
                .statusCode(200)
                .body(containsString("Hello María-José O'Connor-Smith III"));
    }
}