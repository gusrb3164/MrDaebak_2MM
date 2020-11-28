package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Service;
import Service.ActionForward;
import Service.MemberService.SignUpService;

@WebServlet("*.signup") //��� ���� �̰ɷγ����¾ֵ� ����û�ұ⸶�� �ٹ���.

public class SignUpController extends HttpServlet{
	private ActionForward nextAction = new ActionForward();
	private SignUpService service = new SignUpService();
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); //��û�� uri �ּ�
		int Index = requestURI.lastIndexOf("/") + 1; //�ڿ� ����
		String requestPage = requestURI.substring(Index); // "xxx.signup" �ø� ����
		
		System.out.println("Controller ���� " + requestPage);
		
		try {
			if(requestPage.equals("signUpView.signup")) { //ȸ������ â��û
				nextAction.setNextPath("/Member/signUpView.jsp");
				nextAction.setRedirect(false);
			}
			
			else if(requestPage.equals("CheckIdLogic.signup")) { //���̵� Ȯ��
				nextAction = service.CheckID(request, response);
			}
			
			else if(requestPage.equals("signUpLogic.signup")) { //ȸ������ ����
				nextAction = service.SignUp(request, response);
				
			}else if(requestPage.equals("Result.signup")) { //ȸ������ ����
				nextAction.setNextPath("MainView.jsp");
				nextAction.setRedirect(false);
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
			request.setAttribute("altmsg", "�������� ó�� �������� ����� �߻��Ͽ����ϴ�.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
