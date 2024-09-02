package ttechlab.bo;

import java.util.List;

import ttechlab.entity.Member;

public interface MemberService {
	
	List<Member> getAllMembers();
	Member getMemberById(Long id);
	Member saveMember(Member member);
	void deleteMember(Long id);

}
