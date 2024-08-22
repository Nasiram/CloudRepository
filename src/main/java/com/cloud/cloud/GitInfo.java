package com.cloud.cloud;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "GIT_INFO")
@Data
public class GitInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "description")
    private String description;
    @Column(name = "clone_url")
    private String cloneUrl;
    @Column(name = "stargazers_count")
    private int stargazersCount;
    @Column(name = "created_at")
    private Timestamp createdAt;


}
