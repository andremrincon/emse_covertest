package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String host = System.getenv().getOrDefault("APP_HOST", "localhost");
        String port = System.getenv().getOrDefault("APP_PORT", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testSubject_i28_sHello_coversI0TrueI1FalseI2FalseI3True() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "hello")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i7_sZzz_coversI0FalseI1TrueI2TrueI3True() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "zzz")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i3_sAaa_coversI0FalseI1FalseI2FalseI3False() {
        given()
            .pathParam("i", 3)
            .pathParam("s", "aaa")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }
}