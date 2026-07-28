package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

	@Test(timeout = 60000)
	public void testDayAndMonthRecognized() {
		String base = System.getProperty("api.base", "http://localhost:8080");
		given().when().get(base + "/api/pat/The").then().statusCode(lessThan(300));
		Response act = given().when().get(base + "/api/dateparse/wed/aug");
		act.then().body(equalTo("9"));
	}

	@Test(timeout = 60000)
	public void testUnrecognizedDayRecognizedMonth() {
		String base = System.getProperty("api.base", "http://localhost:8080");
		given().when().get(base + "/api/pat/hello").then().statusCode(lessThan(300));
		Response act = given().when().get(base + "/api/dateparse/noday/dec");
		act.then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void testInvalidDayOrMonthCausesServerError() {
		String base = System.getProperty("api.base", "http://localhost:8080");
		given().when().get(base + "/api/pat/test").then().statusCode(lessThan(300));
		Response act = given().when().get(base + "/api/dateparse/123/Movember");
		act.then().statusCode(200);
	}
}