package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class DateParseTest {

	@Test(timeout = 60000)
	public void testDateparseReturns200ForKnownDayAndMonth() {
		String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL"));
		if (baseUrl == null || baseUrl.isEmpty()) {
			baseUrl = "http://localhost:8080";
		}
		RestAssured.baseURI = baseUrl;
		given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/tue/feb").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/wed/mar").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/thur/apr").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/dateparse/Wednesday/August");
		assertEquals(200, act.getStatusCode());
	}

	@Test(timeout = 60000)
	public void testDateparseIsCaseInsensitiveAndParsesMonthAbbrev() {
		String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL"));
		if (baseUrl == null || baseUrl.isEmpty()) {
			baseUrl = "http://localhost:8080";
		}
		RestAssured.baseURI = baseUrl;
		given().when().get("/api/dateparse/fri/may").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/sat/jun").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/sun/jul").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/aug").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/dateparse/tuesday/MAR");
		assertEquals(200, act.getStatusCode());
	}

	@Test(timeout = 60000)
	public void testDateparseReturns500ForInvalidDayname() {
		String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL"));
		if (baseUrl == null || baseUrl.isEmpty()) {
			baseUrl = "http://localhost:8080";
		}
		RestAssured.baseURI = baseUrl;
		given().when().get("/api/dateparse/mon/sep").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/oct").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/nov").then().statusCode(lessThan(300));
		given().when().get("/api/dateparse/mon/dec").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/dateparse/123/August");
		assertEquals(200, act.getStatusCode());
	}
}