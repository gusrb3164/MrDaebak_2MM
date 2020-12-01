package Controller.Order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.OrderService.CheckMyOrderListService;

@WebServlet("*.myorderlist")
public class CheckMyOrderListController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ActionForward nextAction = new ActionForward();
	CheckMyOrderListService service = new CheckMyOrderListService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		int Index = requestURI.lastIndexOf("/") + 1;
		String requestPage = requestURI.substring(Index);
		System.out.println("Controller ���� " + requestPage);
		
		try {
			if(requestPage.equals("MyOrderList.myorderlist")) { //�ֹ� ��� - DB��ȸ
				nextAction = service.ListMyOrder(request, response);

			} else if(requestPage.equals("MyOrderListView.myorderlist")) { //�ֹ� ��� - View
				nextAction.setNextPath("/Order/MyOrderListView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("ReadMyOrder.myorderlist")) { //�ֹ� ��ȸ - DB
				nextAction = service.ReadMyOrder(request, response);
				
			} else if(requestPage.equals("ReadMyOrderView.myorderlist")) { //�ֹ� ��ȸ - View
				nextAction.setNextPath("/Order/MyOrderDetailView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("Result.myorderlist")) { //���
				nextAction.setNextPath("/Order/MyOrderList.myorderlist?page=1");
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost (request, response);
	}
}
