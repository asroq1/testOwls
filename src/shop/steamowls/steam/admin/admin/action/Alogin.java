package shop.steamowls.steam.admin.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.steamowls.common.Action;
import shop.steamowls.common.ActionForward;
import shop.steamowls.common.LoginManager;

public class Alogin implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sq = lm.getMemberSq(session);
		
		if(sq != null) {
			ActionForward forward = new ActionForward();
			forward.setPath("/admin");
			forward.setRedirect(true);
			return forward;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/Alogin.jsp");
		return forward;
	}
}
