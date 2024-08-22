package com.cloud.cloud.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@JsonSerialize
public class GitDetails {
    private String fullName;
    private String description;
    private String cloneUrl;
    private int stargazersCount;
    private Timestamp createdAt;

}
