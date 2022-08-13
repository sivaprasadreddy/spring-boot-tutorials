package com.sivalabs.bookmarker.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookmarkDTO {
    private Long id;

    @NotBlank(message = "URL cannot be blank")
    private String url;

    private String title;

    @JsonProperty("created_user_id")
    private Long createdUserId;

    @JsonProperty("created_user_name")
    private String createdUserName;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
