package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    private String base() {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) {
            b = System.getenv("API_BASE");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        return b;
    }

    @Test(timeout = 60000)
    public void testMaleMr_returns1() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/title/{sex}/{title}", "male", "mr");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleDr_returns0() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/title/{sex}/{title}", "female", "dr");
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneDr_returns2() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/title/{sex}/{title}", "none", "dr");
        act.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testMaleDr_mixedCase_returns1() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/title/{sex}/{title}", "MALE", "Dr");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleMr_returnsMinus1() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/title/{sex}/{title}", "female", "mr");
        act.then().assertThat().body(equalTo("-1"));
    }
}