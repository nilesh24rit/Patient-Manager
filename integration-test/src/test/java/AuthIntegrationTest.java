import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.IsNull.notNullValue;

public class AuthIntegrationTest {
    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "http://localhost:8084";

    }

    @Test
    public void shouldReturnOKWithValidToken() {
        String Payload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(Payload)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("token",notNullValue())
                .extract()
                .response();
        System.out.println("Generated Token: " + response.jsonPath().getString("token"));
    }
}
