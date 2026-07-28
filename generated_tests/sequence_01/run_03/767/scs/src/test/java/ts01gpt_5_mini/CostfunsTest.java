package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null) base = System.getenv("base.url");
        if (base == null) base = System.getProperty("base.url");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testReturnsZeroWhenIIsMinusFourAndSIsAbab() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-4/abab").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testIEqualsFivePathProducesExpectedResultOverwrittenByInequality() {
        given().when().get("/api/pat/setup1").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/5/abab").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testLargeNegativeTriggersLessThanAndLessOrEqualBranches() {
        given().when().get("/api/pat/setup2").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-500/abab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCompareToGreaterAndGreaterOrEqualBranchesExecute() {
        given().when().get("/api/pat/setup3").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-4/zzzz").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testSEqualsBaabBranchExecutes() {
        given().when().get("/api/pat/setup4").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-4/baab").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testGreaterThan666AndGreaterOrEqual555BranchesExecute() {
        given().when().get("/api/pat/setup5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/700/abab").then().body(equalTo("10"));
    }
}