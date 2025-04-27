package com.hydrolink.api.report.services;

import com.hydrolink.api.report.model.dto.ReportResponse;

import java.io.File;

public interface ReportService {

    ReportResponse generateReport();
    ReportResponse getReportById(Long reportId);
    void deleteReport(Long reportId);
    File exportReport(Long reportId, String format);
}
