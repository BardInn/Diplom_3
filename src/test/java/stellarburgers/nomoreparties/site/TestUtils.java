package stellarburgers.nomoreparties.site;

import org.openqa.selenium.chrome.ChromeOptions;

public class TestUtils {

	public static void setUpBrowser() {
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		if (browser.equalsIgnoreCase("yandex")) {
			System.out.println("running in yandex");
			System.setProperty("webdriver.chrome.driver", "/Users/fenwer/Downloads/yandexdriver");
			ChromeOptions options = new ChromeOptions();
			options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
		}
	}
}
