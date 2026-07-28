package ts01glm_5_2;

import org.junit.Before;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatternLengthLessThan3() {
        given()
            .pathParam("txt", "hello")
            .pathParam("pat", "ab")
        .when()
            .get(baseUrl + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundReverseNotFound() {
        given()
            .pathParam("txt", "abcdef")
            .pathParam("pat", "abc")
        .when()
            .get(baseUrl + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundReverseAdjacent() {
        given()
            .pathParam("txt", "abccba")
            .pathParam("pat", "abc")
        .when()
            .get(baseUrl + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundReverseNonAdjacent() {
        given()
            .pathParam("txt", "abcxcba")
            .pathParam("pat", "abc")
        .when()
            .get(baseUrl + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatternAdjacent() {
        given()
            .pathParam("txt", "cbaabc")
            .pathParam("pat", "abc")
        .when()
            .get(baseUrl + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatternNonAdjacent() {
        given()
            .pathParam("txt", "cbaxabc")
            .pathParam("pat", "abc")
        .when()
            .get(baseUrl + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatternNotFound() {
        given()
            .pathParam("txt", "cbaxyz")
            .pathParam("pat", "abc")
        .when()
            .get(baseUrl + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFirstCharMatchesReverseButNotFullMatch() {
        given()
            .pathParam("txt", "cxyxyz")
            .pathParam("pat", "abc")
        .when()
            .get(baseUrl + "/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatTxtOnlyEndpoint() throws Exception {
        String encoded = URLEncoder.encode("The quick brown fox", "UTF-8").replace("+", "%20");
        given()
            .pathParam("txt", encoded)
        .when()
            .get(baseUrl + "/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}