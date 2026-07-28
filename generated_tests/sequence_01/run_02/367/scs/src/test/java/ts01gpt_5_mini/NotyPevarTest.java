package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotypevar_returns2_when_compareToLess() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        given().when().get(base + "/api/calc/add/1/1").then().statusCode(lessThan(300));
        given().when().get(base + "/api/notypevar/3/zulu").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_executes_i1_branch_status200() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/api/text2txt/The/" + uuid + "/brown").then().statusCode(lessThan(300));
        given().when().get(base + "/api/notypevar/7/a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotypevar_executes_i0_branch_status200() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        given().when().get(base + "/api/pat/hello").then().statusCode(lessThan(300));
        given().when().get(base + "/api/notypevar/28/a").then().statusCode(200);
    }
}