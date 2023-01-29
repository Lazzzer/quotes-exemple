package ch.heig.icecreams.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.IceCreamsApi;
import org.openapitools.client.model.IceCreamDTOid;
import org.openapitools.client.model.IceCreamDTOidOptional;
import org.openapitools.client.model.IceCreamDTOobj;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IceCreamSteps {

    private final IceCreamsApi api = new IceCreamsApi();
    private IceCreamDTOid iceCream;

    private IceCreamDTOidOptional iceCreamForPatch;
    private int statusCode;

    private int chosenIdToGet;
    private int chosenIdToDelete;


    @Given("I have the id of the ice cream I want")
    public void iHaveTheIdOfTheIceCreamIWant() {
        chosenIdToGet = 1;
    }

    @When("I GET it the \\/ice-creams\\/id endpoint")
    public void iGETItTheIceCreamsIdEndpoint() {
        try {
            ApiResponse<IceCreamDTOobj> response = api.getIceCreamWithHttpInfo(chosenIdToGet);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

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
        iceCream.setId(1);
        iceCream.setName("Super chocolat");
        iceCream.setPrice(7.5f);
        iceCream.setOriginId(3);
        iceCream.setContainerIds(List.of(1, 2));
    }

    @When("I PUT it to the \\/ice-creams endpoint")
    public void i_put_it_to_the_ice_creams_endpoint() {
        try {
            ApiResponse<Void> response = api.updateCreateIceCreamWithHttpInfo(iceCream);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Given("I have a partial edited ice cream payload")
    public void iHaveAPartialEditedIceCreamPayload() {
        iceCreamForPatch = new IceCreamDTOidOptional();
        iceCreamForPatch.setId(3);
        iceCreamForPatch.setName("Melon glacé");
    }

    @When("I PATCH it to the \\/ice-creams endpoint")
    public void iPATCHItToTheIceCreamsEndpoint() {
        try {
            ApiResponse<Void> response = api.updateIceCreamWithHttpInfo(iceCreamForPatch);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Given("I have an id for the ice i want to delete")
    public void iHaveAnIdForTheIceIWantToDelete() {
        chosenIdToDelete = 2;
    }

    @When("I DELETE it to the \\/ice-creams endpoint")
    public void iDELETEItToTheIceCreamsEndpoint() {
        try {
            ApiResponse<Void> response = api.deleteIceCreamWithHttpInfo(chosenIdToDelete);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }
}
