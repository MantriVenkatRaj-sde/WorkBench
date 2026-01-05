package com.mantrivenkatraj.workbench.repositories;

import com.mantrivenkatraj.workbench.entities.MemberSkill;
import com.mantrivenkatraj.workbench.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberSkillRepository extends JpaRepository<MemberSkill,Long> {
    List<Skill> findByMember_User_Username(String username);
}
