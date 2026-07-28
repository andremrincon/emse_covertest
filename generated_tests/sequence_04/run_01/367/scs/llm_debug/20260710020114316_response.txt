package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsUrl() {
        given()
            .pathParam("txt", "http://a/a")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404)
            .body(containsString("/api/pat/http:/a/a"));
    }
}