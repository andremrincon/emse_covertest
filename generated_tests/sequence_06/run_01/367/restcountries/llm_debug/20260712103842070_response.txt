package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testResponseEntityViaName404() {
        given()
            .when()
            .get("/v1/name/123")
            .then()
            .statusCode(404)
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testResponseEntityViaCapital404() {
        given()
            .when()
            .get("/v1/capital/123")
            .then()
            .statusCode(404)
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testResponseEntityViaPostV1() {
        given()
            .when()
            .post("/v1")
            .then()
            .statusCode(404)
            .body(equalTo(""));
    }
}