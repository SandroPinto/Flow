package flow.implementation;

import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import flow.Engine;
import flow.IAdapter;
import flow.samples.TextAdapter;
import flow.samples.TextAgent;
import flow.samples.TextApp;
import flow.samples.TextAgent;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class TestSteps {

    private TextAgent aPerson;
    private TextApp anEmailServer;
    private Engine anEngine;
    private TextAdapter anEmailClient;
    private Map<String, IAdapter> messages = new HashMap();


    @Given("^A User sends a \"([^\"]*)\" message$")
    public void aUserSendsAMessage(String strClientMessage) throws Throwable {
        //A person (Agent) wants to send a message via email (Action) to another person
        //To be able to send the message an email client (Adapter) is required
        aPerson = new TextAgent(strClientMessage);
        anEmailClient = new TextAdapter();
        messages.put("TEXT", anEmailClient);
    }

    @When("^The message is converted by the Adapter$")
    public void theMessageIsConvertedByTheAdapter() throws Throwable {
        //The email client will then communicate via http (Event) to the email server (App)
        anEmailServer = new TextApp();
        anEngine = new Engine(aPerson, messages, anEmailServer);
        anEngine.run();
    }

    @Then("^The Message server should contain the \"([^\"]*)\" message in the queue$")
    public void validateMessageServerContainTheMessageInTheQueue(String strExpServerMessage) throws Throwable {
        Assert.assertEquals(strExpServerMessage, anEmailServer.popResponse());
    }
}
