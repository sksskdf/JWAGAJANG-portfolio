package member.service;

import member.model.Member;

public interface MemberService {
	void add(Member member);
	String idCheck(String id);
	void update(Member member);
}
