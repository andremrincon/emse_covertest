package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

	@BeforeClass
	public static void init() {
		String base = System.getProperty("api.base");
		if (base == null || base.isEmpty()) {
			base = System.getenv("API_BASE");
		}
		if (base == null || base.isEmpty()) {
			base = "http://localhost:8080";
		}
		RestAssured.baseURI = base;
	}

	@Test(timeout = 60000)
	public void test_iEqualsFive_returns200() {
		RestAssured.given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
		Response resp = RestAssured.given().when().get("/api/costfuns/5/example");
		resp.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_iLessThanMinus444_returns200() {
		RestAssured.given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
		Response resp = RestAssured.given().when().get("/api/costfuns/-445/a");
		resp.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_iGreaterThan666_returns200() {
		RestAssured.given().when().get("/api/calc/subtract/10/2").then().statusCode(lessThan(300));
		Response resp = RestAssured.given().when().get("/api/costfuns/700/test");
		resp.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_iEqualsMinus4_and_sEqualsBaab_returns200() {
		RestAssured.given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
		Response resp = RestAssured.given().when().get("/api/costfuns/-4/baab");
		resp.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_sCompareToGreater_returns200() {
		RestAssured.given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
		Response resp = RestAssured.given().when().get("/api/costfuns/0/zzzz");
		resp.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_iAtLeast555_and_sAmbiguous_returns200() {
		RestAssured.given().when().get("/api/calc/add/2/3").then().statusCode(lessThan(300));
		Response resp = RestAssured.given().when().get("/api/costfuns/555/abab");
		resp.then().statusCode(200);
	}
}