/*
 * blackduck-detect
 *
 * Copyright (c) 2022 Synopsys, Inc.
 *
 * Use subject to the terms and conditions of the Synopsys End User Software License and Maintenance Agreement. All rights reserved worldwide.
 */
package com.synopsys.integration.jenkins.detect;

import java.io.IOException;

import com.synopsys.integration.exception.IntegrationException;
import com.synopsys.integration.jenkins.detect.exception.DetectJenkinsException;
import com.synopsys.integration.jenkins.detect.extensions.DetectDownloadStrategy;
import com.synopsys.integration.jenkins.extensions.JenkinsIntLogger;

public class DetectPipelineCommands {
    private final JenkinsIntLogger logger;
    private final DetectRunner detectRunner;

    public DetectPipelineCommands(DetectRunner detectRunner, JenkinsIntLogger logger) {
        this.detectRunner = detectRunner;
        this.logger = logger;
    }

    public int runDetect(boolean returnStatus, String detectArgumentString, DetectDownloadStrategy detectDownloadStrategy) throws IOException, IntegrationException, InterruptedException {
        int exitCode = detectRunner.runDetect(null, detectArgumentString, detectDownloadStrategy);
        if (exitCode > 0) {
            String errorMsg = "Detect failed with exit code " + exitCode;
            if (returnStatus) {
                logger.error(errorMsg);
            } else {
                throw new DetectJenkinsException(errorMsg);
            }
        }

        return exitCode;
    }

}
