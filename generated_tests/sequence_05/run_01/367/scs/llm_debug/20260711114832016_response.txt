package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotyPevar_Path1() {
        given()
            .baseUri(System.getProperty("baseUrl", "http://localhost:8080"))
            .pathParam("i", 28)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_Path2() {
        given()
            .baseUri(System.getProperty("baseUrl", "http://localhost:8080"))
            .pathParam("i", 7)
            .pathParam("s", "z")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_Path3() {
        given()
            .baseUri(System.getProperty("baseUrl", "http://localhost:8080"))
            .pathParam("i", 2)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}