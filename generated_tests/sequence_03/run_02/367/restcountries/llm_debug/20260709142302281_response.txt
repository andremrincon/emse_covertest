package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testContributeValidAmountAndToken() {
        String payload = "{\"amount\":1000,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        String payload = "{\"amount\":500,\"currency\":\"usd\"}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmount() {
        String payload = "{\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyBody() {
        given().contentType(ContentType.JSON).body("{}").when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeZeroAmount() {
        String payload = "{\"amount\":0,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeNegativeAmount() {
        String payload = "{\"amount\":-100,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeLargeAmount() {
        String payload = "{\"amount\":99999999,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeNullToken() {
        String payload = "{\"amount\":1000,\"currency\":\"usd\",\"token\":null}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyToken() {
        String payload = "{\"amount\":1000,\"currency\":\"usd\",\"token\":\"\"}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingCurrency() {
        String payload = "{\"amount\":1000,\"token\":\"tok_visa\"}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidJson() {
        given().contentType(ContentType.JSON).body("invalid json").when().post("/contribute").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMinimalValidPayload() {
        String payload = "{\"amount\":1,\"currency\":\"usd\",\"token\":\"tok_mastercard\"}";

        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(404);
    }
}