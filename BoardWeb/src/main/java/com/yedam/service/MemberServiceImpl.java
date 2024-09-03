package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;
import com.yedam.vo.MemberVO;

// 기능의 구현.
public class MemberServiceImpl implements MemberService {

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

	@Override
	public List<MemberVO> getMembers() {
		return mapper.memberList();
	}

	@Override
	public boolean addMember(MemberVO member) {
		if(mapper.selectMember(member.getMemberId())!=null) {
			return false;
		}
		return mapper.insertMember(member)==1;
	}

	@Override
	public boolean removeMember(String memberId) {
		
		return mapper.deleteMember(memberId)==1;
	}

	@Override
	public boolean modifyMember(MemberVO member) {
		return mapper.updateMember(member)==1;
	}

	@Override
	public MemberVO getMember(String memberId) {
		return mapper.selectMember(memberId);
	}

}
