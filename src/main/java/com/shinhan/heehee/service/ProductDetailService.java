package com.shinhan.heehee.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dao.ProductDetailDAO;
import com.shinhan.heehee.dto.request.ImageFileDTO;
import com.shinhan.heehee.dto.request.JjimDTO;
import com.shinhan.heehee.dto.request.ProductModifyRequestDTO;
import com.shinhan.heehee.dto.request.RecentlyDTO;
import com.shinhan.heehee.dto.request.ProductDetailRequestDTO;
import com.shinhan.heehee.dto.request.ViewLogDTO;
import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProdDetailImgDTO;
import com.shinhan.heehee.dto.response.ProdDetailRecoDTO;

@Service
public class ProductDetailService {

	@Autowired
	ProductDetailDAO productDetailDao;
	
	@Autowired
	AWSS3Service fileUploadService;
	
	public ProdDetailDTO prodInfo(ProductDetailRequestDTO sampleDTO) {
		return productDetailDao.productInfo(sampleDTO);
	}
	
	public List<ProdDetailImgDTO> prodImg(Integer prodSeq) {
		return productDetailDao.productImg(prodSeq);
	}
	
	/*
	 * public SellProDTO userIntroduce(Integer prodSeq) { return
	 * productDetailDao.userIntroduce(prodSeq); }
	 */

	public List<ProdDetailRecoDTO> prodReco(ProductDetailRequestDTO detailRecoDTO) {
		return productDetailDao.prodReco(detailRecoDTO);
	}
	
	@Transactional
	public void prodModify(ProductModifyRequestDTO modiDTO, String userId) throws IOException {
		String filePath = "images/sell/";
		List<MultipartFile> files = modiDTO.getUploadFiles();
		
		// 파일 업로드 전 기존 파일 삭제
		if(modiDTO.getDelArr() != null) {
			for(String delItem : modiDTO.getDelArr()) {
				ImageFileDTO imgDTO= new ImageFileDTO();
				imgDTO.setImgSeq(Integer.parseInt(delItem));
				imgDTO.setTablePk(modiDTO.getProdSeq());
				productDetailDao.deleteImgFiles(imgDTO);
			}
		}
		
		// 파일 업로드 로직
		for(MultipartFile file : files) {
			if(file.getSize() != 0) {
				ImageFileDTO imgfile = new ImageFileDTO();
				String fileName = fileUploadService.uploadOneObject(file, filePath);
				imgfile.setImgName(fileName);
				imgfile.setTablePk(modiDTO.getProdSeq());
				imgfile.setUserId(userId);
				productDetailDao.insertImgFile(imgfile);
			}
		}
		
		// SELL_PRODUCT 테이블 UPDATE
		productDetailDao.updateProduct(modiDTO);
		
	}
	
	@Transactional
	public void prodRegistry(ProductModifyRequestDTO regiDTO, String userId) throws IOException { // userId 받아와서
		regiDTO.setUserId(userId); // regiDTO에다가 userId 세팅해주기
		
		String filePath = "images/sell/";
		List<MultipartFile> files = regiDTO.getUploadFiles();
		
		// 파일 업로드 전 기존 파일 삭제
		if(regiDTO.getDelArr() != null) {
			for(String delItem : regiDTO.getDelArr()) {
				ImageFileDTO imgDTO= new ImageFileDTO();
				imgDTO.setImgSeq(Integer.parseInt(delItem));
				imgDTO.setTablePk(regiDTO.getProdSeq());
				productDetailDao.deleteImgFiles(imgDTO);
			}
		}
		
		// SELL_PRODUCT 테이블 Insert
		productDetailDao.insertProductCategory(regiDTO);
		// insertProductCategory 쿼리문이 먼저 실행된 후에 insertProduct가 해당 컬럼 중 하나인(selectSeq)를 참조함
		// 서로 DTO가 같기 때문에 아래의 prodSeq 처럼 변수에 따로 넣어주지 않아도 됨
		productDetailDao.insertProduct(regiDTO);
		
		
		Integer prodseq = regiDTO.getProdSeq(); 
		// 매퍼에서의 id값이 updateProduct인 쿼리문이 insertProduct의 (prodSeq)를 참조해야하기에 변수에 따로 넣어줘야함 (서로 DTO가 다르기 때문)
		
		
		// 파일 업로드 로직
		for(MultipartFile file : files) {
			if(file.getSize() != 0) {
				ImageFileDTO imgfile = new ImageFileDTO();
				String fileName = fileUploadService.uploadOneObject(file, filePath);
				imgfile.setImgName(fileName);
				imgfile.setUserId(userId);
				
				imgfile.setTablePk(prodseq);
				
				productDetailDao.insertImgFile(imgfile);
			}
		}
		
		
	}
	
	public void insertViewLog(ViewLogDTO viewLogDTO) {
		productDetailDao.insertViewLog(viewLogDTO);
	}
	
	public List<ProdDetailDTO> selectRecently(String userId) {
		return productDetailDao.selectRecently(userId);
	}
	
	public int proStatusSelling(int productSeq) {
		return productDetailDao.proStatusSelling(productSeq);
	}
	
	public int proStatusReserve(int productSeq) {
		return productDetailDao.proStatusReserve(productSeq);
	}

	public int proStatusPutOff(int productSeq) {
		return productDetailDao.proStatusPutOff(productSeq);
	}

	public int proStatusDelete(int productSeq) {
		return productDetailDao.proStatusDelete(productSeq);
	}

	public int insertJjim(JjimDTO jjimDTO) {
		return productDetailDao.insertJjim(jjimDTO);
	}

	public int deleteJjim(JjimDTO jjimDTO) {
		return productDetailDao.deleteJjim(jjimDTO);
	}
	
	public int selectJjim(JjimDTO jjimDto) {
		return productDetailDao.selectJjim(jjimDto);
	}

	/*
	 * public int insertRecently(RecentlyDTO recentlyDTO) { return
	 * productDetailDao.insertRecently(recentlyDTO); }
	 */
}
