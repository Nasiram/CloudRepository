package com.cloud.cloud.service;

import com.cloud.cloud.GitInfo;
import com.cloud.cloud.model.GitDetailsResponse;
import com.cloud.cloud.utility.CloudConstants;
import com.cloud.cloud.utility.GitUtility;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CloudService {


    Logger LOGGER = LoggerFactory.getLogger(CloudService.class);

    @Value(CloudConstants.REPO_URL)
    String repoUrl ;

    @Autowired
    private GitService gitService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * This also does loading of the corresponding repository details obtained in the subsequent services
     * @param owner
     * @param repositoryName
     * @return
     */
    @Transactional
    public GitDetailsResponse getGitDetailsResponse(String owner, String repositoryName) {
        String METHOD_NAME = ":: getGitDetailsResponse :: ";
        LOGGER.info(METHOD_NAME + "owner : "+owner + ", repositoryName : "+repositoryName);
        try {
            final String uri = repoUrl +owner+"/"+repositoryName;
            LOGGER.info(METHOD_NAME +" uri " +uri);
            GitDetailsResponse gitDetailsResponse = restTemplate.getForObject(uri, GitDetailsResponse.class);
            if(Objects.nonNull(gitDetailsResponse)) {
                GitInfo gitInfo = GitUtility.dtoToEntityMapper(gitDetailsResponse);
                final var savedGitInfo = gitService.loadToDB(gitInfo);
                LOGGER.info(METHOD_NAME + "savedGitInfo : "+savedGitInfo+" , the entity object gitInfo : "+ gitInfo);
                return gitDetailsResponse;
            } else {
                LOGGER.warn(METHOD_NAME + "gitDetailsResponse is null "+gitDetailsResponse );
                return null;
            }
        } catch (RestClientException e) {
            LOGGER.error(METHOD_NAME + CloudConstants.EXCEPTION + " : "+e.getMessage());
            return null;
        }
    }
}
