package laboratorium.uruun;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage {

    private WebElement login;
    private WebElement password;

    @FindAll({
            @FindBy(className = "error"),
            @FindBy(id = "login-error-message")
    })
    protected List<WebElement> error;

    public void login(String username, String pass)
    {
        login.sendKeys(username);
        password.sendKeys(pass);
        password.sendKeys(Keys.ENTER);
    }

    public boolean getError()
    {
        return error.size() > 0;
    }
}
