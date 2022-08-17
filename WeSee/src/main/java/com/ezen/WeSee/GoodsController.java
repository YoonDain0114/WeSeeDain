package com.ezen.WeSee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.WeSee.dto.*;
import com.ezen.WeSee.service.AdminMapper;
import com.ezen.WeSee.service.GoodsMapper;
import com.ezen.WeSee.service.MovieMapper;

@Controller
public class GoodsController {

	@Resource(name="uploadPath")
	private String upPath;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private MovieMapper movieMapper;
	
	//카트에 상품 담기
	   @RequestMapping("/doCartAdd.goods")
	   public String doCartAdd(HttpServletRequest req) {
	      try {
	         int gnum = Integer.parseInt(req.getParameter("gnum"));
	         int gqty = Integer.parseInt(req.getParameter("gqty"));
	         String gname = req.getParameter("gname");
	         
	         //세션에 넣어둔 장바구니 꺼내오기
	         HttpSession session = req.getSession();
	         List<GoodsDTO> list = (List)session.getAttribute("cart");
	         GoodsDTO dto = null;
	         
	         //장바구니가 비어있을경우 장바구니 생성
	         if(list == null || list.size() == 0) {
	            list = new ArrayList<GoodsDTO>();
	            dto = goodsMapper.getGoods(gnum);
	         }else {
	            //장바구니에 상품이 담겨있는지 확인
	            Iterator<GoodsDTO> it = list.iterator();
	            while(it.hasNext()) {
	               GoodsDTO gdto = it.next();
	               if(gdto.getGnum() == gnum) {//같은 상품 중복으로 장바구니에 넣으면   
	                  dto = gdto;
	                  it.remove();
	                  gqty=gdto.getGqty()+gqty;
	                  gdto.setGqty(gqty);
	                  
	                  
	               }
	            }
	            //없다면 데이터베이스에서 빼오기 -> 세션에 저장한 상품목록 있으면 거기서 빼오는걸로 변경
	            if(dto == null) {
	               //dto = (GoodsDTO)session.getAttribute("goodsView");
	               dto = goodsMapper.getGoods(gnum);
	            }
	         }
	         
	         //상품 수량 설정 후 장바구니에 담기
	         dto.setGqty(gqty);
	         list.add(dto);
	         session.setAttribute("cart", list);
	         
	         //메세지추가
	         req.setAttribute("msg", "상품을 장바구니에 담았습니다. 장바구니를 확인하시겠습니까?");
	         req.setAttribute("url", "goCart.goods");
	         
	         return "forward:WEB-INF/views/confirm.jsp";
	               
	      }catch(Exception e) {
	         e.printStackTrace();
	         req.setAttribute("msg", "오류가 발생했습니다. 다시 시도해 주세요.");
	         req.setAttribute("url", "Goods/GoodsCartInsert_No");
	         
	         return "forward:WEB-INF/views/message.jsp";
	      } 
	   }
	
	//상품 담고 계속 장볼때
	@RequestMapping("/GoodsCartInsert_No")
	public String GoodsCartInsert_No() {
		return "Goods/GoodsCartInsert_No";
	}
	
	//상품 담고 장바구니로 갈때
	@RequestMapping("/GoodsCartInsert_Yes")
	public String GoodsCartInsert_Yes() {
		return "Goods/GoodsCartInsert_Yes";
	}
	
	//상품상세보기에서 바로구매 버튼
	@RequestMapping(value="/goOrderPage.goods", method=RequestMethod.GET)
	public String doOrder(HttpServletRequest req) {
		int gnum = Integer.parseInt(req.getParameter("gnum"));
		int gqty = Integer.parseInt(req.getParameter("gqty"));
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginData");
		
		String addr[] = mdto.getAddress().split("_");
		req.setAttribute("addr", addr); 
		
		GoodsDTO dto = goodsMapper.getGoods(gnum);
		dto.setGqty(gqty);
		req.setAttribute("goOrder", dto);
		
		return "Goods/GoodsOrder";
	}
	
