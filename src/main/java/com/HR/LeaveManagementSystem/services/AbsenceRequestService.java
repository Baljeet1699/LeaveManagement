package com.HR.LeaveManagementSystem.services;
import com.HR.LeaveManagementSystem.payloads.AbsenceRequestDto;
import com.HR.LeaveManagementSystem.payloads.EmployeeDto;

import java.util.List;

public interface AbsenceRequestService {

    AbsenceRequestDto createAbsenceRequest(AbsenceRequestDto absenceRequestDto);
    AbsenceRequestDto updateAbsenceRequest(AbsenceRequestDto absenceRequestDto,Integer absenceRequestId);
    void deleteAbsenceRequest(Integer absenceRequestId);
    AbsenceRequestDto getAbsenceRequestById(Integer absenceRequestId);
    List<AbsenceRequestDto> getAllRequest();
    List<AbsenceRequestDto> getAllRequestRequiredApproval(String manager);
    List<AbsenceRequestDto> getAbsenceRequestByEmpId(Integer employeeId);

}
