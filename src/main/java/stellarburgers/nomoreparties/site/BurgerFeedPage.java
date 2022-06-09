package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class BurgerFeedPage {
	@FindBy(how = How.XPATH, using = ".//span[contains(text(),'Булки')]")
	public SelenideElement buns;
	@FindBy(how = How.XPATH, using = ".//span[contains(text(),'Соусы')]")
	public SelenideElement sauces;
	@FindBy(how = How.XPATH, using = ".//span[contains(text(),'Начинки')]")
	public SelenideElement toppings;
	@FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти в аккаунт')]")
	public SelenideElement logInToAccount;
	@FindBy(how = How.XPATH, using = ".//p[contains(text(),'Мясо бессмертных моллюсков Protostomia')]")
	public SelenideElement firstTopping;
	@FindBy(how = How.XPATH, using = ".//p[contains(text(),'Соус Spicy-X')]")
	public SelenideElement firstSauces;
	@FindBy(how = How.XPATH, using = ".//p[contains(text(),'Флюоресцентная булка R2-D3')]")
	public SelenideElement firstBuns;

	public void bunsClick() {
		buns.click();
	}

	public void saucesClick() {
		sauces.click();
	}

	public void toppingsClick() {
		toppings.click();
	}

	public void logInToAccountClick() {
		logInToAccount.click();
	}

	public BurgerLoginPage loginPageClass() {
		return page(BurgerLoginPage.class);
	}
}
