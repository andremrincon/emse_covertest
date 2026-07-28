package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

	@BeforeClass
	public static void setup() {
		String base = System.getProperty("base.url");
		if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
		if (base == null || base.isEmpty()) base = "http://localhost:8080";
		RestAssured.baseURI = base;
	}

	@Test(timeout = 60000)
	public void test_i28_szzzz_returns3() {
		given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
		Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "zzzz");
		resp.then().body(equalTo("3"));
	}

	@Test(timeout = 60000)
	public void test_i5_saaa_returns0() {
		given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
		Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "aaa");
		resp.then().body(equalTo("0"));
	}

	@Test(timeout = 60000)
	public void test_i7_triggers_i1_branch_status200() {
		given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
		Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "any");
		resp.then().statusCode(200);
	}
}