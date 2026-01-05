package com.mantrivenkatraj.workbench.resources;

import com.mantrivenkatraj.workbench.entities.Skill;
import com.mantrivenkatraj.workbench.services.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillResource {
    private final SkillService skillService;

    public SkillResource(SkillService skillService) {
        this.skillService = skillService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allSkills")
    public ResponseEntity<List<Skill>> getAllSkills(){
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @GetMapping("/allActiveSkills")
    public ResponseEntity<List<Skill>> getAllActiveSkills(){
        return ResponseEntity.ok(skillService.getAllActiveSkills());
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/addSkills")
    public ResponseEntity<String> addSkills(@RequestBody List<String> skills){
        skillService.addSkills(skills);
        return ResponseEntity.ok("All the skills added successfully");
    }

    @PreAuthorize("hasRole('SCOPE_ADMIN')")
    @DeleteMapping("/{skill}/deleteSkill")
    public ResponseEntity<String> deleteSkill(@PathVariable String skill) {
        skillService.deleteSkill(skill);
        return ResponseEntity.ok("Skill deleted successfully!");
    } // admin only
}
