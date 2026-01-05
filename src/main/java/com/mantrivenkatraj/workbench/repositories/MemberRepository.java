package com.mantrivenkatraj.workbench.repositories;

import com.mantrivenkatraj.workbench.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByUser_Username(String username);
}
