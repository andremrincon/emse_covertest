package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class DateParseTest {

    @Test(timeout = 60000)
    public void testDateParseFeb() {
        given().when().get("/api/dateparse/mon/feb").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseApr() {
        given().when().get("/api/dateparse/tue/apr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseJun() {
        given().when().get("/api/dateparse/wed/jun").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseAug() {
        given().when().get("/api/dateparse/thur/aug").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseOct() {
        given().when().get("/api/dateparse/fri/oct").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseDec() {
        given().when().get("/api/dateparse/sat/dec").then().statusCode(200);
    }
}