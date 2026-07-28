package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    private String baseUrl() {
        String prop = System.getProperty("api.baseUrl");
        String env = System.getenv("BASE_URL");
        if (prop != null && !prop.isEmpty()) return prop;
        if (env != null && !env.isEmpty()) return env;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testMaleRecognizedTitle() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/title/{sex}/{title}", "Male", "Mr");
        r.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleUnrecognizedTitle() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/title/{sex}/{title}", "male", "principal");
        r.then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleRecognizedTitle() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/title/{sex}/{title}", "Female", "DR");
        r.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleUnrecognizedTitle() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/title/{sex}/{title}", "female", "queen");
        r.then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneRecognizedTitle() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/title/{sex}/{title}", "none", "Prof");
        r.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoneUnrecognizedTitle() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/title/{sex}/{title}", "none", "mr");
        r.then().assertThat().body(equalTo("-1"));
    }
}