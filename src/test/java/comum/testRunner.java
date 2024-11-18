package comum;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/feature",
				 glue= {"pageObject", "comum"},
				 monochrome=true,
				 //plugin= {"com.aventstark.extenreport.cucumber.adapter.ExtentCucumberAdapter:"},
				 plugin= {"pretty", "junit:target/JUnitReports/report.xml",
						  			"json:target/JSONReports/report.json",
						  			"html:terget/HtmlReports"},
						  			//"com.aventstark.extenreport.cucumber.adapter.ExtentCucumberAdapter:"},
				 tags="@usuarios"
				)

public class testRunner {
}
