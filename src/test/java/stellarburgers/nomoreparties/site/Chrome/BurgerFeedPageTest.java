package stellarburgers.nomoreparties.site.Chrome;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.nomoreparties.site.BurgerFeedPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;


public class BurgerFeedPageTest {

	public static final String URL = "https://stellarburgers.nomoreparties.site";
	private BurgerFeedPage feedPage;

	@Before
	public void before() {
		feedPage = open(URL, BurgerFeedPage.class);
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
