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
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleMr_returns1() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/male/mr");
        Assert.assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testMaleMrs_returnsMinusOne() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/male/mrs");
        Assert.assertEquals("-1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testFemaleMs_returns0() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/female/ms");
        Assert.assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testFemaleUnknown_returnsMinusOne() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/female/unknowntitle");
        Assert.assertEquals("-1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoneDr_returns2() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/none/dr");
        Assert.assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUnknownSex_returnsMinusOne() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/other/some");
        Assert.assertEquals("-1", resp.asString());
    }
}