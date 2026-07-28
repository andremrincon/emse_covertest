package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    static {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i0True_i2True_i3True() {
        int i = 28;
        String s = "world";
        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i1True_i3True() {
        int i = 7;
        String s = "abc";
        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i2True_i3False() {
        int i = 3;
        String s = "world";
        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }
}