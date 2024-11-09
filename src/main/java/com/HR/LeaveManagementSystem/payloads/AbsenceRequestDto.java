package com.HR.LeaveManagementSystem.payloads;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@jakarta.persistence.Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AbsenceRequestDto {
    private int id;
    private int emp_id;
    private int absence_type_id;
    private String startDate;
    private String endDate;
    private String is_approved;
    private String manager;
}
