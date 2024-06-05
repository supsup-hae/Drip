package com.univ.drip.repository;

import com.univ.drip.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

  @Override
  Optional<Member> findById(String id);
}