package com.mantrivenkatraj.workbench.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Size(max = 1000)
    private String description;

    private String gitHubLink;
    private String deployedLink;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status = ProjectStatus.DRAFT;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Member owner;

    public enum ProjectStatus {
        DRAFT,
        COMPLETED
    }
}
