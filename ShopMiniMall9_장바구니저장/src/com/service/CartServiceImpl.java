package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.CartDAO;
import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;

public class CartServiceImpl implements CartService {

	@Override
	public int cartAdd(CartDTO dto) {
		int n = 0;
		SqlSession session = MySqlSessionFactory.getSession();
			try {
				//DAO연동
				CartDAO dao = new CartDAO();
				n = dao.cartAdd(session, dto);
				session.commit();
			}finally {
				session.close();
			}
		return n;
	}

	
}

