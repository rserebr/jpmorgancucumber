package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApiResources {
    USERS("users"),
    COMMENTS("comments"),
    POSTS("posts");

    @Getter
    private final String value;

}
