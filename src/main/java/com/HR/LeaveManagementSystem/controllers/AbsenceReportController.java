package com.HR.LeaveManagementSystem.controllers;

import com.HR.LeaveManagementSystem.payloads.AbsenceRequestDto;
import com.HR.LeaveManagementSystem.payloads.ApiResponse;
import com.HR.LeaveManagementSystem.payloads.EventDto;
import com.HR.LeaveManagementSystem.services.AbsenceRequestService;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/absenceReport")
@CrossOrigin(origins="*")
public class AbsenceReportController {

    @Autowired
    private AbsenceRequestService absenceRequestService;

//    private HttpSession session;

    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> exportToExcel(@PathVariable Integer employeeId,HttpSession session){

        List<AbsenceRequestDto> absenceRequestDtos =  this.absenceRequestService.getAbsenceRequestByEmpId(employeeId);

        this.export(absenceRequestDtos,session);

        return new ResponseEntity<>(new ApiResponse("Absence Report is generated successfully",true), HttpStatus.OK);
    }

    private void export(List<AbsenceRequestDto> absenceRequestDtos, HttpSession session) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reports");

        writeHeaderLine(sheet);

        writeDataLines(absenceRequestDtos, workbook, sheet);

//        String excelFilePath = session.getServletContext().getRealPath("/")+"WEB-INF"+ File.separator+"resources"+File.separator+"image"+File.separator+"Reports-export.xlsx";

        String excelFilePath = "C:\\Users\\immor\\Desktop\\exportedFile"+File.separator+"Reports-export.xlsx";

        try{
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
        }catch(IOException ex){
            ex.getStackTrace();
        };
    }

    private void writeDataLines(List<AbsenceRequestDto> absenceRequestDtos, XSSFWorkbook workbook, XSSFSheet sheet) {

        int rowCount = 1;

        for(AbsenceRequestDto reportData : absenceRequestDtos) {

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(reportData.getAbsence_type_id());

            cell = row.createCell(columnCount++);
            cell.setCellValue(reportData.getStartDate());

            cell = row.createCell(columnCount++);
            cell.setCellValue(reportData.getEndDate());

        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Absence Type Id");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Start Date");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("End Date");

    }



}
