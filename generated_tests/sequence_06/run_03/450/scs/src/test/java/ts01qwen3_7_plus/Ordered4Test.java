package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String response = given()
            .when()
            .get("/api/ordered4/apple/berry/delta/cherry")
            .then()
            .statusCode(200)
            .extract()
            .asString();

        assertThat(response, equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String response = given()
            .when()
            .get("/api/ordered4/zebra/yakxx/wolfz/xrayy")
            .then()
            .statusCode(200)
            .extract()
            .asString();

        assertThat(response, equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedNotInSequence() {
        String response = given()
            .when()
            .get("/api/ordered4/apple/cherry/delta/berry")
            .then()
            .statusCode(200)
            .extract()
            .asString();

        assertThat(response, equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthViolation() {
        String response = given()
            .when()
            .get("/api/ordered4/app/berry/delta/cherry")
            .then()
            .statusCode(200)
            .extract()
            .asString();

        assertThat(response, equalTo("unordered"));
    }
}