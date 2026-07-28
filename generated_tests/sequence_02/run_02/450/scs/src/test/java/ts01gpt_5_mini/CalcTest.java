package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

	@BeforeClass
	public static void init() {
		String base = System.getProperty("base.url");
		if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
		if (base == null || base.isEmpty()) base = "http://localhost:8080";
		RestAssured.baseURI = base;
	}

	@Test(timeout = 60000)
	public void testAdditionProducesCorrectResult() {
		given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/log/2.718281828459045/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/plus/5/3").then().body(equalTo("8.0"));
	}

	@Test(timeout = 60000)
	public void testDivisionProducesCorrectDecimalResult() {
		given().when().get("/api/calc/subtract/10/4").then().statusCode(lessThan(300));
		given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
		given().when().get("/api/calc/divide/9/3").then().statusCode(lessThan(300));
		given().when().get("/api/calc/divide/10/4").then().body(equalTo("2.5"));
	}

	@Test(timeout = 60000)
	public void testPiConstantReturnsExpectedValue() {
		given().when().get("/api/calc/plus/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/subtract/0/0").then().statusCode(lessThan(300));
		given().when().get("/api/calc/multiply/1/1").then().statusCode(lessThan(300));
		given().when().get("/api/calc/divide/1/1").then().statusCode(lessThan(300));
		given().when().get("/api/calc/pi/0/0").then().body(equalTo("3.141592653589793"));
	}
}