package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatLengthTwoReturnsZero() {
        given().when().get(RestAssured.baseURI + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/abcd/ab");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReturns1() {
        given().when().get(RestAssured.baseURI + "/api/pat/ready").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/xxabczz/abc");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundReturns2() {
        given().when().get(RestAssured.baseURI + "/api/pat/ping").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/zzzcbazz/abc");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatThenReverseAdjacentReturnsIndex() {
        given().when().get(RestAssured.baseURI + "/api/pat/awake").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/xxabccbayy/abc");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReverseThenPatAdjacentReturnsIndex() {
        given().when().get(RestAssured.baseURI + "/api/pat/ready2").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/cbaabc/abc");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonAdjacentReturnsIndex() {
        given().when().get(RestAssured.baseURI + "/api/pat/check").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/xxabcxxxcba/abc");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatLengthGreaterNoMatchReturnsZero() {
        given().when().get(RestAssured.baseURI + "/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/abcdefgh/xyz");
        resp.then().body(equalTo("0"));
    }
}