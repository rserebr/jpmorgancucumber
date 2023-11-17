package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApiTags {
    USER_NAME("username"),
    USER_ID("userId"),
    POST_ID("postId");

    @Getter
    private final String value;
}
