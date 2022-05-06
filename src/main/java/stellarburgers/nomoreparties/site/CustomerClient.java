package stellarburgers.nomoreparties.site;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import stellarburgers.nomoreparties.site.CustomerData;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class CustomerClient {
	public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
	private static final String CUSTOMER_PATH = "/api/auth/";
	protected static RequestSpecification getBaseSpec() {
		return new RequestSpecBuilder()
				.addFilter(new AllureRestAssured())
				.setContentType(JSON)
				.setBaseUri(BASE_URL)
				.build();
	}
	@Step("Login customer")
	public static ValidatableResponse login(String email,String password) {
		@AllArgsConstructor
		@Data
		class Credentials{
			String email;
			String password;
		}
		return given()
				.spec(getBaseSpec())
				.body(new Credentials(email,password))
				.when()
				.post(CUSTOMER_PATH + "login")
				.then();
	}

	@Step("Delete customer")
	public static ValidatableResponse delete(CustomerData customerData) {
		return given()
				.spec(getBaseSpec())
				.auth().oauth2(customerData.getCustomerToken().getToken())
				.when()
				.delete(CUSTOMER_PATH + "user")
				.then()
				.assertThat()
				.statusCode(202);
	}

}
