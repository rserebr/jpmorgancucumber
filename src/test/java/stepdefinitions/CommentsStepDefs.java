package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.Comment;
import models.Post;
import org.junit.Assert;
import services.CommentsApi;
import services.PostsApi;
import stepdata.StepData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsStepDefs  extends StepDefs{

    CommentsApi commentsApi = new CommentsApi();
    PostsApi postsApi = new PostsApi();

    public CommentsStepDefs(StepData stepData) {
        super(stepData);
        this.stepData.commentBuilder = Comment.builder();
    }


    @When("all comments are fetched from CommentsAPI perspective for all post")
    public void allCommentsAreFetchedFromCommentsAPIPerspectiveForAllPost() {
        Map<Post, List<Comment>> postCommentMap = new HashMap<>();
        List<Comment> comments = new ArrayList<>();
        stepData.testPosts.forEach(
                post -> {
                    List<Comment> commentList = commentsApi.getAllCommentsByPostId(post.getId());
                    postCommentMap.put(post, commentList);
                    comments.addAll(commentList);
                }
        );
        Assert.assertTrue("Comments should be present in the posts.", comments.size() > 0);
        stepData.testCommentsCommentApi = comments;
        stepData.testPostsWithCommentsCommentsApi = postCommentMap;
    }

    @When("all comments are fetched from PostsAPI perspective for all post")
    public void allCommentsAreFetchedFromPostsAPIPerspectiveForAllPost() {
        Map<Post, List<Comment>> postCommentMap = new HashMap<>();
        List<Comment> comments = new ArrayList<>();
        stepData.testPosts.forEach(
                post -> {
                    List<Comment> commentList = postsApi.getAllCommentsByPostId(post.getId());
                    comments.addAll(commentList);
                    postCommentMap.put(post, commentList);
                }
        );
        Assert.assertTrue("Comments should be present in the posts.", comments.size() > 0);
        stepData.testCommentsPostApi = comments;
        stepData.testPostsWithCommentsPostApi = postCommentMap;
    }

    @Then("comments fetched from PostAPI and CommentApi are the same")
    public void commentsFetchedFromPostAPIAndCommentApiAreTheSame() {
        Assert.assertEquals(stepData.testPostsWithCommentsPostApi, stepData.testPostsWithCommentsPostApi);
    }

    @When("{string} name is set for comment")
    public void nameIsSetForComment(String name) {
        stepData.commentBuilder.name(name);
    }

    @And("{string} email is set for comment")
    public void emailIsSetForComment(String email) {
        stepData.commentBuilder.email(email);
    }

    @And("{string} body is set for comment")
    public void isSetForComment(String body) {
        stepData.commentBuilder.body(body);
    }

    @And("postId is set for new comment")
    public void postIdIsSetForNewComment() {
        stepData.commentBuilder.postId(stepData.testPosts.get(0).getId());
    }

    @And("postId is set for existing comment")
    public void postidIsSetForExistingComment() {
        stepData.commentBuilder.postId(stepData.testCommentsCommentApi.get(0).getPostId());
    }

    @And("comment id is set for existing comment")
    public void commentIdIsSetForExistingComment() {
        stepData.commentBuilder.id(stepData.testCommentsCommentApi.get(0).getId());
    }

    @When("POST request is send with the comment")
    public void postRequestIsSendWithTheComment() {
        Comment comment = stepData.commentBuilder.build();
        stepData.postCommentResponse = commentsApi.sendPostCommentRequest(comment);
    }

    @Then("comment is created successfully")
    public void commentIsCreatedSuccessfully() {
        Response commentResponse = stepData.postCommentResponse;
        Comment comment = commentResponse.getBody().as(Comment.class);
        Assert.assertEquals(201, commentResponse.getStatusCode());
        Assert.assertTrue(comment.getId() > 0);
    }

    @And("PATCH request is send with the comment")
    public void patchRequestIsSendWithTheComment() {
        Comment comment = stepData.commentBuilder.build();
        stepData.patchCommentResponse = commentsApi.sendPatchCommentRequest(comment);
        stepData.testComment = comment;
    }

    @Then("comment is updated successfully")
    public void commentIsUpdatedSuccessfully() {
        Response putResponse = stepData.patchCommentResponse;
        Comment comment = putResponse.getBody().as(Comment.class);
        Assert.assertEquals(200, putResponse.getStatusCode());
        Assert.assertEquals(stepData.testComment, comment);
    }

    @Then("all postId values are same and matched with the post in the comments")
    public void allPostIdValuesAreSameAndMatchedWithThePostInTheComments() {
        stepData.testPostsWithCommentsCommentsApi.forEach(
                (post, comments) -> comments.forEach(
                        comment -> Assert.assertEquals(
                                String.format("%s post should have same PostId in comment %s commentId.",
                                        post.getId(), comment.getId()), post.getId(), comment.getPostId())
                )
        );
    }

    @And("DELETE request is send with the comment")
    public void deleteRequestIsSendWithTheComment() {
        Comment comment = stepData.commentBuilder.build();
        stepData.deleteCommentResponse = commentsApi.sendDeleteCommentRequest(comment);
    }

    @Then("comment is deleted successfully")
    public void commentIsDeletedSuccessfully() {
        Response deleteCommentResponse = stepData.deleteCommentResponse;
        Assert.assertEquals(200, deleteCommentResponse.getStatusCode());
    }

    @And("postId is set for comment for existing post")
    public void postidIsSetForCommentForExistingPost() {
        Response postResponse = stepData.postPostResponse;
        Post post = postResponse.getBody().as(Post.class);
        stepData.commentBuilder.postId(post.getId());
    }

    @Then("post and comment are created successfully")
    public void postAndCommentAreCreatedSuccessfully() {
        Response postResponse = stepData.postPostResponse;
        Response commentResponse = stepData.postCommentResponse;
        Post post = postResponse.getBody().as(Post.class);
        Comment comment = commentResponse.getBody().as(Comment.class);
        Assert.assertEquals(201, postResponse.getStatusCode());
        Assert.assertEquals(201, commentResponse.getStatusCode());
        Assert.assertTrue(post.getId() > 0);
        Assert.assertTrue(comment.getId() > 0);
        Assert.assertEquals(comment.getPostId(), post.getId());
    }
}
