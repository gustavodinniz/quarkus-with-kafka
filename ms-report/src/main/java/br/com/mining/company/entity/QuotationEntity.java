package br.com.mining.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quotation_report")
public class QuotationEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @Column(name = "currency_price")
    private BigDecimal currencyPrice;
}
