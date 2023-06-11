package com.itg.autopart.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="creditcards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="card_owner")
    private String cardOwner;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="validity_date")
    private String validityDate;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

}
