package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubject_iEquals5() {
        given().when().get("/api/costfuns/5/a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iLessThanMinus444() {
        given().when().get("/api/costfuns/-500/baab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iGreaterThan666() {
        given().when().get("/api/costfuns/700/c").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iBetweenMinus444AndMinus333() {
        given().when().get("/api/costfuns/-400/ababba").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEqualsMinus4() {
        given().when().get("/api/costfuns/-4/abab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iBetween555And666() {
        given().when().get("/api/costfuns/600/a").then().statusCode(200);
    }
}