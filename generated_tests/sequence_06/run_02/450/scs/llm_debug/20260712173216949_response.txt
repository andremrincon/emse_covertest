package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

@RunWith(Parameterized.class)
public class DateParseTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    private final String dayname;
    private final String monthname;
    private final int expectedStatus;

    public DateParseTest(String dayname, String monthname, int expectedStatus) {
        this.dayname = dayname;
        this.monthname = monthname;
        this.expectedStatus = expectedStatus;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"mon", "jan", 200},
            {"tue", "feb", 200},
            {"wed", "mar", 200},
            {"thur", "apr", 200},
            {"fri", "may", 200},
            {"sat", "jun", 200},
            {"sun", "jul", 200},
            {"mon", "aug", 200},
            {"tue", "sep", 200},
            {"wed", "oct", 200},
            {"thur", "nov", 200},
            {"fri", "dec", 200},
            {"Superday", "jan", 200},
            {"mon", "Movember", 200},
            {"123", "456", 200}
        });
    }

    @Test(timeout = 60000)
    public void testDateParse() {
        given()
            .when()
            .get("/api/dateparse/{dayname}/{monthname}", dayname, monthname)
            .then()
            .statusCode(expectedStatus);
    }
}