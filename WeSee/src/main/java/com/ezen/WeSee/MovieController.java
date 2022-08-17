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
	
	//���������� �̵�
	@RequestMapping("/goMyPage.movie")
	public String goMyPage(HttpServletRequest req) throws ParseException {
		HttpSession session = req.getSession();
		session.setAttribute("upPath", upPath);
		
		String membership[] = new String[] {"����� �̰�����", "�⺻ �����", "Pro �����", "VIP �����"};
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
						req.setAttribute("msg", "������� ����Ǿ����ϴ�.");
						req.setAttribute("url", "goMyPage.movie");
					}else {
						req.setAttribute("msg", "���� �߻�!");
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
						req.setAttribute("msg", "������� ���ŵǾ����ϴ�.");
						req.setAttribute("url", "goMyPage.movie");
					}else {
						req.setAttribute("msg", "���� �߻�!");
						req.setAttribute("url", "goMyPage.movie");
					}
				return "forward:/WEB-INF/views/message.jsp";
			}else if(compare>=0){
			
				return "Movie/MovieMyPage";
			
			}
		}
		return "Movie/MovieMyPage";
			
	}
	
	//ȸ���������� �̵� �� ��й�ȣ Ȯ�� �������� �̵� 
	@RequestMapping("/goPasswdCheck.movie")
	public String goPasswdCheck() {
		return "Movie/MoviePasswdCheck";
	}
	
	//ȸ���������� �̵� �� ��й�ȣ Ȯ��
	@RequestMapping("/doPasswdCheck.movie")
	public String doPasswdCheck(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		String passwd = req.getParameter("passwd");
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		String tpasswd = dto.getPasswd();
		
		if(passwd.equals(tpasswd)) {
			return "Movie/MoviePasswdCheck_ok";
		}else {
			req.setAttribute("msg", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			req.setAttribute("url", "goPasswdCheck.movie");
			return "forward:WEB-INF/views/message.jsp";
		}
	}
	
	//ȸ���������� �������� �̵�
	@RequestMapping("/goMemberEdit.movie")
	public String goMemberEdit(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		String address = dto.getAddress();
		if(address != null) {
			String[] addr = address.split("_");
			req.setAttribute("addr", addr);
		}
		return "Movie/MovieMemberEdit";//ȸ���������� ������
	}
	
	//ȸ����������
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
			memberimage = "basic.jpg";	//�̹��� ��� ���ϸ� �⺻�̹�����.
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
			msg = "ȸ�� ������ �����߽��ϴ�.";
			url = "goMyPage.movie";
			
			MemberDTO mmdto = adminMapper.getMember(mdto.getMembernum());
			session.setAttribute("loginData", mmdto);
		}else {
			msg = "���� ������ �����߽��ϴ�! ��� �� �ٽ� �õ��� �ּ���.";
			url = "goMemberEdit.movie";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "forward:/WEB-INF/views/message.jsp";
	}
	
	//�α׾ƿ�
	@RequestMapping("/doLogout.movie")
	public String doLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("loginData");
		
		req.setAttribute("msg", "�α׾ƿ� �Ǿ����ϴ�.");
		req.setAttribute("url", "goLogin.do");
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//����� �������� �̵�
	@RequestMapping("/goMembership.movie")
	public String goMembership(HttpServletRequest req){
		HttpSession session = req.getSession();
		String membership[] = new String[] {"����� �̰�����", "�⺻ �����", "Pro �����", "VIP �����"};
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		for(int i=0; i<4; i++) {
			if(i == dto.getFe()) {
				req.setAttribute("memberFe", membership[i]);
			}
		}
		return "Movie/MovieMembership";
	}
	
	//����� ����
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
						req.setAttribute("msg", "����� ������ �Ϸ�Ǽ̽��ϴ�.");
						req.setAttribute("url", "goMyPage.movie");
					}else {
						req.setAttribute("msg", "���� �߻�! ����� ���Կ� �����߽��ϴ�. ��� �� �ٽ� �õ��� �ּ���.");
						req.setAttribute("url", "goMembership.movie");
					}
				}else {
					int res = movieMapper.updateOnlyFe(dto);
					if(res > 0) {
						session.setAttribute("loginData", dto);
						req.setAttribute("msg", "������� �����߽��ϴ�.");
						req.setAttribute("url", "goMyPage.movie");
					}else {
						req.setAttribute("msg", "���� �߻�! ����� ���濡 �����߽��ϴ�. ��� �� �ٽ� �õ��� �ּ���.");
						req.setAttribute("url", "goMembership.movie");
					}
				}
			}else {
				req.setAttribute("msg", "���� �߻�! ��� �� �ٽ� �õ��� �ּ���.");
				req.setAttribute("url", "goMembership.movie");
			}
			return "forward:WEB-INF/views/message.jsp";
		}
	
	//����� �ߴ�
	@RequestMapping("/doMembershipStop.movie")
	public String doMemberShipStop(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		dto.setAutopay(0);
		int res = movieMapper.stopFe(dto);
		
		if(res > 0) {
			session.setAttribute("loginData", dto);
			req.setAttribute("msg", "�ڵ������� �����Ǿ����ϴ�.");
			req.setAttribute("url", "goMyPage.movie");
		}else {
			req.setAttribute("msg", "���� �߻�! ����� ���濡 �����߽��ϴ�. ��� �� �ٽ� �õ��� �ּ���.");
			req.setAttribute("url", "goMembership.movie");
		}
		
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//ȸ��Ż�� Ȯ��
	@RequestMapping("/goAskDelete.movie")
	public String goAskDelete(HttpServletRequest req) {
		req.setAttribute("msg",	"������ ȸ���� Ż���Ͻðڽ��ϱ�?");
		req.setAttribute("url",	"doDeleteMember.movie");
		return "forward:WEB-INF/views/confirm.jsp";
	}
	
	//ȸ��Ż��
	@RequestMapping("/doDeleteMember.movie")
	public String doDeleteMember(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		int membernum = dto.getMembernum();
		
		
		int res = goodsMapper.deleteMemberOrder(membernum);
		if(res>0) {
			System.out.println("�����θ��Ű�� �ɷ��ִ� ��������Ʈ �����Ϸ�");
		}else {
			System.out.println("�����θ��Ű�� �ɷ��ִ� ��������Ʈ ��������!!!!!");
		}
		
		int res1 = movieMapper.deleteMember(membernum);
		
		String msg=null, url=null;
		if(res1 > 0) {
			msg="ȸ�� Ż�� �Ϸ�Ǿ����ϴ�. �׵��� WeSee�� �̿����ּż� �����մϴ�.";
			
			
			session.invalidate();
		}else {
			msg="������ �߻��߽��ϴ�. ȸ�� Ż�� �����߽��ϴ�.";
			url="goMemberEdit.movie";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//ȸ��Ż�� �������� �̵�
		@RequestMapping("/goMemberDelete.movie")
		public String goDeleteMember() {
			return "Movie/MovieMemberDelete_ok";
		}
	
	//���� ���� ��� �������� �̵�
	@RequestMapping("/goWishList.movie")
	public String goWishList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String wish = dto.getWish();
		
		if(wish == null) {
			req.setAttribute("msg", "���� ������ �����ϴ�.");
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
	
	//�� ���� ��� �������� �̵�
	@RequestMapping("/goWatchList.movie")
	public String goWatchList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String watch = dto.getWatch();
		
		if(watch == null) {
			req.setAttribute("msg", "�� ������ �����ϴ�.");
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
	
	//���ϱ�
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
			
			msg="�� ��Ͽ� �߰��߽��ϴ�!";
		}else {
			msg="���� �߻�! �� ��� �߰��� �����߽��ϴ�.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "goMovieContents.movie?movienum="+movienum);
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//���Ͽ��� ����
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
			msg="�� ��Ͽ��� �����߽��ϴ�!";
		}else {
			msg="���� �߻�! �� ��Ͽ��� ������ �����߽��ϴ�.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "goMovieContents.movie?movienum="+movienum);
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//�������� �̵�
	@RequestMapping("/goWatchList_go.movie")
	public String goWatchList_go(HttpServletRequest req) {
		return null;
	}
	
	
	//�� : ������ || �Ʒ� : �̾Ƹ�
	
	
	
	//ã�� �������� �̵�
	@RequestMapping("/goMovieNameSearch.movie")
	public String doMovieDetail(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		List<MovieDTO> list = movieMapper.findMovieList(title);
		req.setAttribute("findMovie", list);
		req.setAttribute("title", title);
		return "Movie/MovieNameSearch";
		}
	
	//��ȭ������������ �̵�
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
	
	//��ȭ ���
	@RequestMapping("/goMoviePlay.movie")
	public String goMoviePlay(HttpServletRequest req, @RequestParam int movienum) {
		MovieDTO mdto = movieMapper.getMovie(movienum);
		req.setAttribute("getMovie", mdto);
		
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		if(Integer.parseInt(dto.getRate()) < mdto.getAge()) {
			req.setAttribute("msg", "�� ��ȭ��" + mdto.getAge() + "�� �������Դϴ�. "
					+ "ȸ������ "+dto.getRate()+"�� ������ ��ȭ������ �����Ͻ� �� �ֽ��ϴ�.");
			req.setAttribute("url", "goMovieContents.movie?movienum="+movienum);
			return "forward:WEB-INF/views/message.jsp";
		}
		
		return "Movie/MoviePlay";
	}
		
	
		
		
		//�� : �̾Ƹ� || �Ʒ� : ���
		
		
		
				
	//��ȭ �帣�� ���� ������ �̵�
	@RequestMapping("/goMovieDetail.movie")
	public String goMovieDetail(HttpServletRequest req) {
		
		List<MovieDTO> action = movieMapper.selectByGenre("�׼�");
		List<MovieDTO> sf = movieMapper.selectByGenre("SF");
		List<MovieDTO> drama = movieMapper.selectByGenre("���");
		List<MovieDTO> comedy = movieMapper.selectByGenre("�ڹ̵�");
		List<MovieDTO> romance = movieMapper.selectByGenre("�θǽ�");
		List<MovieDTO> thriller = movieMapper.selectByGenre("������");
		
		req.setAttribute("action", action);
		req.setAttribute("sf", sf);
		req.setAttribute("drama", drama);
		req.setAttribute("comedy", comedy);
		req.setAttribute("romance", romance);
		req.setAttribute("thriller", thriller);
		
		return "Movie/MovieDetail";
	}	
	
	//��ȭ �󼼺��� ������ �̵�
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
		//�߰� �ڵ�
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
		
		//��ȸ�� ����
		int res = movieMapper.plusViewcount(movienum);
		if(res > 0) {
			return "Movie/MovieContents";
		}else {
			req.setAttribute("msg", "��ȸ�� ������ �����߽��ϴ�.");
			req.setAttribute("url", "goMainPage.movie");
			return "forward:WEB-INF/views/message.jsp";
		}
	}
	
	//��ȭ �˻��ϱ�
	@RequestMapping("/doSearchMovie.movie") 
	public String doSearchmovie(HttpServletRequest req) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		List<MovieDTO> list = new ArrayList<MovieDTO>();
		String word = req.getParameter("word");
		String search = req.getParameter("search");
		System.out.println(word);
		if(search.equals("genre")) {
			System.out.println("�帣");
		    list = movieMapper.searchgenre(word);
		    req.setAttribute("word", word);
		} else {
			System.out.println("����");
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
	
	
	
	//�� : ��� || �Ʒ� : �赿��
	
	
	
	//�帣�� �̹��� ����
	@RequestMapping("/doSameGenre.movie")
	public String doSameGenre(HttpServletRequest req, @RequestParam int movienum){
		MovieDTO dto = movieMapper.getMovie(movienum);
		req.setAttribute("getMovie", dto);
		String genre = dto.getGenre();
		List<MovieDTO> samegenre = movieMapper.sameGenre(genre);
		return "Movie/MovieContents";
	}
	
	//���� ��������
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
			
			req.setAttribute("msg", "�����û�� �Ϸ��Ͽ����ϴ�.");
			req.setAttribute("url", "goMainPage.movie");
			return "forward:WEB-INF/views/message.jsp";
		}else {
			req.setAttribute("msg", "�� ���� ��� ������Ʈ�� �����߽��ϴ�.");
			req.setAttribute("url", "goMainPage.movie");
			return "forward:WEB-INF/views/message.jsp";
		}
	}
}
