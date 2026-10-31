package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleMrReturnsOne() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/male/mr");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFemaleMrsReturnsZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/female/mrs");
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoneDrReturnsTwo() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/none/dr");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUnknownTitleReturnsMinusOne() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/male/unknown");
        assertEquals("-1", act.getBody().asString());
    }
}