package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Service;
import Service.MemberService.ManageMemberService;
import Service.ActionForward;

@WebServlet("*.manageMem")

public class ManageMemberController extends HttpServlet{
	ActionForward nextAction  = new ActionForward();
	ManageMemberService service = new ManageMemberService();
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI(); //��û�� uri �ּ�
		int Index = requestURI.lastIndexOf("/") + 1; //�ڿ� ����
		String requestPage = requestURI.substring(Index); // "xxx.manageMem" �ø� ����
		System.out.println("Controller ���� " + requestPage);
		
		try {
			if(requestPage.equals("MemberList.manageMem")) { //ȸ�� ��� - DB��ȸ
				nextAction = service.ListMember(request, response);

			} else if(requestPage.equals("MemberListView.manageMem")) { //ȸ�� ��� - View
				nextAction.setNextPath("/Member/MemberListView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("MemberRead.manageMem")) { //ȸ�� ��ȸ - DB
				nextAction = service.ReadMember(request, response);
				
			} else if(requestPage.equals("MemberReadView.manageMem")) { //ȸ�� ��ȸ - View
				nextAction.setNextPath("/Member/EditMemberView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("MemberEdit.manageMem")) { //ȸ�� ���� - DB
				nextAction = service.EditMember(request, response);
				
			} else if(requestPage.equals("MemberDelete.manageMem")) { //ȸ�� ���� -DB
				nextAction = service.DeleteMember(request, response);
				
			} else if (requestPage.equals("Check.manageMem")) {
				nextAction.setNextPath("MemberList.manageMem?page=1");
				nextAction.setRedirect(false);
				
			} else if (requestPage.equals("Result.manageMem")) {
				nextAction.setNextPath("MemberList.manageMem?page=1");
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
