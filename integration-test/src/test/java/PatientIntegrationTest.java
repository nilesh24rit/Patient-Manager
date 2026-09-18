import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class PatientIntegrationTest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8084";
    }

    @Test
    public void shouldReturnPatientsWithValidToken() {

        String Payload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;
        String token = given()
                .contentType(ContentType.JSON)
                .body(Payload)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("token");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/patient/all-patient")
                .then()
                .statusCode(200)
                .log()
                .body();

    }
}
