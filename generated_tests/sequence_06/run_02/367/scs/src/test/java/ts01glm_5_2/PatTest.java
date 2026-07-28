package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        String port = System.getProperty("server.port");
        if (port != null) {
            RestAssured.port = Integer.parseInt(port);
        }
        String host = System.getProperty("server.host");
        if (host != null) {
            RestAssured.baseURI = host;
        }
    }

    @Test(timeout = 60000)
    public void patLenLessThan3ReturnsZero() {
        given()
            .when()
            .get("/api/pat/abc/ab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void patFoundReverseNotFoundReturnsOne() {
        given()
            .when()
            .get("/api/pat/abcacdef/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void patFoundReverseAdjacentReturnsIndex() {
        given()
            .when()
            .get("/api/pat/abccba/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void patFoundReverseNonAdjacentReturnsIndex() {
        given()
            .when()
            .get("/api/pat/abcxcba/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void reverseFoundFirstPatNotFoundReturnsTwo() {
        given()
            .when()
            .get("/api/pat/cba/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void reverseFoundFirstPatAdjacentReturnsIndex() {
        given()
            .when()
            .get("/api/pat/cbaabc/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void reverseFoundFirstPatNonAdjacentReturnsIndex() {
        given()
            .when()
            .get("/api/pat/cbaxabc/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void neitherPatNorReverseFoundReturnsZero() {
        given()
            .when()
            .get("/api/pat/xyz/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void reverseFoundInnerLoopPartialMatchReturnsTwo() {
        given()
            .when()
            .get("/api/pat/cbaacda/abc")
            .then()
            .statusCode(200);
    }
}