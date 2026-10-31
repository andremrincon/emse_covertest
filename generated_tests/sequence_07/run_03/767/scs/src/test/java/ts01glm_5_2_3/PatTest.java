package ts01glm_5_2_3;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatternLengthLessThan3() {
        given().when().get("/api/pat/{txt}/{pat}", "abc", "ab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundReverseNotFound() {
        given().when().get("/api/pat/{txt}/{pat}", "abcdef", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundReverseFoundAdjacent() {
        given().when().get("/api/pat/{txt}/{pat}", "abccba", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundReverseFoundNonAdjacent() {
        given().when().get("/api/pat/{txt}/{pat}", "abcXcba", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatternFoundAdjacent() {
        given().when().get("/api/pat/{txt}/{pat}", "cbaabc", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatternFoundNonAdjacent() {
        given().when().get("/api/pat/{txt}/{pat}", "cbaXabc", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatternNotFound() {
        given().when().get("/api/pat/{txt}/{pat}", "cba", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatternNorReverseFound() {
        given().when().get("/api/pat/{txt}/{pat}", "xyz", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFirstCharMatchesButFullPatternDoesNot() {
        given().when().get("/api/pat/{txt}/{pat}", "aXc", "abc").then().statusCode(200);
    }
}