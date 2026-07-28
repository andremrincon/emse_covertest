package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

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
	public void test_sum_branch_executes_status200() {
		given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
		given().when().get("/api/notypevar/{i}/{s}", 28, "uniqueA").then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_concat_equals_branch_status200() {
		given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
		given().when().get("/api/notypevar/{i}/{s}", 7, "uniqueB").then().statusCode(200);
	}

	@Test(timeout = 60000)
	public void test_compareTo_branch_returns2() {
		given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
		given().when().get("/api/notypevar/{i}/{s}", 1, "z").then().assertThat().body(equalTo("2"));
	}

	@Test(timeout = 60000)
	public void test_greaterThan_branch_returns3() {
		given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
		given().when().get("/api/notypevar/{i}/{s}", 6, "a").then().assertThat().body(equalTo("3"));
	}
}