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
	
	//별도의 로그인 컨트롤러 생성 고려할것
	
	//로그인 페이지로 가기
	@RequestMapping("/goLogin.do")
	public String goLogin() {
		return "Login/Login";
	}
	
	//로그인하기
	@RequestMapping("/doLogin.do")
	public String doLogin(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam Map<String, String> params) {
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");
		
		int res = adminMapper.IdPwCheck(email, passwd);
		if(res < 0) {
			req.setAttribute("msg", "일치하는 이메일 혹은 비밀번호가 없습니다.");
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
			
			req.setAttribute("msg", "관리자 계정으로 로그인했습니다.");
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
			
			req.setAttribute("msg", "환영합니다, "+dto.getName()+"님!");
			req.setAttribute("url", "goMainPage.movie");
		}else {
			req.setAttribute("msg", "에러 발생! 다시 시도해 주세요.");
			req.setAttribute("url", "goLogin.do");
		}
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//회원가입 페이지로 가기
	@RequestMapping("/goNewMember.do")
	public String goNewMember() {
		return "Login/LoginNewMember";
	}
	
	//회원가입하기
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
		String memberimage = "basic.jpg";	//회원가입시에는 기본이미지로
		
		String address = req.getParameter("postcode")+"_"+req.getParameter("address")+
				"_"+req.getParameter("detailAddress");
		
		MemberDTO dto = new MemberDTO();
		dto.setEmail(email);	dto.setName(name);	dto.setPasswd(passwd);
		dto.setBirth(getBirth);	dto.setHp1(hp1);	dto.setHp2(hp2);
		dto.setHp3(hp3);		dto.setRate(rate);
		dto.setAddress(address);dto.setMemberimage(memberimage);
		
		int res = adminMapper.newMember(dto);
		if(res > 0) {
			req.setAttribute("msg", "회원 가입을 축하합니다, "+name+"님! 생성한 계정으로 로그인해주세요!");
			req.setAttribute("url", "goLogin.do");
		}else {
			req.setAttribute("msg", "회원 가입에 실패했습니다. 잠시 후 다시 시도하거나 관리자에게 문의해 주세요.");
			req.setAttribute("url", "goLogin.do");
		}
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//이메일 중복 확인
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
	
	//아이디/비밀번호 찾기페이지 이동
	@RequestMapping("/goSearch.do")
	public String goSearch(HttpServletRequest req) {
		String option = req.getParameter("option");
		String goSearchOption = null;
		
		if(option.equals("email")) {
			goSearchOption = "email";
		}else if(option.equals("passwd")) {
			goSearchOption = "passwd";
		}else {
			req.setAttribute("msg", "에러 발생! 잠시 후 다시 시도해 주세요!");
			req.setAttribute("url", "goLogin.do");
			return "forward:WEB-INF/views/message.jsp";
		}
		req.setAttribute("option", goSearchOption);
		return "Login/LoginSearch";
	}
	
	//아이디/비밀번호 찾기
	@RequestMapping("/doSearch.do")
	public String doSearch(HttpServletRequest req) {
		String option = req.getParameter("option");
		String email = null;
		
		if(option.equals("passwd")) {
			email = req.getParameter("email");
		}else if(option.equals("email")) {
			email = null;
		}else {
			req.setAttribute("msg", "에러 발생! 잠시 후 다시 시도해 주세요!");
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
			msg = "일치하는 회원이 없습니다.";
			url = "goLogin.do";
			return "forward:WEB-INF/views/message.jsp";
		}
		
		if(option.equals("passwd")) {
			Random random = new Random();
	        int checkNum = random.nextInt(888888) + 111111;
	        
	        int passRes = adminMapper.ChangePasswd(String.valueOf(checkNum), mdto.getEmail());
	        
	        if(passRes > 0) {
		        /* 이메일 보내기 */
		        String setFrom = "testmailsender22@naver.com";
		        String toMail = mdto.getEmail();
		        String title = "변경된 비밀번호를 확인해주세요.";
		        String content = 
		                mdto.getName() + "님! 요청에 따라 비밀번호가 변경되었습니다." +
		                "<br><br>" + 
		                "변경된 비밀번호는 " + checkNum + "입니다." + 
		                "<br>" + 
		                "로그인하신 다음 반드시 비밀번호를 변경해 주세요.";
	
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
		        msg = "변경된 비밀번호가 이메일로 발송되었습니다."
		        		+ " 비밀번호를 확인해 주세요.";
				url = "goLogin.do";
	        }else {
	        	msg = "에러가 발생했습니다. 잠시 후 다시 시도해 주세요.";
				url = "goLogin.do";
	        }
		}else if(option.equals("email")) {
			msg = "회원님의 이메일은 "+mdto.getEmail()+"입니다.";
			url = "goLogin.do";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:WEB-INF/views/message.jsp";
	}
	
	
	
	//위 : 김형준 || 아래 : 김한슬
	
	
	
	//관리자 메인 페이지 이동
		@RequestMapping("/goAdminManagerPage.admin")
		public String goAdminManagerPage(HttpServletRequest req) {
			return "Admin/AdminManagerPage";
		}
		
		//관리자 영상 관리 페이지 이동
		@RequestMapping("/goAdminList.admin")
		public String goAdminList(HttpServletRequest req) {
			HttpSession session = req.getSession();
			session.setAttribute("upPath", upPath);	//세션에 upPath 저장
			
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
			 list = adminMapper.AdminMovieList(startRow, endRow);	//데이터베이스에서 영화 목록 불러오기
			}
			int movieNum = rowCount - (startRow - 1);
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
			req.setAttribute("movieNum", movieNum);
			req.setAttribute("listAdmin", list);
			return "Admin/AdminList";
		}
		
		//영상업로드 페이지로 이동
		@RequestMapping(value="/goAdminUpload.admin", method=RequestMethod.GET)
		public String goAdminUpload() {
			return "Admin/AdminUpload";
		}
		
		//영상업로드
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
				msg = "영화를 등록했습니다. 영화목록페이지로 이동합니다.";
				url = "goAdminList.admin";
			}else {
				msg = "영화를 등록하지 못했습니다. 영화업로드창으로 이동합니다.";
				url = "goAdminUpload.admin";
			}
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			return "forward:WEB-INF/views/message.jsp";
		}
		
		//관리자 영상 상세보기 페이지 이동
		@RequestMapping("/goAdminView.admin")
		public String movieView(HttpServletRequest req, @RequestParam int movienum) {
			MovieDTO dto = adminMapper.getMovie(movienum);
			req.setAttribute("getMovie", dto);
			return "Admin/AdminView";
		}
		
		//관리자 영상 삭제
		@RequestMapping("/doAdmindelete.admin")
		public String doAdminDelete(HttpServletRequest req,
						@RequestParam Map<String, String> params) {
			int res = adminMapper.deleteMovie(Integer.parseInt(params.get("movienum")));
			String msg = null, url = "goAdminList.admin";
			
			if (res>0) {
				File file = new File(upPath, params.get("image"));
				if (file.exists()){
					file.delete();//file값을 삭제 이미지 삭제 시키기
					msg = "영화삭제성공(이미지삭제 성공)!! 영화목록페이지로 이동합니다.";
				}else {//image가 없거나 삭제에 실패했다면
					msg = "영화삭제성공(이미지삭제 실패)!! 영화목록페이지로 이동합니다.";
				}
			}else {
				msg = "영화삭제실패!! 영화목록페이지로 이동합니다.";
			}
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			return "forward:WEB-INF/views/message.jsp";
		}
		
		//관리자 영상 수정 페이지로 이동
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
			
	//관리자 영상 수정 행동
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
			filename = req.getParameter("image2");//수정값 받은 image2로 값을 수정하고 filename부른다
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
			msg = "영화수정성공!! 영화목록페이지로 이동합니다.";
			url = "goAdminList.admin";
		}else {
			msg = "영화수정실패!! 영화수정페이지로 이동합니다.";
			url = "goAdminUpdate.admin?movienum=" + dto.getMovienum();//수정실패시 값유지하여 화면에 출력
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:WEB-INF/views/message.jsp";//메세지값 불러주기
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
				 find = adminMapper.findMovie(title, startRow, endRow);	//데이터베이스에서 영화 목록 불러오기
			}
			int movieNum = rowCount - (startRow - 1);
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
			req.setAttribute("movieNum", movieNum);
			req.setAttribute("listAdmin", find);
			req.setAttribute("title", title);
			return "Admin/AdminList";
		}
		
		//굿즈페이지와 연결
		@RequestMapping("/goGoodsMainPage.goods")
		public String goGoodsMainPage() {
			return "Goods/GoodsMainPage";
		}
		
		//관리자로그아웃
		@RequestMapping("/doAdminLogout.movie")
		public String doLogout(HttpServletRequest req) {
			HttpSession session = req.getSession();
			session.removeAttribute("AdminloginData");
			
			req.setAttribute("msg", "관리자 계정에서 로그아웃 하셨습니다.");
			req.setAttribute("url", "goLogin.do");
			return "forward:WEB-INF/views/message.jsp";
		}
		
		//관리자 굿즈 상세보기 페이지 이동
		@RequestMapping("/goAdminGoodsView.admin")
		public String AdminGoodsView(HttpServletRequest req, @RequestParam int gnum) {
			GoodsDTO dto = adminMapper.getAGoods(gnum);
			req.setAttribute("getAGoods", dto);
			return "Admin/AdminGoodsView";
		}
	
		
	
	
	//위 : 김한슬 || 아래 : 이아린
	
		
		
	
		//관리자 굿즈 메인페이지 이동
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
	
	//관리자 굿즈 등록 페이지 이동
	@RequestMapping("/goAdminGoodsInput.admin")
	public String goAdminGoodsInput(HttpServletRequest req) {
		List<MovieDTO> list = adminMapper.MovieList();
		req.setAttribute("listMovie", list);
		return "Admin/AdminGoodsInput";
	}
	
	//관리자 굿즈 등록 행동
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
			msg = "굿즈 등록 성공";
			url = "goAdminGoodsList.admin";
		}else {
			msg = "굿즈 등록 실패";
			url = "goAdminGoodsInput.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";
	}

	//관리자 굿즈 수정 페이지 이동
	@RequestMapping("/goAdminGoodsUpdate.admin")
	public String goAdminGoodsUpdate(HttpServletRequest req, @RequestParam int gnum) {
		GoodsDTO dto = adminMapper.getAGoods(gnum);
		String gspec[] = new String[] {"best", "newest", "season", "normal"};
		String gcategory[] = new String[] {"에코백", "컵", "포스터", "그립툭", "키링", "필름마크", "문구류", "기타"};
		req.setAttribute("updateGoods", dto);
		req.setAttribute("gspec", gspec);
		req.setAttribute("gcategory", gcategory);
		
		List<MovieDTO> list = adminMapper.MovieList();//왜? - moviename 리스트가 필요합니다
		req.setAttribute("listMovie", list);
		return "Admin/AdminGoodsUpdate";
	}
	
	//관리자 굿즈 수정 행동
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
				mf.transferTo(file);	//mf의 값을 file에 전송하는 것
			}catch(IOException e) {}
		}
		
		int res = adminMapper.updateGoods(dto);
		String msg = null, url = null;
		if(res > 0) {
			msg = "상품 수정 성공";
			url = "goAdminGoodsList.admin";
		}else {
			msg = "상품 수정 실패";
			url = "goAdminGoodsUpdate.admin?gnum="+mr.getParameter("gnum");
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";
	}
	
	//관리자 굿즈 삭제 행동
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
				msg = "상품 삭제 성공, 이미지 삭제 성공";
				url = "goAdminGoodsList.admin";
			}else{
				msg = "상품 삭제 성공, 이미지 삭제 실패.";
				url = "goAdminGoodsList.admin";
			}
		}else {
			msg = "상품 삭제 실패.";
			url = "goAdminGoodsList.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";
	}
	
	@RequestMapping("/doAdminLogout.admin")
	public String doAdminLogout(HttpServletRequest req) {
		req.setAttribute("msg", "관리자 계정에서 로그아웃했습니다.");
		req.setAttribute("url", "goLogin.do");
		return "forward:/WEB-INF/views/message.jsp";
	}
	
	//관리자 굿즈 검색
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
	
	
	
	//위 : 이아린 || 아래 : 윤다인
	
	

	   
   //년별 매출 페이지로 가기
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
   
   //일별 매출 페이지로 가기
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
   
   //월별 매출 페이지로 가기
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
   
	//매출 상세보기 페이지
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
				gdto.setGname(tmp[0] + " 요금제");
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
