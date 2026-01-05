package com.mantrivenkatraj.workbench.dtos;

import com.mantrivenkatraj.workbench.entities.Member;
import com.mantrivenkatraj.workbench.entities.Project;
import com.mantrivenkatraj.workbench.entities.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String displayName;
    private String bio;
    private String primaryInterest;
    private Member.Gender gender;
    private Member.Rank rank;
    private Member.VisibilityStatus visibilityStatus;
}