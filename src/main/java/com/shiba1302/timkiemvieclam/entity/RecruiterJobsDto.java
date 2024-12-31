package com.shiba1302.timkiemvieclam.entity;

public class RecruiterJobsDto {
    private Long totalCandidates;
    private Integer jobPostID;
    private String jobTitle;
    private JobLocation jobLocationID;
    private JobCompany jobCompanyID;

    public RecruiterJobsDto(Long totalCandidates, Integer jobPostID, String jobTitle, JobLocation jobLocationID,
            JobCompany jobCompanyID) {
        this.totalCandidates = totalCandidates;
        this.jobPostID = jobPostID;
        this.jobTitle = jobTitle;
        this.jobLocationID = jobLocationID;
        this.jobCompanyID = jobCompanyID;
    }

    public Long getTotalCandidates() {
        return totalCandidates;
    }

    public void setTotalCandidates(Long totalCandidates) {
        this.totalCandidates = totalCandidates;
    }

    public Integer getJobPostID() {
        return jobPostID;
    }

    public void setJobPostID(Integer jobPostID) {
        this.jobPostID = jobPostID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobLocation getJobLocationID() {
        return jobLocationID;
    }

    public void setJobLocationID(JobLocation jobLocationID) {
        this.jobLocationID = jobLocationID;
    }

    public JobCompany getJobCompanyID() {
        return this.jobCompanyID;
    }

    public void setJobCompanyID(JobCompany jobCompanyID) {
        this.jobCompanyID = jobCompanyID;
    }

}
