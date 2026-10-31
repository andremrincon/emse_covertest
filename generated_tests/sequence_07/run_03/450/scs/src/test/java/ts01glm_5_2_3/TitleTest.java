package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitleMr() {
        given().when().get("/api/title/male/mr").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        given().when().get("/api/title/male/smith").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitleMrs() {
        given().when().get("/api/title/female/mrs").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        given().when().get("/api/title/female/smith").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitleDr() {
        given().when().get("/api/title/none/dr").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitle() {
        given().when().get("/api/title/none/smith").then().statusCode(200);
    }
}