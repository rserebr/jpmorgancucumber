package services;

import enums.ApiResources;
import enums.ApiTags;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Comment;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CommentsApi {

    public List<Comment> getAllCommentsByPostId(long postId) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .param(ApiTags.POST_ID.getValue(), postId)
                .get(ApiResources.COMMENTS.getValue())
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<Comment>>() {
                });
    }

    public Response sendPostCommentRequest(Comment comment) {
        return given()
                .contentType(ContentType.JSON)
                .body(comment)
                .when()
                .post(ApiResources.COMMENTS.getValue())
                .thenReturn();
    }

    public Response sendPatchCommentRequest(Comment comment) {
        return given()
                .contentType(ContentType.JSON)
                .body(comment)
                .pathParam("id", comment.getId())
                .when()
                .patch(ApiResources.COMMENTS.getValue() + "/{id}")
                .thenReturn();
    }

    public Response sendDeleteCommentRequest(Comment comment) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", comment.getId())
                .when()
                .delete(ApiResources.COMMENTS.getValue() + "/{id}")
                .thenReturn();
    }
}
