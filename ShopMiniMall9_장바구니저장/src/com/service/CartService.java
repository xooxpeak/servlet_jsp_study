package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;
import com.dto.GoodsDTO;

public interface CartService {
	
	public int cartAdd(CartDTO dto);
}
