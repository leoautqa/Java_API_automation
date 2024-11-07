package comum;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extendReport {

	public static ExtentReports _extentReports;
    public static ExtentTest _feature;
    public static ExtentTest _scenario;

    private static final String baseDir = System.getProperty("user.dir");
    private static final String testResultPath = baseDir + "/TestResults";

    private static final String applicationName = "serverest";
    private static final String browserName = "Chrome";
    
    public static void extentReportInit() {
        try {
            ensureTestResultDirectoryExists();

            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(testResultPath + "/AutomationStatusReport.html");
            htmlReporter.config().setReportName("Automation Status Report");
            htmlReporter.config().setDocumentTitle("Automation Status Report");

            _extentReports = new ExtentReports();
            _extentReports.attachReporter(htmlReporter);
            addSystemInfo();
        } catch (Exception ex) {
            System.out.println("Erro ao inicializar o relatório: " + ex.getMessage());
        }
    }

    private static void ensureTestResultDirectoryExists() {
        File directory = new File(testResultPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private static void addSystemInfo() {
        _extentReports.setSystemInfo("Application", applicationName);
        _extentReports.setSystemInfo("Browser", browserName);
    }

    public static void extentReportTearDown() {
        try {
            if (_extentReports != null) {
                _extentReports.flush();
            }
        } catch (Exception ex) {
            System.out.println("Erro ao finalizar o relatório: " + ex.getMessage());
        }
    }

    public static String takeScreenshot(WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File(testResultPath + "/screenshot.png");
            FileUtils.copyFile(source, dest);
            return dest.getAbsolutePath();
        } catch (IOException ex) {
            System.out.println("Erro ao capturar o screenshot: " + ex.getMessage());
            return "";
        }
    }
}
