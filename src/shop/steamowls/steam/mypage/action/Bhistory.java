package shop.steamowls.steam.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.steamowls.common.Action;
import shop.steamowls.common.ActionForward;

public class Bhistory implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/Bhistory.jsp");
		return forward;
	}
}
