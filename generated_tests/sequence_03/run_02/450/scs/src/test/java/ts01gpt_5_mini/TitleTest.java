package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleMatchingTitleReturnsOk() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleNonMatchingTitleReturnsMinusOneBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mrs").then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleMatchingTitleReturnsZeroBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "ms").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleNonMatchingTitleReturnsMinusOneBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "mr").then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneMatchingTitleReturnsTwoBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "dr").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoneNonMatchingTitleReturnsMinusOneBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "mr").then().assertThat().body(equalTo("-1"));
    }
}