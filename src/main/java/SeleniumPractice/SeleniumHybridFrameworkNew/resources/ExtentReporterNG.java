package SeleniumPractice.SeleniumHybridFrameworkNew.resources;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReport() {
		String currentTime = new SimpleDateFormat("dd-MM-yyyy-hhmmss").format(new Date());

		String path = System.getProperty("user.dir") + "\\Report\\TestReport_"+currentTime+".html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(new File(path));
		reporter.config().setReportName("Test Automation Report");
		reporter.config().setDocumentTitle("Test Report");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Subhratanu Roy");
		extent.setSystemInfo("Env", "UAT");
		return extent;
	}

}
