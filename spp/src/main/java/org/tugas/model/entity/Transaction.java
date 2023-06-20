package org.tugas.model.entity;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Integer id;
    private String nim;
    private String codeTransaction;
    private Integer semester;
    private Double amount;
    private Date paidOfAt;
}
