package com.mantrivenkatraj.workbench.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, updatable = true)
    private String displayName;

    @Size(max = 5000)
    @Column(nullable = true, updatable = true)
    private String bio;

     @OneToMany(
        mappedBy = "member",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @Builder.Default
    private List<MemberSkill> memberSkills = new ArrayList<>();

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Project> projects = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Rank rank = Rank.BEGINNER;

    @Column(nullable = true)
    private String primaryInterest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = true)
    @Builder.Default
    private VisibilityStatus visibilityStatus = VisibilityStatus.PUBLIC;

    @CreationTimestamp  // ✅ Use this instead of manual setting
    @Column(nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;


    public enum Rank {
        BEGINNER,
        INTERMEDIATE,
        PROJECT_READY
    }

    public enum VisibilityStatus {
        PUBLIC,
        PRIVATE
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

}