package Service.OrderService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OrderDAO;
import DTO.Order;
import Service.ActionForward;
import Service.Service;

public class CheckMyOrderListService extends Service{
	public ActionForward ListMyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		try {
			HttpSession session = request.getSession();
			int memberno = (int)session.getAttribute("No");
			int page = Integer.parseInt(request.getParameter("page"));
			
			OrderDAO dao = OrderDAO.getInstance();
			ArrayList<Order> list = dao.getList(page, memberno);
			int totalpages = dao.getTotalPage(memberno);
			
			request.setAttribute("totalpage", totalpages);
			request.setAttribute("currentpage", page);
			request.setAttribute("list", list);
			
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "�� �ֹ� ����Ʈ�� �ҷ����� ���� ����� �߻��Ͽ����ϴ�.");
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("MyOrderListView.myorderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	
	public ActionForward ReadMyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("UTF-8");
			nextAction = new ActionForward();
		try {
			int orderNum = Integer.parseInt( request.getParameter("OrdNo"));
			OrderDAO dao = OrderDAO.getInstance();
			Order order = dao.selectOrder(orderNum);
			order.setCart(dao.selectOrderedMenu(order.getNo()));
			request.setAttribute("order", order);
			nextAction.setNextPath("ReadMyOrderView.myorderlist");
			nextAction.setRedirect(false);
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "�� �ֹ� ���������� �ҷ����� ���� ����� �߻��Ͽ����ϴ�.");
			nextAction.setNextPath("Result.myorderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
}
