package com.mantrivenkatraj.workbench.repositories;

import com.mantrivenkatraj.workbench.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
}