	//장바구니에서 주문하기 버튼
   @RequestMapping(value="/goOrderPage.goods", method=RequestMethod.POST)
   public String goOrderPage(HttpServletRequest req) {
      HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginData");
		
		String addr[] = mdto.getAddress().split("_");
		req.setAttribute("addr", addr);
      String mode=req.getParameter("mode");
      session.setAttribute("mode", mode);
      return "Goods/GoodsOrder";
   }

	
	 //결제하기
   @RequestMapping("/goOrderConfirm.goods")
   public String goOrderConfirm(HttpServletRequest req) {
      HttpSession session = req.getSession();
      Map<String, String> values = new Hashtable<>();
      List<GoodsDTO> list = (List)session.getAttribute("cart");

      MemberDTO mdto = (MemberDTO)session.getAttribute("loginData");
      values.put("ordermember", String.valueOf(mdto.getMembernum()));
      
      String gnum = req.getParameter("dtoNum");
      
      String ordergoods = null;
      if(gnum != null) {
         GoodsDTO dto = goodsMapper.getGoods(Integer.parseInt(gnum));
         ordergoods = String.valueOf(dto.getGnum())+"/"+req.getParameter("gqty");
         values.put("ordergoods", ordergoods);
      }else {
         Iterator<GoodsDTO> it = list.iterator();
         while(it.hasNext()) {
            GoodsDTO dto = it.next();
            if(ordergoods == null) {
               ordergoods = String.valueOf(dto.getGnum())+"/"+String.valueOf(dto.getGqty());
            }else {
               ordergoods = ordergoods +"_"+ String.valueOf(dto.getGnum())+"/"+String.valueOf(dto.getGqty());
            }
            it.remove();
         }
         values.put("ordergoods", ordergoods);
      }
      
      if(req.getParameter("postcode") != null && req.getParameter("address")!= null &&
    		  req.getParameter("detailAddress") != null) {
    	  String address = req.getParameter("postcode")+"_"+req.getParameter("address")+
  				"_"+req.getParameter("detailAddress");
    	  values.put("orderaddress", address);
      }
      
      values.put("stage", "1");
      values.put("memo", req.getParameter("memo"));
      values.put("payment", req.getParameter("payment"));
      values.put("totalpay", req.getParameter("totalpay"));
      
      LocalDateTime now = LocalDateTime.now();
      String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
      String formatedNow2 = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
      values.put("orderdate", formatedNow);
      
      int res = goodsMapper.insertOrderlist(values);
      if(res > 0) {
         
         String ordercode = formatedNow2 + "-" + String.valueOf(res);
         goodsMapper.insertOrdercode(ordercode, res);
         
         String mode=(String)session.getAttribute("mode");
         System.out.println("mode="+mode);
         if(mode == null) {
            String gqty=req.getParameter("gqty");
            int res1 = goodsMapper.M_updategqty(Integer.parseInt(gnum),Integer.parseInt(gqty));
            if(res1>0) {
               System.out.println("구매한 상품 수량만큼 수량이 변경되었습니다");
            }else {
               System.out.println("구매한 상품 수량만큼 수량이 변경실패!!!!!!");
            }
         }else {
            System.out.println("카트장바구니로 넘어옴");
            
            
            String order=values.get("ordergoods");
            String order1[]=order.split("/");
            System.out.println(Arrays.toString(order1));
            
            String gnum1=null,gqty=null;
            
               if(order1.length<=2) {//장바구니에서 하나만 담았을 때 
                  gnum1=order1[0];
                  gqty=order1[1];
                  
                  int res1 = goodsMapper.M_updategqty(Integer.parseInt(gnum1),Integer.parseInt(gqty));
                  if(res1>0) {
                     System.out.println("구매한 상품 수량만큼 수량이 변경되었습니다");
                  }else {
                     System.out.println("구매한 상품 수량만큼 수량이 변경실패!!!!!!");
                  }
                  
               }else {//장바구니에서 두개이상 담았을 때
                  String order2[]=order.split("_|/");
                  System.out.println(Arrays.toString(order2));
                  for(int t=0;t<order2.length;++t) {
                     if(t%2==0) {
                        gnum1=order2[t];
                        System.out.println("gnum1="+gnum1);
                        gqty=order2[t+1];
                        System.out.println("gqty="+gqty);
                        
                        int res1 = goodsMapper.M_updategqty(Integer.parseInt(gnum1),Integer.parseInt(gqty));
                        if(res1>0) {
                           System.out.println("구매한 상품 수량만큼 수량이 변경되었습니다");
                        }else {
                           System.out.println("구매한 상품 수량만큼 수량이 변경실패!!!!!!");
                        }
                     }   
                  }
               }
         }
         
         
         //카트 비우기
         if(gnum == null) session.removeAttribute("cart");

         String odate[] = formatedNow.split("-");
         
         req.setAttribute("orderdate", odate);
         req.setAttribute("ordercode", ordercode);
         session.removeAttribute("mode");
         
         return "Goods/GoodsOrderConfirm";
      }else {
         String msg = "오류 발생! 주문에 실패했습니다. 잠시 후 다시 시도해 주세요.";
         String url = "doOrder.goods";

         req.setAttribute("msg", msg);
         req.setAttribute("url", url);
         return "forward:WEB-INF/views/message.jsp";
      }
   }
	   
	   
	
