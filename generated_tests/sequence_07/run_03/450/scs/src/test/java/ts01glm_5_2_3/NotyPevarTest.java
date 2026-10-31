package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_i1False_i2False_i3True() {
        given()
        .when()
            .get("/api/notypevar/28/a")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0False_i1True_i2True_i3True() {
        given()
        .when()
            .get("/api/notypevar/7/z")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_allConditionsFalse() {
        given()
        .when()
            .get("/api/notypevar/3/a")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}