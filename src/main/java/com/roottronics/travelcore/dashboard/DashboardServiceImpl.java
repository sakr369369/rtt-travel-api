package com.roottronics.travelcore.dashboard;

import com.roottronics.travelcore.candidate.repository.CandidateRepository;
import com.roottronics.travelcore.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CompanyRepository companyRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public DashboardResponse getDashboardData() {



        long totalCompanies = companyRepository.count();

        long totalCandidates = candidateRepository.count();

        return new DashboardResponse(
                totalCompanies,
                totalCandidates
        );
    }
}