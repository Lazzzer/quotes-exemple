package ch.heig.icecreams.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.IceCreamsApi;
import org.openapitools.client.model.IceCreamDTOid;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IceCreamSteps {

    private final IceCreamsApi api = new IceCreamsApi();
    private IceCreamDTOid iceCream;
    private int statusCode;

    @Given("I have an ice cream payload")
    public void i_have_an_ice_cream_payload() {
        iceCream = new IceCreamDTOid();
        iceCream.setName("Vanilla");
        iceCream.setPrice(2.5f);
        iceCream.setOriginId(1);
        iceCream.setContainerIds(List.of(1, 2));
    }

    @When("I POST it to the \\/ice-creams endpoint")
    public void i_post_it_to_the_ice_creams_endpoint() {
        try {
            ApiResponse<Void> response = api.addIceCreamWithHttpInfo(iceCream);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(Integer int1) {
        assertEquals(int1, statusCode);
    }

    @Given("I have an edited ice cream payload")
    public void i_have_an_edited_ice_cream_payload() {
        iceCream = new IceCreamDTOid();
        iceCream.setName("Chocolate");
        iceCream.setPrice(3.5f);
        iceCream.setOriginId(2);
        iceCream.setContainerIds(List.of(3, 4));
    }

    @When("I PUT it to the \\/ice-creams endpoint")
    public void i_put_it_to_the_ice_creams_endpoint() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
