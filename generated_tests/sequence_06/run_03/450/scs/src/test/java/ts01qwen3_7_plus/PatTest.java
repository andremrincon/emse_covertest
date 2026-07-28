package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualToTwo() {
        Response response = given().when().get("/api/pat/abc/ab");
        response.then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatNotFound() {
        Response response = given().when().get("/api/pat/xyz/abc");
        response.then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevNotFound() {
        Response response = given().when().get("/api/pat/xyzabcxyz/abc");
        response.then().statusCode(200).body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        Response response = given().when().get("/api/pat/xyzcbaxyz/abc");
        response.then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevAdjacent() {
        Response response = given().when().get("/api/pat/xyzabccba/abc");
        response.then().statusCode(200).body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevNotAdjacent() {
        Response response = given().when().get("/api/pat/xyzabcxyzcba/abc");
        response.then().statusCode(200).body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatAdjacent() {
        Response response = given().when().get("/api/pat/xyzcbaabc/abc");
        response.then().statusCode(200).body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatNotAdjacent() {
        Response response = given().when().get("/api/pat/xyzcbaxyzabc/abc");
        response.then().statusCode(200).body(equalTo("3"));
    }
}