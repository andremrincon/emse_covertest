package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DateParseTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getProperty("api.base", "http://localhost:8080");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testWedMarProduces4() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/dateparse/wed/mar");
        Assert.assertEquals("4", act.asString());
    }

    @Test(timeout = 60000)
    public void testMonMovemberProduces1() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/dateparse/mon/Movember");
        Assert.assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testSatDecProduces13() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/dateparse/sat/dec");
        Assert.assertEquals("13", act.asString());
    }

    @Test(timeout = 60000)
    public void testUnknownUnknownProduces0() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/dateparse/superday/movember");
        Assert.assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testTueMayProduces6() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/dateparse/tue/may");
        Assert.assertEquals("6", act.asString());
    }
}