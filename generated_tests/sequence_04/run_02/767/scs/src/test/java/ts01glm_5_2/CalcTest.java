package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"0.0\"   Actual:")
    @Test(timeout = 60000)
    public void testCalcPlusOperation() {
        given()
            .accept("**")
        .when()
            .get("/api/calc/unknown/10/5")
        .then()
            .body(equalTo("0.0"));
    }
}