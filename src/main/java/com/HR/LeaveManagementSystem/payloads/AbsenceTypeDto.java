package com.HR.LeaveManagementSystem.payloads;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@jakarta.persistence.Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AbsenceTypeDto {

    private int id;
    private String name;
    private int type;
    private int category;
    private int approval_flow;
    private String description;

}
