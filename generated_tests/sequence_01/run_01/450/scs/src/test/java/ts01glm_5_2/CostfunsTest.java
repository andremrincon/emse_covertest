package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host");
        if (host == null) {
            host = "localhost";
        }
        String port = System.getProperty("server.port");
        if (port == null) {
            port = "8080";
        }
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testSubjectIEquals5AndSIsA() {
        given()
            .when()
                .get("/api/costfuns/5/a")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanMinus444AndSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/-445/baab")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsMinus4AndSEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/-4/ababba")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666AndSGreaterThanAbabba() {
        given()
            .when()
                .get("/api/costfuns/667/zzzzz")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCostfunsInvalidIntegerReturns400() {
        given()
            .when()
                .get("/api/costfuns/one/test")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testCostfunsIntegerOverflowReturns500() {
        given()
            .when()
                .get("/api/costfuns/3000000000/test")
            .then()
                .statusCode(400);
    }
}