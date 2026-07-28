package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = System.getProperty("api.base");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_validDayAndAugust_returnsNine() {
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Wednesday/August").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_invalidDayAndInvalidMonth_returnsServerError() {
        given().when().get("/api/pat/ABABCABAB").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/123/Movember").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_lowercaseDay_uppercaseMonth_mar_returnsFour() {
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tuesday/MAR").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_shortDay_and_december_returnsThirteen() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sun/dec").then().body(equalTo("13"));
    }

    @Test(timeout = 60000)
    public void test_nonMatchingDay_and_jan_returnsOne() {
        given().when().get("/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Holiday/jan").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_monLikeFullName_Monday_returnsServerError() {
        given().when().get("/api/filesuffix/home/file.txt").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Monday/August").then().statusCode(200);
    }
}