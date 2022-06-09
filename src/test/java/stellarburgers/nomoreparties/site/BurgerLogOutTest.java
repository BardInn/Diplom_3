package stellarburgers.nomoreparties.site;


import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import stellarburgers.nomoreparties.site.BurgerHeader;
import stellarburgers.nomoreparties.site.BurgerLoginPage;
import stellarburgers.nomoreparties.site.BurgerPersonalAccount;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static stellarburgers.nomoreparties.site.BurgerRestClient.BASE_URL;

import stellarburgers.nomoreparties.site.*;

public class BurgerLogOutTest {

	private BurgerHeader personalAccount;
	private CustomerData customerData;



	@BeforeClass
	public static void beforeClass() {
		TestUtils.setUpBrowser();
	}

	@Before
	public void before() {
		CustomerClient customerClient = new CustomerClient();
		customerData = CustomerData.getRandom();
		ValidatableResponse isCreated = customerClient.create(customerData.getName(),customerData.getEmail(), customerData.getPassword());
		BurgerLoginPage loginPage = open(BASE_URL + "login", BurgerLoginPage.class);
		loginPage.inputEmail.shouldBe(visible);
		loginPage.setInputEmail(customerData.getEmail());
		loginPage.setInputPassword(customerData.getPassword());
		loginPage.loginButtonClick();
		personalAccount = loginPage.headerClass();
		personalAccount.personalAccountClick();
	}

	@After
	public void tearDown() {
		CustomerClient.tearDown(customerData);
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
