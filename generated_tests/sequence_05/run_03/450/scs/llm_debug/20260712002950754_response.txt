package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLengthLessThanOrEqualToTwo() {
        Response response = given()
            .when()
                .get("/api/pat/{txt}/{pat}", "ABABCABAB", "a");

        response.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatNotFoundAndReverseNotFound() {
        Response response = given()
            .when()
                .get("/api/pat/{txt}/{pat}", "hello%20world", "ABAB");

        response.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotFound() {
        Response response = given()
            .when()
                .get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");

        response.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundAdjacent() {
        Response response = given()
            .when()
                .get("/api/pat/{txt}/{pat}", "ABABBABA", "ABAB");

        response.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundNonAdjacent() {
        Response response = given()
            .when()
                .get("/api/pat/{txt}/{pat}", "ABABxyBABA", "ABAB");

        response.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundAdjacent() {
        Response response = given()
            .when()
                .get("/api/pat/{txt}/{pat}", "BABAABAB", "ABAB");

        response.then().body(equalTo("0"));
    }
}