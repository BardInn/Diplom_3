package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class BurgerHeader {
	public static final String URL = "https://stellarburgers.nomoreparties.site/";
	@FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
	public SelenideElement personalAccount;
	@FindBy(how = How.XPATH, using = ".//p[contains(text(),'Конструктор')]")
	public SelenideElement constructor;
	@FindBy(how = How.XPATH, using = ".//p[contains(text(),'Лента Заказов')]")
	public SelenideElement orderFeed;
	@FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
	public SelenideElement homeButton;

	public void personalAccountClick() {
		personalAccount.click();
	}

	public void constructorClick() {
		constructor.click();
	}

	public void homeButtonClick() {
		homeButton.click();
	}

	public BurgerPersonalAccount burgerPersonalAccount() {
		return page(BurgerPersonalAccount.class);
	}

	public BurgerLoginPage burgerLoginPage() {
		return page(BurgerLoginPage.class);
	}
}
