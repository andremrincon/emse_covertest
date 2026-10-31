package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParseFriMay() {
        given().when().get("/api/dateparse/fri/may").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseSatJun() {
        given().when().get("/api/dateparse/sat/jun").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseSunJul() {
        given().when().get("/api/dateparse/sun/jul").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseMonAug() {
        given().when().get("/api/dateparse/mon/aug").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseTueSep() {
        given().when().get("/api/dateparse/tue/sep").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWedNov() {
        given().when().get("/api/dateparse/wed/nov").then().statusCode(200);
    }
}