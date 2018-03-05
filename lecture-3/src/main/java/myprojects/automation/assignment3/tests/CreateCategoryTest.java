package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.BaseScript;
import org.openqa.selenium.WebDriver;


public class CreateCategoryTest extends BaseScript {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = getConfiguredDriver();
        connectLogger(driver);
        // login
        login(driver);
        // create category
        createCategory(driver);
        // check that new category appears in Categories table

        // finish script
    }
}