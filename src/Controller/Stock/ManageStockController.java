package Controller.Stock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ActionForward;
import Service.StockService.ManageStockService;

@WebServlet("*.stock")
public class ManageStockController extends HttpServlet{
	ActionForward nextAction  = new ActionForward();
	ManageStockService service = new ManageStockService();
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI(); //��û�� uri �ּ�
		int Index = requestURI.lastIndexOf("/") + 1; //�ڿ� ����
		String requestPage = requestURI.substring(Index); // "xxx.stock" �ø� ����
		
		System.out.println("Controller ���� " + requestPage);
		
		try {
			if(requestPage.equals("StockList.stock")) { //��� ��� - DB��ȸ
				nextAction = service.ListStock(request, response);

			} else if(requestPage.equals("StockListView.stock")) { //��� ��� - View
				nextAction.setNextPath("/Stock/StockListView.jsp");
				nextAction.setRedirect(false);
				
			} else if(requestPage.equals("StockEdit.stock")) { //��� ���� -DB
				nextAction = service.EditStock(request, response);
				
			} else if (requestPage.equals("Result.stock")) { //Ȯ�� ��
				nextAction.setNextPath("");
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
