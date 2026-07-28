package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given().when().get("/api/pat/hello/ab").then().statusCode(lessThan(300));
        given().when().get("/api/pat/hello/ab").then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevNotFound() {
        given().when().get("/api/pat/helloworld/world").then().statusCode(lessThan(300));
        given().when().get("/api/pat/helloworld/world").then().statusCode(200).body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundImmediatelyAfter() {
        given().when().get("/api/pat/XXabccba/abc").then().statusCode(lessThan(300));
        given().when().get("/api/pat/XXabccba/abc").then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundElsewhere() {
        given().when().get("/api/pat/XabcXYZcba/abc").then().statusCode(lessThan(300));
        given().when().get("/api/pat/XabcXYZcba/abc").then().statusCode(200).body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        given().when().get("/api/pat/hellocbaX/abc").then().statusCode(lessThan(300));
        given().when().get("/api/pat/hellocbaX/abc").then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundImmediatelyAfter() {
        given().when().get("/api/pat/Xcbaabc/abc").then().statusCode(lessThan(300));
        given().when().get("/api/pat/Xcbaabc/abc").then().statusCode(200).body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundElsewhere() {
        given().when().get("/api/pat/XXcbaXYZabc/abc").then().statusCode(lessThan(300));
        given().when().get("/api/pat/XXcbaXYZabc/abc").then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorPatRevFound() {
        given().when().get("/api/pat/helloworld/xyz").then().statusCode(lessThan(300));
        given().when().get("/api/pat/helloworld/xyz").then().statusCode(200).body(equalTo("0"));
    }
}