package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Action;
import Service.ActionForward;
import Service.MemberService.SignUpService;

@WebServlet("*.signup") //��� ���� �̰ɷγ����¾ֵ� ����û�ұ⸶�� �ٹ���.

public class SignUpController extends HttpServlet{
	ActionForward nextAction = null; 
	Action action = null;
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); //��û�� uri �ּ�
		int Index = requestURI.lastIndexOf("/") + 1; //�ڿ� ����
		String requestPage = requestURI.substring(Index); // "xxx.signup" �ø� ����
		
		System.out.println("Controller ���� " + requestPage);
		System.out.println("�̰� ����");
		
		try {
			if(requestPage.equals("signUpView.signup")) { //ȸ������ â��û
				nextAction = new ActionForward();
				nextAction.setNextPath("signUpView.jsp");
				nextAction.setRedirect(false);
			}
			
			else if(requestPage.equals("CheckIdLogic.signup")) { //���̵� Ȯ��
				action = new SignUpService();
				nextAction = ((SignUpService)action).CheckID(request, response);
			}
			
			else if(requestPage.equals("signUpLogic.signup")) { //ȸ������ ����
				action = new SignUpService();
				nextAction = action.execute(request, response);
			}
			
			
			if(nextAction != null) { //�����̷�Ʈ ������� nextPath
				if(nextAction.isRedirect()) {
					response.sendRedirect(nextAction.getNextPath()); // nextPath �� redirect
				} else { //forward������� nextpath
					request.getRequestDispatcher(nextAction.getNextPath()).forward(request, response);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
