package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action{ //model ���� �� execute()�� �������̵��ؼ� ���.
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}