package dyr.runs;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	
	private int retryCount=0;
	private static final int MAX_RETRY=3;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(retryCount<MAX_RETRY) {
			retryCount++;
			return true;
		}
		return false;
	}

}
