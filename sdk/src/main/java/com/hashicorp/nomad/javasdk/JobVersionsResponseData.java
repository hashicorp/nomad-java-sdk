package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.apimodel.Job;
import com.hashicorp.nomad.apimodel.JobDiff;

import java.util.List;

/**
 * Represents a job versions API response.
 */
public class JobVersionsResponseData {
    private List<Job> versions;
    private List<JobDiff> jobDifferences;

    /**
     * Gets the versions of the job.
     */
    @JsonProperty("Versions")
    public List<Job> getVersions() {
        return versions;
    }

    /**
     * Sets the versions of the job.
     *
     * @param versions the versions of the job
     */
    public JobVersionsResponseData setJobs(List<Job> versions) {
        this.versions = versions;
        return this;
    }

    /**
     * Gets the diffs between job versions.
     */
    @JsonProperty("JobDiffs")
    public List<JobDiff> getJobDiffs() {
        return jobDifferences;
    }

    /**
     * Sets the diffs between job versions.
     *
     * @param jobDifferences the diffs between job versions
     */
    public JobVersionsResponseData setJobDifferences(List<JobDiff> jobDifferences) {
        this.jobDifferences = jobDifferences;
        return this;
    }
}
