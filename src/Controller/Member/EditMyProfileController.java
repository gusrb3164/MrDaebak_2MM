package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.MemberService.EditMyProfileService;

@WebServlet("*.myprofile") //��� ���� �̰ɷγ����¾ֵ� ����û�ұ⸶�� �ٹ���.
public class EditMyProfileController extends HttpServlet{
	ActionForward nextAction = new ActionForward();
	EditMyProfileService service = new EditMyProfileService();
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI(); 
		int Index = requestURI.lastIndexOf("/") + 1; 
		String requestPage = requestURI.substring(Index); 
		
		//System.out.println("Controller ���� " + requestPage);
		
		try {
			if(requestPage.equals("Read.myprofile")) { //ȸ������ �޾ƿ��� (����)
				service = new EditMyProfileService();
				nextAction = service.ReadMyProfile(request, response);
			}
			else if(requestPage.equals("EditView.myprofile")) { //�������� �޾ƿ��� (��)
				nextAction.setNextPath("/Member/EditView.jsp");
				nextAction.setRedirect(false);
			}
			else if(requestPage.equals("Check.myprofile")) { //ȸ������ �޾ƿ��� (��)
				nextAction.setNextPath("/MainView.jsp"); //�´� ȸ�� ��� �ٲ����.
				nextAction.setRedirect(false);
			}
			else if(requestPage.equals("Update.myprofile")) { //ȸ������ ������Ʈ
				nextAction = service.UpdateMyProfile(request, response);
			}
			else if(requestPage.equals("Delete.myprofile")) { //ȸ������ ����
				nextAction = service.DeleteMyProfile(request, response);
			}
			else if(requestPage.equals("Result.myprofile")) { //���
				nextAction.setNextPath("/MainView.jsp"); 
				nextAction.setRedirect(false);
			}
			
			if(nextAction != null) { 
				if(nextAction.isRedirect()) {
					response.sendRedirect(nextAction.getNextPath());
				} else {
					request.getRequestDispatcher(nextAction.getNextPath()).forward(request, response);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
