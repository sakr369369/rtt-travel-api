package com.roottronics.travelcore.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<BaseResponse<DashboardResponse>> getDashboard() {

        DashboardResponse response =
                dashboardService.getDashboardData();

        BaseResponse<DashboardResponse> baseResponse =
                new BaseResponse<>(
                        true,
                        "Dashboard data fetched successfully.",
                        response
                );

        return ResponseEntity.ok(baseResponse);
    }
}