package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleKnownTitleReturns1() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/male/MR");
        Assert.assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFemaleKnownTitleReturns0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/female/Miss");
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoneWithDoctorReturns2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/none/Dr");
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testMaleUnknownTitleReturnsMinusOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/male/Queen");
        Assert.assertEquals("-1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFemaleDoctorReturns0SharedTitleCase() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/female/dr");
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsMinusOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/robot/mr");
        Assert.assertEquals("-1", resp.getBody().asString());
    }
}