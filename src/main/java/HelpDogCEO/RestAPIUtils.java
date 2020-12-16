package HelpDogCEO;


import org.apache.commons.lang3.RandomStringUtils;

public class RestAPIUtils {
	
	public static String generateString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		return ("Venkat"+generatedString);
	}
	
	public static String generateNUmber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		return (generatedNumber);
	}
	
	public static String generateRandonString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(10);
		return (generatedString);
	}

}
