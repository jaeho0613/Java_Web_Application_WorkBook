package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

public class LogInController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		MemberDao memberDao = (MemberDao) model.get("memberDao");
		if (model.get("info") != null) {
			Member memberInfo = (Member) model.get("info");

			Member member = memberDao.exist(memberInfo.getEmail(), memberInfo.getPwd());

			if (member != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.do";
			} else {
				return "/auth/LogInFail.jsp";
			}
		} else {
			return "/auth/LogInForm.jsp";
		}
	}
}