package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @Before
    public void setUp() {
        String port = System.getProperty("server.port", "8080");
        RestAssured.port = Integer.parseInt(port);
        String host = System.getProperty("server.host", "localhost");
        RestAssured.baseURI = "http://" + host;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderAllLength5() {
        given()
            .when()
                .get("/api/ordered4/apple/grape/zebra/mango")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderAllLength5() {
        given()
            .when()
                .get("/api/ordered4/zebra/mango/apple/grape")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthTooShort() {
        given()
            .when()
                .get("/api/ordered4/a/grape/zebra/mango")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthInRangeNoOrder() {
        given()
            .when()
                .get("/api/ordered4/apple/grape/mango/apple")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderAllLength6() {
        given()
            .when()
                .get("/api/ordered4/apples/grapes/zebras/mangos")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLength4Boundary() {
        given()
            .when()
                .get("/api/ordered4/appl/grape/zebra/mango")
            .then()
                .statusCode(200);
    }
}