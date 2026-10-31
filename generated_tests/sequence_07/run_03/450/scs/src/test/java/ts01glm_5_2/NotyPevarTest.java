package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String host = System.getenv("API_HOST");
        if (host == null || host.isEmpty()) {
            host = "localhost";
        }
        String port = System.getenv("API_PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testSubject_i28_sA_branchI0True() {
        given().when().get("/api/notypevar/28/a").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i7_sA_branchI1True() {
        given().when().get("/api/notypevar/7/a").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0_sWorld_branchI2TrueI3False() {
        given().when().get("/api/notypevar/0/world").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubject_i5_sA_allBranchesFalse() {
        given().when().get("/api/notypevar/5/a").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_i28_sWorld_i0TrueI2True() {
        given().when().get("/api/notypevar/28/world").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i7_sWorld_i1TrueI2True() {
        given().when().get("/api/notypevar/7/world").then().body(equalTo("3"));
    }
}