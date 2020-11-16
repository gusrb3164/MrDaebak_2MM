package Service.MemberService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.Member;
import Service.ActionForward;
import Service.Service;

public class EditMyProfileService extends Service{
	
	public ActionForward UpdateMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		request.setCharacterEncoding("UTF-8");
		
		int no = Integer.parseInt(request.getParameter("user_no"));
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");
		String name = request.getParameter("user_name");
		String mobile = request.getParameter("user_mobile");
		String address= request.getParameter("user_address");
		
		Member dto = new Member();
		dto.setNo(no);
		dto.setId(id);
		dto.setPw(password);
		dto.setName(name);
		dto.setAddress(address);
		dto.setMobile(mobile);
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.update(dto);
		
		if(result) {
			request.setAttribute("altmsg", "������ �����Ͽ����ϴ�.");
		}else {
			request.setAttribute("altmsg", "�������� ����� �߻��Ͽ����ϴ�.");
		}
		
		nextAction = new ActionForward();
		nextAction.setNextPath("Result.myprofile");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
	public ActionForward DeleteMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int no = Integer.parseInt(request.getParameter("user_no"));
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.delete(no);
		
		
		if(result) {
			request.setAttribute("altmsg", "������ �����Ͽ����ϴ�.");
		}else {
			request.setAttribute("altmsg", "�������� ����� �߻��Ͽ����ϴ�.");
		}
		
		nextAction = new ActionForward();
		nextAction.setNextPath("Result.myprofile");
		nextAction.setRedirect(false);	

		return nextAction;
	}
	
	public ActionForward ReadMyProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = (String) request.getSession().getAttribute("Id");
		MemberDAO dao = MemberDAO.getInstance();
		Member member = dao.select(id);
		
		request.setAttribute("member", member);
		
		nextAction = new ActionForward();
		nextAction.setNextPath("EditView.myprofile");
		nextAction.setRedirect(false);
		
		return nextAction;
	}
	
}
