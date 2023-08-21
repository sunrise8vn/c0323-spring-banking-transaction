package com.cg.model.dto;

import com.cg.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DepositResDTO {

    private Long id;
    private BigDecimal transactionAmount;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone="Asia/Ho_Chi_Minh")
    private Date createdAt;

    @JsonFormat(pattern = "HH:mm:ss", timezone="Asia/Ho_Chi_Minh")
    private Date createdOn;
}
