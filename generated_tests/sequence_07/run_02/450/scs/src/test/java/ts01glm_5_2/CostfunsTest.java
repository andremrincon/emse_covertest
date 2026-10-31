package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIEqualsFiveBranch() {
        given().when().get("/api/costfuns/5/algorithm").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanNegative444Branch() {
        given().when().get("/api/costfuns/-500/a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsNegativeFourBranch() {
        given().when().get("/api/costfuns/-4/a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666Branch() {
        given().when().get("/api/costfuns/700/a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSEqualsBaabBranch() {
        given().when().get("/api/costfuns/1/baab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSCompareToEqualBranch() {
        given().when().get("/api/costfuns/1/ababba").then().statusCode(200);
    }
}