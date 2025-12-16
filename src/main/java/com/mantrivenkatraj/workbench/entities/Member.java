package com.mantrivenkatraj.workbench.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @NotEmpty
    @Column(nullable = false)
    private String displayName;

    @Size(max = 500)
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rank rank = Rank.BEGINNER;

    private String primaryInterest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisibilityStatus visibilityStatus = VisibilityStatus.PUBLIC;

    @Column(nullable = false)
    private LocalDateTime joinedAt;

    public enum Rank {
        BEGINNER,
        INTERMEDIATE,
        PROJECT_READY
    }

    public enum VisibilityStatus {
        PUBLIC,
        PRIVATE
    }
}
