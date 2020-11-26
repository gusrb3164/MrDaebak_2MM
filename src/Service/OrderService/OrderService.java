package Service.OrderService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DAO.OrderDAO;
import DTO.Member;
import DTO.Menu;
import DTO.Style;
import Service.ActionForward;
import Service.Service;

public class OrderService extends Service{
	public ActionForward GetOrderForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			OrderDAO dao = OrderDAO.getInstance();
			
			ArrayList<Menu> menulist = dao.getMenulist();
			ArrayList<Style> stylelist = dao.getStylelist();
			
			for(int i=0; i<menulist.size(); i++) {
				int mno = menulist.get(i).getNo();
				
				menulist.get(i).setAvailableStyle(dao.getAvailableStyle(mno));
				//�޴��� �ش��ϴ� �׸�
				menulist.get(i).setMenuDetailList(dao.getMenuDetaillist(mno));
				//��Ÿ �׸�
				menulist.get(i).setExtraDetailList(dao.getExtraDetaillist(mno));
			}
			
			for(int i=0; i<menulist.get(1).getMenuDetailList().size(); i++) {
				System.out.println(menulist.get(1).getMenuDetailList().get(i).getStockNo());
			}
			
			HttpSession session = request.getSession();
			int memberno = (int)session.getAttribute("No");
			
			MemberDAO mdao = MemberDAO.getInstance();
			Member customer = mdao.select(memberno);
			
			request.setAttribute("member", customer);
			request.setAttribute("menulist", menulist);
			request.setAttribute("stylelist", stylelist);
			
		}catch (Exception e) {
			request.setAttribute("altmsg", "�ֹ��� ���� ������ �ҷ����� ���� ����� �߻��Ͽ����ϴ�.");
		}
		finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("OrderView.order");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
}
