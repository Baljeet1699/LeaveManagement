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
public class EventDto {

    private int id;
    private String title;
    private String start;
    private String end;
    private String status;
    private String backgroundColor;

}
