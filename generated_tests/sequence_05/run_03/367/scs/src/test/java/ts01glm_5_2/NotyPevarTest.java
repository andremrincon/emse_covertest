package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testI0TrueI1FalseI2FalseI3True() {
        given().when().get("/api/notypevar/28/a").then().statusCode(200).body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testI0FalseI1TrueI2FalseI3True() {
        given().when().get("/api/notypevar/7/a").then().statusCode(200).body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testI0FalseI1FalseI2TrueI3False() {
        given().when().get("/api/notypevar/3/world").then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testAllBranchesFalse() {
        given().when().get("/api/notypevar/3/a").then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testInvalidIntegerParameter() {
        given().when().get("/api/notypevar/abc/test").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testIntegerOverflowParameter() {
        given().when().get("/api/notypevar/2147483648/test").then().statusCode(400);
    }
}