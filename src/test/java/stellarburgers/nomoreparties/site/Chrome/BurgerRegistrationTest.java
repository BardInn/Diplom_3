package stellarburgers.nomoreparties.site.Chrome;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import stellarburgers.nomoreparties.site.BurgerHeader;
import stellarburgers.nomoreparties.site.BurgerLoginPage;
import stellarburgers.nomoreparties.site.BurgerRegisterPage;
import stellarburgers.nomoreparties.site.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;


public class BurgerRegistrationTest {

	private static CustomerData customerData;
	private static BurgerHeader header;
	private static BurgerLoginPage loginPage;
	private static CustomerData customerDataWithError;

	@BeforeClass
	public static void beforeTest() {
		customerData = CustomerData.getRandom();
		customerDataWithError = CustomerData.getRandom();
	}

	@AfterClass
	public static void tearDown() {
		ValidatableResponse login = CustomerClient.login(customerData.getEmail(), customerData.getPassword());
		customerData.getCustomerToken().setAccessToken(login.extract().path("accessToken"));
		ValidatableResponse delete = CustomerClient.delete(customerData);
	}

	@Test
	@DisplayName("Registration page")
	@Description("Try to registration with short password (less than 5)")
	public void registrationShortPassword() {
		header = open("https://stellarburgers.nomoreparties.site/", BurgerHeader.class);
		header.personalAccountClick();
		loginPage = header.burgerLoginPage();
		loginPage.registrationLinkClick();
		BurgerRegisterPage registration = loginPage.registerPageClass();
		registration.inputName.shouldBe(visible);
		registration.setInputName(customerDataWithError.getName());
		registration.setInputEmail(customerDataWithError.getEmail());
		registration.setInputPassword("12345");
		registration.registrationClick();
		registration.errorMessageConfirmed();
	}

	@Test
	@DisplayName("Registration page")
	@Description("Try to registration")
	public void registrationTest() {
		header = open("https://stellarburgers.nomoreparties.site/", BurgerHeader.class);
		header.personalAccountClick();
		loginPage = header.burgerLoginPage();
		loginPage.registrationLinkClick();
		BurgerRegisterPage registration = loginPage.registerPageClass();
		registration.setInputName(customerData.getName());
		registration.setInputEmail(customerData.getEmail());
		registration.setInputPassword(customerData.getPassword());
		registration.registrationClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
	}


}
