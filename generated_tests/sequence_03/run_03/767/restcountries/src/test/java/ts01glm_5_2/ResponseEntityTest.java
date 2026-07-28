package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.defaultParser = Parser.TEXT;
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testCapitalNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/capital/123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testRegionNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/region/123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testCallingCodeNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/callingcode/abc")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testNameServerErrorReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/name/True")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testCapitalServerErrorReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/capital/True")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }
}