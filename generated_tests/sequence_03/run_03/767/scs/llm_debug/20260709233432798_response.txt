package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        if (prop != null && !prop.isEmpty()) {
            RestAssured.baseURI = prop;
        } else if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testReturnsZeroWhenNoConditionsMatch() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/notypevar/0/aaa");
        assertEquals("0", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBranches_i0_i2_i3_with_i28_producesThree() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/notypevar/28/zzz");
        assertEquals("3", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBranches_i1_and_i3_with_i7_producesThree() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/notypevar/7/aaa");
        assertEquals("3", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCompareToBranch_resultsInTwo_for_i5_and_sWorld() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/notypevar/5/world");
        assertEquals("2", r.getBody().asString());
    }
}