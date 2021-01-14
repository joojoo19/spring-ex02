package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;

	/*
	 * public BoardServiceImpl(BoardMapper mapper) { this.mapper = mapper; }
	 * -> @AllArgsConstructor
	 */

	@Override
	public void register(BoardVO board) {

		mapper.insertSelectKey(board);
	}

	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}
	
	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}
	
	@Override
	public boolean remove(Long bno) {
		return mapper.delete(bno) == 1;
	}
	
	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board) == 1;
	}
}
