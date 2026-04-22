package com.example.Backend.DTOS.AdminDashboards.ServiceReports;

import java.util.List;

public class ServiceReport {
    private OverView overView;
    private List<ServiceReportList> services;

    public ServiceReport(OverView overView, List<ServiceReportList> services) {
        this.overView = overView;
        this.services = services;
    }

    public OverView getOverView() {
        return overView;
    }

    public void setOverView(OverView overView) {
        this.overView = overView;
    }

    public List<ServiceReportList> getServices() {
        return services;
    }

    public void setServices(List<ServiceReportList> services) {
        this.services = services;
    }
}
