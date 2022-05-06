package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BurgerPersonalAccount {
	@FindBy(how = How.XPATH,using =  ".//button[contains(text(),'Выход')]")
	public SelenideElement logOut;

	public void logOutClick() {
		logOut.click();
	}
}
