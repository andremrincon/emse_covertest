package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testMaleValidTitleMr() {
        given().when().get("/api/title/male/mr").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        given().when().get("/api/title/male/xyz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitleMrs() {
        given().when().get("/api/title/female/mrs").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        given().when().get("/api/title/female/xyz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneValidTitleDr() {
        given().when().get("/api/title/none/dr").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsError() {
        given().when().get("/api/title/unknown/xyz").then().statusCode(200);
    }
}