package com.HR.LeaveManagementSystem.controllers;

import com.HR.LeaveManagementSystem.payloads.AbsenceTypeDto;
import com.HR.LeaveManagementSystem.payloads.ApiResponse;
import com.HR.LeaveManagementSystem.services.AbsenceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/absenceType")
@CrossOrigin(origins="*")
public class AbsenceTypeSetupController {

    @Autowired
    private AbsenceTypeService absenceTypeService;

    @PostMapping("/")
    public ResponseEntity<AbsenceTypeDto> createAbsenceType(@RequestBody AbsenceTypeDto absenceTypeDto){
        AbsenceTypeDto savedAbsenceTypeDto = this.absenceTypeService.createAbsenceType(absenceTypeDto);
        return new ResponseEntity<>(savedAbsenceTypeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{absenceTypeId}")
    public ResponseEntity<AbsenceTypeDto> updateAbsenceType(@RequestBody AbsenceTypeDto absenceTypeDto ,@PathVariable Integer absenceTypeId){
        AbsenceTypeDto updatedAbsenceTypeDto = this.absenceTypeService.updateAbsenceType(absenceTypeDto,absenceTypeId);
        return new ResponseEntity<>(updatedAbsenceTypeDto,HttpStatus.OK);
    }

    @GetMapping("/{absenceTypeId}")
    public ResponseEntity<AbsenceTypeDto> getAbsenceTypeById(@PathVariable Integer absenceTypeId){
        AbsenceTypeDto absenceTypeDto =  this.absenceTypeService.getAbsenceType(absenceTypeId);
        return new ResponseEntity<>(absenceTypeDto,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AbsenceTypeDto>> getAbsenceTypes(){
       List<AbsenceTypeDto> absenceTypeDtos =  this.absenceTypeService.getAbsenceTypes();
       return new ResponseEntity<>(absenceTypeDtos,HttpStatus.OK);
    }

    @DeleteMapping("/{absenceTypeId}")
    public ResponseEntity<ApiResponse> deleteAbsenceType(@PathVariable Integer absenceTypeId){
        this.absenceTypeService.deleteAbsenceType(absenceTypeId);
        return new ResponseEntity<>(new ApiResponse("Absence type is deleted successfully",true),HttpStatus.OK);
    }


}
