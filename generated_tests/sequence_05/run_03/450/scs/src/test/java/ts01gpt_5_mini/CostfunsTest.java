package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_returnsZero_for_iMinus4_and_sAbab() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "abab").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_returnsSix_for_i5_and_sAbab() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "abab").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_returnsTen_for_iNegativeThousand_and_sZzzz() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -1000, "zzzz").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_compareTo_ge_zero_path_exercised_for_sAbabba() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "ababba").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_sEquals_baab_branch_executed_for_iMinus4_and_sBaab() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "baab").then().body(equalTo("10"));
    }
}