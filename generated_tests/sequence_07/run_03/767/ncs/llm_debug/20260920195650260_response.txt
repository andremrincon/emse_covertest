package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class RemainderTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testRemainderAZeroBPositive() {
        given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderBZero() {
        given()
            .when()
                .get("/api/remainder/17/0")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderBothPositive() {
        Response resp = given()
            .when()
                .get("/api/remainder/17/5");
        resp.then().statusCode(200);
        String body = resp.asString();
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(body);
        assertTrue(m.find());
        int value = Integer.parseInt(m.group());
        assertEquals(2, value);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        given()
            .when()
                .get("/api/remainder/-9/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderBothNegative() {
        given()
            .when()
                .get("/api/remainder/-9/-5")
            .then()
                .statusCode(lessThan(300));
    }
}