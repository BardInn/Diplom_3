package stellarburgers.nomoreparties.site;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import stellarburgers.nomoreparties.site.BurgerFeedPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static stellarburgers.nomoreparties.site.BurgerRestClient.BASE_URL;


public class BurgerFeedPageTest {

	private BurgerFeedPage feedPage;


	@BeforeClass
	public static void beforeClass() {
		TestUtils.setUpBrowser();
	}

	@Before
	public void before() {
		feedPage = open(BASE_URL, BurgerFeedPage.class);
	}

	@Test
	@DisplayName("Transitions between sections")
	@Description("Try to change section from buns to sauces")
	public void fromBunsToSauces() {
		feedPage.saucesClick();
		feedPage.firstSauces.shouldBe(visible);
	}

	@Test
	@DisplayName("Transitions between sections")
	@Description("Try to change section from buns to toppings")
	public void fromBunsToToppings() {
		feedPage.toppingsClick();
		feedPage.firstTopping.shouldBe(visible);
	}

	@Test
	@DisplayName("Transitions between sections")
	@Description("Try to change section from sauces to buns")
	public void fromSaucesToBuns() {
		feedPage.saucesClick();
		feedPage.bunsClick();
		feedPage.firstBuns.shouldBe(visible);
	}

	@Test
	@DisplayName("Transitions between sections")
	@Description("Try to change section from sauces to toppings")
	public void fromSaucesToToppings() {
		feedPage.saucesClick();
		feedPage.toppingsClick();
		feedPage.firstTopping.shouldBe(visible);
	}

	@Test
	@DisplayName("Transitions between sections")
	@Description("Try to change section from toppings to buns")
	public void fromToppingsToBuns() {
		feedPage.toppingsClick();
		feedPage.bunsClick();
		feedPage.firstBuns.shouldBe(visible);
	}

	@Test
	@DisplayName("Transitions between sections")
	@Description("Try to change section from toppings to sauces")
	public void fromToppingsToSauces() {
		feedPage.toppingsClick();
		feedPage.saucesClick();
		feedPage.firstSauces.shouldBe(visible);
	}
}
