package com.nhnacademy.jdbc.board.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Likes")
public class Like {

    @EmbeddedId
    private Pk pk;

    @Column(name = "liked")
    private String liked;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "post_id")
        private Long postId;
    }
}
