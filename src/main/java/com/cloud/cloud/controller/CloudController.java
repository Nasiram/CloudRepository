package com.cloud.cloud.controller;


import com.cloud.cloud.model.CloudResponse;
import com.cloud.cloud.model.GitDetailsResponse;
import com.cloud.cloud.model.StatusDTO;
import com.cloud.cloud.service.CloudService;
import com.cloud.cloud.utility.CloudConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Controller responsible for Cloud repository actions
 */
@RestController
@RequestMapping(value = "/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CloudController {

    Logger LOGGER = LoggerFactory.getLogger(CloudController.class);
    @Autowired
    private CloudService cloudService;

    /**
     * get API for fetching Git repository Details
     * This also does loading of the corresponding repository details obtained in the subsequent services
     * @param owner
     * @param repositoryName
     * @return
     */
    @GetMapping(value = "/details/{owner}/{repositoryName}")
    public ResponseEntity<CloudResponse> fetchGitDetails(@PathVariable("owner") String owner,
                                                         @PathVariable("repositoryName") String repositoryName) {
        String METHOD_NAME = ":: getCloudDetails :: ";
        CloudResponse response;
        StatusDTO statusDTO;
        try {
            if(owner == null || owner.isBlank() || repositoryName == null || repositoryName.isBlank()) {
                statusDTO = new StatusDTO(CloudConstants.STATUS_FAILED, CloudConstants.ERR_BAD_REQUEST);
                response = new CloudResponse(null, statusDTO);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            GitDetailsResponse gitDetailsResponse = cloudService.getGitDetailsResponse(owner,repositoryName);
            statusDTO = new StatusDTO(CloudConstants.STATUS_SUCCESS, CloudConstants.SUCCESSFULLY_FETCH_CLOUD_DETAILS);
            response = new CloudResponse(gitDetailsResponse, statusDTO);
            LOGGER.info(METHOD_NAME + CloudConstants.SUCCESSFULLY_FETCH_CLOUD_DETAILS);
        } catch (Exception e) {
            statusDTO = new StatusDTO(CloudConstants.STATUS_FAILED, CloudConstants.ERR_FETCH_CLOUD_DETAILS_FAILED);
            response = new CloudResponse(null, statusDTO);
            LOGGER.error(METHOD_NAME + CloudConstants.ERR_FETCH_CLOUD_DETAILS_FAILED + ", " +CloudConstants.EXCEPTION +" : " +e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
