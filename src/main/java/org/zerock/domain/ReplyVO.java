package org.zerock.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReplyVO {
	private Long rno;
	private Long bno;
	
	private String reply;
	private String replyer;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm")
	private Date replyDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm")
	private Date updateDate;
}
