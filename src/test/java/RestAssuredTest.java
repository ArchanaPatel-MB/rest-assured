import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.json.JSONObject;

import static org.hamcrest.core.IsEqual.equalTo;

//import org.testng.annotations.Test;

public class RestAssuredTest {

    @Test
    public void GetWeatherDetails() {
        RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/Hyderabad");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

    }

    @Test
    public void todoistGetRequest() {
        String token = "c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.header("Authorization", "Bearer " + token).header("Content-Type", "application/json").get("https://api.todoist.com/rest/v1/tasks");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
    }

    @Test
    public void StatusCheck() {
        String token = "c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured
                .given()
                .header("Authorization", "Bearer " + token).header("Content-Type", "application/json").get("https://api.todoist.com/rest/v1/projects/2271791984")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void taskStatusCheck() {

        String token = "c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get("https://api.todoist.com/rest/v1/tasks")
                .then()
                .statusCode(200)
                .log()
                .all();

    }

    @Test
    public void idCheck() {
        String token = "c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get("https://api.todoist.com/rest/v1/tasks/5081923746")
                .then()
                .assertThat()
                .body("id", equalTo(5081923746L))
                .log()
                .all();

    }


    @Test
    public void contentCheck() {
        String token = "c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get("https://api.todoist.com/rest/v1/tasks/5081923746")
                .then()
                .assertThat()
                .body("content", equalTo("Click this task to see more details"))
                .log()
                .all();

    }


    @Test
    public void deleteRecord() {
        String id = "5081923756";
        String token = "c0d2f28135872130984acf1ccf39461203f233db";

        RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks/";
        RequestSpecification request = RestAssured
                .given()
                .header("Authorization", "Bearer " + token);

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Delete the request and check the response
        Response response = request.delete(id);

        int statusCode = response.getStatusCode();
        System.out.println(response.asString());
        Assert.assertEquals(statusCode, 204);

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("successfully! deleted Records"), false);
    }

    @Test
    public void StatusForDeleteCheck() {
        String token = "c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get("https://api.todoist.com/rest/v1/projects/5081923756")
                .then()
                .statusCode(404)
                .log()
                .all();
    }

    @Test
    public void taskCloseTest() {

        String token = "c0d2f28135872130984acf1ccf39461203f233db";
//        RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks/5086551360/close";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .post("https://api.todoist.com/rest/v1/tasks/5086551360/close");

        Assert.assertEquals(response.getStatusCode(), 204);
    }


    @Test
    public void RegistrationSuccessful() {
        RestAssured.baseURI = "https://restapi.demoqa.com/customer";
        RequestSpecification request = RestAssured.given();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("FirstName", "Virender"); // Cast
        jsonObject.put("LastName", "Singh");
        jsonObject.put("UserName", "sdimpleuser2dd2011");
        jsonObject.put("Password", "password1");

        jsonObject.put("Email", "sample2ee26d9@gmail.com");
        request.body(jsonObject.toString());
        Response response = request.post("/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, "201");
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals("Correct Success code was returned", successCode, "OPERATION_SUCCESS");
    }

}