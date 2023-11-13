package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.junit.Assert;
import services.PostsApi;
import stepdata.StepData;

import java.util.List;

public class PostsStepDefs extends StepDefs{

    PostsApi postsApi = new PostsApi();

    public PostsStepDefs(StepData stepData) {
        super(stepData);
        this.stepData.postBuilder = Post.builder();
    }

    @And("{string} title is entered for the post")
    public void titleIsEnteredForThePost(String title) {
        stepData.postBuilder.title(title);
    }

    @And("{string} body is entered for the post")
    public void bodyIsEnteredForThePost(String body) {
        stepData.postBuilder.body(body);
    }

    @And("test userId is entered for the post")
    public void testUserIdIsEnteredForThePost() {
        stepData.postBuilder.userId(stepData.testUser.getId());
    }

    @When("POST request is send with the post")
    public void postRequestIsSendWithThePost() {
        Post post = stepData.postBuilder.build();
        stepData.postPostResponse = postsApi.sendPostRequestWithBody(post);
    }

    @Then("post is created successfully")
    public void postIsCreatedSuccessfully() {
        Response postResponse = stepData.postPostResponse;
        Post post = postResponse.getBody().as(Post.class);
        Assert.assertEquals(201, postResponse.getStatusCode());
        Assert.assertTrue(post.getId() > 0);
    }

    @And("there is a post written by test user")
    public void thereIsAPostWrittenByTestUser() {
        User testUser = stepData.testUser;
        Assert.assertNotNull(testUser);
        List<Post> posts = postsApi.getPostsCreatedByUser(testUser);
        Assert.assertTrue(posts.size() > 0);
        stepData.testPosts = posts;
    }

    @When("user updates post {string} title")
    public void userUpdatesPostTitle(String title) {
        stepData.postBuilder.title(title);
    }

    @And("user updates post {string} body")
    public void userUpdatesPostBody(String body) {
        stepData.postBuilder.body(body);
    }

    @And("test user id is updated for the post")
    public void testUserIdIsUpdatedForThePost() {
        stepData.postBuilder.userId(stepData.testUser.getId());
    }

    @And("PATCH request is sent for the post")
    public void putRequestIsSentForThePost() {
        Post post = stepData.postBuilder.build();
        stepData.patchPostResponse = postsApi.sendPatchPostRequest(post);
        stepData.testPost = post;
    }

    @And("user sets id for the post")
    public void userSetsIdForThePost() {
        Post post = stepData.testPosts.get(0);
        long postId = post.getId();
        stepData.postBuilder.id(postId);
        stepData.testPostId = postId;
    }

    @Then("post updated successfully")
    public void postUpdatedSuccessfully() {
        Response putResponse = stepData.patchPostResponse;
        Post post = stepData.patchPostResponse.getBody().as(Post.class);
        Assert.assertEquals(200, putResponse.getStatusCode());
        Assert.assertEquals(stepData.testPost, post);
    }

    @And("DELETE request is sent for the post")
    public void deleteRequestIsSentForThePost() {
        stepData.deletePostResponse = postsApi.sendDeletePostRequest(stepData.testPostId);
    }

    @Then("post deleted successfully")
    public void postDeletedSuccessfully() {
        Response deletePoastResponse = stepData.deletePostResponse;
        Assert.assertEquals(200, deletePoastResponse.getStatusCode());
    }
}
