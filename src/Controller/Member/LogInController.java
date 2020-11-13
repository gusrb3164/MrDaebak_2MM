package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.Action;
import Service.MemberService.LogInService;

@WebServlet("*.login") //��� ���� �̰ɷγ����¾ֵ� ����û�ұ⸶�� �ٹ���.

public class LogInController extends HttpServlet{
	ActionForward nextAction = null; 
	Action action = null;
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//URI���� ����ڰ� ��û�� ������ ����
		String requestURI = request.getRequestURI(); //��û�� uri �ּ�
		int Index = requestURI.lastIndexOf("/") + 1; //�ڿ� ����
		String requestPage = requestURI.substring(Index); // "xxx.login" �ø� ����
		
		System.out.println("Controller ���� " + requestPage);
		
		try {
			if(requestPage.equals("LoginView.login")) { //�α��� â��û
				nextAction = new ActionForward();
				nextAction.setNextPath("loginView.jsp");
				nextAction.setRedirect(false);
			}
			else if(requestPage.equals("Logic.login")) { //�α��� ���� ��û
				action = new LogInService();
				nextAction = action.execute(request, response);
			}
			else if(requestPage.equals("ResultView.login")) { //�α��� ��� â��û
				nextAction = new ActionForward();
				nextAction.setNextPath("loginResultView.jsp");
				nextAction.setRedirect(false);
			}
			
			System.out.println("���� ��� " + nextAction.getNextPath());
			System.out.println(request.getAttribute("member"));
			
			if(nextAction != null) { //�����̷�Ʈ ������� nextPath
				if(nextAction.isRedirect()) {
					response.sendRedirect(nextAction.getNextPath()); // nextPath �� redirect
				} else { //forward������� nextpath
					request.getRequestDispatcher(nextAction.getNextPath()).forward(request, response);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}