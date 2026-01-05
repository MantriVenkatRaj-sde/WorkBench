package com.mantrivenkatraj.workbench.services;

import com.mantrivenkatraj.workbench.entities.Member;
import com.mantrivenkatraj.workbench.entities.MemberSkill;
import com.mantrivenkatraj.workbench.entities.Skill;
import com.mantrivenkatraj.workbench.entities.User;
import com.mantrivenkatraj.workbench.exceptions.EntityNotFoundException;
import com.mantrivenkatraj.workbench.repositories.MemberRepository;
import com.mantrivenkatraj.workbench.repositories.SkillRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;


    public MemberService(MemberRepository memberRepository, SkillRepository skillRepository) {
        this.memberRepository = memberRepository;
        this.skillRepository = skillRepository;
    }

    public void registerMember(User user, Member.Gender gender) {
        Member member = Member.builder()
                .displayName(user.getUsername())
                .user(user)
                .gender(gender)
                .build();
        memberRepository.save(member);
    }
    //To update a specific field
    @Transactional
    public void updateField(String username, String field, String value) {
        Member member = memberRepository.findByUser_Username(username);

        if (member == null) {
            throw new EntityNotFoundException("Member not found for user: " + username);
        }

        // Update the specific field
        switch (field.toLowerCase()) {
            case "bio":
                member.setBio(value);
                break;
            case "primaryinterest":
                member.setPrimaryInterest(value);
                break;
            case "displayname":
                member.setDisplayName(value);
                break;
            case "gender":
                member.setGender(Member.Gender.valueOf(value.toUpperCase()));
                break;
            case "visibilitystatus":
                member.setVisibilityStatus(Member.VisibilityStatus.valueOf(value.toUpperCase()));
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }

        memberRepository.save(member);
    }

    @Transactional
    public Member updateMultipleFields(String username, Member updates) {
        Member member = memberRepository.findByUser_Username(username);

        if (member == null) {
            throw new EntityNotFoundException("Member not found for user: " + username);
        }

        // Only update non-null fields
        if (updates.getBio() != null) {
            member.setBio(updates.getBio());
        }
        if (updates.getPrimaryInterest() != null) {
            member.setPrimaryInterest(updates.getPrimaryInterest());
        }
        if (updates.getDisplayName() != null) {
            member.setDisplayName(updates.getDisplayName());
        }
        if (updates.getGender() != null) {
            member.setGender(updates.getGender());
        }
        if (updates.getVisibilityStatus() != null) {
            member.setVisibilityStatus(updates.getVisibilityStatus());
        }

        return memberRepository.save(member);
    }

    //Associating a new skill to the user/member
    @Transactional
    public void addSkill(String username,String skill) {
        Member member=memberRepository.findByUser_Username(username);

        Skill skill1=skillRepository.findByName(skill).orElseThrow(() -> new EntityNotFoundException("Skill"));

        boolean alreadyExists = member.getMemberSkills().stream()
                .anyMatch(ms -> ms.getSkill().equals(skill));

        if (alreadyExists) return;

        MemberSkill memberSkill = MemberSkill.builder()
                .member(member)
                .skill(skill1)
                .build();

        member.getMemberSkills().add(memberSkill);
    };
}
