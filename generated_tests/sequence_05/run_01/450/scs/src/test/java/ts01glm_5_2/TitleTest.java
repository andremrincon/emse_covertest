package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TitleTest {

    @Before
    public void setUp() {
        String host = System.getProperty("host", "localhost");
        String port = System.getProperty("port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testMaleValidTitle() {
        given().when().get("/api/title/male/mr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        given().when().get("/api/title/male/Smith").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitle() {
        given().when().get("/api/title/female/mrs").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        given().when().get("/api/title/female/Li").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneValidTitle() {
        given().when().get("/api/title/none/dr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneInvalidTitle() {
        given().when().get("/api/title/none/Smith").then().statusCode(200);
    }
}