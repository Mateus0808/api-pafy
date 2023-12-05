package com.atomic.pafyplatform.request;

import java.math.BigDecimal;
import java.util.Date;

import com.atomic.pafyplatform.models.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateTransactionDTO {
	@NotBlank
    private String title;
    @Positive
    @NotNull
    private BigDecimal value;
    @NotBlank
    private String category;
    @NotNull
    private TransactionType transactionType;
    @NotNull
    private Date createdAt;
}