	//주문내역 불러오기
	@RequestMapping("/goOrderListView.goods")
	public String goOrderListView(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		List<OrderlistDTO> list = goodsMapper.getOrderlist(dto.getMembernum());
		
		Iterator<OrderlistDTO> it = list.iterator();
		while(it.hasNext()) {
			OrderlistDTO tmpdto = it.next();
			String olist[] = tmpdto.getOrdergoods().split("_");
			String num[] = olist[0].split("/");
			String gname = null;
			if(olist.length < 2) {
				if(num[0].equals("standard") || num[0].equals("pro") || num[0].equals("vip")) {
					gname = num[0] + " 요금제 결제";
				}else {
					gname = goodsMapper.getGoods(Integer.parseInt(num[0])).getGname();
				}
			}else {
				gname = goodsMapper.getGoods(Integer.parseInt(num[0])).getGname()
						+ " 외 "+ String.valueOf(olist.length / 2) + "개";
			}
			tmpdto.setOrdergoods(gname);
		}
		
		req.setAttribute("orderlist", list);
		return "Goods/GoodsOrderListView";
	}
	
	
	
	//위 : 김형준 || 아래 : 이아린
	
	
	
	//굿즈 카테고리별 분류 페이지 이동
	@RequestMapping("/goGoodsCategory.goods")
	public String goGoodsCategory() {//데이터베이스에서 안 빼와도 되나?
		return "Goods/GoodsCategory";
	}
	
	//카테고리 선택 후 이동?
	@RequestMapping("/goGoodsCateList.goods")
	public String goGoodsCateList(HttpServletRequest req) {
		String gcategory  = req.getParameter("gcategory");
		List<GoodsDTO> list = goodsMapper.cateList(gcategory);
		req.setAttribute("cateList", list);
		req.setAttribute("gcategory", gcategory);
		return "Goods/GoodsCateList";
	}
	
	
	
	//위 : 이아린 || 아래 : 윤다인
	
	
	//굿즈메인페이지?
	@RequestMapping("/goods.goods")
	   public String goGoods(HttpServletRequest req) {
	      HttpSession session = req.getSession();
	      session.setAttribute("upPath", upPath);
	      
	      List<GoodsDTO> glist=adminMapper.listGoods(); //adminMapper에서 goods 리스트 불러오는 메소드 이름 맞추기
	      session.setAttribute("listGoods", glist);
	      
	      List<GoodsDTO> plist1 = goodsMapper.selectByspec("best");//저장 이름 추가
	      List<GoodsDTO> plist2 = goodsMapper.selectByspec("newest");
	      List<GoodsDTO> plist3 = goodsMapper.selectByspec("season");
	      List<GoodsDTO> plist4 = goodsMapper.selectByspec("normal");
	      
	      //수정하기
	      session.setAttribute("best", plist1);
	      session.setAttribute("newest", plist2);
	      session.setAttribute("season", plist3);
	      session.setAttribute("normal", plist4);
	      
	      return "Goods/GoodsMainPage";
	   }
	
	//굿즈상세보기?
	@RequestMapping("/go.goodsView.goods")
	public ModelAndView goGoodsView(HttpServletRequest req, @RequestParam int gnum, String gspec, int gmovie) {
		HttpSession session = req.getSession();
		GoodsDTO gdto = goodsMapper.getGoods(gnum, gspec);
		GoodsDTO dto = adminMapper.getAGoods(gnum);
		
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginData");
		if(mdto.getGoodsWish() != null) {
			String wishes[] = mdto.getGoodsWish().split("_");
			for(int i=0; i<wishes.length; i++) {
				if(wishes[i].equals(String.valueOf(gnum))) {
					req.setAttribute("checkwish", 1);
				}
			}
		}
		
		MovieDTO mvdto = movieMapper.getMovie(gmovie);
		session.setAttribute("getmMovie", mvdto);
		
		List<GoodsDTO> sawlist = (List)session.getAttribute("sawlist");
		if(sawlist==null) {
			sawlist = new ArrayList<GoodsDTO>();
		}
		if(sawlist.size()>4) {
			sawlist.remove(0);
			sawlist.add(gdto);
		}else {
			sawlist.add(gdto);
		}
		
		session.setAttribute("sawlist", sawlist);
		
		req.setAttribute("getAGoods", dto);		
		req.setAttribute("getmMovie", mvdto);
		return new ModelAndView("Goods/GoodsContents","getGoods",gdto);
	}
	
