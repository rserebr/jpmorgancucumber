package services;

import enums.ApiResources;
import enums.ApiTags;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import models.User;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersApi {

    public User getUserByUsername(String username) {
        List<User> testUsers = given()
                .contentType(ContentType.JSON)
                .when()
                .param(ApiTags.USER_NAME.getValue(), username)
                .get(ApiResources.USERS.getValue())
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<User>>() {
                });
        Assert.assertEquals(1, testUsers.size());
        return testUsers.get(0);
    }
}
