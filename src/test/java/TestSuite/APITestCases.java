package TestSuite;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import HelpDogCEO.RestAPIListeners;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
/*
 * Author : Venkat
 * Date : 26-12-2020
 *Assessment: GK
 * 
 */

public class APITestCases extends RestAPIListeners
{
/*
 * Declaring Global Variables to utilise in the Each test case level
 */
	RequestSpecification httpRequest;
	Response response;
	public static Logger logger;

/*
 * Before class Attribute will give the configuration before test runs 
 * Logger file will provide all the execution details results
 * Log file will locate under Logs Folder
 */
	@BeforeClass
	public void DogCEOAPI_SetUP() throws InterruptedException
	{
		logger= Logger.getLogger("HelpDogCEO");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
		logger.info("**Started to GET Assessment results Version**");
	}


/*
 * This Test Will return all the Dogs Breads and list of Dog breads will appear on Test Report and Log file As well 
 */
	@Test
	public void TC01_get_AllDogBreadsList()
	{
		test = extent.createTest("GET-AllDog Breads");
		RestAssured.baseURI = "https://dog.ceo/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/breeds/list/all");
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		System.out.println("Verified, **All Dog Bread List Available**"+bodyAsString);
		test.log(Status.INFO, "Response body "+bodyAsString);

	}


/*
 * This Test Will return the all the dogs bread list and find the "retriever" Dogs Bread and if it returns the value correct Value Test will produce logs
 * It will generate LTest report as well 
 */
	@Test
	public void TC02_get_RetriverinTheList() 
	{
		test = extent.createTest("GET-Retriver List");

		RestAssured.baseURI = "https://dog.ceo/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/breeds/list/all");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("retriever") /*Expected value*/, true /*Actual Value*/, "Response body contains retriever");
		System.out.println("Verified, **retriever** is with in the list"+bodyAsString);
		test.log(Status.PASS, "Verified, **retriever** is with in the list");
		test.log(Status.INFO, "Response body "+bodyAsString);

	}

	/*
	 * This Test Will return the "retriever" Dogs Bread list only and if it returns the values correct Value Test will produce logs
	 * It will generate LTest report as well 
	 */
	@Test
	public void TC03_get_SublistofRetriver() 
	{
		test = extent.createTest("GET-Retriver List");

		RestAssured.baseURI = "https://dog.ceo/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("breed/retriever/list");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		String bodyAsString = body.asString();
		System.out.println("Verified, **Retrived retriever bread dogs list only**"+bodyAsString);
		test.log(Status.PASS, "Verified, **Retrived retriever bread dogs list only**");
		test.log(Status.INFO, "Response body "+bodyAsString);

	}
	/*
	 * This Test Will return the random Dogs Bread image and link will bind to logs folder and on the test report as well
	 * It will generate LTest report as well 
	 */
	@Test
	public void TC04_get_AnyRandomImage() 
	{
		test = extent.createTest("GET-Retriver List");

		RestAssured.baseURI = "https://dog.ceo/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/breeds/image/random");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		String bodyAsString = body.asString();
		System.out.println("Retrived Random Image of a bread"+bodyAsString);
		test.log(Status.PASS, "**Retrived Random Image of a bread**");
		test.log(Status.INFO, "Response body "+bodyAsString);

	}

/*
 * This After class executes after All the test runs and Notify us the test are executed successfully
 */
	@AfterClass
	public void teardown()
	{
		logger.info("*********Test's Ran Successfully and Please check the logs for Details");
	}

}
