package com.shinhan.heehee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.MainDAO;
import com.shinhan.heehee.dto.request.ProductModifyRequestDTO;
import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.dto.response.MainProdRankDTO;
import com.shinhan.heehee.dto.response.MainProdRecentlyDTO;
import com.shinhan.heehee.dto.response.MainProdRecoDTO;
import com.shinhan.heehee.dto.response.ProductCategoryDTO;

@Service
public class MainService {

	@Autowired
	MainDAO mainDao;
	
	public List<MainProdRankDTO> rankProdList() {
		return mainDao.rankProdList();
	}

	public List<MainProdRecoDTO> recommandList(String loginId) {
		return mainDao.recommandList(loginId);
	}

	public List<MainProdRecentlyDTO> recentprodList() {
		return mainDao.recentprodList();
	}
	
	public List<CategoryDTO> mainCateList() {
		List<ProductCategoryDTO> categoryList = mainDao.cateList();
		List<CategoryDTO> mainCateList= mainDao.mainCateList();
		
		for(CategoryDTO mainCate: mainCateList) {
			List<Map<String,Object>> subcateList = new ArrayList<Map<String,Object>>();
			for(ProductCategoryDTO subcate:categoryList) {
				if(mainCate.getCategory().equals(subcate.getCategory())) {
					Map<String,Object> tempMap = new HashMap<String,Object>();
					tempMap.put("cateSeq",subcate.getProductCateSeq());
					tempMap.put("detailCate",subcate.getDetailCategory());
					subcateList.add(tempMap);
				}
			}
			mainCate.setSubCategory(subcateList);
		}
		return mainCateList;
	}
}