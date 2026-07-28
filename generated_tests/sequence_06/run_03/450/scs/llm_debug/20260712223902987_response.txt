package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env != null && !env.isEmpty()) ? env : "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_returns3() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/28/z");
        resp.then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i5_returns2() {
        given().when().get("/api/pat/arrange-five").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/5/z");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_triggers_hello7_path_returns3() {
        given().when().get("/api/pat/arrange-seven").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/7/a");
        resp.then().assertThat().body(equalTo("3"));
    }
}