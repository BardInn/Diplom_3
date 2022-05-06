package stellarburgers.nomoreparties.site.Yandex;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.chrome.ChromeOptions;
import stellarburgers.nomoreparties.site.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class BurgerLogOutYandexTest {
	private static final String URL = "https://stellarburgers.nomoreparties.site/";
	private static BurgerHeader personalAccount;
	private static CustomerData customerData;

	@BeforeClass
	public static void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/fenwer/Downloads/yandexdriver");
		ChromeOptions options = new ChromeOptions();
		options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

	}

	@BeforeClass
	public static void setUp() {
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
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
		personalAccount = loginPage.headerClass();
		personalAccount.personalAccountClick();
	}

	@After
	public void tearDown() {
		ValidatableResponse login = CustomerClient.login(customerData.getEmail(), customerData.getPassword());
		customerData.getCustomerToken().setAccessToken(login.extract().path("accessToken"));
		ValidatableResponse delete = CustomerClient.delete(customerData);
	}


	@Test
	@DisplayName("LogOut")
	@Description("Try to log out from account")
	public void logOutTest() {
		BurgerPersonalAccount personalAccountLogOut = personalAccount.burgerPersonalAccount();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/account/profile"));
		personalAccountLogOut.logOutClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
	}
}
