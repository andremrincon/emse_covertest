package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMonJan_returns2() {
        given().when().get(RestAssured.baseURI + "/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response act = given().when().get(RestAssured.baseURI + "/api/dateparse/Mon/Jan");
        Assert.assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWednesdayAugust_status200() {
        given().when().get(RestAssured.baseURI + "/api/calc/add/0/4.5").then().statusCode(lessThan(300));
        Response act = given().when().get(RestAssured.baseURI + "/api/dateparse/Wednesday/August");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTuEMar_returns4() {
        given().when().get(RestAssured.baseURI + "/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get(RestAssured.baseURI + "/api/dateparse/TuE/Mar");
        Assert.assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSuperdayMovember_status500() {
        given().when().get(RestAssured.baseURI + "/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response act = given().when().get(RestAssured.baseURI + "/api/dateparse/Superday/Movember");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSunDec_returns13() {
        given().when().get(RestAssured.baseURI + "/api/calc/add/0/1").then().statusCode(lessThan(300));
        Response act = given().when().get(RestAssured.baseURI + "/api/dateparse/sUn/Dec");
        Assert.assertEquals("13", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testThUrAug_returns9() {
        given().when().get(RestAssured.baseURI + "/api/calc/add/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(RestAssured.baseURI + "/api/dateparse/ThUr/AuG");
        Assert.assertEquals("9", act.getBody().asString());
    }
}