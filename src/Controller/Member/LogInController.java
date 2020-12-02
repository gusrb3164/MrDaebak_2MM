package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.MemberService.LogInService;

@WebServlet("*.login") //��� ���� �̰ɷγ����¾ֵ� ����û�ұ⸶�� �ٹ���.

public class LogInController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ActionForward nextAction = new ActionForward(); 
	private LogInService service = new LogInService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); //��û�� uri �ּ�
		int Index = requestURI.lastIndexOf("/") + 1; //�ڿ� ����
		String requestPage = requestURI.substring(Index); // "xxx.login" �ø� ����
		
		//System.out.println("Controller ���� " + requestPage);
		
		try {
			if(requestPage.equals("LoginView.login")) { //�α��� â��û
				nextAction = new ActionForward();
				nextAction.setNextPath("/Member/loginView.jsp");
				nextAction.setRedirect(false);
			}
			else if(requestPage.equals("Logic.login")) { //�α��� �� ��û
				nextAction = service.LogIn(request, response);
			}
			else if(requestPage.equals("Result.login")) { //ȸ�� â��û
				nextAction = new ActionForward();
				nextAction.setNextPath("/MainView.jsp");
				nextAction.setRedirect(false);
			}
			
			
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
			doGet(request, response);
	}
}
