package com.HR.LeaveManagementSystem.controllers;

import com.HR.LeaveManagementSystem.payloads.*;
import com.HR.LeaveManagementSystem.services.AbsenceRequestService;
import com.HR.LeaveManagementSystem.services.AbsenceTypeService;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/absenceRequest")
@CrossOrigin(origins="*")
public class AbsenceRequestController {

    @Autowired
    private AbsenceRequestService absenceRequestService;

    @Autowired
    private AbsenceTypeService absenceTypeService;

    @Autowired
    private OllamaChatModel ollamaChatModel;


    @PostMapping("/")
    public ResponseEntity<AbsenceRequestDto> createAbsenceRequest(@RequestBody AbsenceRequestDto absenceRequestDto){
        AbsenceRequestDto savedAbsenceRequestDto = this.absenceRequestService.createAbsenceRequest(absenceRequestDto);
        return new ResponseEntity<>(savedAbsenceRequestDto, HttpStatus.CREATED);
    }

    @PostMapping ("/{absenceRequestId}")
    public ResponseEntity<AbsenceRequestDto> updateAbsenceRequest(@RequestBody AbsenceRequestDto absenceRequestDto ,@PathVariable Integer absenceRequestId){
        AbsenceRequestDto updatedAbsenceRequestDto = this.absenceRequestService.updateAbsenceRequest(absenceRequestDto,absenceRequestId);
        return new ResponseEntity<>(updatedAbsenceRequestDto,HttpStatus.OK);
    }

    @GetMapping("/{absenceRequestId}")
    public ResponseEntity<AbsenceRequestDto> getAbsenceRequestById(@PathVariable Integer absenceRequestId){
        AbsenceRequestDto absenceRequestDto =  this.absenceRequestService.getAbsenceRequestById(absenceRequestId);
        return new ResponseEntity<>(absenceRequestDto,HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EventDto>> getAbsenceRequestByEmpId(@PathVariable Integer employeeId){

        List<AbsenceRequestDto> absenceRequestDtos =  this.absenceRequestService.getAbsenceRequestByEmpId(employeeId);

        List<EventDto> eventDtos = absenceRequestDtos.stream().map(absenceRequestDto -> {
            AbsenceTypeDto absenceTypeDto = absenceTypeService.getAbsenceType(absenceRequestDto.getAbsence_type_id());
            EventDto event = new EventDto();
            event.setId(absenceRequestDto.getId());
            event.setStart(absenceRequestDto.getStartDate());
            event.setEnd(absenceRequestDto.getEndDate());
            event.setStatus(absenceRequestDto.getIs_approved());

            if(!event.getStatus().equals("noAction")) {
                event.setBackgroundColor("#AFE1AF");
                event.setTitle(absenceTypeDto.getName()+ " - Approved");
            }
            else {
                event.setBackgroundColor("#ADD8E6");
                event.setTitle(absenceTypeDto.getName() + " - Pending for Approval");
            }

            return event;
        }).toList();


        return new ResponseEntity<>(eventDtos,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AbsenceRequestDto>> getAbsenceRequests(){
        List<AbsenceRequestDto> absenceDtos =  this.absenceRequestService.getAllRequest();
        return new ResponseEntity<>(absenceDtos,HttpStatus.OK);
    }

    @GetMapping("/manager/{manager}")
    public ResponseEntity<List<AbsenceRequestDto>> getAbsenceRequests(@PathVariable String manager){
        List<AbsenceRequestDto> absenceDtos =  this.absenceRequestService.getAllRequestRequiredApproval(manager);
        return new ResponseEntity<>(absenceDtos,HttpStatus.OK);
    }

    @GetMapping("/ai/generate/{message}")
    public String generate(@PathVariable String message) {
        return ollamaChatModel.call(message);
    }

    @DeleteMapping("/{absenceRequestId}")
    public ResponseEntity<ApiResponse> deleteAbsenceType(@PathVariable Integer absenceRequestId){
        this.absenceRequestService.deleteAbsenceRequest(absenceRequestId);
        return new ResponseEntity<>(new ApiResponse("Absence Request is deleted successfully",true),HttpStatus.OK);
    }

}
