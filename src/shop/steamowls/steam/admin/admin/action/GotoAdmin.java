package shop.steamowls.steam.admin.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.steamowls.common.Action;
import shop.steamowls.common.ActionForward;

public class GotoAdmin implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/gotoAdmin.jsp");
		return forward;
	}
}
