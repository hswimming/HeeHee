package com.shinhan.heehee.dto.response;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MainProdRecoDTO {
	private int imgSeq;
	private String imgName;
	private String articleTitle;
	private int productPrice;
	private Date createDate;
	private String agoTime;
	private int imgCateSeq;
	private String proStatus;
	private int productSeq;
	private String userId;
}
