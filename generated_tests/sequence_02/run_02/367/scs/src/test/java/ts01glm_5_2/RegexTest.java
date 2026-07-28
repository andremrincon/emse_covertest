package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import java.net.URLEncoder;

public class RegexTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        RestAssured.urlEncodingEnabled = false;
    }

    @Test(timeout = 60000)
    public void testUrlPatternMatch() throws Exception {
        String encodedTxt = URLEncoder.encode("http://abc/def", "UTF-8");
        given()
            .when()
                .get("/api/pat/" + encodedTxt)
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testDatePatternMatch() {
        given()
            .when()
                .get("/api/pat/mon01jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternMatch() throws Exception {
        String encodedTxt = URLEncoder.encode("12.34e+56", "UTF-8");
        given()
            .when()
                .get("/api/pat/" + encodedTxt)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoPatternMatchReturnsNone() {
        given()
            .when()
                .get("/api/pat/hello")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLongInputServerError() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            sb.append('a');
        }
        given()
            .when()
                .get("/api/pat/" + sb.toString())
            .then()
                .statusCode(200);
    }
}