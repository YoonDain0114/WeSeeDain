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
	
	//������̼� 
	//���� ��ü�� Ÿ�Կ� �ش��ϴ� ��(Bean)�� ã�� �����ϴ� ����
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping("/goMemberList.member")//ȸ�� ����Ʈ�� ȣ��
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
			list = memberMapper.listMember(startRow, endRow);//MemberDTO�� �Է¹��� ���� list��ü�� �־��ֱ�
		}
		int memberNum = rowCount - (startRow - 1);
		if (rowCount>0) {
			//��ü �������� ���
			int pageCount = rowCount/pageSize + (rowCount%pageSize==0 ? 0 : 1);
													//�Խñ��� 13���̰� pageSize�� 4��� �� 4�������� �ʿ��ϴ�
													//�������� �ִٸ� �׸�ŭ �����ϱ� ���ؼ� +1�� �ʿ��ϴ�
			//�� �������� ������ ������ ��
			int pageBlock = 3;
			//�� �������� ������ �������� ���۹�ȣ ���
			int startPage = (currentPage - 1)/pageBlock  * pageBlock + 1;
			//�� �������� ������ ������ �� ����ȣ ���
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
		}
		req.setAttribute("rowCount", rowCount);
		req.setAttribute("memberNum", memberNum);
		req.setAttribute("listMember", list);//list�Ӽ����� ���� listMember�Ӽ��̸����� �ҷ��ش�
		return "Admin/AdminMemberList";//���� ���� ȸ������Ʈ�� �����ش�
	}
	
	@RequestMapping("/doMemberDelete.member")//ȸ�� ���� ������ ȣ��
	public String memberDelete(HttpServletRequest req, @RequestParam int membernum) {//MemberDTO�� �Է¹��� num���� ȣ�����ֱ�
		MemberDTO mdto = adminMapper.getMember(membernum);
		int res = memberMapper.deleteMember(membernum);//membernum������ ���������� ȣ��
		
		/* �̸��� ������ */
        String setFrom = "testmailsender22@naver.com";
        String toMail = mdto.getEmail();
        String title = "WeSee���� Ż��Ǽ̽��ϴ�.";
        String content = 
                mdto.getName() + "���� �����ڿ� ���� WeSee���� Ż��Ǽ̽��ϴ�." +
        		"<br><br>" + 
                "�ڼ��� Ż�� ������ �����ڿ��� �������ֽñ� �ٶ��ϴ�." +
                "<br><br>" + 
                "�׵��� �츮 WeSee�� �̿����ּż� �����մϴ�.";

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
			msg = "ȸ����������, ȸ��������� �̵��մϴ�.";
			url = "goMemberList.member";
		}else {
			msg = "ȸ����������, ȸ��������� �̵��մϴ�.";
			url = "goMemberList.member";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";//�޼��� �ҷ�����
	}
	
	@RequestMapping("/doMemberFind.member")//ȸ�� ã�������� ȣ��
	public String memberFind(HttpServletRequest req) {//MemberDTO�� �Է¹���name�� �θ���
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
			find = memberMapper.findMember(name, startRow, endRow);//MemberDTO�� �Է¹��� ���� list��ü�� �־��ֱ�
		}
		int memberNum = rowCount - (startRow - 1);
		if (rowCount>0) {
			//��ü �������� ���
			int pageCount = rowCount/pageSize + (rowCount%pageSize==0 ? 0 : 1);
													//�Խñ��� 13���̰� pageSize�� 4��� �� 4�������� �ʿ��ϴ�
													//�������� �ִٸ� �׸�ŭ �����ϱ� ���ؼ� +1�� �ʿ��ϴ�
			//�� �������� ������ ������ ��
			int pageBlock = 3;
			//�� �������� ������ �������� ���۹�ȣ ���
			int startPage = (currentPage - 1)/pageBlock  * pageBlock + 1;
			//�� �������� ������ ������ �� ����ȣ ���
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) endPage = pageCount;
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
		}
		req.setAttribute("rowCount", rowCount);
		req.setAttribute("memberNum", memberNum);
		req.setAttribute("listMember", find);//find�Ӽ����� listMember�Ӽ��̸����� �ҷ��ֱ�
		req.setAttribute("name", name);
		return "Admin/AdminMemberList";//���� ������ memberList�� �����ֱ�
	}

}
