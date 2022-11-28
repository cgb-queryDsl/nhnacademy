package com.nhnacademy.jdbc.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Uuid")
public class Uuid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "uuid_value")
    private String uuidValue;
}
