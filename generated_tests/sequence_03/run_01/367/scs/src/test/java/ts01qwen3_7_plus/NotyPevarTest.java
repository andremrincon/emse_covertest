package ts01qwen3_7_plus;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        io.restassured.RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testNotyPevar_AllConditionsFalse() {
        given()
            .pathParam("i", 3)
            .pathParam("s", "abc")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_I0True_I2True_I3True() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "world")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_I1True_I3True() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "abc")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }
}