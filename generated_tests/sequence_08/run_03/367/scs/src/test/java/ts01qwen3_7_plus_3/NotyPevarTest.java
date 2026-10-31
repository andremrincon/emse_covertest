package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotyPevar_C1True_C4True() {
        RestAssured.given()
            .pathParam("i", 28)
            .pathParam("s", "a")
            .when()
            .get("/api/notypevar/{i}/{s}")
            .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_C2True_C3True() {
        RestAssured.given()
            .pathParam("i", 7)
            .pathParam("s", "z")
            .when()
            .get("/api/notypevar/{i}/{s}")
            .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_AllFalse() {
        RestAssured.given()
            .pathParam("i", 0)
            .pathParam("s", "a")
            .when()
            .get("/api/notypevar/{i}/{s}")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}