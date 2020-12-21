import org.json.simple.JSONObject;
import org.testng.Assert;  //M. Talha Arshad
import org.testng.annotations.Test;



import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*; //static because now, directly use

import io.restassured.http.ContentType;
//methods without creating objects.
import io.restassured.response.Response;

public class Test_GET 
{
	//@Test 
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
	
	//@Test
	void test_02()
	{
		given().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200)
		.body("data.id[0]", equalTo(7));
	}
	
	//@Test
	void test_03()
	{
		given().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200)
		.body("data.id[0]", equalTo(7))
		.body("data.first_name", hasItems("Michael"));
		//.log().all();
	}
	
	//@Test
	void test_04()
	{
		//Map<String, Object> map =  new HashMap <String, Object>();
		
		//map.put("name","talha");
		//map.put("bla","blabber");
		
		//System.out.println(map);
	
		JSONObject request = new JSONObject();
		
		request.put("name", "talha");
		request.put("job", "blabber");
		//System.out.println(request);
		System.out.println("1 "+request);
		
		given().body(request.toJSONString()).
		contentType (ContentType.JSON).
		when().
		post("https://reqres.in/api/users").
		then().
		statusCode(201)
		.log().all();
		
	}
	
	@Test(priority=1)
	void test_05() throws InterruptedException
	{
		JSONObject request = new JSONObject();
		
		request.put("name","talha");
		request.put("job","blabber");

		System.out.println("1 "+request);
		
		given().body(request.toJSONString()).
		contentType (ContentType.JSON).
		when().
		put("https://reqres.in/api/users/2").
		then().
		statusCode(200)
		.log().all();
		System.out.println("test05");
		//Thread.sleep(-10);
	}
	
	@Test(priority=0)
	void test_06()
	{
		JSONObject request = new JSONObject();
		
		request.put("name","talha");
		request.put("job","blabber");

		System.out.println("1 "+request);
		
		given().body(request.toJSONString()).
		contentType (ContentType.JSON).
		when().
		patch("https://reqres.in/api/users/2").
		then().
		statusCode(200)
		.log().all();
		System.out.println("test06");
	}
}
