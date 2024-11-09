package com.HR.LeaveManagementSystem.services;

import com.HR.LeaveManagementSystem.payloads.AbsenceTypeDto;

import java.util.List;

public interface AbsenceTypeService {

    AbsenceTypeDto createAbsenceType(AbsenceTypeDto absenceTypeDto);

    AbsenceTypeDto updateAbsenceType(AbsenceTypeDto absenceTypeDto,int absenceTypeId);

    void deleteAbsenceType(int absenceTypeId);

    AbsenceTypeDto getAbsenceType(int absenceTypeId);

    List<AbsenceTypeDto> getAbsenceTypes();
}
