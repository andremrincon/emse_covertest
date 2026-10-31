package ts01glm_5_2_3;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getenv().getOrDefault("APP_HOST", "localhost");
        String port = System.getenv().getOrDefault("APP_PORT", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
                .get("/api/pat/ABCDEF/ABC")
            .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testReversePatFoundNoPat() {
        given()
            .when()
                .get("/api/pat/DEFABC/CBA")
            .then()
                .statusCode(200)
                .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testBothPatAndReverseNotAdjacent() {
        given()
            .when()
                .get("/api/pat/ABCXYZCBA/ABC")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testPalindromePatFollowedByReverse() {
        given()
            .when()
                .get("/api/pat/ABCCBA/ABC")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFollowedByPatImmediately() {
        given()
            .when()
                .get("/api/pat/CBAABC/ABC")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testPatLenTwoOrLess() {
        given()
            .when()
                .get("/api/pat/ABABDEFG/AB")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }
}