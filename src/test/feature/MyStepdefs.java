import com.example.demo.Model;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class MyStepdefs {

    private String givenFirstName;
    private ResponseEntity<String> response;

    @Given("I have a first name of {string}")
    public void i_have_a_first_name_of(String firstName) {
        // Write code here that turns the phrase above into concrete actions
        this.givenFirstName = firstName;
    }
    @When("I call the following API {string} with a GET")
    public void i_call_the_following_api_with_a_post(String uri) {
        // Write code here that turns the phrase above into concrete actions
        var restTemplate = new RestTemplate();
        response = restTemplate.getForEntity(URI.create(uri), String.class);
    }
    @Then("I receive a {int} HTTP status")
    public void i_receive_a_http_status(Integer expectedStatusCode) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(response.getStatusCodeValue(), expectedStatusCode);
    }
    @Then("I have the following values in the response")
    public void i_have_the_following_values_in_the_response(io.cucumber.datatable.DataTable dataTable) {

        var json = JsonPath.parse(response.getBody());
        dataTable.asMap().entrySet().forEach(entry ->{
            var path = entry.getKey();
            var expectedValue = entry.getValue();
            System.out.println(entry);

            var fieldValue = json.read( path.trim());
            if(fieldValue == null) {
                Assertions.assertEquals(entry.getValue(), "null");
            } else{
                Assertions.assertEquals(entry.getValue(), fieldValue);
            }

        });
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

    }
}
