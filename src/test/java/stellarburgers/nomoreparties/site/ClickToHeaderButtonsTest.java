package stellarburgers.nomoreparties.site;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import stellarburgers.nomoreparties.site.BurgerHeader;
import stellarburgers.nomoreparties.site.BurgerLoginPage;
import stellarburgers.nomoreparties.site.BurgerRegisterPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static stellarburgers.nomoreparties.site.BurgerRestClient.BASE_URL;

import stellarburgers.nomoreparties.site.*;

public class ClickToHeaderButtonsTest {

	private static BurgerHeader personalAccount;
	private static CustomerData customerData;


	@BeforeClass
	public static void before() {
		TestUtils.setUpBrowser();
		CustomerClient customerClient = new CustomerClient();
		customerData = CustomerData.getRandom();
		ValidatableResponse isCreated = customerClient.create(customerData.getName(), customerData.getEmail(), customerData.getPassword());
		BurgerLoginPage loginPage = open(BASE_URL + "login", BurgerLoginPage.class);
		loginPage.inputEmail.shouldBe(visible);
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		personalAccount = loginPage.headerClass();
	}

	@AfterClass
	public static void tearDown() {
		CustomerClient.tearDown(customerData);
	}

	@Test
	@DisplayName("Transitions from header button")
	@Description("Try to click on personal account button")
	public void clickPersonalAccountTest() {
		personalAccount.personalAccountClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/account/profile"));
	}

	@Test
	@DisplayName("Transitions from header button")
	@Description("Try to click on constructor button")
	public void clickToFeedButtonTest() {
		personalAccount.personalAccountClick();
		personalAccount.constructorClick();
		webdriver().shouldHave(url(BASE_URL));
	}

	@Test
	@DisplayName("Transitions from header button")
	@Description("Try to click on logo button")
	public void clickToLogoTest() {
		personalAccount.personalAccountClick();
		personalAccount.homeButtonClick();
		webdriver().shouldHave(url(BASE_URL));
	}


}
