package com.cloud.cloud.services;


import com.cloud.cloud.model.GitDetailsResponse;
import com.cloud.cloud.service.CloudService;
import com.cloud.cloud.service.GitService;
import com.cloud.cloud.utility.CloudConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
public class CloudServiceTest {


    @InjectMocks
    private CloudService cloudService;
    @Value(CloudConstants.REPO_URL)
    private String repoUrl ;

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private GitService gitService;

    @Test
    public void getGitDetails_success_test() throws Exception {
        GitDetailsResponse gitDetailsResponse = new GitDetailsResponse("NAME",
                "repo description",
                "http://testurl/api",
                1, Timestamp.valueOf(LocalDateTime.now()));
        ReflectionTestUtils.setField(cloudService, "repoUrl", "https://api.github.com/repos/");
        Mockito.when(restTemplate.getForObject(repoUrl+"testA/testB", GitDetailsResponse.class))
                .thenReturn(gitDetailsResponse);
        GitDetailsResponse response = cloudService.getGitDetailsResponse("testA","testB");
        Assertions.assertNotNull(response);
        Assertions.assertEquals("NAME",response.fullName());
        Assertions.assertEquals(1,response.stargazersCount());
        Assertions.assertEquals("http://testurl/api",response.cloneUrl());
    }

    @Test
    public void getGitDetails_gitDetailsResponse_null_test() {
        Mockito.when(restTemplate.getForObject(repoUrl, GitDetailsResponse.class))
                .thenReturn(null);
        GitDetailsResponse response = cloudService.getGitDetailsResponse("testA","testB");
        Assertions.assertNull(response);
    }

    @Test
    public void getGitDetails_repoUrl_null_test() {
        GitDetailsResponse gitDetailsResponse = new GitDetailsResponse("NAME",
                "repo description",
                "http://testurl/api",
                1, Timestamp.valueOf(LocalDateTime.now()));
        Mockito.when(restTemplate.getForObject(repoUrl, GitDetailsResponse.class))
                .thenReturn(gitDetailsResponse);
        GitDetailsResponse response = cloudService.getGitDetailsResponse("testA","testB");
        Assertions.assertNull(response);
    }

}
