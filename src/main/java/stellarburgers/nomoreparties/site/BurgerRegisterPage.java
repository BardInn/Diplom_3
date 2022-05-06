package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;


public class BurgerRegisterPage {

	public SelenideElement inputEmail = $$(By.xpath(".//input[@name='name']")).get(1);
	public SelenideElement inputName = $$(By.xpath(".//input[@name='name']")).get(0);
	@FindBy(how = How.XPATH, using = (".//input[@name='Пароль']"))
	public SelenideElement inputPassword;
	@FindBy(how = How.XPATH, using = ".//button[contains(text(),'Зарегистрироваться')]")
	public SelenideElement registrationButton;
	@FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
	public SelenideElement logInButton;
	@FindBy(how = How.XPATH, using = ".//p[contains(text(),'Некорректный пароль')]")
	public SelenideElement errorMessage;

	public void registrationClick() {
		registrationButton.click();
	}

	public void logInButtonClick() {
		logInButton.click();
	}

	public void setInputName(String username) {
		inputName.sendKeys(username);
	}

	public void setInputEmail(String email) {
		inputEmail.sendKeys(email);
	}

	public void setInputPassword(String password) {
		inputPassword.setValue(password);
	}

	public void errorMessageConfirmed() {
		errorMessage.shouldBe(visible);
	}

	public BurgerLoginPage loginClassPage() {
		return page(BurgerLoginPage.class);
	}

	public BurgerHeader headerClassPage() {
		return page(BurgerHeader.class);
	}
}
