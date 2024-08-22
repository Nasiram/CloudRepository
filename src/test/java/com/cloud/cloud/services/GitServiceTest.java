package com.cloud.cloud.services;


import com.cloud.cloud.GitInfo;
import com.cloud.cloud.repository.CloudRepository;
import com.cloud.cloud.service.GitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GitServiceTest {

    @InjectMocks
    private GitService gitService;
    @Mock
    private CloudRepository cloudRepository;

    @Test
    public void loadToDB_test() {
        GitInfo gitInfoSend = new GitInfo();
        GitInfo gitInfoReceived = new GitInfo();
        gitInfoReceived.setStargazersCount(1);
        gitInfoReceived.setFullName("TestA");
        Mockito.when(cloudRepository.saveAndFlush(gitInfoSend)).thenReturn(gitInfoReceived);
        GitInfo result = (GitInfo) gitService.loadToDB(gitInfoSend);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("TestA",result.getFullName());
        Assertions.assertEquals(1,result.getStargazersCount());
    }


    @Test
    public void loadToDB_failed_test() {
        GitInfo gitInfoSend = new GitInfo();
        GitInfo gitInfoReceived = null;
        Mockito.when(cloudRepository.saveAndFlush(gitInfoSend)).thenReturn(gitInfoReceived);
        GitInfo result = (GitInfo) gitService.loadToDB(gitInfoSend);
        Assertions.assertNull(result);
    }
}
