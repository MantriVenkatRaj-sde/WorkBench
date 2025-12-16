package com.mantrivenkatraj.workbench.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reflection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each reflection belongs to one project
    @OneToOne
    @JoinColumn(name = "project_id", nullable = false, unique = true)
    private Project project;

    // Who wrote the reflection
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String projectGoal;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String reasonForProject;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String approach;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String challenges;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String keyLearning;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String improvementsIfRedo;

    @Column(columnDefinition = "TEXT")
    private String skillUsageExplanation;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}

