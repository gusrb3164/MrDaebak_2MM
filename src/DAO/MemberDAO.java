package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.Customer;
import DTO.Employee;
import DTO.Member;

public class MemberDAO {
		//private String db_url = "jdbc:oracle:thin:@localhost:1521:orcl";
		//private String db_username = "mrdaebak";
		//private String db_password = "1234";
	
		private static MemberDAO dao;
		private Connection con;
		private PreparedStatement ps;
		private ResultSet rs;
		private String sql;
		
		static private DataSource ds;
		
		
		static {
			try {
				System.out.println("start DBCP!");
				Context context = new InitialContext();
				ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//�̱��� ����
		private MemberDAO() {}
		public synchronized static MemberDAO getInstance() {
			if(dao == null) {
				dao = new MemberDAO();
			}
			return dao;
		}
		
		
		public boolean insert(Customer customer) {
			sql = "INSERT INTO member VALUES(member_seq.NEXTVAL, ?, ?, ?, ?, ?, default, default, default)";
			boolean result = false;
			
			try{
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				
				ps.setString(1, customer.getId());
				ps.setString(2, customer.getPw());
				ps.setString(3, customer.getName());
				ps.setString(4, customer.getMobile());
				ps.setString(5, customer.getAddress());
				//ps.setInt(6, 0); //Ÿ�� default = 0 (�մ�)
				//ps.setInt(7, 0); //vip���� default = 0 (vip�ƴ�)
				//ps.setInt(8, 0); //��å default = Customer
				
				
				result = ps.executeUpdate() == 1;	
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {  // �ݴ°� ����ó�� ������� ����Ǿ�� ������.
				try {
					if( ps != null) {
						ps.close(); // null�̸� ���� �Ұ��� (null pointer exception �߻�)
					}
					if(con != null) {
						con.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}

			}
			return result; 

		}
		
		
		public Member select(String id, String password) {
			Member member = null;
			sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, password);
				rs = ps.executeQuery();
				 
				if(rs.next()) {
					
					int type = rs.getInt("type");
					
					switch (type) {
					
					case 0: //customer
						member = new Customer();
						member.setNo(rs.getInt("no"));
						member.setId(rs.getString("id"));
						member.setPw(rs.getString("pw"));
						member.setName(rs.getString("name"));
						member.setMobile(rs.getString("phone"));
						member.setAddress(rs.getString("address"));
						
						if(rs.getInt("vip") == 0) {
							((Customer)member).setVip(false);
						}
						else {
							((Customer)member).setVip(true);
						}
						break;
						
					case 1: //employee
						member = new Employee();
						member.setNo(rs.getInt("no"));
						member.setId(rs.getString("id"));
						member.setPw(rs.getString("pw"));
						member.setName(rs.getString("name"));
						member.setMobile(rs.getString("phone"));
						member.setAddress(rs.getString("address"));
						((Employee)member).setPosition(rs.getString("position"));
						break;
						
					default:
						break;
					}
				}		
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
					if(con != null)con.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return member;
		}
		
		
		public boolean delete(String id, String password) {
			boolean result = false;
			sql = "DELETE FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, password);
				result = 1 ==ps.executeUpdate();	
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
					if(con != null)con.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		public boolean isExistID(String id) {
			boolean exist = false;
			String sql = "SELECT * FROM MEMBER WHERE ID = ?";
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				exist = rs.next();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
					if(con != null) con.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return exist;
		}
}