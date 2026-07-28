package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String host = System.getenv().getOrDefault("API_HOST", "localhost");
        String port = System.getenv().getOrDefault("API_PORT", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_i1False_i2False_i3True() {
        given()
            .when()
                .get("/api/notypevar/28/abc")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0False_i1True_i2True_i3True() {
        given()
            .when()
                .get("/api/notypevar/7/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0False_i1False_i2True_i3False() {
        given()
            .when()
                .get("/api/notypevar/5/world")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubject_allBranchesFalse() {
        given()
            .when()
                .get("/api/notypevar/5/abc")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_invalidIntegerParam() {
        given()
            .when()
                .get("/api/notypevar/abc/test")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubject_integerOverflowParam() {
        given()
            .when()
                .get("/api/notypevar/2147483648/test")
            .then()
                .statusCode(400);
    }
}