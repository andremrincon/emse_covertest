package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateParseTest {
    private static String base;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("API_BASE");
        String env = System.getenv("API_BASE");
        base = prop != null ? prop : env;
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testDateparse_wed_aug_returns200() {
        given().when().get(base + "/api/pat/The%20quick%20brown%20fox").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/dateparse/wed/aug");
        assertTrue(resp.getStatusCode() == 200);
    }

    @Test(timeout = 60000)
    public void testDateparse_mon_jan_returnsBody2() {
        given().when().get(base + "/api/pat/Example").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/dateparse/mon/jan");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDateparse_sun_dec_returnsBody13() {
        given().when().get(base + "/api/pat/Check").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/dateparse/sun/dec");
        assertEquals("13", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDateparse_random_feb_returnsBody2() {
        given().when().get(base + "/api/pat/Arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/dateparse/random/feb");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDateparse_thur_mar_returnsBody4() {
        given().when().get(base + "/api/pat/Setup").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/dateparse/thur/mar");
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDateparse_xyz_unknown_returnsBody0() {
        given().when().get(base + "/api/pat/Health").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/dateparse/xyz/unknownmonth");
        assertEquals("0", resp.asString());
    }
}