	//장바구니 보기
	@RequestMapping("/goCart.goods")
	public String goCart(HttpServletRequest req) {
		HttpSession session = req.getSession();
		List<GoodsDTO> list = (List)session.getAttribute("cart");
		
		if(list == null || list.size() == 0) {
			return "Goods/GoodsCart";
		}else {
			int listSize = list.size();
			req.setAttribute("listSize", listSize);
			return "Goods/GoodsCart";
		}
	}
	
	//굿즈 검색
	@RequestMapping("/doSearch.goods")
	public String doSearchGoods(HttpServletRequest req) {
	      HttpSession session = req.getSession();
	      session.setAttribute("upPath", upPath);
	      List<GoodsDTO> list= null;
	      String search = req.getParameter("search");
	      list=goodsMapper.findByName(search);
	      req.setAttribute("findGoods", list);
	      req.setAttribute("search", search);
	      return "Goods/GoodsMainPage";
	   }
	
	
	
	//위 : 윤다인 || 아래 : 김동완
	
	
	
	//카트 리스트 수량 수정
   @RequestMapping("/doCartEdit.goods")
   public String GoodsCartEdit(HttpServletRequest req) {
	   String gqty = req.getParameter("gqty");
	      String gnum = req.getParameter("gnum");
	      
	      HttpSession session = req.getSession();
	      List<GoodsDTO> list = (List)session.getAttribute("cart");
	      GoodsDTO adto = adminMapper.getAGoods(Integer.parseInt(gnum));
	      
	      
	      for(GoodsDTO dto : list) {
	         if (dto.getGnum() == Integer.parseInt(gnum)) {
	        	 if(adto.getGqty() < Integer.parseInt(gqty)) {
	        		req.setAttribute("msg", "수량이 너무 많습니다.");
	   		      	req.setAttribute("url", "goCart.goods");
	        		break;
	        	 }else {
	        	 dto.setGqty(Integer.parseInt(gqty));
	        	 session.setAttribute("cart", list);
			      
			     req.setAttribute("msg", "수량을 수정했습니다.");
			     req.setAttribute("url", "goCart.goods");
	        	 break;
	        	 }
	         }
	      }
	      
	      return "forward:/WEB-INF/views/message.jsp";
	   }
	   
	 //카트 리스트 물품삭제
	   @RequestMapping("/doCartDelete.goods")
	   public String doCartDelete(HttpServletRequest req, @RequestParam int gnum) {
	      
	      HttpSession session = req.getSession();
	      List<GoodsDTO> list = (List)session.getAttribute("cart");
	      
	      for(GoodsDTO dto : list) {
	         if(dto.getGnum() == gnum) {
	            list.remove(list.indexOf(dto));
	            break;
	         }
	      }
	      
	      session.setAttribute("cart", list);
	      
	      req.setAttribute("msg", "물품을 삭제했습니다.");
	      req.setAttribute("url", "goCart.goods");
	      
	      return "forward:/WEB-INF/views/message.jsp";
	   }
	   
	 //찜하기
   @RequestMapping("/doGoodsWish.goods")
	public String doGoodsWish(HttpServletRequest req, @RequestParam int gnum, String gspec, int gmovie) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String goodswishList = dto.getGoodsWish();			
		
		if(goodswishList == null) {
			goodswishList = String.valueOf(gnum);
		}else {
			goodswishList = String.valueOf(gnum) + "_" + goodswishList;
		}
		
