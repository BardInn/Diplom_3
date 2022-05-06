package stellarburgers.nomoreparties.site.Chrome;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import stellarburgers.nomoreparties.site.*;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;



public class BurgerLoginTest {
	public static final String URL = "https://stellarburgers.nomoreparties.site/";
	private static BurgerHeader header;
	private static CustomerData customerData;
	private static BurgerLoginPage loginPage;

	@BeforeClass
	public static void beforeTest() {
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
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		webdriver().shouldHave(url(URL));
	}
}
