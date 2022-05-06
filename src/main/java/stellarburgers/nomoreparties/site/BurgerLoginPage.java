package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



import static com.codeborne.selenide.Selenide.page;

public class BurgerLoginPage {

	@FindBy(how = How.XPATH, using = (".//input[@name='name']"))
	public SelenideElement inputEmail;
	@FindBy(how = How.XPATH, using = (".//input[@name='Пароль']"))
	public SelenideElement inputPassword;
	@FindBy(how = How.XPATH,using =  ".//button[contains(text(),'Войти')]")
	public SelenideElement loginButton;
	@FindBy(how = How.CLASS_NAME,using =  "Auth_link__1fOlj")
	public SelenideElement loginAtResetPasswordPage;
	@FindBy(how = How.CLASS_NAME,using =  "Auth_link__1fOlj")
	public SelenideElement getRegistration;



	public void registrationLinkClick(){
		getRegistration.click();
	}
	public void loginButtonClick() {
		loginButton.click();
	}

	public void setInputPassword(String password) {inputPassword.setValue(password);}
	public void setInputEmail(String email) {inputEmail.setValue(email);}

	public void loginAtResetPasswordPageClick() {
		loginAtResetPasswordPage.click();
	}

	public BurgerHeader headerClass() {
		return page(BurgerHeader.class);
	}

	public BurgerRegisterPage registerPageClass() {
		return page(BurgerRegisterPage.class);
	}
}
