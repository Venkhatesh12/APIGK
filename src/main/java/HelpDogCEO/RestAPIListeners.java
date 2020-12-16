package HelpDogCEO;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class RestAPIListeners extends TestListenerAdapter
{
	/*
	 * Declaring Extent report Variables Globally to utilities in test levels
	 */
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	/*
	 * Before Suite will execute when the test execution starts and will create test report at respected folder with some environment capabilities
	 */
	@BeforeSuite
	public void SetUP()
	{
		htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/TestReport/DogCeoAPIReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS","Windows 10");
		extent.setSystemInfo("Host Name","Localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("Report Name","ReST API Report");

		htmlReporter.config().setDocumentTitle("DOGCEO-Report");
		htmlReporter.config().setReportName("DOGCEO-RestAPI");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTimeStampFormat("yyyy-MM-dd hh:MM:ss");
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	/*
	 * BeforeMethod  will execute when the test execution completed and will give status eaither it's failed or passed
	 */
	@AfterMethod
	public void getResult(ITestResult result){
	    if(result.getStatus()==ITestResult.FAILURE){
	        test.fail(MarkupHelper.createLabel(result.getName()+" Test Case is FAILED", ExtentColor.RED));
	        test.fail(result.getThrowable());
	    }
	    else if(result.getStatus()==ITestResult.SUCCESS){
	        test.pass(MarkupHelper.createLabel(result.getName()+" Test Case is PASSED",ExtentColor.GREEN));
	    }
	    else {
	        test.skip(MarkupHelper.createLabel(result.getName()+" Test Case is SKIPPED",ExtentColor.YELLOW));
	        test.skip(result.getThrowable());
	    }
	}

	/*
	 * After Suite Method will execute when the test execution completed then report generated
	 */
	@AfterSuite
	public void tearDown(){
	    extent.flush();
	}
	
}
