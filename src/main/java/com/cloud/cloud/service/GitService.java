package com.cloud.cloud.service;


import com.cloud.cloud.GitInfo;
import com.cloud.cloud.repository.CloudRepository;
import com.cloud.cloud.utility.CloudConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitService {

    Logger LOGGER = LoggerFactory.getLogger(GitService.class);

    @Autowired
    private CloudRepository cloudRepository;

    /**
     * This method only used to insert git repository details to GIT_INFO table
     * @param gitInfo
     * @return
     */
    public Object loadToDB(GitInfo gitInfo) {
        String METHOD = ":: loadToDB :: ";
        try {
            LOGGER.info(METHOD + "gitInfo saving in to DB has started");
            final var res = cloudRepository.saveAndFlush(gitInfo);
            LOGGER.info(METHOD + "gitInfo saving completed");
            return res;
        } catch (Exception e) {
            LOGGER.error(METHOD + CloudConstants.EXCEPTION +" : "+e.getCause());
            return null;
        }
    }

}
