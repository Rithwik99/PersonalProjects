package ttechlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ttechlab.entity.Member;

public interface MemberDao extends JpaRepository<Member, Long> {

}
