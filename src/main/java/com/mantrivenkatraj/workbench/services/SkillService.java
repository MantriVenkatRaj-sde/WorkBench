package com.mantrivenkatraj.workbench.services;

import com.mantrivenkatraj.workbench.entities.Skill;
import com.mantrivenkatraj.workbench.exceptions.EntityNotFoundException;
import com.mantrivenkatraj.workbench.repositories.SkillRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public @Nullable List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public @Nullable List<Skill> getAllActiveSkills() {
        return skillRepository.findAll().stream().filter(Skill::isActive).toList();
    }

    @Transactional
    public void addSkills(List<String> skillNames) {

        for (String name : skillNames) {

            String normalized = name.trim();

            skillRepository.findByNameIgnoreCase(normalized)
                    .orElseGet(() -> skillRepository.save(
                            Skill.builder().name(normalized).build()
                    ));
        }
    }

    public void deleteSkill(String skill) {
        Skill s=skillRepository.findByName(skill).orElseThrow(()->new EntityNotFoundException("Skill"));
        s.setActive(false);
    }
}
