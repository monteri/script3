package myprojects.automation.assignment3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverLogger extends AbstractWebDriverEventListener {

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("WebDriver navigated to '" + url + "'");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("WebDriver click on element - "
                + elementDescription(element));
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        System.out.println("WebDriver will change value for element - "
                + elementDescription(element));
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        System.out.println("WebDriver changed value for element - "
                + elementDescription(element));
    }

    private String elementDescription(WebElement element) {
        String description = "tag:" + element.getTagName();
        if (element.getAttribute("id") != null) {
            description += " id: " + element.getAttribute("id");
        }
        else if (element.getAttribute("name") != null) {
            description += " name: " + element.getAttribute("name");
        }

        description += " ('" + element.getText() + "')";

        return description;
    }
}
