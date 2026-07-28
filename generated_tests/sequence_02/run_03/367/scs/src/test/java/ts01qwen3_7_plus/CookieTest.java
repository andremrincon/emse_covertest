package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @Test(timeout = 60000)
    public void testUseridLongStartsWithUser() {
        given()
            .when()
                .get("/api/cookie/userid/user1234/example.com")
            .then()
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShort() {
        given()
            .when()
                .get("/api/cookie/userid/user12/example.com")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridLongNotStartsWithUser() {
        given()
            .when()
                .get("/api/cookie/userid/admin1234/example.com")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNotAm() {
        given()
            .when()
                .get("/api/cookie/session/pm/abc.com")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .when()
                .get("/api/cookie/other/val/site")
            .then()
                .body(equalTo("0"));
    }
}