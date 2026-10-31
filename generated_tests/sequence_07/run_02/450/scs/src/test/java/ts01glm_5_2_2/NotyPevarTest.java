package ts01glm_5_2_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import org.junit.BeforeClass;
import org.junit.Test;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_branchI0True() {
        given().when().get("/api/notypevar/28/a").then().statusCode(equalTo(200));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_sWorld_branchesI1I2I3True() {
        given().when().get("/api/notypevar/7/world").then().statusCode(equalTo(200));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i5_sA_allBranchesFalse() {
        given().when().get("/api/notypevar/5/a").then().statusCode(equalTo(200));
    }
}