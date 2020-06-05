package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
	(
	features=".//Features/",
	glue="stepDefinitions",
	dryRun=false,
	monochrome=true,
	plugin= {"pretty", "html:test-output"},
	tags= {"@sanity, @regression"}
	)






public class TestRun {

}

//To run all the feature files 
//features=".//Features/",
//To run selected Feature files
// features={".//Features/Customer.feature",".//Features/Login1.feature" },
//Same would be applicable for stepDefinitions
//tags= {"@sanity", "@regression"}-->runs all the scenarios that fall under @sanity and @regression.
//We dont have any that falls under both
//tags= {"@sanity, @regression"}
//runs all that falls under either @sanity or @regression