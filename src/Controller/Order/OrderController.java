package Controller.Order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.OrderService.OrderService;

@WebServlet("*.order")
public class OrderController extends HttpServlet{
	ActionForward nextAction  = new ActionForward();
	OrderService service = new OrderService();
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		int Index = requestURI.lastIndexOf("/") + 1;
		String requestPage = requestURI.substring(Index);
		System.out.println("Controller ���� " + requestPage);
		
		try {
			if(requestPage.equals("GetOrderForm.order")) { //�޴���������- DB��ȸ
				nextAction = service.GetOrderForm(request, response);

			} else if(requestPage.equals("OrderView.order")) { //�ֹ�  - View
				nextAction.setNextPath("/Order/OrderView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("DoOrder.order")) { //�ֹ��ϱ�
				nextAction = service.DoOrder(request, response);
				
			}else if(requestPage.equals("ResultView.order")) { //��� �Ϸ� ��
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
				
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
