package com.cloud.cloud.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record GitDetailsResponse(@JsonProperty("full_name") String fullName,
                                 String description,
                                 @JsonProperty("clone_url") String cloneUrl,
                                 @JsonProperty("stargazers_count") int stargazersCount,
                                 @JsonProperty("created_at") Timestamp createdAt) {

}
