package comum;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.core.exception.ExceptionUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.AfterAll;

public class hooks {

    private static ExtentTest scenarioNode;

    @BeforeAll
    public static void beforeTestRun() {
        extendReport.extentReportInit();
    }

    @Before
    public static void beforeScenario(Scenario scenario) {
        String featureName = scenario.getUri().getPath();
        featureName = featureName.substring(featureName.lastIndexOf("/") + 1).replace(".feature", "");
        
        extendReport._feature = extendReport._extentReports.createTest(featureName);
        
        scenarioNode = extendReport._feature.createNode(scenario.getName());
    }

    @After
    public static void afterScenario(Scenario scenario) {
        try {
            String featureName = scenario.getUri().getPath();
            featureName = featureName.substring(featureName.lastIndexOf("/") + 1).replace(".feature", "");

            if (scenario.isFailed()) {
                String errorMessage = "An error occurred in the scenario.";
                String stackTrace = "Stack trace not available.";

                try {
                    Throwable error = new Throwable("Error in Scenario");
                    errorMessage = error.getMessage();
                    stackTrace = ExceptionUtils.printStackTrace(error);
                } catch (Exception e) {
                    System.out.println("Error capturing stack trace: " + e.getMessage());
                }

                scenarioNode.fail("Test failed with error: " + errorMessage)
                            .fail("Stack trace: " + stackTrace);

            } else {
                scenarioNode.pass("Test Passed");
            }
        } catch (Exception e) {
            System.out.println("Error in afterScenario: " + e.getMessage());
        }
    }

    @AfterStep
    public static void afterStep(Scenario scenario) {
    	String stepName = scenario.getName();    	
        String stepType = determineStepType(stepName);

        switch (stepType) {
		    case "Given":
		        scenarioNode.info(stepName).pass("Success");
		        break;
		    case "When":
		        scenarioNode.info(stepName).pass("Success");
		        break;
		    case "Then":
		        scenarioNode.info(stepName).pass("Success");
		        break;
		    default:
		        scenarioNode.info(stepName).pass("Success");
		        break;
		}
    }

    private static String determineStepType(String stepName) {
        if (stepName.startsWith("Given")) {
            return "Given";
        } else if (stepName.startsWith("When")) {
            return "When";
        } else if (stepName.startsWith("Then")) {
            return "Then";
        }
        return "And";
    }

    @AfterAll
    public static void afterTestRun() {
        extendReport.extentReportTearDown();
    }
}
