package stellarburgers.nomoreparties.site.Chrome;

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

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

import stellarburgers.nomoreparties.site.*;

public class ClickToHeaderButtonsTest {
	private static final String URL = "https://stellarburgers.nomoreparties.site/";
	private static BurgerHeader personalAccount;
	private static CustomerData customerData;


	@BeforeClass
	public static void before() {
		BurgerRegisterPage create = open(URL + "register", BurgerRegisterPage.class);
		customerData = CustomerData.getRandom();
		create.setInputName(customerData.getName());
		create.setInputEmail(customerData.getEmail());
		create.setInputPassword(customerData.getPassword());
		create.registrationClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
		BurgerLoginPage loginPage = create.loginClassPage();
		loginPage.inputEmail.shouldBe(Condition.visible);
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		personalAccount = loginPage.headerClass();
	}

	@AfterClass
	public static void tearDown() {
		ValidatableResponse login = CustomerClient.login(customerData.getEmail(), customerData.getPassword());
		customerData.getCustomerToken().setAccessToken(login.extract().path("accessToken"));
		ValidatableResponse delete = CustomerClient.delete(customerData);
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
		webdriver().shouldHave(url(URL));
	}

	@Test
	@DisplayName("Transitions from header button")
	@Description("Try to click on logo button")
	public void clickToLogoTest() {
		personalAccount.personalAccountClick();
		personalAccount.homeButtonClick();
		webdriver().shouldHave(url(URL));
	}


}
