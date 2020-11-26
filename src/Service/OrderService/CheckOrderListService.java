package Service.OrderService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.OrderDAO;
import DTO.Order;
import Service.ActionForward;
import Service.Service;

public class CheckOrderListService extends Service{
	public ActionForward ListOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			int page = Integer.parseInt(request.getParameter("page"));
			OrderDAO dao = OrderDAO.getInstance();
			ArrayList<Order> list = dao.getList(page);
			int totalpages = dao.getTotalPage();
			
			request.setAttribute("totalpage", totalpages);
			request.setAttribute("currentpage", page);
			request.setAttribute("list", list);
			
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "�ֹ� ����Ʈ�� �ҷ����� ���� ����� �߻��Ͽ����ϴ�.");
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("OrderListView.orderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	public ActionForward ReadOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			int orderNum = Integer.parseInt( request.getParameter("OrdNo"));
			OrderDAO dao = OrderDAO.getInstance();
			Order order = dao.selectOrder(orderNum);
			order.setCart(dao.selectOrderedMenu(order.getNo()));
			request.setAttribute("order", order);
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "�ֹ� ���������� �ҷ����� ���� ����� �߻��Ͽ����ϴ�.");
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("ReadOrderView.orderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	
	public ActionForward DeleteOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		try {
			System.out.println(request.getParameter("order_no"));
			int orderNum = Integer.parseInt(request.getParameter("order_no")); // url�� ÷�ε� �۹�ȣ �Ķ���� �ޱ�
			OrderDAO dao = OrderDAO.getInstance();
			boolean result = dao.delete(orderNum);
			
			if(!result) {
				throw new Exception();
			}else {
				request.setAttribute("altmsg", "�ֹ����� ������ �����Ͽ����ϴ�.");
			}
		}catch(Exception e) {
			request.setAttribute("altmsg", "�ֹ����� �������� ������ �߻��Ͽ����ϴ�.");
			
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("Result.orderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	
	public ActionForward UpdateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		try {
			int orderNum = Integer.parseInt(request.getParameter("order_no"));
			int status = Integer.parseInt(request.getParameter("order_status"));
			OrderDAO dao = OrderDAO.getInstance();
			boolean result = dao.update(orderNum, status);
			
			if(!result) {
				throw new Exception();
			}else {
				request.setAttribute("altmsg", "�ֹ� ���� ������ �����Ͽ����ϴ�.");
			}
			
		}catch(Exception e) {
			request.setAttribute("altmsg", "�ֹ� ���� ���� ���� ������ �߻��Ͽ����ϴ�.");
			
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("Result.orderlist");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
}
