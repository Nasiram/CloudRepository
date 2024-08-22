package com.cloud.cloud.utility;

import com.cloud.cloud.GitInfo;
import com.cloud.cloud.model.GitDetailsResponse;

/**
 * All Utilities related with Git have been included
 */
public class GitUtility {

    public static GitInfo dtoToEntityMapper(GitDetailsResponse gitDetailsResponse) {
        GitInfo gitInfo = new GitInfo();
        gitInfo.setDescription(gitDetailsResponse.description());
        gitInfo.setCloneUrl(gitDetailsResponse.cloneUrl());
        gitInfo.setCreatedAt(gitDetailsResponse.createdAt());
        gitInfo.setFullName(gitDetailsResponse.fullName());
        gitInfo.setStargazersCount(gitDetailsResponse.stargazersCount());
        return gitInfo;
    }
}
