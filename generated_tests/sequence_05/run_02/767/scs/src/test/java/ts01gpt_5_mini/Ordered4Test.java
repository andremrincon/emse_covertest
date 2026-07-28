package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

	@BeforeClass
	public static void init() {
		String base = System.getProperty("base.url");
		if (base == null) {
			base = System.getenv("BASE_URL");
		}
		if (base != null) {
			RestAssured.baseURI = base;
		}
	}

	@Test(timeout = 60000)
	public void testIncreasingOrderProducesIncreasingResult() {
		given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
		given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "aaaab", "aaaad", "aaaac").then().body(equalTo("increasing"));
	}

	@Test(timeout = 60000)
	public void testDecreasingOrderProducesDecreasingResult() {
		given().when().get("/api/pat/{txt}", "healthcheck2").then().statusCode(lessThan(300));
		given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx").then().body(equalTo("decreasing"));
	}

	@Test(timeout = 60000)
	public void testOutOfRangeLengthProducesUnorderedResult() {
		given().when().get("/api/pat/{txt}", "hc3").then().statusCode(lessThan(300));
		given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "a", "aaaaa", "aaaaa", "aaaaa").then().body(equalTo("unordered"));
	}

	@Test(timeout = 60000)
	public void testInRangeButNotOrderedProducesUnorderedResult() {
		given().when().get("/api/pat/{txt}", "hc4").then().statusCode(lessThan(300));
		given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "ddddd", "bbbbb").then().body(equalTo("unordered"));
	}
}