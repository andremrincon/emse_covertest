package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThan3() {
        Response response = RestAssured.given()
                .pathParam("txt", "abc")
                .pathParam("pat", "ab")
                .when()
                .get(baseUrl + "/api/pat/{txt}/{pat}");
        response.then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatrev() {
        Response response = RestAssured.given()
                .pathParam("txt", "xabcy")
                .pathParam("pat", "abc")
                .when()
                .get(baseUrl + "/api/pat/{txt}/{pat}");
        response.then().statusCode(200).body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatrevAdjacent() {
        Response response = RestAssured.given()
                .pathParam("txt", "abccba")
                .pathParam("pat", "abc")
                .when()
                .get(baseUrl + "/api/pat/{txt}/{pat}");
        response.then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatrevSeparated() {
        Response response = RestAssured.given()
                .pathParam("txt", "abcXcba")
                .pathParam("pat", "abc")
                .when()
                .get(baseUrl + "/api/pat/{txt}/{pat}");
        response.then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatrevFoundNoPat() {
        Response response = RestAssured.given()
                .pathParam("txt", "xcbay")
                .pathParam("pat", "abc")
                .when()
                .get(baseUrl + "/api/pat/{txt}/{pat}");
        response.then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatrevAndPatAdjacent() {
        Response response = RestAssured.given()
                .pathParam("txt", "cbaabc")
                .pathParam("pat", "abc")
                .when()
                .get(baseUrl + "/api/pat/{txt}/{pat}");
        response.then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatrevAndPatSeparated() {
        Response response = RestAssured.given()
                .pathParam("txt", "cbaXabc")
                .pathParam("pat", "abc")
                .when()
                .get(baseUrl + "/api/pat/{txt}/{pat}");
        response.then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNeitherFound() {
        Response response = RestAssured.given()
                .pathParam("txt", "xyz")
                .pathParam("pat", "abc")
                .when()
                .get(baseUrl + "/api/pat/{txt}/{pat}");
        response.then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatCharMatchStringMismatch() {
        Response response = RestAssured.given()
                .pathParam("txt", "abXcba")
                .pathParam("pat", "abc")
                .when()
                .get(baseUrl + "/api/pat/{txt}/{pat}");
        response.then().statusCode(200).body(equalTo("2"));
    }
}