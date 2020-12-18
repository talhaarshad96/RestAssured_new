import org.testng.Assert;  //M. Talha Arshad
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*; //static because now, directly use
											//methods without creating objects.
import io.restassured.response.Response;

public class Test_GET 
{
	@Test 
	void test_01() 
	{
		//Response resp = RestAssured.get("https://reqres.in/api/users?page=2"); //example is this, cant use RestAssured.get()
		Response resp = get("https://reqres.in/api/users?page=2");
		
		System.out.println("1 "+resp.getBody());
		System.out.println("2 "+resp.asString());
		System.out.println("3 "+resp.getBody().asString());
		System.out.println("4 "+resp.getStatusCode());
		System.out.println("5 "+resp.getStatusLine());
		System.out.println("6 "+resp.getTime());
		System.out.println("7 "+resp.getHeader("content-type"));
		
		int statusCode = resp.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void test_02()
	{
		given().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200)
		.body("data.id[0]", equalTo(7));
	}
}
