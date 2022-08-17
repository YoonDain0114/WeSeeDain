package com.ezen.WeSee;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.WeSee.dto.MemberDTO;
import com.ezen.WeSee.service.AdminMapper;
import com.ezen.WeSee.service.MemberMapper;

@Controller
public class MemberController {
	
	//어노테이션 
	//의존 객체의 타입에 해당하는 빈(Bean)을 찾아 주입하는 역할
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping("/goMemberList.member")//회원 리스트를 호출
	public String memberList(HttpServletRequest req) {
		int pageSize = 5;
		String pageNum = req.getParameter("pageNum");
		if (pageNum==null){
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize -1;
		int rowCount = memberMapper.getMemberlistCount();
		if (endRow > rowCount) endRow = rowCount;
		List<MemberDTO> list = null;
		if(rowCount>0) {
			list = memberMapper.listMember(startRow, endRow);//MemberDTO의 입력받은 값을 list객체에 넣어주기
		}
		int memberNum = rowCount - (startRow - 1);
		if (rowCount>0) {
			//전체 페이지수 계산
			int pageCount = rowCount/pageSize + (rowCount%pageSize==0 ? 0 : 1);
													//게시글이 13개이고 pageSize가 4라면 총 4페이지가 필요하다
													//나머지가 있다면 그만큼 수용하기 위해서 +1이 필요하다
			//한 페이지에 보여줄 페이지 블럭
			int pageBlock = 3;
			//한 페이지에 보여줄 페이지블럭 시작번호 계산
			int startPage = (currentPage - 1)/pageBlock  * pageBlock + 1;
			//한 페이지에 보여줄 페이지 블럭 끝번호 계산
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
		}
		req.setAttribute("rowCount", rowCount);
		req.setAttribute("memberNum", memberNum);
		req.setAttribute("listMember", list);//list속성값을 가진 listMember속성이름으로 불러준다
		return "Admin/AdminMemberList";//값으 갖고 회원리스트로 보내준다
	}
	
	@RequestMapping("/doMemberDelete.member")//회원 삭제 페이지 호출
	public String memberDelete(HttpServletRequest req, @RequestParam int membernum) {//MemberDTO의 입력받은 num값을 호출해주기
		MemberDTO mdto = adminMapper.getMember(membernum);
		int res = memberMapper.deleteMember(membernum);//membernum값으로 삭제페이지 호출
		
		/* 이메일 보내기 */
        String setFrom = "testmailsender22@naver.com";
        String toMail = mdto.getEmail();
        String title = "WeSee에서 탈퇴되셨습니다.";
        String content = 
                mdto.getName() + "님은 관리자에 의해 WeSee에서 탈퇴되셨습니다." +
        		"<br><br>" + 
                "자세한 탈퇴 사유는 관리자에게 문의해주시기 바랍니다." +
                "<br><br>" + 
                "그동안 우리 WeSee를 이용해주셔서 감사합니다.";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
        }catch(Exception e) {
            e.printStackTrace();
        }
		
		String msg = null, url = null;
		if (res>0) {
			msg = "회원삭제성공, 회원목록으로 이동합니다.";
			url = "goMemberList.member";
		}else {
			msg = "회원삭제실패, 회원목록으로 이동합니다.";
			url = "goMemberList.member";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";//메세지 불러오기
	}
	
	@RequestMapping("/doMemberFind.member")//회원 찾기페이지 호출
	public String memberFind(HttpServletRequest req) {//MemberDTO의 입력받은name값 부르기
		String name = req.getParameter("name");
		int pageSize = 10;
		String pageNum = req.getParameter("pageNum");
		if (pageNum==null){
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize -1;
		int rowCount = memberMapper.getMemberlistCount();
		if (endRow > rowCount) endRow = rowCount;
		List<MemberDTO> find = null;
		if(rowCount>0) {
			find = memberMapper.findMember(name, startRow, endRow);//MemberDTO의 입력받은 값을 list객체에 넣어주기
		}
		int memberNum = rowCount - (startRow - 1);
		if (rowCount>0) {
			//전체 페이지수 계산
			int pageCount = rowCount/pageSize + (rowCount%pageSize==0 ? 0 : 1);
													//게시글이 13개이고 pageSize가 4라면 총 4페이지가 필요하다
													//나머지가 있다면 그만큼 수용하기 위해서 +1이 필요하다
			//한 페이지에 보여줄 페이지 블럭
			int pageBlock = 3;
			//한 페이지에 보여줄 페이지블럭 시작번호 계산
			int startPage = (currentPage - 1)/pageBlock  * pageBlock + 1;
			//한 페이지에 보여줄 페이지 블럭 끝번호 계산
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
		}
		req.setAttribute("rowCount", rowCount);
		req.setAttribute("memberNum", memberNum);
		req.setAttribute("listMember", find);//find속성값을 listMember속성이름으로 불러주기
		req.setAttribute("name", name);
		return "Admin/AdminMemberList";//값을 가지로 memberList로 보내주기
	}

}
