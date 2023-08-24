package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.GoodsDAO;
import com.dto.GoodsDTO;

public class GoodsServiceImpl implements GoodsService {

	@Override
	public List<GoodsDTO> goodsList(String gCategory) {
		List<GoodsDTO> list = null;
		 SqlSession session = MySqlSessionFactory.getSession();
			try {
			  //DAO 연동
			GoodsDAO dao = new GoodsDAO();
			list = dao.goodsList(session, gCategory);
			}finally {
				session.close();
			}
		return list;
	}

	/*
    SqlSession session = MySqlSessionFactory.getSession();
		try {
		
		
		}finally {
			session.close();
		}


*/	
	
}
