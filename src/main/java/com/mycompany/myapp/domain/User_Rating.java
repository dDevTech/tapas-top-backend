package com.mycompany.myapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "jhi_user_rating")
public class User_Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
}
