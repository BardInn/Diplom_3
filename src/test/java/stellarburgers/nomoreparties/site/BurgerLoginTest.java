package stellarburgers.nomoreparties.site;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.*;

import io.qameta.allure.junit4.DisplayName;
import stellarburgers.nomoreparties.site.*;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static stellarburgers.nomoreparties.site.BurgerRestClient.BASE_URL;


public class BurgerLoginTest {
	public static final String TEST_URL = "https://stellarburgers.nomoreparties.site/login";
	private static BurgerHeader header;
	private static CustomerData customerData;
	private static BurgerLoginPage loginPage;


	@BeforeClass
	public static void beforeClass() {
		TestUtils.setUpBrowser();
	}

	@Before
	public void beforeTest() {
		CustomerClient customerClient = new CustomerClient();
		customerData = CustomerData.getRandom();
		ValidatableResponse isCreated = customerClient.create(customerData.getName(),customerData.getEmail(), customerData.getPassword());
	}

	@After
	public void logOut() {
		header = loginPage.headerClass();
		header.personalAccountClick();
		BurgerPersonalAccount personalAccountLogOut = header.burgerPersonalAccount();
		personalAccountLogOut.logOutClick();
		webdriver().shouldHave(url(TEST_URL));
	}

	@AfterClass
	public static void tearDown() {
		CustomerClient.tearDown(customerData);
	}

	@Test
	@DisplayName("LogIn")
	@Description("Login from personal account")
	public void loginFromPersonalAccountTest() {
		header = open(BASE_URL, BurgerHeader.class);
		header.personalAccountClick();
		loginPage = header.burgerLoginPage();
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		webdriver().shouldHave(url(BASE_URL));
	}

	@Test
	@DisplayName("LogIn")
	@Description("Login from button in the middle of the site")
	public void loginToAccountInTheMiddleTest() {
		BurgerFeedPage feedPage = open(BASE_URL, BurgerFeedPage.class);
		feedPage.logInToAccountClick();
		loginPage = feedPage.loginPageClass();
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		webdriver().shouldHave(url(BASE_URL));
	}

	@Test
	@DisplayName("LogIn")
	@Description("Login from button in the registration form")
	public void loginFromTheRegistrationPageTest() {
		BurgerRegisterPage registerPage = open(BASE_URL + "register", BurgerRegisterPage.class);
		registerPage.logInButtonClick();
		loginPage = registerPage.loginClassPage();
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		webdriver().shouldHave(url(BASE_URL));
	}

	@Test
	@DisplayName("LogIn")
	@Description("Login from button in password recovery page")
	public void loginFromResetPasswordPageTest() {
		loginPage = open(BASE_URL + "forgot-password", BurgerLoginPage.class);
		loginPage.loginAtResetPasswordPageClick();
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		webdriver().shouldHave(url(BASE_URL));
	}
}
