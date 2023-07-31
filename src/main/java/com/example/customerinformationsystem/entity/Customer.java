package com.example.customerinformationsystem.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Table
        (name = "customer_tbl",
                uniqueConstraints = {
                        @UniqueConstraint(
                                name = "username",
                                columnNames = "username"
                        ),
                        @UniqueConstraint(
                                name = "email",
                                columnNames = "email_address")}
        )

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @Email
    @Column(name = "email_address")
    private String email;

    @Column(name = "username",
            nullable = false,
            length = 50)
    private String userName;

    @Column(length = 100)
    private String password;

}