package DTO;

import java.util.ArrayList;

public class Order {
	private int no; //DB���������ڵ� (�ʿ����)
	private String name; //�ֹ����̸�
	private String mobile; //
	private String address;
	private int totalPrice;	//(cart�� ���� ������ ��) if (�ܰ� = ����)
	private String cardNum;
	private boolean isDiscounted;
	private String deliverydateTime; //���ϴ� ���� ��¥, �ð�
	private int status; //����(��� �ʿ����) ������ ó�� �ֹ����� 0
	private int memberNo; //���� (��� �ʿ����)
	private String memberID; //���� (��� �ʿ����)
	private String info; 
	private ArrayList<OrderedMenu> cart = null;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public boolean getIsDiscounted() {
		return isDiscounted;
	}
	public void setIsDiscounted(boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
	}
	public String getDeliverydateTime() {
		return deliverydateTime;
	}
	public void setDeliverydateTime(String deliverydateTime) {
		this.deliverydateTime = deliverydateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public ArrayList<OrderedMenu> getCart() {
		return cart;
	}
	public void setCart(ArrayList<OrderedMenu> cart) {
		this.cart = cart;
	}
	
	
	
}
