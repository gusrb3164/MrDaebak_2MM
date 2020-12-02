package Controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.MemberService.LogOutService;

@WebServlet("*.logout")
public class LogOutController extends HttpServlet{
	ActionForward nextAction = new ActionForward();
	LogOutService service = new LogOutService();
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI(); 
		int Index = requestURI.lastIndexOf("/") + 1; 
		String requestPage = requestURI.substring(Index);
		
		//System.out.println("Controller ���� " + requestPage);
		
		
		try {
			if(requestPage.equals("Logic.logout")) { //�α׾ƿ�
				nextAction = service.LogOut(request, response);
			}
			
			if(nextAction != null) {
				if(nextAction.isRedirect()) {
					response.sendRedirect(nextAction.getNextPath());
				} else {
					request.getRequestDispatcher(nextAction.getNextPath()).forward(request, response);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
}
	
}
