package com.mantrivenkatraj.workbench.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skills",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;
}
