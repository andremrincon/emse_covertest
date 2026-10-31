package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaV1Alpha() {
        Response response = given()
                .when()
                .get("/v1/alpha/US");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaV1Lang() {
        Response response = given()
                .when()
                .get("/v1/lang/es");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaV2Alpha() {
        Response response = given()
                .when()
                .get("/v2/alpha/US");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaV2Lang() {
        Response response = given()
                .when()
                .get("/v2/lang/Spanish");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaV1Name() {
        Response response = given()
                .when()
                .get("/v1/name/France");

        response.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaV2Name() {
        Response response = given()
                .when()
                .get("/v2/name/Germany");

        response.then().statusCode(lessThan(300));
    }
}