package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Service;
import Service.ActionForward;
import Service.MemberService.EditMyProfileService;
import Service.MemberService.SignUpService;

@WebServlet("*.myprofile") //��� ���� �̰ɷγ����¾ֵ� ����û�ұ⸶�� �ٹ���.
public class EditMyProfileController extends HttpServlet{
	ActionForward nextAction = null; 
	Service action = null;
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); 
		int Index = requestURI.lastIndexOf("/") + 1; 
		String requestPage = requestURI.substring(Index); 
		
		System.out.println("Controller ���� " + requestPage);
		
		try {
			if(requestPage.equals("Read.myprofile")) { //ȸ������ �޾ƿ��� (����)
				action = new EditMyProfileService();
				nextAction = ((EditMyProfileService)action).ReadMyProfile(request, response);
				
			}
			
			else if(requestPage.equals("EditView.myprofile")) { //ȸ������ �޾ƿ��� (��)
				nextAction = new ActionForward();
				nextAction.setNextPath("/Member/EditView.jsp");
				nextAction.setRedirect(false);
			}
			else if(requestPage.equals("Check.myprofile")) { //ȸ������ �޾ƿ��� (��)
				nextAction = new ActionForward();
				nextAction.setNextPath("index.jsp"); //�´� ȸ�� ��� �ٲ����.
				nextAction.setRedirect(false);
			}
			else if(requestPage.equals("Update.myprofile")) { //ȸ������ �޾ƿ��� (��)
				action = new EditMyProfileService();
				nextAction = ((EditMyProfileService)action).UpdateMyProfile(request, response);
			}
			else if(requestPage.equals("Delete.myprofile")) { //ȸ������ �޾ƿ��� (��)
				action = new EditMyProfileService();
				nextAction = ((EditMyProfileService)action).ReadMyProfile(request, response);
			}
			else if(requestPage.equals("Result.myprofile")) { //ȸ������ �޾ƿ��� (��)
				nextAction = new ActionForward();
				nextAction.setNextPath("/Member/index.jsp");  //�´� ȸ�� ��� �ٲ����.
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
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
