package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i0True_i3True() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "hello")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i1True_i3True() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "hello")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i2True_i3False() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "world")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }
}