package shop.steamowls.steam.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.steamowls.common.Action;
import shop.steamowls.common.ActionForward;
import shop.steamowls.steam.admin.action.Alogin;
import shop.steamowls.steam.admin.action.AloginProc;
import shop.steamowls.steam.admin.action.Answer;
import shop.steamowls.steam.admin.action.AnswerProc;
import shop.steamowls.steam.admin.action.BookingCancel;
import shop.steamowls.steam.admin.action.BookingManage;
import shop.steamowls.steam.admin.action.BookingManageProc;
import shop.steamowls.steam.admin.action.GotoAdmin;
import shop.steamowls.steam.admin.action.GotoAdminProc;
import shop.steamowls.steam.admin.action.MemberManage;
import shop.steamowls.steam.admin.action.MemberManageProc;
import shop.steamowls.steam.admin.action.MemberModify;
import shop.steamowls.steam.admin.action.MemberModifyProc;
import shop.steamowls.steam.admin.action.ProductAdd;
import shop.steamowls.steam.admin.action.ProductAddProc;
import shop.steamowls.steam.admin.action.ProductDelete;
import shop.steamowls.steam.admin.action.ProductDeleteProc;
import shop.steamowls.steam.admin.action.ProductManage;
import shop.steamowls.steam.admin.action.ProductManageProc;
import shop.steamowls.steam.admin.action.ProductModify;
import shop.steamowls.steam.admin.action.ProductModifyProc;
import shop.steamowls.steam.admin.action.QuestionManage;
import shop.steamowls.steam.admin.action.QuestionManageProc;
import shop.steamowls.steam.admin.action.SalesManageProc;
import shop.steamowls.steam.admin.action.memberWithdraw;
import shop.steamowls.steam.admin.action.memberWithdrawProc;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/admin", "");

		ActionForward forward = null;

		if (command.equals("")) {
			Action action = new Alogin();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/AloginProc")) {
			Action action = new AloginProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/gotoAdmin")) {
			Action action = new GotoAdmin();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/gotoAdminProc")) {
			Action action = new GotoAdminProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/productManage")) {
			Action action = new ProductManage();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/productManageProc")) {
			Action action = new ProductManageProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/productModify")) {
			Action action = new ProductModify();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/productModifyProc")) {
			Action action = new ProductModifyProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/productDelete")) {
			Action action = new ProductDelete();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/producDeleteProc")) {
			Action action = new ProductDeleteProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/productAdd")) {
			Action action = new ProductAdd();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/producAddProc")) {
			Action action = new ProductAddProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookingManage")) {
			Action action = new BookingManage();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookingManageProc")) {
			Action action = new BookingManageProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookingCancel")) {
			Action action = new BookingCancel();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/salesMange")) {
			Action action = new SalesManageProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/salesMangeProc")) {
			Action action = new SalesManageProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/questionManage")) {
			Action action = new QuestionManage();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/questionManageProc")) {
			Action action = new QuestionManageProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/answer")) {
			Action action = new Answer();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/answerProc")) {
			Action action = new AnswerProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberManage")) {
			Action action = new MemberManage();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberManageProc")) {
			Action action = new MemberManageProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberModify")) {
			Action action = new MemberModify();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberModifyProc")) {
			Action action = new MemberModifyProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberWithdraw")) {
			Action action = new memberWithdraw();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberWithdrawProc")) {
			Action action = new memberWithdrawProc();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				// 리다이렉트
				response.sendRedirect(forward.getPath());
			} else {
				// 디스패치
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
