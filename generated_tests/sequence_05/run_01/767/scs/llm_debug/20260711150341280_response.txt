package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
        RestAssured.basePath = "/";
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_i2True_i3True() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i1True_i2False_i3True() {
        given()
            .when()
                .get("/api/notypevar/7/a")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i2True_i3False() {
        given()
            .when()
                .get("/api/notypevar/5/world")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubject_allFalse() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_invalidIntegerParam() {
        given()
            .when()
                .get("/api/notypevar/abc/a")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubject_overflowIntegerParam() {
        given()
            .when()
                .get("/api/notypevar/2147483648/a")
            .then()
                .statusCode(400);
    }
}