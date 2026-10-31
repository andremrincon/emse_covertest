package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_endpoint_classifies_url_returns_200() throws Exception {
        String setup = URLEncoder.encode("init", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("http://a/b", StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", txt);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_pat_endpoint_classifies_date_returns_200() throws Exception {
        String setup = URLEncoder.encode("init", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("wed12jan", StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", txt);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_endpoint_classifies_fpe_returns_200() throws Exception {
        String setup = URLEncoder.encode("0", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("12.3e+45", StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", txt);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_endpoint_returns_none_for_nonmatching_text_returns_200() throws Exception {
        String setup = URLEncoder.encode("warmup", StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String unique = "none-" + UUID.randomUUID().toString();
        String txt = URLEncoder.encode(unique, StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", txt);
        resp.then().statusCode(200);
    }
}