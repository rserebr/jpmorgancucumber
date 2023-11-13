package stepdata;

import io.restassured.response.Response;
import models.Comment;
import models.Post;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepData {

    public User testUser;

    public Map<String, User> testUsersMap = new HashMap<>();
    public List<Post> testPosts;
    public Post testPost;
    public Post.PostBuilder postBuilder;
    public Response postPostResponse;
    public Comment.CommentBuilder commentBuilder;
    public Map<Post, List<Comment>> testPostsWithCommentsPostApi;
    public Map<Post, List<Comment>> testPostsWithCommentsCommentsApi;
    public List<Comment> testCommentsPostApi;
    public List<Comment> testCommentsCommentApi;
    public Response patchPostResponse;
    public long testPostId;
    public Response deletePostResponse;
    public Response postCommentResponse;
    public Response patchCommentResponse;
    public Comment testComment;
    public Response deleteCommentResponse;
}
