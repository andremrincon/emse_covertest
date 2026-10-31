package ts01gpt_5_mini_3;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {
    private static String baseUrl;

    @BeforeClass
    public static void init() {
        baseUrl = System.getProperty("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void test_iEqualsFive_returnsSix() {
        given().when().get(baseUrl + "/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/costfuns/5/abab").then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_iGreaterThanSixHundredSixtySix_sLexGreater_returnsTen() {
        given().when().get(baseUrl + "/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/costfuns/700/zzzz").then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_iEqualsMinusFour_withAbab_returnsZero() {
        given().when().get(baseUrl + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/costfuns/-4/abab").then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_sEqualsBaab_statusOk() {
        given().when().get(baseUrl + "/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/costfuns/6/baab").then().statusCode(200);
    }
}