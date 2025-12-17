package com.mantrivenkatraj.workbench.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_resources")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceType resourceType;

    @Column(nullable = false)
    private String title;
    // Example: "Spring Boot JPA Mapping Tutorial"

    @Column(columnDefinition = "TEXT")
    private String link;
    // URL if applicable

    @Column(columnDefinition = "TEXT")
    private String description;
    // How this resource helped

    public enum ResourceType {
        TUTORIAL,
        DOCUMENTATION,
        BLOG,
        VIDEO,
        STACK_OVERFLOW,
        FORUM,
        MENTOR,
        COURSE,
        OTHER
    }
}
