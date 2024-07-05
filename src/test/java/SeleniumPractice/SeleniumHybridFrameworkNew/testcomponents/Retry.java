package SeleniumPractice.SeleniumHybridFrameworkNew.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count = 0;
	int maxtry = 1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		while (count < maxtry) {
			count++;
			return true;
		}
		return false;
	}

}
