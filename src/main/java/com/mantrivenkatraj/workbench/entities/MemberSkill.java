package com.mantrivenkatraj.workbench.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "memberskills")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "skill_id",nullable = false)
    private Skill skill;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String evidence;
}
