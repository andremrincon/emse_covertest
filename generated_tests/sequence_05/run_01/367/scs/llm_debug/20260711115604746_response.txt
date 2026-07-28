package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

	@BeforeClass
	public static void init() {
		String base = System.getenv("BASE_URL");
		if (base == null || base.isEmpty()) {
			base = System.getProperty("api.base", "http://localhost:8080");
		}
		RestAssured.baseURI = base;
	}

	@Test(timeout = 60000)
	public void test_i_equals_28_triggers_xPlusY_branch_and_returns_200() {
		String uid = UUID.randomUUID().toString();
		given().when().get("/api/text2txt/The/" + uid + "/brown").then().statusCode(lessThan(300));
		Response resp = given().when().get("/api/notypevar/28/a");
		assertEquals(200, resp.getStatusCode());
	}

	@Test(timeout = 60000)
	public void test_i_equals_7_triggers_hello7_and_compareTo_branch_and_returns_200() {
		String uid = UUID.randomUUID().toString();
		given().when().get("/api/pat/" + uid).then().statusCode(lessThan(300));
		Response resp = given().when().get("/api/notypevar/7/z");
		assertEquals(200, resp.getStatusCode());
	}

	@Test(timeout = 60000)
	public void test_i_equals_0_with_small_s_triggers_default_path_and_returns_200() {
		String uid = UUID.randomUUID().toString();
		given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
		Response resp = given().when().get("/api/notypevar/0/a");
		assertEquals(200, resp.getStatusCode());
	}
}