package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {
	@BeforeClass
	public static void init() {
		String base = System.getProperty("api.base");
		if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
		if (base == null || base.isEmpty()) base = "http://localhost:8080";
		RestAssured.baseURI = base;
	}
	@Test(timeout = 60000)
	public void testConstantsAndUnaryOperators() {
		given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/log/2.718281828459045/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/sine/1/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/cosine/1/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/tangent/1/0").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/calc/pi/0/0");
		act.then().statusCode(200);
	}
	@Test(timeout = 60000)
	public void testBinaryPlusBody() {
		given().when().get("/api/calc/Multiply/5/2").then().statusCode(lessThan(300));
		given().when().get("/api/calc/subtract/10/3").then().statusCode(lessThan(300));
		given().when().get("/api/calc/divide/9/3").then().statusCode(lessThan(300));
		Response act = given().when().get("/api/calc/plus/15.5/4.5");
		act.then().body(equalTo("20.0"));
	}
	@Test(timeout = 60000)
	public void testInvalidNumberFormatResultsIn400() {
		given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/add/10/twenty").then().statusCode(400);
	}
}