		int res = goodsMapper.updategoodsWish(dto.getMembernum(), goodswishList);
		String msg=null;
		if(res > 0) {
			MemberDTO mdto = adminMapper.getMember(dto.getMembernum());
			session.setAttribute("loginData", mdto);
			
			req.setAttribute("msg", "찜 목록에 추가했습니다!");
			req.setAttribute("url", "go.goodsView.goods?gnum="+gnum + "&gspec="+gspec + "&gmovie="+gmovie);
			return "forward:WEB-INF/views/message.jsp";
			
		}else {
			msg="오류 발생! 찜 목록 추가에 실패했습니다.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "go.goodsView.goods?gnum="+gnum + "&gspec="+gspec + "&gmovie="+gmovie);
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//찜상품 삭제
   @RequestMapping("/doGoodsWishDelete.goods")
	public String doGoodsWishDelete(HttpServletRequest req, @RequestParam int gnum, String gspec, int gmovie) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String goodswishes[] = dto.getGoodsWish().split("_");
		ArrayList<String> gtmpList = new ArrayList<>(Arrays.asList(goodswishes));
		
		String goodswishList = null;
		
		for(int i=0; i<goodswishes.length; i++) {
			if(goodswishes[i].equals(String.valueOf(gnum))) {
				gtmpList.remove(i);
			}
		}
		
		goodswishes = gtmpList.toArray(new String[0]);
	    for(int i=0; i<goodswishes.length; i++) {
	    	if(goodswishList == null) {
				goodswishList = goodswishes[i];
			}else {
				goodswishList = goodswishList + "_" + goodswishes[i];
			}
	    }
	    
	    if(goodswishList == null) goodswishList = "";
		int res = goodsMapper.updategoodsWish(dto.getMembernum(), goodswishList);
	    String msg=null;
	    if(res > 0) {
			MemberDTO mdto = adminMapper.getMember(dto.getMembernum());
			session.setAttribute("loginData", mdto);						
			msg="찜 목록에서 삭제했습니다!";
		}else {
			msg="오류 발생! 찜 목록에서 삭제에 실패했습니다.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "go.goodsView.goods?gnum="+gnum + "&gspec="+gspec + "&gmovie="+gmovie);
		return "forward:WEB-INF/views/message.jsp";
	}
	   
	 //찜한 상품 목록 페이지로 이동
	@RequestMapping("/goWishList.goods")
	public String goWishList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String goodswish = dto.getGoodsWish();
		
		if(goodswish == null) {
			req.setAttribute("msg", "찜한 상품이 없습니다.");
			req.setAttribute("url", "goods.goods");
			return "forward:WEB-INF/views/message.jsp";
		}else {
			String goodswishList[] = goodswish.split("_");
			
			List<GoodsDTO> list = new ArrayList<>();
			
			for(String input : goodswishList) {
				GoodsDTO mdto = goodsMapper.getGoods(Integer.parseInt(input));
				list.add(mdto);
			}
			req.setAttribute("goodswishList", list);
			return "Goods/GoodsWishList";
		}
	}
	
	//찜한 상품 리스트 페이지에서 삭제
	@RequestMapping("/doGoodsWishListDelete.goods")
	public String doGoodsWishListDelete(HttpServletRequest req, @RequestParam int gnum) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String goodswishes[] = dto.getGoodsWish().split("_");
		ArrayList<String> gtmpList = new ArrayList<>(Arrays.asList(goodswishes));
		
		String goodswishList = null;
		
		for(int i=0; i<goodswishes.length; i++) {
			if(goodswishes[i].equals(String.valueOf(gnum))) {
				gtmpList.remove(i);
			}
		}
		
		goodswishes = gtmpList.toArray(new String[0]);
	    for(int i=0; i<goodswishes.length; i++) {
	    	if(goodswishList == null) {
				goodswishList = goodswishes[i];
			}else {
				goodswishList = goodswishList + "_" + goodswishes[i];
			}
	    }
	    
	    if(goodswishList == null) goodswishList = "";
		int res = goodsMapper.updategoodsWish(dto.getMembernum(), goodswishList);
	    String msg=null;
	    if(res > 0) {
			MemberDTO mdto = adminMapper.getMember(dto.getMembernum());
			session.setAttribute("loginData", mdto);						
			msg="찜 목록에서 삭제했습니다!";
		}else {
			msg="오류 발생! 찜 목록에서 삭제에 실패했습니다.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "goWishList.goods?gnum="+gnum);
		return "forward:WEB-INF/views/message.jsp";
	}

	   
	   
	   
	   //위 : 김동완 || 아래 : 이시율
	   
	   
	
	
	//주문내역 상세보기 페이지
	@RequestMapping("/goOrderlistDetail.goods")
	public String orderlistdetail(HttpServletRequest req,@RequestParam int ordernum) {
		OrderlistDTO dto = goodsMapper.getOrder(ordernum);
				
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
		
		req.setAttribute("ordergoodsList", list);
		req.setAttribute("orderlistDTO", dto);
	   
	   return "Goods/GoodsOrderListDetail";
	}
}
