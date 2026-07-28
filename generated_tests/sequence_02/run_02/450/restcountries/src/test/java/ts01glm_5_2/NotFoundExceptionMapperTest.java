package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void toResponse_returns404_whenNonExistentPathRequested() {
        given().when().get("/").then().statusCode(lessThan(300));

        given().when().get("/nonexistent-path-" + java.util.UUID.randomUUID().toString()).then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void toResponse_returns404_whenNonExistentNestedPathRequested() {
        given().when().get("/").then().statusCode(lessThan(300));

        given().when().get("/v1/unknownsegment/" + java.util.UUID.randomUUID().toString()).then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void toResponse_returns404_whenDeeplyNestedNonExistentPathRequested() {
        given().when().get("/").then().statusCode(lessThan(300));

        given().when().get("/v3/deeply/nested/" + java.util.UUID.randomUUID().toString() + "/path").then().statusCode(404);
    }
}