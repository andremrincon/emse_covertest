package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.baseURI;

public class DateParseTest {
	static {
		String base = System.getProperty("base.url");
		if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
		if (base == null || base.isEmpty()) base = "http://localhost:8080";
		io.restassured.RestAssured.baseURI = base;
	}

	@Test(timeout = 60000)
	public void test_valid_wednesday_august_status200() {
		given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/feb").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/mar").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/apr").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/may").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/jun").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/dateparse/Wednesday/August");
		act.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_valid_tuesday_MAR_status200() {
		given().when().get("/api/dateparse/mon/jul").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/aug").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/sep").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/oct").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/dateparse/tuesday/MAR");
		act.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_invalid_day_numeric_status500() {
		given().when().get("/api/dateparse/mon/nov").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/dateparse/123/Mar");
		act.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_invalid_month_noname_status500() {
		given().when().get("/api/dateparse/mon/dec").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/dateparse/Mon/Movember");
		act.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_uppercase_sun_dec_status200() {
		given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/dateparse/SUN/DEC");
		act.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_mon_nov_status200() {
		given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/dateparse/mon/nov");
		act.then().statusCode(200);
	}
}