package stellarburgers.nomoreparties.site.Chrome;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import stellarburgers.nomoreparties.site.BurgerHeader;
import stellarburgers.nomoreparties.site.BurgerLoginPage;
import stellarburgers.nomoreparties.site.BurgerPersonalAccount;
import stellarburgers.nomoreparties.site.BurgerRegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

import stellarburgers.nomoreparties.site.*;

public class BurgerLogOutTest {

	private static final String URL = "https://stellarburgers.nomoreparties.site/";
	private BurgerHeader personalAccount;
	private CustomerData customerData;

	@Before
	public void before() {
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
		personalAccountLogOut.logOutClick();
		webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
	}
}
