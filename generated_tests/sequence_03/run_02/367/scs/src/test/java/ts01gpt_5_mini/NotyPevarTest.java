package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_i0_true_status_200() throws Exception {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/28/" + URLEncoder.encode("a", StandardCharsets.UTF_8.name()));
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_i1_true_final_result_3() throws Exception {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/7/" + URLEncoder.encode("zzzz", StandardCharsets.UTF_8.name()));
        assertEquals("3", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void test_all_conditions_false_returns_0() throws Exception {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/5/" + URLEncoder.encode("a", StandardCharsets.UTF_8.name()));
        assertEquals("0", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void test_i2_true_returns_2() throws Exception {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/3/" + URLEncoder.encode("z", StandardCharsets.UTF_8.name()));
        assertEquals("2", resp.asString().trim());
    }
}