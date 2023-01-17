package ch.heig.icecreams.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.IceCreamsEndPointApi;
import org.openapitools.client.model.IceCream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IceCreamSteps {

    private final IceCreamsEndPointApi api = new IceCreamsEndPointApi();
    private IceCream iceCream;
    private int statusCode;

    @Given("I have an ice cream payload")
    public void i_have_an_iceCream_payload() throws Throwable {
        iceCream = new IceCream();
        iceCream.setName("Rocket");
        iceCream.setPrice(1f);
    }

    @When("I POST it to the \\/ice-creams endpoint")
    public void i_POST_it_to_the_iceCreams_endpoint() throws Throwable {
        try {
            ApiResponse response = api.addIceCreamWithHttpInfo(iceCream);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, statusCode);
    }
}
