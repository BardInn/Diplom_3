package stellarburgers.nomoreparties.site.Yandex;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.*;

import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.chrome.ChromeOptions;
import stellarburgers.nomoreparties.site.*;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class BurgerLoginYandexTest {
	public static final String URL = "https://stellarburgers.nomoreparties.site/";
	private static BurgerHeader header;
	private static CustomerData customerData;
	private static BurgerLoginPage loginPage;

	@BeforeClass
	public static void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "/Users/fenwer/Downloads/yandexdriver");
		ChromeOptions options = new ChromeOptions();
		options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
	}

	@BeforeClass
	public static void setUp(){
		BurgerRegisterPage create = open(URL + "register", BurgerRegisterPage.class);
		customerData = CustomerData.getRandom();
		create.setInputName(customerData.getName());
		create.setInputEmail(customerData.getEmail());
		create.setInputPassword(customerData.getPassword());
		create.registrationClick();
		header = create.headerClassPage();
		header.homeButtonClick();
	}

	@After
	public void logOut() {
		header = loginPage.headerClass();
		header.personalAccountClick();
		BurgerPersonalAccount personalAccountLogOut = header.burgerPersonalAccount();
		personalAccountLogOut.logOutClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
	}

	@AfterClass
	public static void tearDown() {
		ValidatableResponse login = CustomerClient.login(customerData.getEmail(), customerData.getPassword());
		customerData.getCustomerToken().setAccessToken(login.extract().path("accessToken"));
		ValidatableResponse delete = CustomerClient.delete(customerData);
	}

	@Test
	@DisplayName("LogIn")
	@Description("Login from personal account")
	public void loginFromPersonalAccountTest() {
		header.personalAccountClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
		loginPage = header.burgerLoginPage();
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		webdriver().shouldHave(url(URL));
	}

	@Test
	@DisplayName("LogIn")
	@Description("Login from button in the middle of the site")
	public void loginToAccountInTheMiddleTest() {
		BurgerFeedPage feedPage = open(URL, BurgerFeedPage.class);
		feedPage.logInToAccountClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
		loginPage = feedPage.loginPageClass();
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		webdriver().shouldHave(url(URL));
	}

	@Test
	@DisplayName("LogIn")
	@Description("Login from button in the registration form")
	public void loginFromTheRegistrationPageTest() {
		BurgerRegisterPage registerPage = open(URL + "register", BurgerRegisterPage.class);
		registerPage.logInButtonClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
		loginPage = registerPage.loginClassPage();
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		webdriver().shouldHave(url(URL));
	}

	@Test
	@DisplayName("LogIn")
	@Description("Login from button in password recovery page")
	public void loginFromResetPasswordPageTest() {
		loginPage = open(URL + "forgot-password", BurgerLoginPage.class);
		loginPage.loginAtResetPasswordPageClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		webdriver().shouldHave(url(URL));
	}
}
