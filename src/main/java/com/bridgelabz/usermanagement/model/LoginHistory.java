package com.bridgelabz.usermanagement.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime loginTime;

    public LoginHistory(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LoginHistory() {
    }
}
