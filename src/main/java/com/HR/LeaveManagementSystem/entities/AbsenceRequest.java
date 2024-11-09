package com.HR.LeaveManagementSystem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;


@Entity
@Table(name="absence_request")
//@jakarta.persistence.Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AbsenceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int emp_id;
    private int absence_type_id;
    private String startDate;
    private String endDate;
    private String is_approved;
    private String manager;
}
