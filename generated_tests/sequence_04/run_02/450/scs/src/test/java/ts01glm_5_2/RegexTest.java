package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        String txt = "http://abc/def";

        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        String txt = "mon01jan";

        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() {
        String txt = "12.34e+56";

        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() {
        String txt = "a";

        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLongInputReturns500() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("a");
        }

        given()
            .when()
                .get("/api/pat/" + sb.toString())
            .then()
                .statusCode(200);
    }
}