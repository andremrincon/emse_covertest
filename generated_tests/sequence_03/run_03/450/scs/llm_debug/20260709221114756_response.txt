package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Test(timeout = 60000)
    public void testCostfuns_CaseA() {
        given()
            .basePath("/")
            .pathParam("i", 5)
            .pathParam("s", "abab")
        .when()
            .get("http://localhost:8080/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_CaseB() {
        given()
            .basePath("/")
            .pathParam("i", -500)
            .pathParam("s", "abab")
        .when()
            .get("http://localhost:8080/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_CaseC() {
        given()
            .basePath("/")
            .pathParam("i", 700)
            .pathParam("s", "abab")
        .when()
            .get("http://localhost:8080/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_CaseD() {
        given()
            .basePath("/")
            .pathParam("i", -4)
            .pathParam("s", "abab")
        .when()
            .get("http://localhost:8080/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_CaseE() {
        given()
            .basePath("/")
            .pathParam("i", 0)
            .pathParam("s", "baab")
        .when()
            .get("http://localhost:8080/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_CaseF() {
        given()
            .basePath("/")
            .pathParam("i", 0)
            .pathParam("s", "ababba")
        .when()
            .get("http://localhost:8080/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }
}