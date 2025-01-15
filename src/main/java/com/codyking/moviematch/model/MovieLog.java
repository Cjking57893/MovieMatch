package com.codyking.moviematch.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie_logs")
public class MovieLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "tmdb_id", nullable = false)
    private long tmdbId;

    @Column(name = "log_date")
    private LocalDateTime logDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "rating")
    private BigDecimal rating;

    public enum Status {
        watched,
        want_to_watch
    }
}
