package com.mantrivenkatraj.workbench.resources;

import com.mantrivenkatraj.workbench.dtos.FieldUpdateRequest;
import com.mantrivenkatraj.workbench.entities.Member;
import com.mantrivenkatraj.workbench.services.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@RestController
public class MemberResource {
    private final MemberService memberService;

    public MemberResource(MemberService memberService) {
        this.memberService = memberService;
    }

    // Approach 1: Single field update with path variable
    @PatchMapping("/{username}/field/{fieldName}/update")
    public ResponseEntity<String> updateField(
            @PathVariable String username,
            @PathVariable String fieldName,
            @RequestBody String value,
            Authentication authentication) {

        // Security check: ensure user can only update their own profile
        if (!authentication.getName().equals(username)) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        memberService.updateField(username, fieldName, value);
        return ResponseEntity.ok("Field updated successfully");
    }

    // Approach 2: Single field update with request body
    @PatchMapping("/{username}/update")
    public ResponseEntity<String> updateFieldWithBody(
            @PathVariable String username,
            @RequestBody FieldUpdateRequest request,
            Authentication authentication) {

        if (!authentication.getName().equals(username)) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        memberService.updateField(username, request.getField(), request.getValue());
        return ResponseEntity.ok("Field updated successfully");
    }

    // Approach 3: Bulk update (update multiple fields at once)
    @PatchMapping("/{username}")
    public ResponseEntity<Member> updateMultipleFields(
            @PathVariable String username,
            @RequestBody Member updates,
            Authentication authentication) {

        if (!authentication.getName().equals(username)) {
            return ResponseEntity.status(403).build();
        }

        Member updated = memberService.updateMultipleFields(username, updates);
        return ResponseEntity.ok(updated);
    }

    //Add a skill to Member
    @PostMapping("/member/{username}/{skill}/addSkill")
    public ResponseEntity<String> addSkill(@PathVariable String username,@PathVariable String skill){
        memberService.addSkill(username,skill);
        return ResponseEntity.ok("Skill added successfully");
    }
}
