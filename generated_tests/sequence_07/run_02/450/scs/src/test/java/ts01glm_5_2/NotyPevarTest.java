package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubject_i28_sA_coversI0TrueI1FalseI2FalseI3True() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i7_sWorld_coversI0FalseI1TrueI2TrueI3True() {
        given()
            .when()
                .get("/api/notypevar/7/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0_sA_coversI0FalseI1FalseI2FalseI3False() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_i3_sWorld_coversI0FalseI1FalseI2TrueI3False() {
        given()
            .when()
                .get("/api/notypevar/3/world")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubject_i28_sWorld_coversI0TrueI1FalseI2TrueI3True() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_invalidInteger_returns400() {
        given()
            .when()
                .get("/api/notypevar/abc/valid-string")
            .then()
                .statusCode(400);
    }
}