package com.ezen.WeSee;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.WeSee.dto.BoardDTO;
import com.ezen.WeSee.dto.MemberDTO;
import com.ezen.WeSee.dto.MovieDTO;
import com.ezen.WeSee.service.*;


@Controller
public class BoardController {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@RequestMapping("/board.do")
	public String listBoard(HttpServletRequest req) {
	
		int pageSize = 5;
		String pageNum = req.getParameter("pageNum");
		if (pageNum==null){
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize -1;
		int rowCount = boardMapper.getBoardCount();
		if (endRow > rowCount) endRow = rowCount;
		List<BoardDTO> list = null;
		if (rowCount>0){
			list = boardMapper.listBoard(startRow, endRow);
		} 
		int boardNum = rowCount - (startRow - 1);
		if (rowCount>0) {
			int pageCount = rowCount/pageSize + (rowCount%pageSize==0 ? 0 : 1);
			int pageBlock = 3;
			int startPage = (currentPage - 1)/pageBlock  * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
		}
		req.setAttribute("rowCount", rowCount);
		req.setAttribute("boardNum", boardNum);
		req.setAttribute("listBoard", list);
		return "Board/Board/Board";
	}
	
	@RequestMapping("/find.do")
	public String findBoard(HttpServletRequest req, @RequestParam String boardtitle) {
		List<BoardDTO> find = boardMapper.findBoard(boardtitle);
		req.setAttribute("listBoard", find);
		req.setAttribute("boardtitle", boardtitle);
		return "Board/Board/Board";
	}
	
	@RequestMapping(value="writereview.do", method=RequestMethod.GET)
	public String goWriteForm(HttpServletRequest req) {
		
		List<MovieDTO> list = boardMapper.getTitle();
		req.setAttribute("listMovie", list);
		
		return "Board/Board/BoardWriteReview";
	}
	
	@RequestMapping(value="writereview.do", method=RequestMethod.POST)
	public String doWriteForm(HttpServletRequest req, BoardDTO dto) {
		
		int res = boardMapper.insertBoard(dto);
		if (res>0) {
			req.setAttribute("msg", "게시글 등록 성공!! 게시글 목록페이지로 이동합니다.");
			req.setAttribute("url", "board.do");
		}else {
			req.setAttribute("msg", "게시글 등록 실패!! 게시글 등록페이지로 이동합니다.");
			req.setAttribute("url", "writereview.do");
		
		}
		
		return "forward:/WEB-INF/views/message.jsp";		
	}
	
	@RequestMapping("/delete_board.do")
	public String deleteBoard(HttpServletRequest req, @ModelAttribute BoardDTO dto) {
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginData");
		String name = mdto.getName();
		
		BoardDTO bdto = boardMapper.getBoard(dto.getBoardnum(),"boardcontent");
		bdto.getBoardname();
		
		if(name.equals(bdto.getBoardname())) {
			int res = boardMapper.deleteBoard(dto.getBoardnum());
				if(res>0) {
					
					req.setAttribute("msg", "리뷰가 삭제 되었습니다.");
					req.setAttribute("url", "board.do");
				}else {
					req.setAttribute("msg", "리뷰 삭제를 실패하였습니다.");
					req.setAttribute("url", "board.do");
				}
				
		}else {
			req.setAttribute("msg", "작성자만 삭제할수있습니다.");
			req.setAttribute("url", "board.do");
		}
		return "forward:/WEB-INF/views/message.jsp";
	}
	@RequestMapping(value="update_board.do", method=RequestMethod.GET)
	public String goUpdateBoard(HttpServletRequest req, @ModelAttribute BoardDTO dto) {
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginData");
		String name = mdto.getName();
		
		dto = boardMapper.getBoard(dto.getBoardnum(), "update");
		dto.getBoardname();
		
		if(!name.equals(dto.getBoardname())) {
			
				req.setAttribute("msg", "작성자만 수정가능합니다. 리뷰게시판으로 이동합니다.");
				req.setAttribute("url", "board.do");
				return "forward:/WEB-INF/views/message.jsp";
		}
		List<MovieDTO> list = boardMapper.getTitle();
		req.setAttribute("listMovie", list);
		dto = boardMapper.getBoard(dto.getBoardnum(), "update");
		
		req.setAttribute("getBoard", dto);
	
		return "Board/Board/BoardUpdateForm";
	}
	@RequestMapping(value="update_board.do", method=RequestMethod.POST)
	public String doUpdateBoard(HttpServletRequest req, @ModelAttribute BoardDTO dto) {
		
		int res = boardMapper.updateBoard(dto);
		if(res>0) {
			
			req.setAttribute("msg", "리뷰 수정 성공!!  이동합니다."); // 수정하기
			req.setAttribute("url", "board.do");
		}else {
			req.setAttribute("msg", "리뷰 수정 실패!! 리뷰게시판으로 이동합니다.");
			req.setAttribute("url", "board.do");
		}
		return "forward:/WEB-INF/views/message.jsp";		
	}
	
	@RequestMapping("/boardcontent.do")
		public String contentBoard(HttpServletRequest req,@RequestParam int boardnum) {
				BoardDTO dto = boardMapper.getBoard(boardnum, "boardcontent");
			req.setAttribute("getBoard", dto);
			
			return "Board/Board/BoardContent";
	}

	@RequestMapping("/good_board.do")
	public String goodBoard(HttpServletRequest req,@RequestParam int boardnum) {
			BoardDTO dto = boardMapper.getBoard(boardnum, "plusgood");
		req.setAttribute("getBoard", dto);
		
		return "Board/Board/BoardContent";
}
	@RequestMapping("/minus_board.do")
	public String minusBoard(HttpServletRequest req,@RequestParam int boardnum) {
			BoardDTO dto = boardMapper.getBoard(boardnum, "minusgood");
		req.setAttribute("getBoard", dto);
		
		return "Board/Board/BoardContent";
}
	
	
}









