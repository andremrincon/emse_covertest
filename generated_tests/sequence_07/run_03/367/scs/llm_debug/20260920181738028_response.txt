package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNone() throws Exception {
        String txt = "a";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsUrl() throws Exception {
        String txt = "http://a/a";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsDate() throws Exception {
        String txt = "mon01jan";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsFpe() throws Exception {
        String txt = "12.34e+56";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .statusCode(lessThan(300));
    }
}