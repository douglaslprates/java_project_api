package br.com.restassuredapitesting.runners;
import br.com.restassuredapitesting.tests.ping.tests.GetHealthCheckTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.HealthCheck.class)
@Suite.SuiteClasses({
        GetHealthCheckTest.class
})
public class HealthCheck {

}
