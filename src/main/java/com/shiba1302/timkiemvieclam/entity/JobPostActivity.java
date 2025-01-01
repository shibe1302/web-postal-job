package com.shiba1302.timkiemvieclam.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "job_post_activity")
public class JobPostActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_id")
    private Integer jobPostID;

    @ManyToOne
    @JoinColumn(name = "posted_by_id", referencedColumnName = "user_id")
    private Users postedById;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_location_id", referencedColumnName = "id")
    private JobLocation jobLocationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_company_id", referencedColumnName = "id")
    private JobCompany jobCompanyID;

    @Transient
    private Boolean isActive;

    @Transient
    private Boolean isSave;

    @Length(max = 10000)
    @Column(name = "description_of_job")
    private String descriptionOfJob;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "salary")
    private String salary;

    @Column(name = "remote")
    private String remote;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "posted_date")
    private Date postedDate;

    @Column(name = "job_title")
    private String jobTitle;

    public JobPostActivity() {
    }

    public JobPostActivity(Users postedById, JobLocation jobLocationId, JobCompany jobCompanyID, Boolean isActive,
            Boolean isSave, @Length(max = 10000) String descriptionOfJob, String jobType, String salary, String remote,
            Date postedDate, String jobTitle) {
        this.postedById = postedById;
        this.jobLocationId = jobLocationId;
        this.jobCompanyID = jobCompanyID;
        this.isActive = isActive;
        this.isSave = isSave;
        this.descriptionOfJob = descriptionOfJob;
        this.jobType = jobType;
        this.salary = salary;
        this.remote = remote;
        this.postedDate = postedDate;
        this.jobTitle = jobTitle;
    }

    public Integer getJobPostID() {
        return jobPostID;
    }

    public void setJobPostID(Integer jobPostID) {
        this.jobPostID = jobPostID;
    }

    public Users getPostedById() {
        return postedById;
    }

    public void setPostedById(Users postedById) {
        this.postedById = postedById;
    }

    public JobLocation getJobLocationId() {
        return jobLocationId;
    }

    public void setJobLocationId(JobLocation jobLocationId) {
        this.jobLocationId = jobLocationId;
    }

    public JobCompany getJobCompanyID() {
        return jobCompanyID;
    }

    public void setJobCompanyID(JobCompany jobCompanyID) {
        this.jobCompanyID = jobCompanyID;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsSave() {
        return isSave;
    }

    public void setIsSave(Boolean isSave) {
        this.isSave = isSave;
    }

    public String getDescriptionOfJob() {
        return descriptionOfJob;
    }

    public void setDescriptionOfJob(String descriptionOfJob) {
        this.descriptionOfJob = descriptionOfJob;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "JobPostActivity [jobPostID=" + jobPostID + ", postedById=" + postedById + ", jobLocationId="
                + jobLocationId + ", jobCompanyID=" + jobCompanyID + ", isActive=" + isActive + ", isSave=" + isSave
                + ", descriptionOfJob=" + descriptionOfJob + ", jobType=" + jobType + ", salary=" + salary + ", remote="
                + remote + ", postedDate=" + postedDate + ", jobTitle=" + jobTitle + "]";
    }

}
