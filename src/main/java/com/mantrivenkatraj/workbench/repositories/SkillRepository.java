package com.mantrivenkatraj.workbench.repositories;

import com.mantrivenkatraj.workbench.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    Optional<Skill> findByName(String name);

    Optional<Skill> findByNameIgnoreCase(String normalized);
}
