package ttechlab.bo.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import ttechlab.bo.MemberService;
import ttechlab.dao.MemberDao;
import ttechlab.entity.Member;
@Service
public class MemberServiceimpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		return memberDao.findAll();
	}

	@Override
	public Member getMemberById(Long id) {
		// TODO Auto-generated method stub
		return memberDao.findById(id).get();
	}

	@Override
	public Member saveMember(Member member) {
		// TODO Auto-generated method stub
		return memberDao.save(member);
	}

	@Override
	public void deleteMember(Long id) {
		memberDao.deleteById(id);
		
	}

}