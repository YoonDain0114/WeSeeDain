package com.ezen.WeSee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.WeSee.dto.*;
import com.ezen.WeSee.service.AdminMapper;
import com.ezen.WeSee.service.CsboardMapper;
import com.ezen.WeSee.service.MemberMapper;


@Controller
public class CsBoardController {
	
	@Autowired
	private CsboardMapper csboardMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private JavaMailSender mailSender;
	
	@Resource(name="uploadPath")
	private String upPath;
	
	
	@RequestMapping(value="/commentForm_csboard.do",method=RequestMethod.GET)
	public String goCommentForm() {
		return "Board/csboard/CSBoardCommentWriteForm";
	}
	
	@RequestMapping(value="/commentForm_csboard.do",method=RequestMethod.POST)
	public String doCommentForm(HttpServletRequest req, CommentDTO dto) {
		
		String msg=null, url=null;
		
		int res = csboardMapper.insertCommentCsBoard(dto);
		if(res>0) {
			String bno = req.getParameter("bno");
			csboardMapper.updateComment(Integer.parseInt(bno));
			CSBoardDTO bdto = csboardMapper.getCsBoard(Integer.parseInt(bno));
			MemberDTO mdto = csboardMapper.getMembername(bdto.getCsboardname());
			/* �̸��� ������ */
	        String setFrom = "testmailsender22@naver.com";
	        String toMail = mdto.getEmail();
	        String title = mdto.getName()+"��! �����Ͻ� �ۿ� ����� �޷Ƚ��ϴ�.";
	        String content = 
	                mdto.getName() + "�Բ��� �ۼ��Ͻ� ���Ǳۿ� ����� �޷Ƚ��ϴ�." +
	                "<br><br>"+
	                "�α����Ͻ� ������ ����� Ȯ���Ͻ� �� �ֽ��ϴ�.";

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
			
			msg="��� ��� ����!! �Խñ� ����������� �̵��մϴ�.";
			url="list_csboard.do";
			 
		}else {
			msg="��۵�� ����!! �Խñ� ����������� �̵��մϴ�.";
			url="commentForm_csboard.do"; 
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		
		return "forward:WEB-INF/views/message.jsp";
	}
	
	@RequestMapping("/commentDelete.do")
	public String doCommentDelete(HttpServletRequest req,CommentDTO dto) {
		String cno = req.getParameter("cno");
		int res = csboardMapper.deleteCommentCsBoard(Integer.parseInt(cno));
		if(res>0) {
			String bno = req.getParameter("bno");
			csboardMapper.MupdateComment(Integer.parseInt(bno));
			
			req.setAttribute("msg", "��� ���� �Ǿ����ϴ�.");
			req.setAttribute("url", "list_csboard.do");
		}else {
			req.setAttribute("msg", "��� ������ �����Ͽ����ϴ�.");
			req.setAttribute("url", "content_csboard.do");
		}
		return "forward:/WEB-INF/views/message.jsp";
	}
	
	//������ �Խ��� �۾��� ������ �̵�
	@RequestMapping(value="/writeForm.do",method=RequestMethod.GET)
	public String goWriteForm(HttpServletRequest req) {
		return "Board/csboard/CSBoardWriteForm";
	}
	
	//������ �Խ��� �۾��� ������ 
	@RequestMapping("/gowriteForm.do")
	public String  doWriteFormOk(HttpServletRequest req,CSBoardDTO dto,BindingResult result) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("csimage");
		String filename=mf.getOriginalFilename();
		dto.setCsimage(filename);
		
		String name= (String)req.getAttribute("csboardname");
		System.out.println("name="+name);
		dto.setRecomment(0);
		
		//int secret=(Integer)req.getAttribute("secret");
		//dto.setSecret(secret);
		
		String msg=null, url=null;
		
		System.out.println(dto.getSecret());
		System.out.println("secret="+mr.getParameter("secret"));
		
		if(filename != null && !(filename.trim().equals(""))) {
			File file = new File(upPath,filename);
			try {
				mf.transferTo(file);
			}catch(IOException e) {}
		}
		int res = csboardMapper.insertCsBoard(dto);
		
		if(res>0) {
			msg="�Խñ� ��� ����!! �Խñ� ����������� �̵��մϴ�.";
			url="list_csboard.do";
		}else {
			msg="�Խñ� ��� ����!! �Խñ� ����������� �̵��մϴ�.";
			url="writeFrom.do"; 
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		req.setAttribute("upPath", upPath);
		
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//������ �Խ��� �̵�
	@RequestMapping("/list_csboard.do")
	public String dolistSearchboard(HttpServletRequest req, 
			@RequestParam(required = false) String mode) {
		HttpSession session = req.getSession();
		List<CSBoardDTO> list = null;
		if(mode==null) {
			//list = csboardMapper.listCsBoard();
			
			int pageSize=20;
			String pageNum=req.getParameter("pageNum");
			if(pageNum==null) {
				pageNum="1";
			}
			int currentPage=Integer.parseInt(pageNum);
			int startRow =(currentPage-1) * pageSize + 1;
			int endRow = startRow + pageSize -1;
			int rowCount = csboardMapper.getCsBoardCount();
			if(endRow > rowCount) endRow = rowCount;
			
			if(rowCount>0) {
				list= csboardMapper.listCsBoard(startRow, endRow);
			}
			int boardNum = rowCount-(startRow - 1);
			if(rowCount>0) {
				int pageCount = rowCount/pageSize + (rowCount%pageSize==0 ? 0 :1);
				int pageBlock = 3;
				int startPage = (currentPage -1)/pageBlock * pageBlock +1;
				int endPage = startPage + pageBlock - 1;
				
				if(endPage > pageCount) endPage=pageCount;
				req.setAttribute("pageCount", pageCount);
				req.setAttribute("startPage", startPage);
				req.setAttribute("endPage", endPage);
			}
			req.setAttribute("rowCount", rowCount);
			req.setAttribute("boardNum", boardNum);
			//req.setAttribute("listCSBoard", list);
			
		}else {
			String search = req.getParameter("search");
			String searchString = req.getParameter("searchString");
			if(search != null && searchString != null) {
				list=csboardMapper.searchCsBoard(search, searchString);
			}else {
				list=new ArrayList<CSBoardDTO>();
			}
			req.setAttribute("searchString", searchString);
		}
		session.setAttribute("listCSBoard", list);
		return "Board/csboard/CSBoardList";
	}

	//������ �Խ��� �� �󼼺���
	@RequestMapping("/content_csboard.do")
	public String goContendCsBoard(HttpServletRequest req , String passwd ) {
		HttpSession session=req.getSession();
		
		
		String bno = req.getParameter("csboardnum");
		
		System.out.println("bno="+bno);
		List<CommentDTO> colist = csboardMapper.listCommentCsBoard(Integer.parseInt(bno));
		
		
		req.setAttribute("listComment", colist);
		
		
		MemberDTO mdto=(MemberDTO)session.getAttribute("loginData");
		
		String loginName=null;
		if(mdto !=null) {
			loginName=mdto.getName(); // ���� ���� �α����� ����� �̸�
		}else {
			mdto =(MemberDTO)session.getAttribute("AdminloginData"); //���� admin�������� �α����ؼ� ������ �� 
			loginName=mdto.getName();
		}
		String csboardnum = req.getParameter("csboardnum");//�۹�ȣ
		//String csboardname=req.getParameter("csboardname");//�� �ۼ����̸�
		int secret = Integer.parseInt(req.getParameter("secret")); // ��б� ����
		CSBoardDTO name=csboardMapper.getCsBoard(Integer.parseInt(csboardnum));//dto���� �� �� ��� ��������(�� ��ȣ��)
		
		//System.out.println("passwd="+passwd);
		
		String membername= name.getCsboardname();//dto�� ����� �� �� ���
		
		MemberDTO find = csboardMapper.getMembername(membername);
		
		System.out.println("membername="+membername);
		System.out.println("loginname="+loginName);
		
		if(!loginName.equals("admin")) {//�α����� ������ admin�� �ƴϴ�
			if(secret==1) { //admin�� �ƴϰ� ��б� �� �� 
				if(loginName.equals(membername)) {//�α����� �̸��� �ۼ��� �̸��� ���ٸ�
					CSBoardDTO dto = csboardMapper.getCsBoard(Integer.parseInt(csboardnum));
					session.setAttribute("getCsBoard", dto);
					return "Board/csboard/CSBoardView";
				}else { //�α����� �̸��� �ۼ��� �̸��� ���� �ʴٸ�
					String msg=null,url=null;
					msg="��б��Դϴ�! �ۼ��ڸ� Ȯ�� �����մϴ�. ������ �Խ������� �̵��մϴ�";
					url="list_csboard.do";
					req.setAttribute("msg", msg);
					req.setAttribute("url", url);
					req.setAttribute("csboardnum", csboardnum);
					req.setAttribute("membername", membername);
					return "forward:WEB-INF/views/message.jsp";
				}
			}else {//admin�� �ƴϰ� ��б� �ƴҶ�
				CSBoardDTO dto = csboardMapper.getCsBoard(Integer.parseInt(csboardnum));
				session.setAttribute("getCsBoard", dto);
				
				return "Board/csboard/CSBoardView";
			}
			
		}else {
			CSBoardDTO dto = csboardMapper.getCsBoard(Integer.parseInt(csboardnum));
			session.setAttribute("getCsBoard", dto);
			
			return "Board/csboard/CSBoardView";
		}
		
	}
	
	
	//������ �Խ��� �� ���� �̵�
	@RequestMapping("/checkpw_csboard.do")
	public String deleteFormBoard(HttpServletRequest req) {
		int csboardnum = Integer.parseInt(req.getParameter("csboardnum"));
		String csimage = req.getParameter("csimage");
		String mode = req.getParameter("mode");
		if(mode != null) {
			req.setAttribute("mode", mode);
		}
		
		req.setAttribute("csboardnum", csboardnum);
		req.setAttribute("csimage", csimage);
		return "Board/csboard/CSBoardDeleteForm";
		
	}
	
	//������ �Խ��� �� ����
	@RequestMapping("/delete_csboard.do")
	public String deleteProBoard(HttpServletRequest req, @ModelAttribute CSBoardDTO dto,
								@RequestParam Map<String, String> params) {
		HttpSession session = req.getSession();
		
		String csboardnum = params.get("csboardnum");
		//String name=params.get("name");
		
		CSBoardDTO name=csboardMapper.getCsBoard(Integer.parseInt(csboardnum));
		
		String passwd = params.get("passwd");
		String csimage = params.get("csimage");
		System.out.println("passwd="+passwd);
		
		String membername= name.getCsboardname();
		
		MemberDTO find = csboardMapper.getMembername(membername);

		if(!find.getPasswd().equals(passwd)) {
			req.setAttribute("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���.");
			req.setAttribute("url", "checkpw_csboard.do?csboardnum="+csboardnum+"&csimage="+csimage+"&mode=godelete");
			return "forward:WEB-INF/views/message.jsp";
		}else if(find.getPasswd().equals(passwd)) {
			int res = csboardMapper.deleteCsBoard(Integer.parseInt(csboardnum));
			System.out.println("��й�ȣ ��ġ");
			String msg = null, url = null;
			
			if (res > 0) {
				try {
					File file = new File(upPath, csimage);
					if (file.exists()){
						file.delete();
						System.out.println("���� ����");
						
						csboardMapper.deleteAllComment(Integer.parseInt(csboardnum));
						
						msg = "�Խñ� �� �̹������� ����!! �Խñ۸���������� �̵��մϴ�.";
						url = "list_csboard.do";
						
					}else {
						System.out.println("���� ���� ����");
						csboardMapper.deleteAllComment(Integer.parseInt(csboardnum));
						msg = "�Խñ� ���� ����, �̹������� ����!! �Խñ۸���������� �̵��մϴ�.";
						url = "list_csboard.do";
						
					}
					req.setAttribute("msg", msg);
					req.setAttribute("url", url);
				}catch(NullPointerException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("�� ���� ����");
				req.setAttribute("msg", "�Խñ� ���� ����!! �Խñ� ������������ �̵��մϴ�.");
				req.setAttribute("url", "content_csboard.do?csboardnum=" + csboardnum);
			}
		}
		else {
			System.out.println("�� ����111 ����");
			req.setAttribute("msg", "�Խñ� ���� ����!! �Խñ� ������������ �̵��մϴ�.");
			req.setAttribute("url", "content_csboard.do?csboardnum=" + csboardnum);
		}
		return "forward:WEB-INF/views/message.jsp";
			
	}
	

}
