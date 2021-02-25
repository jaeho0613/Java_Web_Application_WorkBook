package spms.controls;

import java.util.Map;
import spms.dao.MySqlMemberDao;

public class MemberDeleteController implements Controller {
	
	MySqlMemberDao memberDao;

	public MemberDeleteController setMemberDao(MySqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		int no = (int) model.get("no");

		memberDao.delete(no);

		return "redirect:list.do";
	}
}
