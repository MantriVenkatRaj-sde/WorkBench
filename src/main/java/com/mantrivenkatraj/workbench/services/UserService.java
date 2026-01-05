package com.mantrivenkatraj.workbench.services;

import com.mantrivenkatraj.workbench.dtos.UserDTO;
import com.mantrivenkatraj.workbench.entities.Member;
import com.mantrivenkatraj.workbench.entities.User;
import com.mantrivenkatraj.workbench.repositories.MemberRepository;
import com.mantrivenkatraj.workbench.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    public UserService(UserRepository userRepository, MemberRepository memberRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public UserDTO getUserDetails(String username){
        User user=userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        Member member=memberRepository.findByUser_Username(username);
        return UserDTO.builder()
                .username(username)
                .email(user.getEmail())
                .displayName((member.getDisplayName()).isEmpty()? username:member.getDisplayName())
                .bio(member.getBio())
                .primaryInterest(member.getPrimaryInterest())
                .gender(member.getGender())
                .rank(member.getRank())
                .visibilityStatus(member.getVisibilityStatus())
                .build();
    }
}
