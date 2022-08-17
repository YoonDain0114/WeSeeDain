package com.ezen.WeSee;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ezen.WeSee.service.GoodsMapper;

@Controller
public class AdminController {
	
	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Resource(name="uploadPath")
	private String upPath;
	
	//������ �α��� ��Ʈ�ѷ� ���� ����Ұ�
	
	//�α��� �������� ����
	@RequestMapping("/goLogin.do")
	public String goLogin() {
		return "Login/Login";
	}
	
	//�α����ϱ�
	@RequestMapping("/doLogin.do")
	public String doLogin(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam Map<String, String> params) {
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");
		
		int res = adminMapper.IdPwCheck(email, passwd);
		if(res < 0) {
			req.setAttribute("msg", "��ġ�ϴ� �̸��� Ȥ�� ��й�ȣ�� �����ϴ�.");
			req.setAttribute("url", "goLogin.do");
		}else if(res == 0) {
			MemberDTO dto = adminMapper.getMember(res);
			HttpSession session = req.getSession();
			session.setAttribute("AdminloginData", dto);
			
			Cookie ck = new Cookie("saveEmail", dto.getEmail());
			if (params.containsKey("saveEmail")) {
				ck.setMaxAge(24*60*60);
			}else {
				ck.setMaxAge(0);
			}
			resp.addCookie(ck);
			
			req.setAttribute("msg", "������ �������� �α����߽��ϴ�.");
			req.setAttribute("url", "goAdminManagerPage.admin");
		}else if(res > 0) {
			MemberDTO dto = adminMapper.getMember(res);
			HttpSession session = req.getSession();
			session.setAttribute("loginData", dto);
			
			Cookie ck = new Cookie("saveEmail", dto.getEmail());
			if (params.containsKey("saveEmail")) {
				ck.setMaxAge(24*60*60);
			}else {
				ck.setMaxAge(0);
			}
			resp.addCookie(ck);
			
			req.setAttribute("msg", "ȯ���մϴ�, "+dto.getName()+"��!");
			req.setAttribute("url", "goMainPage.movie");
		}else {
			req.setAttribute("msg", "���� �߻�! �ٽ� �õ��� �ּ���.");
			req.setAttribute("url", "goLogin.do");
		}
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//ȸ������ �������� ����
	@RequestMapping("/goNewMember.do")
	public String goNewMember() {
		return "Login/LoginNewMember";
	}
	
	//ȸ�������ϱ�
	@RequestMapping("/doNewMember.do")
	public String doNewMember(HttpServletRequest req) {
		String email = req.getParameter("emailName")+"@"+req.getParameter("domainName");
		
		String name = req.getParameter("name");
		String passwd = req.getParameter("passwd");
		
		String getBirth = req.getParameter("birth");
		String birth[] = getBirth.split("-");
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int year_res = year - Integer.parseInt(birth[0].trim());
		
		String rate = "0"; 
		if(year_res >= 18) rate = "18";
		else if(year_res >= 15) rate = "15";
		else if(year_res >= 12) rate = "12";
		else rate = "1";
		
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		String memberimage = "basic.jpg";	//ȸ�����Խÿ��� �⺻�̹�����
		
		String address = req.getParameter("postcode")+"_"+req.getParameter("address")+
				"_"+req.getParameter("detailAddress");
		
		MemberDTO dto = new MemberDTO();
		dto.setEmail(email);	dto.setName(name);	dto.setPasswd(passwd);
		dto.setBirth(getBirth);	dto.setHp1(hp1);	dto.setHp2(hp2);
		dto.setHp3(hp3);		dto.setRate(rate);
		dto.setAddress(address);dto.setMemberimage(memberimage);
		
		int res = adminMapper.newMember(dto);
		if(res > 0) {
			req.setAttribute("msg", "ȸ�� ������ �����մϴ�, "+name+"��! ������ �������� �α������ּ���!");
			req.setAttribute("url", "goLogin.do");
		}else {
			req.setAttribute("msg", "ȸ�� ���Կ� �����߽��ϴ�. ��� �� �ٽ� �õ��ϰų� �����ڿ��� ������ �ּ���.");
			req.setAttribute("url", "goLogin.do");
		}
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//�̸��� �ߺ� Ȯ��
	@RequestMapping("/doCheckEmail.do")
	public String doCheckEmail(HttpServletRequest req) {
		String email = req.getParameter("email");
		String domain = req.getParameter("domain");
		String f_email = email+"@"+domain;
		
		int res = adminMapper.CheckEmail(f_email);
		if(res == 0) {
			req.setAttribute("email", email);
		}
		return "Login/CheckEmail";
	}
	
	//���̵�/��й�ȣ ã�������� �̵�
	@RequestMapping("/goSearch.do")
	public String goSearch(HttpServletRequest req) {
		String option = req.getParameter("option");
		String goSearchOption = null;
		
		if(option.equals("email")) {
			goSearchOption = "email";
		}else if(option.equals("passwd")) {
			goSearchOption = "passwd";
		}else {
			req.setAttribute("msg", "���� �߻�! ��� �� �ٽ� �õ��� �ּ���!");
			req.setAttribute("url", "goLogin.do");
			return "forward:WEB-INF/views/message.jsp";
		}
		req.setAttribute("option", goSearchOption);
		return "Login/LoginSearch";
	}
	
	//���̵�/��й�ȣ ã��
	@RequestMapping("/doSearch.do")
	public String doSearch(HttpServletRequest req) {
		String option = req.getParameter("option");
		String email = null;
		
		if(option.equals("passwd")) {
			email = req.getParameter("email");
		}else if(option.equals("email")) {
			email = null;
		}else {
			req.setAttribute("msg", "���� �߻�! ��� �� �ٽ� �õ��� �ּ���!");
			req.setAttribute("url", "goLogin.do");
			return "forward:WEB-INF/views/message.jsp";
		}
		
		MemberDTO dto = new MemberDTO();
		dto.setName(req.getParameter("name"));
		dto.setHp1(req.getParameter("hp1"));
		dto.setHp2(req.getParameter("hp2"));
		dto.setHp3(req.getParameter("hp3"));
		dto.setEmail(email);
		dto.setBirth(req.getParameter("birth"));
		
		MemberDTO mdto = adminMapper.searchMember(dto);
		String msg = null, url = null;
		
		if(mdto == null) {
			msg = "��ġ�ϴ� ȸ���� �����ϴ�.";
			url = "goLogin.do";
			return "forward:WEB-INF/views/message.jsp";
		}
		
		if(option.equals("passwd")) {
			Random random = new Random();
	        int checkNum = random.nextInt(888888) + 111111;
	        
	        int passRes = adminMapper.ChangePasswd(String.valueOf(checkNum), mdto.getEmail());
	        
	        if(passRes > 0) {
		        /* �̸��� ������ */
		        String setFrom = "testmailsender22@naver.com";
		        String toMail = mdto.getEmail();
		        String title = "����� ��й�ȣ�� Ȯ�����ּ���.";
		        String content = 
		                mdto.getName() + "��! ��û�� ���� ��й�ȣ�� ����Ǿ����ϴ�." +
		                "<br><br>" + 
		                "����� ��й�ȣ�� " + checkNum + "�Դϴ�." + 
		                "<br>" + 
		                "�α����Ͻ� ���� �ݵ�� ��й�ȣ�� ������ �ּ���.";
	
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
		        msg = "����� ��й�ȣ�� �̸��Ϸ� �߼۵Ǿ����ϴ�."
		        		+ " ��й�ȣ�� Ȯ���� �ּ���.";
				url = "goLogin.do";
	        }else {
	        	msg = "������ �߻��߽��ϴ�. ��� �� �ٽ� �õ��� �ּ���.";
				url = "goLogin.do";
	        }
		}else if(option.equals("email")) {
			msg = "ȸ������ �̸����� "+mdto.getEmail()+"�Դϴ�.";
			url = "goLogin.do";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:WEB-INF/views/message.jsp";
	}
	
	
	
	//�� : ������ || �Ʒ� : ���ѽ�
	
	
	
	//������ ���� ������ �̵�
		@RequestMapping("/goAdminManagerPage.admin")
		public String goAdminManagerPage(HttpServletRequest req) {
			return "Admin/AdminManagerPage";
		}
		
		//������ ���� ���� ������ �̵�
		@RequestMapping("/goAdminList.admin")
		public String goAdminList(HttpServletRequest req) {
			HttpSession session = req.getSession();
			session.setAttribute("upPath", upPath);	//���ǿ� upPath ����
			
			int pageSize = 10;
			String pageNum = req.getParameter("pageNum");
			if (pageNum==null){
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage-1) * pageSize + 1;
			int endRow = startRow + pageSize -1;
			int rowCount = adminMapper.getAdminlistCount("");  
			if (endRow > rowCount) endRow = rowCount;
			List<MovieDTO> list = null;
			if (rowCount>0){
			 list = adminMapper.AdminMovieList(startRow, endRow);	//�����ͺ��̽����� ��ȭ ��� �ҷ�����
			}
			int movieNum = rowCount - (startRow - 1);
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
			req.setAttribute("movieNum", movieNum);
			req.setAttribute("listAdmin", list);
			return "Admin/AdminList";
		}
		
		//������ε� �������� �̵�
		@RequestMapping(value="/goAdminUpload.admin", method=RequestMethod.GET)
		public String goAdminUpload() {
			return "Admin/AdminUpload";
		}
		
		//������ε�
		@RequestMapping(value="/doAdminUpload.admin", method=RequestMethod.POST)
		public String doAdminUpload(HttpServletRequest req, @ModelAttribute MovieDTO dto, 
				BindingResult result) {
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
			MultipartFile mf = mr.getFile("image");
			MultipartFile mv = mr.getFile("videofile");
			MultipartFile mv2 = mr.getFile("videofile2");
			MultipartFile mv3 = mr.getFile("videofile3");
			String filename = mf.getOriginalFilename();
			String videoname = mv.getOriginalFilename();
			String videoname2 = mv2.getOriginalFilename();
			String videoname3 = mv3.getOriginalFilename();
			dto.setImage(filename);
			dto.setVideofile(videoname);
			dto.setVideofile2(videoname2);
			dto.setVideofile3(videoname3);
			
			if (filename != null && !(filename.trim().equals("")) ||
				videoname != null && !(videoname.trim().equals(""))||
				videoname2 != null && !(videoname2.trim().equals(""))||
				videoname3 != null && !(videoname3.trim().equals("")))  {
				File file = new File(upPath, filename);
				File file2 = new File(upPath, videoname);
				File file3 = new File(upPath, videoname2);
				File file4 = new File(upPath, videoname3);
				try {
					mf.transferTo(file);
					mv.transferTo(file2);
					mv2.transferTo(file3);
					mv3.transferTo(file4);
				}catch(IOException e) {}
			}
		
			int res = adminMapper.insertMovie(dto);
			String msg = null, url = null;
			
			if(res > 0) {
				msg = "��ȭ�� ����߽��ϴ�. ��ȭ����������� �̵��մϴ�.";
				url = "goAdminList.admin";
			}else {
				msg = "��ȭ�� ������� ���߽��ϴ�. ��ȭ���ε�â���� �̵��մϴ�.";
				url = "goAdminUpload.admin";
			}
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			return "forward:WEB-INF/views/message.jsp";
		}
		
		//������ ���� �󼼺��� ������ �̵�
		@RequestMapping("/goAdminView.admin")
		public String movieView(HttpServletRequest req, @RequestParam int movienum) {
			MovieDTO dto = adminMapper.getMovie(movienum);
			req.setAttribute("getMovie", dto);
			return "Admin/AdminView";
		}
		
		//������ ���� ����
		@RequestMapping("/doAdmindelete.admin")
		public String doAdminDelete(HttpServletRequest req,
						@RequestParam Map<String, String> params) {
			int res = adminMapper.deleteMovie(Integer.parseInt(params.get("movienum")));
			String msg = null, url = "goAdminList.admin";
			
			if (res>0) {
				File file = new File(upPath, params.get("image"));
				if (file.exists()){
					file.delete();//file���� ���� �̹��� ���� ��Ű��
					msg = "��ȭ��������(�̹������� ����)!! ��ȭ����������� �̵��մϴ�.";
				}else {//image�� ���ų� ������ �����ߴٸ�
					msg = "��ȭ��������(�̹������� ����)!! ��ȭ����������� �̵��մϴ�.";
				}
			}else {
				msg = "��ȭ��������!! ��ȭ����������� �̵��մϴ�.";
			}
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			return "forward:WEB-INF/views/message.jsp";
		}
		
		//������ ���� ���� �������� �̵�
		@RequestMapping(value="/goAdminUpdate.admin", method=RequestMethod.GET)
		public String adminUpdate(HttpServletRequest req, @RequestParam int movienum) {
			MovieDTO dto = adminMapper.getMovie(movienum);
			System.out.println(dto.getMoviefile());
			req.setAttribute("getMovie", dto);
			int ages=dto.getAge();
			String ages_s = String.valueOf(ages);
			req.setAttribute("age", ages_s);
			return "Admin/AdminUpdate";
		}
			
	//������ ���� ���� �ൿ
	@RequestMapping(value="/doAdminUpdate.admin", method=RequestMethod.POST)
	public String adminUpdate(HttpServletRequest req, @ModelAttribute MovieDTO dto,
				BindingResult result) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("image");
		/*
		MultipartFile mv = mr.getFile("videofile");
		MultipartFile mv2 = mr.getFile("videofile2");
		MultipartFile mv3 = mr.getFile("videofile3");
		*/
		String filename = mf.getOriginalFilename();
		/*
		String videoname = mv.getOriginalFilename();
		String videoname2 = mv2.getOriginalFilename();
		String videoname3 = mv3.getOriginalFilename();
		*/
		
		if (filename != null && !(filename.trim().equals("")))  {
				File file = new File(upPath, filename);
				try {
					mf.transferTo(file);
				}catch(IOException e) {}
		}else {
			filename = req.getParameter("image2");//������ ���� image2�� ���� �����ϰ� filename�θ���
		}		
		dto.setImage(filename);
		/*
		dto.setVideofile(videoname);
		dto.setVideofile2(videoname2);
		dto.setVideofile3(videoname3);
		*/
		
		int res = adminMapper.updateMovie(dto);
		String msg = null, url = null;
		if (res>0) {
			msg = "��ȭ��������!! ��ȭ����������� �̵��մϴ�.";
			url = "goAdminList.admin";
		}else {
			msg = "��ȭ��������!! ��ȭ������������ �̵��մϴ�.";
			url = "goAdminUpdate.admin?movienum=" + dto.getMovienum();//�������н� �������Ͽ� ȭ�鿡 ���
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:WEB-INF/views/message.jsp";//�޼����� �ҷ��ֱ�
	}
		
		
		@RequestMapping("/goAdminFind.admin")
		public String adminFind(HttpServletRequest req, @RequestParam String title) {
			int pageSize = 10;
			String pageNum = req.getParameter("pageNum");
			if (pageNum==null){
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage-1) * pageSize + 1;
			int endRow = startRow + pageSize -1;
			int rowCount = adminMapper.getAdminlistCount(title);
			if (endRow > rowCount) endRow = rowCount;
			List<MovieDTO> find = null;
			if (rowCount>0){
				 find = adminMapper.findMovie(title, startRow, endRow);	//�����ͺ��̽����� ��ȭ ��� �ҷ�����
			}
			int movieNum = rowCount - (startRow - 1);
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
			req.setAttribute("movieNum", movieNum);
			req.setAttribute("listAdmin", find);
			req.setAttribute("title", title);
			return "Admin/AdminList";
		}
		
		//������������ ����
		@RequestMapping("/goGoodsMainPage.goods")
		public String goGoodsMainPage() {
			return "Goods/GoodsMainPage";
		}
		
		//�����ڷα׾ƿ�
		@RequestMapping("/doAdminLogout.movie")
		public String doLogout(HttpServletRequest req) {
			HttpSession session = req.getSession();
			session.removeAttribute("AdminloginData");
			
			req.setAttribute("msg", "������ �������� �α׾ƿ� �ϼ̽��ϴ�.");
			req.setAttribute("url", "goLogin.do");
			return "forward:WEB-INF/views/message.jsp";
		}
		
		//������ ���� �󼼺��� ������ �̵�
		@RequestMapping("/goAdminGoodsView.admin")
		public String AdminGoodsView(HttpServletRequest req, @RequestParam int gnum) {
			GoodsDTO dto = adminMapper.getAGoods(gnum);
			req.setAttribute("getAGoods", dto);
			return "Admin/AdminGoodsView";
		}
	
		
	
	
	//�� : ���ѽ� || �Ʒ� : �̾Ƹ�
	
		
		
	
		//������ ���� ���������� �̵�
		@RequestMapping("/goAdminGoodsList.admin")
		public String goAdminGoodsList(HttpServletRequest req) {
			
			int pageSize = 5;
			String pageNum = req.getParameter("pageNum");
			if (pageNum==null){
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage-1) * pageSize + 1;
			int endRow = startRow + pageSize -1;
			int rowCount = adminMapper.getGoodslistCount("");
			if (endRow > rowCount) endRow = rowCount;
			List<GoodsDTO> list = null;
			if(rowCount>0) {
			list = adminMapper.listGoodsPage(startRow, endRow);
			}
			int gNum = rowCount - (startRow - 1);
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
			req.setAttribute("gNum", gNum);
			req.setAttribute("listGoods", list);
			return "Admin/AdminGoodsList";
		}
	
	//������ ���� ��� ������ �̵�
	@RequestMapping("/goAdminGoodsInput.admin")
	public String goAdminGoodsInput(HttpServletRequest req) {
		List<MovieDTO> list = adminMapper.MovieList();
		req.setAttribute("listMovie", list);
		return "Admin/AdminGoodsInput";
	}
	
	//������ ���� ��� �ൿ
	@RequestMapping("/doAdminGoodsInput.admin")
	public String doAdminGoodsInput(HttpServletRequest req, @ModelAttribute GoodsDTO dto, 
			BindingResult result) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("gimage");
		MultipartFile mf2 = mr.getFile("gimage2");
		MultipartFile mf3 = mr.getFile("gimage3");
		MultipartFile mf4 = mr.getFile("gimage4");
		MultipartFile mf5 = mr.getFile("gimage5");
		String filename = mf.getOriginalFilename();
		String filename2 = mf2.getOriginalFilename();
		String filename3 = mf3.getOriginalFilename();
		String filename4 = mf4.getOriginalFilename();
		String filename5 = mf5.getOriginalFilename();
		dto.setGimage(filename);
		dto.setGimage2(filename2);
		dto.setGimage3(filename3);
		dto.setGimage4(filename4);
		dto.setGimage5(filename5);
		
		if (filename != null && !(filename.trim().equals(""))) {
			File file = new File(upPath, filename);
			File file2 = new File(upPath, filename2);
			File file3 = new File(upPath, filename3);
			File file4 = new File(upPath, filename4);
			File file5 = new File(upPath, filename5);
			try {
				mf.transferTo(file);
				mf2.transferTo(file2);
				mf3.transferTo(file3);
				mf4.transferTo(file4);
				mf5.transferTo(file5);
			}catch(IOException e) {}
		}
		
		int res = adminMapper.insertGoods(dto);
		String msg = null, url = null;
		if(res > 0) {
			msg = "���� ��� ����";
			url = "goAdminGoodsList.admin";
		}else {
			msg = "���� ��� ����";
			url = "goAdminGoodsInput.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";
	}

	//������ ���� ���� ������ �̵�
	@RequestMapping("/goAdminGoodsUpdate.admin")
	public String goAdminGoodsUpdate(HttpServletRequest req, @RequestParam int gnum) {
		GoodsDTO dto = adminMapper.getAGoods(gnum);
		String gspec[] = new String[] {"best", "newest", "season", "normal"};
		String gcategory[] = new String[] {"���ڹ�", "��", "������", "�׸���", "Ű��", "�ʸ���ũ", "������", "��Ÿ"};
		req.setAttribute("updateGoods", dto);
		req.setAttribute("gspec", gspec);
		req.setAttribute("gcategory", gcategory);
		
		List<MovieDTO> list = adminMapper.MovieList();//��? - moviename ����Ʈ�� �ʿ��մϴ�
		req.setAttribute("listMovie", list);
		return "Admin/AdminGoodsUpdate";
	}
	
	//������ ���� ���� �ൿ
	@RequestMapping("/doAdminGoodsUpdate.admin")
	public String doAdminGoodsUpdate(HttpServletRequest req, @ModelAttribute GoodsDTO dto, 
			BindingResult result) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("gimage");
		
		String gimage = mf.getOriginalFilename();
		if(gimage == null) gimage = mr.getParameter("gimage2");
		dto.setGimage(gimage);
		
		if(gimage != null && !(gimage.trim().equals(""))) {
			File file = new File(upPath, gimage);
			try {
				mf.transferTo(file);	//mf�� ���� file�� �����ϴ� ��
			}catch(IOException e) {}
		}
		
		int res = adminMapper.updateGoods(dto);
		String msg = null, url = null;
		if(res > 0) {
			msg = "��ǰ ���� ����";
			url = "goAdminGoodsList.admin";
		}else {
			msg = "��ǰ ���� ����";
			url = "goAdminGoodsUpdate.admin?gnum="+mr.getParameter("gnum");
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";
	}
	
	//������ ���� ���� �ൿ
	@RequestMapping("/doAdminGoodsDelete.admin")
	public String doAdminGoodsDelete(HttpServletRequest req) {
		String gnum = req.getParameter("gnum");
		String gimage = req.getParameter("gimage");
		int res = adminMapper.deleteGoods(Integer.parseInt(gnum));
		String msg = null, url = null;
		
		if(res > 0) {
			File file = new File(upPath, gimage);
			if(file.exists()){
				file.delete();
				msg = "��ǰ ���� ����, �̹��� ���� ����";
				url = "goAdminGoodsList.admin";
			}else{
				msg = "��ǰ ���� ����, �̹��� ���� ����.";
				url = "goAdminGoodsList.admin";
			}
		}else {
			msg = "��ǰ ���� ����.";
			url = "goAdminGoodsList.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";
	}
	
	@RequestMapping("/doAdminLogout.admin")
	public String doAdminLogout(HttpServletRequest req) {
		req.setAttribute("msg", "������ �������� �α׾ƿ��߽��ϴ�.");
		req.setAttribute("url", "goLogin.do");
		return "forward:/WEB-INF/views/message.jsp";
	}
	
	//������ ���� �˻�
	@RequestMapping("/goAdminFindGoods.admin")
	public String goAdminFindGoods(HttpServletRequest req) {
		String gname = req.getParameter("gname");
		int pageSize = 5;
		String pageNum = req.getParameter("pageNum");
		if (pageNum==null){
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize -1;
		int rowCount = adminMapper.getGoodslistCount(gname);
		if (endRow > rowCount) endRow = rowCount;
		List<GoodsDTO> list = null;
		if(rowCount>0) {
		list = adminMapper.adminFindGoods(gname, startRow, endRow);
		}
		int gNum = rowCount - (startRow - 1);
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
		req.setAttribute("gNum", gNum);
		req.setAttribute("listGoods", list);
		req.setAttribute("gname", gname);
		return "Admin/AdminGoodsList";
	}
	
	
	
	//�� : �̾Ƹ� || �Ʒ� : ������
	
	

	   
   //�⺰ ���� �������� ����
   @RequestMapping("/doYearSalelist.admin")
   public String doYearSalelist(HttpServletRequest req,@RequestParam(required = false) String mode) {
      HttpSession session = req.getSession();
      List<OrderlistDTO> list = null;
      String searchString = null;
      if(mode != null) {
         searchString = req.getParameter("searchString");
         if(searchString != null) {
            int pageSize=20;
            String pageNum=req.getParameter("pageNum");
            if(pageNum==null) {
               pageNum="1";
            }
            int currentPage=Integer.parseInt(pageNum);
            int startRow =(currentPage-1) * pageSize + 1;
            int endRow = startRow + pageSize -1;
            int rowCount = adminMapper.yearSaleCount(searchString);
            
            if(endRow > rowCount) endRow = rowCount;
            if(rowCount>0) {
               list=adminMapper.searchYear(startRow, endRow, searchString);
               int yearsum = adminMapper.YearSum(searchString);
               req.setAttribute("yearsum", yearsum);
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
         }else {
            list=new ArrayList<OrderlistDTO>();
         }
      }
      session.setAttribute("yearSalelist", list);
      req.setAttribute("mode", mode);
      req.setAttribute("year", searchString);
      return "Admin/AdminYearSale";
   }
   
   //�Ϻ� ���� �������� ����
   @RequestMapping("/doDaySalelist.admin")
   public String doDaySalelist(HttpServletRequest req,@RequestParam(required = false) String mode) {
      HttpSession session = req.getSession();
      List<OrderlistDTO> list = null;
      String date = null;
      if(mode != null) {
         date = (String)req.getParameter("date");
         if( date != null) {
            int daysum = adminMapper.DaySum(date);
            int pageSize=20;
            String pageNum=req.getParameter("pageNum");
            if(pageNum==null) {
               pageNum="1";
            }
            int currentPage=Integer.parseInt(pageNum);
            int startRow =(currentPage-1) * pageSize + 1;
            int endRow = startRow + pageSize -1;
            int rowCount = adminMapper.daySaleCount(date);
            
            if(endRow > rowCount) endRow = rowCount;
            
            if(rowCount>0) {
               
               list=adminMapper.searchDay(startRow, endRow, date);
               System.out.println(String.valueOf(list));
               
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
            req.setAttribute("daysum", daysum);
            
         }else {
            list=new ArrayList<OrderlistDTO>();
         }
      }
      session.setAttribute("daySalelist", list);
      req.setAttribute("mode", mode);
      req.setAttribute("date",date);
      return "Admin/AdminDaySale";
   }
   
   //���� ���� �������� ����
   @RequestMapping("/doMonthSalelist.admin")
   public String doMonthSalelist(HttpServletRequest req,@RequestParam(required = false) String mode) {
      HttpSession session = req.getSession();
      List<OrderlistDTO> list = null;
      String month = null, year=null;
      if(mode != null) {
         year=req.getParameter("year");
         month = req.getParameter("month");
         
         System.out.println(month);
         System.out.println(year);
      
         if( month != null&& year !=null) {
            int monthsum = adminMapper.MonthSum(year,month);
            
            int pageSize=20;
            String pageNum=req.getParameter("pageNum");
            if(pageNum==null) {
               pageNum="1";
            }
            int currentPage=Integer.parseInt(pageNum);
            int startRow =(currentPage-1) * pageSize + 1;
            int endRow = startRow + pageSize -1;
            int rowCount = adminMapper.monthSaleCount(month, year);
            if(endRow > rowCount) endRow = rowCount;
            
            if(rowCount>0) {
               
               list=adminMapper.searchMonth(startRow, endRow, month, year);
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
            req.setAttribute("monthsum", monthsum);
            
         }else {
            list=new ArrayList<OrderlistDTO>();
         }
      }
      session.setAttribute("monthSalelist", list);
      req.setAttribute("mode", mode);
      req.setAttribute("month",month);
      req.setAttribute("year", year);
      return "Admin/AdminMonthSale";
   }
   
	//���� �󼼺��� ������
	@RequestMapping("/goAdminSaleDetail.admin")
	public String goAdminSaleDetail(HttpServletRequest req,@RequestParam int ordernum) {
		OrderlistDTO dto = goodsMapper.getOrder(ordernum);
		MemberDTO mdto = adminMapper.getMember(dto.getOrdermember());		
		List<GoodsDTO> list = new ArrayList<>();
		String[] ordergoods = dto.getOrdergoods().split("_");
		
		for(int i=0; i<ordergoods.length; i++) {
			String tmp[] = ordergoods[i].split("/");
			if(tmp[0].equals("standard") || tmp[0].equals("pro") || tmp[0].equals("vip")) {
				GoodsDTO gdto = new GoodsDTO();
				gdto.setGname(tmp[0] + " �����");
				gdto.setGprice(dto.getTotalpay());
				gdto.setGqty(1);
				list.add(gdto);
				req.setAttribute("noImage", "noImage");
			}else {
				GoodsDTO gdto = goodsMapper.getGoods(Integer.parseInt(tmp[0]));
				gdto.setGqty(Integer.parseInt(tmp[1]));
				list.add(gdto);
			}
		}
		req.setAttribute("mdto", mdto);
		req.setAttribute("ordergoodsList", list);
		req.setAttribute("orderlistDTO", dto);
	   
	   return "Admin/AdminSaleDetail";
	}
}
