package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitle() {
        String path = "/api/title/male/mr";
        given().when().get(path).then().statusCode(lessThan(300));
        given().when().get(path).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        String path = "/api/title/male/mrs";
        given().when().get(path).then().statusCode(lessThan(300));
        given().when().get(path).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitle() {
        String path = "/api/title/female/mrs";
        given().when().get(path).then().statusCode(lessThan(300));
        given().when().get(path).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        String path = "/api/title/female/mr";
        given().when().get(path).then().statusCode(lessThan(300));
        given().when().get(path).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitle() {
        String path = "/api/title/none/dr";
        given().when().get(path).then().statusCode(lessThan(300));
        given().when().get(path).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitle() {
        String path = "/api/title/none/mr";
        given().when().get(path).then().statusCode(lessThan(300));
        given().when().get(path).then().statusCode(200);
    }
}