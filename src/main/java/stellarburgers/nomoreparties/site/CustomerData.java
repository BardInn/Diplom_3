package stellarburgers.nomoreparties.site;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import static net.andreinc.mockneat.unit.user.Emails.emails;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerData {

	private String email;
	private String password;
	private String name;

	@JsonIgnore
	private CustomerToken customerToken;



	@Step("Generating random data to create a customer")
	public static CustomerData getRandom() {
		final String email = emails().val();
		final String password = RandomStringUtils.randomAlphabetic(10);
		final String name = RandomStringUtils.randomAlphabetic(10);

		Allure.addAttachment("Email", email);
		Allure.addAttachment("Password", password);
		Allure.addAttachment("FirstName", name);

		return new CustomerData(email, password, name, new CustomerToken());
	}
}
