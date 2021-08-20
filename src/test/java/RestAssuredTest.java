
import org.junit.Test;
//import org.testng.annotations.Test;
        import io.restassured.RestAssured;
        import io.restassured.http.Method;
        import io.restassured.response.Response;
        import io.restassured.specification.RequestSpecification;

import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredTest {

    @Test
    public void GetWeatherDetails()
    {
        RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/Hyderabad");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

    }

    @Test
    public void todoistGetRequest(){
        String token="c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured.baseURI = "https://api.todoist.com/rest/v1/tasks";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.header( "Authorization", "Bearer "+token).header("Content-Type","application/json").get("https://api.todoist.com/rest/v1/tasks");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
    }

    @Test
  public void StatusCheck() {
                String token="c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured
                .given()
                .header( "Authorization", "Bearer "+token).header("Content-Type","application/json").get("https://api.todoist.com/rest/v1/projects/2271791984")
                        .then()
                        .statusCode(200)
                        .log()
                        .all();
    }

    @Test
    public void taskStatusCheck(){

        String token="c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured
                .given()
                .header( "Authorization", "Bearer "+token).header("Content-Type","application/json").get("https://api.todoist.com/rest/v1/tasks")
                .then()
                .statusCode(200)
                .log()
                .all();

    }
    @Test
     public void idCheck(){
        String token="c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured
                .given()
                .header( "Authorization", "Bearer "+token).header("Content-Type","application/json").get("https://api.todoist.com/rest/v1/tasks/5081923746")
                .then()
                .assertThat()
                .body("id", equalTo(5081923746L))
                .log()
                .all();

    }


    @Test
    public void contentCheck(){
        String token="c0d2f28135872130984acf1ccf39461203f233db";
        RestAssured
                .given()
                .header( "Authorization", "Bearer "+token).header("Content-Type","application/json").get("https://api.todoist.com/rest/v1/tasks/5081923746")
                .then()
                .assertThat()
                .body("content", equalTo("Click this task to see more details")
                .log()
                .all();

    }



}