package services;

import enums.ApiResources;
import enums.ApiTags;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Comment;
import models.Post;
import models.User;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsApi {
    public Response sendPostRequestWithBody(Post post) {
        return given()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .post(ApiResources.POSTS.getValue())
                .thenReturn();
    }

    public List<Post> getPostsCreatedByUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .param(ApiTags.USER_ID.getValue(), user.getId())
                .get(ApiResources.POSTS.getValue())
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<Post>>() {
                });
    }

    public Response sendPatchPostRequest(Post post) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", String.valueOf(post.getId()))
                .body(post)
                .when()
                .patch(ApiResources.POSTS.getValue() + "/{id}")
                .thenReturn();
    }

    public Response sendDeletePostRequest(long postId) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", postId)
                .when()
                .delete(ApiResources.POSTS.getValue() + "/{id}")
                .thenReturn();
    }

    public List<Comment> getAllCommentsByPostId(long postId) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("postId", postId)
                .get(ApiResources.POSTS.getValue() + "/{postId}/" + ApiResources.COMMENTS.getValue())
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<Comment>>() {
                });
    }
}
