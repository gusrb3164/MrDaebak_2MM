package Service.MemberService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import DAO.MemberDAO;
import DTO.Member;
import Service.ActionForward;
import Service.Service;

public class EditMyProfileService extends Service{
	
	public ActionForward UpdateMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		request.setCharacterEncoding("UTF-8");
		try {
			MemberDAO dao = MemberDAO.getInstance();
			
			int no = Integer.parseInt(request.getParameter("user_no"));
			String password = request.getParameter("user_password");
			String name = request.getParameter("user_name");
			String mobile = request.getParameter("user_mobile");
			String address= request.getParameter("user_address");
			
			Member member = new Member();
			member.setNo(no);
			member.setPw(password);
			member.setName(name);
			member.setAddress(address);
			member.setMobile(mobile);
			
			
			if(!dao.update(member)) {
				throw new Exception();
			}
			request.setAttribute("altmsg", "������ �����Ͽ����ϴ�.");
		}
		catch (Exception e) {
			request.setAttribute("altmsg", "�������� ����� �߻��Ͽ����ϴ�.");
		}
		finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("Result.myprofile");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	
	public ActionForward DeleteMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		try {
			int no = Integer.parseInt(request.getParameter("user_no"));
			MemberDAO dao = MemberDAO.getInstance();
			
			if(!dao.delete(no)) {
				throw new Exception();
			}
			
			request.setAttribute("altmsg", "������ �����Ͽ����ϴ�.");
			nextAction.setNextPath("Logic.logout");
			
		}catch (Exception e) {
			request.setAttribute("altmsg", "�������� ����� �߻��Ͽ����ϴ�.");
			nextAction = new ActionForward();
			nextAction.setNextPath("Result.myprofile");
			nextAction.setRedirect(false);	
		}
		return nextAction;
	}
	
	public ActionForward ReadMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			String id = (String) request.getSession().getAttribute("Id");
			MemberDAO dao = MemberDAO.getInstance();
			Member member = dao.select(id);
			
			if(member == null) {
				throw new Exception();
			}
			request.setAttribute("member", member);
						
		}catch (Exception e) {
			request.setAttribute("altmsg", "�о���µ� ������ �߻��߽��ϴ�.");
			e.printStackTrace();
		}finally {
			nextAction = new ActionForward();
			nextAction.setNextPath("EditView.myprofile");
			nextAction.setRedirect(false);
		}
		return nextAction;
	}
	

	
}
