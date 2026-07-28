package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class DateParseTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testDateParseJan() {
        RestAssured.baseURI = getBaseUrl();
        given().when().get("/api/dateparse/mon/jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseFeb() {
        RestAssured.baseURI = getBaseUrl();
        given().when().get("/api/dateparse/mon/feb").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseMar() {
        RestAssured.baseURI = getBaseUrl();
        given().when().get("/api/dateparse/mon/mar").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseApr() {
        RestAssured.baseURI = getBaseUrl();
        given().when().get("/api/dateparse/mon/apr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseAug() {
        RestAssured.baseURI = getBaseUrl();
        given().when().get("/api/dateparse/mon/aug").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseOct() {
        RestAssured.baseURI = getBaseUrl();
        given().when().get("/api/dateparse/mon/oct").then().statusCode(200);
    }
}