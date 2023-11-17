package stepdefinitions;

import io.cucumber.java.en.Given;
import models.User;
import org.junit.Assert;
import services.UsersApi;
import stepdata.StepData;

public class UsersStepDefs extends StepDefs{

    UsersApi usersApi = new UsersApi();
    public UsersStepDefs(StepData stepData) {
        super(stepData);
    }

    @Given("there is an user has {string} username")
    public void thereIsAnUserHasUsername(String username) {
        User testUser =usersApi.getUserByUsername(username);
        Assert.assertNotNull(testUser);
        Assert.assertEquals(String.format("Given %s username should be exist to execute test.", username), username, testUser.getUsername());
        stepData.testUser = testUser;
        stepData.testUsersMap.put(username, testUser);
    }

}
