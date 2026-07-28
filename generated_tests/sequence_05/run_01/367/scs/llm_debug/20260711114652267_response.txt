package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return envUrl != null ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testUseridValidUserPrefix() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("name", "userid")
            .pathParam("val", "user12345")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridLengthLessThanOrEqualTo6() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("name", "userid")
            .pathParam("val", "user12")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridLengthGreaterThan6NoUserPrefix() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("name", "userid")
            .pathParam("val", "admin1234")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionValidAmAndAbcCom() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("name", "session")
            .pathParam("val", "am")
            .pathParam("site", "abc.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionInvalidValOrSite() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("name", "session")
            .pathParam("val", "pm")
            .pathParam("site", "abc.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("name", "other")
            .pathParam("val", "val")
            .pathParam("site", "site.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }
}