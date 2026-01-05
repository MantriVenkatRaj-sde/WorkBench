package com.mantrivenkatraj.workbench.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "project_skills",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_project_skill",
                        columnNames = {"project_id", "skill_id"}
                )
        })
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(columnDefinition = "TEXT")
    @Size(max = 300)
    private String usageDescription;
}
