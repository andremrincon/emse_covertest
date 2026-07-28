package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_notypevar_path_sets_result_to_three_when_i28_and_s_zz() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/The/" + uid + "/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/28/zz");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_path_sets_result_to_two_when_i3_and_s_zz() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/3/zz");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns_200_for_i7_and_s_aaa() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/7/aaa");
        act.then().statusCode(200);
    }
}