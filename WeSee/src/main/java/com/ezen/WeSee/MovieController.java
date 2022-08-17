package com.ezen.WeSee;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.WeSee.service.AdminMapper;
import com.ezen.WeSee.service.GoodsMapper;
import com.ezen.WeSee.service.MovieMapper;
import com.ezen.WeSee.dto.*;

@Controller
public class MovieController {

	@Resource(name="uploadPath")
	private String upPath;
	
	@Autowired
	private MovieMapper movieMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	//마이페이지 이동
	@RequestMapping("/goMyPage.movie")
	public String goMyPage(HttpServletRequest req) throws ParseException {
		HttpSession session = req.getSession();
		session.setAttribute("upPath", upPath);
		
		String membership[] = new String[] {"요금제 미가입자", "기본 요금제", "Pro 요금제", "VIP 요금제"};
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		for(int i=0; i<4; i++) {
			if(i == dto.getFe()) {
				req.setAttribute("memberFe", membership[i]);
			}
		}
		
		if(dto.getWish() != null) {
			String wish[] = dto.getWish().split("_");
			
			if(wish.length > 3) {
				MovieDTO wishList[] = new MovieDTO[3];
				for(int i=0; i<3; i++) {
					wishList[i] = movieMapper.getMovie(Integer.parseInt(wish[i]));
				}
				req.setAttribute("wishList", wishList);
			}else {
				MovieDTO wishList[] = new MovieDTO[wish.length];
				for(int i=0; i<wish.length; i++) {
					wishList[i] = movieMapper.getMovie(Integer.parseInt(wish[i]));
				}
				req.setAttribute("wishList", wishList);
			}
		}
		
		if(dto.getWatch() != null) {
			String watch[] = dto.getWatch().split("_");
			
			if(watch.length > 3) {
				MovieDTO watchList[] = new MovieDTO[3];
				for(int i=0; i<3; i++) {
					watchList[i] = movieMapper.getMovie(Integer.parseInt(watch[i]));
				}
				req.setAttribute("watchList", watchList);
			}else {
				MovieDTO watchList[] = new MovieDTO[watch.length];
				for(int i=0; i<watch.length; i++) {
					watchList[i] = movieMapper.getMovie(Integer.parseInt(watch[i]));
				}
				req.setAttribute("watchList", watchList);
			}
		}
		if(dto.getAutopay() == 0) {
			LocalDateTime now = LocalDateTime.now();
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			int compare = dto.getPaydate().compareTo(formatedNow);
			System.out.print(compare);
			if(compare<0) {
				dto.setFe(0);
				dto.setAutopay(2);
				dto.setPaydate(null);
				int res = movieMapper.paydateOver(dto);
					if(res > 0) {
						session.setAttribute("loginData", dto);
						req.setAttribute("msg", "요금제가 종료되었습니다.");
						req.setAttribute("url", "goMyPage.movie");
					}else {
						req.setAttribute("msg", "에러 발생!");
						req.setAttribute("url", "goMyPage.movie");
					}
					return "forward:/WEB-INF/views/message.jsp";
			}else if(compare>=0){
			
				return "Movie/MovieMyPage";
			}
			
		}else if(dto.getAutopay() == 1) {
			LocalDateTime now = LocalDateTime.now();
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			
			LocalDateTime now2 = LocalDateTime.now();
			String formatedNow2 = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
			
			Map<String, String> values = new Hashtable<>();
			values.put("ordermember", String.valueOf(dto.getMembernum()));
			String ordergoods = null;
			if(dto.getFe()==1) {
				ordergoods = "standard/1";
				values.put("ordergoods", ordergoods);
				values.put("payment", "9900");
				values.put("totalpay", "9900");
			}else if(dto.getFe()==2) {
				ordergoods = "pro/1";
				values.put("ordergoods", ordergoods);
				values.put("payment", "12900");
				values.put("totalpay", "9900");
			}else if(dto.getFe()==3) {
				ordergoods = "vip/1";
				values.put("ordergoods", ordergoods);
				values.put("payment", "19900");
				values.put("totalpay", "9900");
			}
			values.put("stage", "1");
			values.put("memo", "0");
			values.put("orderdate", formatedNow2);
			
			
			int compare = dto.getPaydate().compareTo(formatedNow);
			System.out.print(compare);
			if(compare<0) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = dateFormat.parse(dto.getPaydate());
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DATE, 30);
				String strDate = dateFormat.format(cal.getTime());
				
				dto.setPaydate(strDate);
				int res = movieMapper.paydateRenew(dto);
				int res2 = goodsMapper.insertOrderlist(values);
					if(res > 0) {
						session.setAttribute("loginData", dto);
						req.setAttribute("msg", "요금제가 갱신되었습니다.");
						req.setAttribute("url", "goMyPage.movie");
					}else {
						req.setAttribute("msg", "에러 발생!");
						req.setAttribute("url", "goMyPage.movie");
					}
				return "forward:/WEB-INF/views/message.jsp";
			}else if(compare>=0){
			
				return "Movie/MovieMyPage";
			
			}
		}
		return "Movie/MovieMyPage";
			
	}
	
	//회원정보수정 이동 전 비밀번호 확인 페이지로 이동 
	@RequestMapping("/goPasswdCheck.movie")
	public String goPasswdCheck() {
		return "Movie/MoviePasswdCheck";
	}
	
	//회원정보수정 이동 전 비밀번호 확인
	@RequestMapping("/doPasswdCheck.movie")
	public String doPasswdCheck(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		String passwd = req.getParameter("passwd");
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		String tpasswd = dto.getPasswd();
		
		if(passwd.equals(tpasswd)) {
			return "Movie/MoviePasswdCheck_ok";
		}else {
			req.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
			req.setAttribute("url", "goPasswdCheck.movie");
			return "forward:WEB-INF/views/message.jsp";
		}
	}
	
	//회원정보수정 페이지로 이동
	@RequestMapping("/goMemberEdit.movie")
	public String goMemberEdit(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		String address = dto.getAddress();
		if(address != null) {
			String[] addr = address.split("_");
			req.setAttribute("addr", addr);
		}
		return "Movie/MovieMemberEdit";//회원정보수정 페이지
	}
	
	//회원정보수정
	@RequestMapping("/doMemberEdit.movie")
	public String doMemberEdit(HttpServletRequest req, @ModelAttribute MemberDTO dto, 
				BindingResult result) {

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("image");
		String filename = mf.getOriginalFilename();
		
		if (filename != null && !(filename.trim().equals(""))) {
			File file = new File(upPath, filename);
			dto.setMemberimage(filename);
			try {
				mf.transferTo(file);
			}catch(IOException e) {}
		}else {
			dto.setMemberimage("basic.jpg");
		}
		
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
		
		String memberimage = req.getParameter("image");
		if(memberimage == null || memberimage.trim().equals("")) {
			memberimage = "basic.jpg";	//이미지 등록 안하면 기본이미지로.
		}
		
		String address = req.getParameter("postcode")+"_"+req.getParameter("address")+
				"_"+req.getParameter("detailAddress");
		
		HttpSession session = mr.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginData");
		dto.setMembernum(mdto.getMembernum());
		
		dto.setRate(rate);
		dto.setAddress(address);
		dto.setMemberimage(memberimage);
		
		int res = movieMapper.updateMember(dto);
		String msg = null, url = null;
		
		if(res > 0) {
			msg = "회원 정보를 수정했습니다.";
			url = "goMyPage.movie";
			
			MemberDTO mmdto = adminMapper.getMember(mdto.getMembernum());
			session.setAttribute("loginData", mmdto);
		}else {
			msg = "정보 수정에 실패했습니다! 잠시 후 다시 시도해 주세요.";
			url = "goMemberEdit.movie";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";
	}
	
	//로그아웃
	@RequestMapping("/doLogout.movie")
	public String doLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("loginData");
		
		req.setAttribute("msg", "로그아웃 되었습니다.");
		req.setAttribute("url", "goLogin.do");
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//요금제 페이지로 이동
	@RequestMapping("/goMembership.movie")
	public String goMembership(HttpServletRequest req){
		HttpSession session = req.getSession();
		String membership[] = new String[] {"요금제 미가입자", "기본 요금제", "Pro 요금제", "VIP 요금제"};
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		for(int i=0; i<4; i++) {
			if(i == dto.getFe()) {
				req.setAttribute("memberFe", membership[i]);
			}
		}
		return "Movie/MovieMembership";
	}
	
	//요금제 변경
		@RequestMapping("/doMembership.movie")
		public String doMembership(HttpServletRequest req, @RequestParam int fe) {
			HttpSession session = req.getSession();
			MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
			
			LocalDateTime now = LocalDateTime.now();
			now = now.plusDays(30);
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			LocalDateTime now2 = LocalDateTime.now();
			String formatedNow2 = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
			
			Map<String, String> values = new Hashtable<>();
			values.put("ordermember", String.valueOf(dto.getMembernum()));
			String ordergoods = null;
			if(fe==1) {
				ordergoods = "standard/1";
				values.put("ordergoods", ordergoods);
				values.put("payment", "9900");
				values.put("totalpay", "9900");
			}else if(fe==2) {
				ordergoods = "pro/1";
				values.put("ordergoods", ordergoods);
				values.put("payment", "12900");
				values.put("totalpay", "9900");
			}else if(fe==3) {
				ordergoods = "vip/1";
				values.put("ordergoods", ordergoods);
				values.put("payment", "19900");
				values.put("totalpay", "9900");
			}
			values.put("stage", "1");
			values.put("memo", "0");
			values.put("orderdate", formatedNow2);
			 
			if(fe == 1 || fe == 2 || fe == 3) {
				dto.setFe(fe);
				dto.setAutopay(1);
				if(dto.getPaydate() == null) {
					dto.setPaydate(formatedNow);
					int res = movieMapper.updateFe(dto);
					int res2 = goodsMapper.insertOrderlist(values); 
					if(res > 0) {
						session.setAttribute("loginData", dto);
						req.setAttribute("msg", "요금제 가입이 완료되셨습니다.");
						req.setAttribute("url", "goMyPage.movie");
					}else {
						req.setAttribute("msg", "에러 발생! 요금제 가입에 실패했습니다. 잠시 후 다시 시도해 주세요.");
						req.setAttribute("url", "goMembership.movie");
					}
				}else {
					int res = movieMapper.updateOnlyFe(dto);
					if(res > 0) {
						session.setAttribute("loginData", dto);
						req.setAttribute("msg", "요금제를 변경했습니다.");
						req.setAttribute("url", "goMyPage.movie");
					}else {
						req.setAttribute("msg", "에러 발생! 요금제 변경에 실패했습니다. 잠시 후 다시 시도해 주세요.");
						req.setAttribute("url", "goMembership.movie");
					}
				}
			}else {
				req.setAttribute("msg", "에러 발생! 잠시 후 다시 시도해 주세요.");
				req.setAttribute("url", "goMembership.movie");
			}
			return "forward:WEB-INF/views/message.jsp";
		}
	
	//요금제 중단
	@RequestMapping("/doMembershipStop.movie")
	public String doMemberShipStop(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		dto.setAutopay(0);
		int res = movieMapper.stopFe(dto);
		
		if(res > 0) {
			session.setAttribute("loginData", dto);
			req.setAttribute("msg", "자동결제가 해지되었습니다.");
			req.setAttribute("url", "goMyPage.movie");
		}else {
			req.setAttribute("msg", "에러 발생! 요금제 변경에 실패했습니다. 잠시 후 다시 시도해 주세요.");
			req.setAttribute("url", "goMembership.movie");
		}
		
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//회원탈퇴 확인
	@RequestMapping("/goAskDelete.movie")
	public String goAskDelete(HttpServletRequest req) {
		req.setAttribute("msg",	"정말로 회원을 탈퇴하시겠습니까?");
		req.setAttribute("url",	"doDeleteMember.movie");
		return "forward:WEB-INF/views/confirm.jsp";
	}
	
	//회원탈퇴
	@RequestMapping("/doDeleteMember.movie")
	public String doDeleteMember(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		int membernum = dto.getMembernum();
		
		
		int res = goodsMapper.deleteMemberOrder(membernum);
		if(res>0) {
			System.out.println("포레인멤버키에 걸려있는 오더리스트 삭제완료");
		}else {
			System.out.println("포레인멤버키에 걸려있는 오더리스트 삭제실패!!!!!");
		}
		
		int res1 = movieMapper.deleteMember(membernum);
		
		String msg=null, url=null;
		if(res1 > 0) {
			msg="회원 탈퇴가 완료되었습니다. 그동안 WeSee를 이용해주셔서 감사합니다.";
			
			
			session.invalidate();
		}else {
			msg="오류가 발생했습니다. 회원 탈퇴에 실패했습니다.";
			url="goMemberEdit.movie";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//회원탈퇴 페이지로 이동
		@RequestMapping("/goMemberDelete.movie")
		public String goDeleteMember() {
			return "Movie/MovieMemberDelete_ok";
		}
	
	//찜한 영상 목록 페이지로 이동
	@RequestMapping("/goWishList.movie")
	public String goWishList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String wish = dto.getWish();
		
		if(wish == null) {
			req.setAttribute("msg", "찜한 영상이 없습니다.");
			req.setAttribute("url", "goMyPage.movie");
			return "forward:WEB-INF/views/message.jsp";
		}else {
			String wishList[] = wish.split("_");
			
			List<MovieDTO> list = new ArrayList<>();
			
			for(String input : wishList) {
				MovieDTO mdto = movieMapper.getMovie(Integer.parseInt(input));
				list.add(mdto);
			}
			req.setAttribute("wishList", list);
			return "Movie/MovieListView";
		}
	}
	
	//본 영상 목록 페이지로 이동
	@RequestMapping("/goWatchList.movie")
	public String goWatchList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String watch = dto.getWatch();
		
		if(watch == null) {
			req.setAttribute("msg", "본 영상이 없습니다.");
			req.setAttribute("url", "goMyPage.movie");
			return "forward:WEB-INF/views/message.jsp";
		}else {
			String watchList[] = dto.getWatch().split("_");
			
			List<MovieDTO> list = new ArrayList<>();
			
			for(String input : watchList) {
				MovieDTO mdto = movieMapper.getMovie(Integer.parseInt(input));
				list.add(mdto);
			}
			req.setAttribute("watchList", list);
			return "Movie/MovieListView";
		}
	}
	
	//찜하기
	@RequestMapping("/doMovieWish.movie")
	public String doMovieWish(HttpServletRequest req, @RequestParam int movienum) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String wish = dto.getWish();
		
		if(wish == null) {
			wish = String.valueOf(movienum);
		}else {
			wish = String.valueOf(movienum) + "_" + wish;
		}
		
		int res = movieMapper.updateWish(dto.getMembernum(), wish);
		String msg=null;
		if(res > 0) {
			MemberDTO mdto = adminMapper.getMember(dto.getMembernum());
			session.setAttribute("loginData", mdto);
			
			msg="찜 목록에 추가했습니다!";
		}else {
			msg="오류 발생! 찜 목록 추가에 실패했습니다.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "goMovieContents.movie?movienum="+movienum);
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//찜목록에서 제거
	@RequestMapping("/doMovieWishDelete.movie")
	public String doMovieWishDelete(HttpServletRequest req, @RequestParam int movienum) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String wishes[] = dto.getWish().split("_");
		ArrayList<String> tmpList = new ArrayList<>(Arrays.asList(wishes));
		
		String wishList = null;
		
		for(int i=0; i<wishes.length; i++) {
			if(wishes[i].equals(String.valueOf(movienum))) {
				tmpList.remove(i);
			}
		}
		
		wishes = tmpList.toArray(new String[0]);
	    for(int i=0; i<wishes.length; i++) {
	    	if(wishList == null) {
				wishList = wishes[i];
			}else {
				wishList = wishList + "_" + wishes[i];
			}
	    }
	    
	    if(wishList == null) wishList = "";
		int res = movieMapper.updateWish(dto.getMembernum(), wishList);
	    String msg=null;
	    if(res > 0) {
			MemberDTO mdto = adminMapper.getMember(dto.getMembernum());
			session.setAttribute("loginData", mdto);						
			msg="찜 목록에서 삭제했습니다!";
		}else {
			msg="오류 발생! 찜 목록에서 삭제에 실패했습니다.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "goMovieContents.movie?movienum="+movienum);
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//본영상목록 이동
	@RequestMapping("/goWatchList_go.movie")
	public String goWatchList_go(HttpServletRequest req) {
		return null;
	}
	
	
	//위 : 김형준 || 아래 : 이아린
	
	
	
	//찾기 페이지로 이동
	@RequestMapping("/goMovieNameSearch.movie")
	public String doMovieDetail(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		List<MovieDTO> list = movieMapper.findMovieList(title);
		req.setAttribute("findMovie", list);
		req.setAttribute("title", title);
		return "Movie/MovieNameSearch";
		}
	
	//영화메인페이지로 이동
	@RequestMapping("/goMainPage.movie")
	public String goMainPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("upPath", upPath);
		
		List<MovieDTO> rlist = movieMapper.recMovie();
		req.setAttribute("recMovie", rlist);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		if(dto.getWish() != null) {
			String wish[] = dto.getWish().split("_");
			
			if(wish.length > 7) {
				MovieDTO wishList[] = new MovieDTO[7];
				for(int i=0; i<7; i++) {
					wishList[i] = movieMapper.getMovie(Integer.parseInt(wish[i]));
				}
				req.setAttribute("wishList", wishList);
			}else {
				MovieDTO wishList[] = new MovieDTO[wish.length];
				for(int i=0; i<wishList.length; i++) {
					wishList[i] = movieMapper.getMovie(Integer.parseInt(wish[i]));
				}
				req.setAttribute("wishList", wishList);
			}
		}
		
		List<MovieDTO> ylist = movieMapper.yearlyMovie();
		req.setAttribute("yearlyMovie", ylist);

		return "Movie/MovieMainPage";
	}
	
	//영화 재생
	@RequestMapping("/goMoviePlay.movie")
	public String goMoviePlay(HttpServletRequest req, @RequestParam int movienum) {
		MovieDTO mdto = movieMapper.getMovie(movienum);
		req.setAttribute("getMovie", mdto);
		
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		if(Integer.parseInt(dto.getRate()) < mdto.getAge()) {
			req.setAttribute("msg", "이 영화는" + mdto.getAge() + "세 관람가입니다. "
					+ "회원님은 "+dto.getRate()+"세 관람가 영화까지만 관람하실 수 있습니다.");
			req.setAttribute("url", "goMovieContents.movie?movienum="+movienum);
			return "forward:WEB-INF/views/message.jsp";
		}
		
		return "Movie/MoviePlay";
	}
		
	
		
		
		//위 : 이아린 || 아래 : 김민
		
		
		
				
	//영화 장르별 보기 페이지 이동
	@RequestMapping("/goMovieDetail.movie")
	public String goMovieDetail(HttpServletRequest req) {
		
		List<MovieDTO> action = movieMapper.selectByGenre("액션");
		List<MovieDTO> sf = movieMapper.selectByGenre("SF");
		List<MovieDTO> drama = movieMapper.selectByGenre("드라마");
		List<MovieDTO> comedy = movieMapper.selectByGenre("코미디");
		List<MovieDTO> romance = movieMapper.selectByGenre("로맨스");
		List<MovieDTO> thriller = movieMapper.selectByGenre("스릴러");
		
		req.setAttribute("action", action);
		req.setAttribute("sf", sf);
		req.setAttribute("drama", drama);
		req.setAttribute("comedy", comedy);
		req.setAttribute("romance", romance);
		req.setAttribute("thriller", thriller);
		
		return "Movie/MovieDetail";
	}	
	
	//영화 상세보기 페이지 이동
	@RequestMapping("/goMovieContents.movie") 
	public String goMovieContents(HttpServletRequest req, @RequestParam int movienum){
		MovieDTO mdto = movieMapper.getMovie(movienum);
		req.setAttribute("getMovie", mdto);
		
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		if(dto.getWish() != null) {
			String wishes[] = dto.getWish().split("_");
			for(int i=0; i<wishes.length; i++) {
				if(wishes[i].equals(String.valueOf(movienum))) {
					req.setAttribute("already", 1);
				}
			}
		}
		//추가 코드
		List<MovieDTO> samegenre = new ArrayList<MovieDTO>();
		List<MovieDTO> inqGenre = new ArrayList<MovieDTO>();
		MovieDTO dto2 = movieMapper.getMovie(movienum);
		req.setAttribute("getMovie", dto2);
		String genre = dto2.getGenre().trim();
		System.out.println(genre);
		String [] sGen;
		sGen = genre.split(",");
		for(int i=0; i < 1; i++) {
			String param = sGen[i].trim();
			System.out.println(param);
			inqGenre = movieMapper.sameGenre(param);	
			samegenre.addAll(inqGenre);
		}
		req.setAttribute("samegenre", samegenre);
		
		//조회수 증가
		int res = movieMapper.plusViewcount(movienum);
		if(res > 0) {
			return "Movie/MovieContents";
		}else {
			req.setAttribute("msg", "조회수 증가에 실패했습니다.");
			req.setAttribute("url", "goMainPage.movie");
			return "forward:WEB-INF/views/message.jsp";
		}
	}
	
	//영화 검색하기
	@RequestMapping("/doSearchMovie.movie") 
	public String doSearchmovie(HttpServletRequest req) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		List<MovieDTO> list = new ArrayList<MovieDTO>();
		String word = req.getParameter("word");
		String search = req.getParameter("search");
		System.out.println(word);
		if(search.equals("genre")) {
			System.out.println("장르");
		    list = movieMapper.searchgenre(word);
		    req.setAttribute("word", word);
		} else {
			System.out.println("제목");
			list = movieMapper.searchtitle(word);
			if(list.size() != 0) {
			    MovieDTO dto = list.get(0);
			    req.setAttribute("word", dto.getTitle());
			}
		}
		req.setAttribute("searchmovie", list);
		req.setAttribute("word", word);
		//System.out.println(list);
		return "Movie/MovieDetail";
	}
	
	
	
	//위 : 김민 || 아래 : 김동완
	
	
	
	//장르별 이미지 보기
	@RequestMapping("/doSameGenre.movie")
	public String doSameGenre(HttpServletRequest req, @RequestParam int movienum){
		MovieDTO dto = movieMapper.getMovie(movienum);
		req.setAttribute("getMovie", dto);
		String genre = dto.getGenre();
		List<MovieDTO> samegenre = movieMapper.sameGenre(genre);
		return "Movie/MovieContents";
	}
	
	//영상 보러가기
	@RequestMapping("/doViewMovie.movie")
	public String doViewMovie(HttpServletRequest req, @RequestParam int movienum){
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String watch[] = null;
		String watchList = null;
		
		if(dto.getWatch() != null) {
			watch = dto.getWatch().split("_");
			ArrayList<String> tmpList = new ArrayList<>(Arrays.asList(watch));
			
			for(int i=0; i<watch.length; i++) {
				if(watch[i].equals(String.valueOf(movienum))) {
					tmpList.remove(i);
				}
			}
			watch = tmpList.toArray(new String[0]);
			for(int i=0; i<watch.length; i++) {
				if(watchList == null) {
					watchList = watch[i];
				}else {
					watchList = watchList + "_" + watch[i];
				}
		    }
		}
		
		if(watchList == null) {
			watchList = String.valueOf(movienum);
		}else {
			watchList = String.valueOf(movienum) + "_" + watchList;
		}
	    
		int res = movieMapper.updateWatch(dto.getMembernum(), watchList);
		if(res > 0) {
			MemberDTO mdto = adminMapper.getMember(dto.getMembernum());
			session.setAttribute("loginData", mdto);
			
			req.setAttribute("msg", "영상시청을 완료하였습니다.");
			req.setAttribute("url", "goMainPage.movie");
			return "forward:WEB-INF/views/message.jsp";
		}else {
			req.setAttribute("msg", "본 영상 목록 업데이트에 실패했습니다.");
			req.setAttribute("url", "goMainPage.movie");
			return "forward:WEB-INF/views/message.jsp";
		}
	}
}
