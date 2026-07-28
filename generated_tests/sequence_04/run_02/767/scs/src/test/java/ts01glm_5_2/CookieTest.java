package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Test;

public class CookieTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testUserIdWithValidPrefixAndLength() {
        RestAssured
            .given()
                .pathParam("name", "userid")
                .pathParam("val", "user1234")
                .pathParam("site", "example.com")
            .when()
                .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        RestAssured
            .given()
                .pathParam("name", "userid")
                .pathParam("val", "abc")
                .pathParam("site", "example.com")
            .when()
                .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValueButNoUserPrefix() {
        RestAssured
            .given()
                .pathParam("name", "userid")
                .pathParam("val", "test1234")
                .pathParam("site", "example.com")
            .when()
                .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithMatchingValueAndSite() {
        RestAssured
            .given()
                .pathParam("name", "session")
                .pathParam("val", "am")
                .pathParam("site", "abc.com")
            .when()
                .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithNonMatchingValue() {
        RestAssured
            .given()
                .pathParam("name", "session")
                .pathParam("val", "other")
                .pathParam("site", "other.com")
            .when()
                .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownName() {
        RestAssured
            .given()
                .pathParam("name", "unknown")
                .pathParam("val", "val")
                .pathParam("site", "site.com")
            .when()
                .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200);
    }
}