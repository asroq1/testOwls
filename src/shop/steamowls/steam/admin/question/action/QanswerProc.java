package shop.steamowls.steam.admin.question.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.steamowls.common.Action;
import shop.steamowls.common.ActionForward;
import shop.steamowls.common.LoginManager;
import shop.steamowls.common.Pagenation;
import shop.steamowls.steam.admin.question.service.QuestionService;
import shop.steamowls.steam.mypage.service.MypageService;
import shop.steamowls.steam.mypage.vo.BoardVo;
import shop.steamowls.steam.mypage.vo.MypageVo;

public class QanswerProc implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String member_sq = lm.getMemberSq(session);

		if (member_sq == null || member_sq.equals("")) {
			ActionForward forward = new ActionForward();
			forward.setPath("/");
			forward.setRedirect(true);
			return forward;
		}

		String board_sq = request.getParameter("board_sq");
		if (board_sq == null || board_sq.equals("")) {
			ActionForward forward = new ActionForward();
			forward.setPath("/");
			forward.setRedirect(true);
			return forward;
		}
		
		String answer_content = request.getParameter("answer_content");
		if(answer_content == null || answer_content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('답글 내용을 입력해주세요.'); history.back();</script>");
			out.close();
			return null;
		}
		
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_sq(Integer.parseInt(board_sq));
		boardVo.setAnswer_content(answer_content);
		
		QuestionService svc = new QuestionService();
		if(!svc.qRegisterAnswer(boardVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('잘못된 접근입니다.'); history.back();</script>");
			out.close();
			return null;
		}		
		
		BoardVo vo = svc.qDetail(board_sq);
		request.setAttribute("boardVo", vo);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/Qdetail.jsp");
		return forward;
	}
}
