package com.service;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.OrderDAO;
import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;

public class OrderServiceImpl implements OrderService {

	@Override
	public CartDTO cartByNum(int num) {
		CartDTO cartDTO = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동
			OrderDAO dao = new OrderDAO();
			cartDTO = dao.cartByNum(session, num);
		}finally {
			session.close();
		}
		
		return cartDTO;
	}

	@Override
	public MemberDTO memberByUserid(String userid) {
		MemberDTO memberDTO = null;
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			//DAO연동
			OrderDAO dao = new OrderDAO();
			memberDTO = dao.memberByUserid(session, userid);
			
		}finally {
			session.close();
		}
		
		return memberDTO;
	}

	@Override
	public int orderDone(OrderDTO dto, int del_num) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
			try {
				//DAO연동
				OrderDAO dao = new OrderDAO();
				
		//트랜잭션 시작	
				// orderinfo에 저장
				n = dao.orderDone(session, dto);
				
				// cart에서 삭제 호출
				n = dao.cartDel(session, del_num);
				
				session.commit();     // 둘 다 성공하면 commit
				
		//트랜잭션 종료   
			}catch(Exception e) {     // 둘 중 하나라도 실패하면 rollback
				session.rollback();
			}finally {
				session.close();
			}
		return n;
	}

}
