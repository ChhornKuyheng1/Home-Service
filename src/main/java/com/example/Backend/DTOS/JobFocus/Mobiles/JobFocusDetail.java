package com.example.Backend.DTOS.JobFocus.Mobiles;

public class JobFocusDetail {
    private JobFocusDtoToM jobFocus;
    private JobFocusProviderView provider;

    public JobFocusDetail(JobFocusDtoToM jobFocus, JobFocusProviderView provider) {
        this.jobFocus = jobFocus;
        this.provider = provider;
    }

    public JobFocusDtoToM getJobFocus() {
        return jobFocus;
    }

    public void setJobFocus(JobFocusDtoToM jobFocus) {
        this.jobFocus = jobFocus;
    }

    public JobFocusProviderView getProvider() {
        return provider;
    }

    public void setProvider(JobFocusProviderView provider) {
        this.provider = provider;
    }
}
