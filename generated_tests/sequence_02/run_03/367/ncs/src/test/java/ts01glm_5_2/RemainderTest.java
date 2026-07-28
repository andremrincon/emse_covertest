package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testRemainderAEqualsZero() {
        given().when().get("/api/remainder/0/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBEqualsZero() {
        given().when().get("/api/remainder/5/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBothPositive() {
        given().when().get("/api/remainder/17/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        given().when().get("/api/remainder/17/-5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        given().when().get("/api/remainder/-9/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBothNegative() {
        given().when().get("/api/remainder/-9/-5").then().statusCode(200);
    }
}