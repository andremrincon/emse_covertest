package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleWithMrReturns200() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testMaleWithNonMatchingTitleReturns200() {
        given().when().get("/api/pat/arrange2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "queen");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFemaleWithMsReturns200() {
        given().when().get("/api/pat/arrange3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "Ms");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFemaleWithDrReturns200() {
        given().when().get("/api/pat/arrange4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "Dr");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNoneWithProfReturns200() {
        given().when().get("/api/pat/arrange5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "prof");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNoneWithNonMatchingTitleReturns200() {
        given().when().get("/api/pat/arrange6").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "visitor");
        assertEquals(200, resp.getStatusCode());
    }
}