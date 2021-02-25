package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

public class LogInController implements Controller, DataBinding {

	MemberDao memberDao;

	public LogInController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member memberInfo = (Member) model.get("memberInfo");

		if (memberInfo.getEmail() == null) { // 입력폼을 요청할 때
			return "/auth/LogInForm.jsp";

		} else { // 회원 등록을 요청할 때
			Member member = memberDao.exist(memberInfo.getEmail(), memberInfo.getPwd());

			if (member != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.do";
			} else {
				return "/auth/LogInFail.jsp";
			}
		}
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "memberInfo", spms.vo.Member.class };
	}
}
