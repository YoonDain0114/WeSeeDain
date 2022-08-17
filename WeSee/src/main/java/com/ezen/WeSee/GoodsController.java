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
	
	//īƮ�� ��ǰ ���
	   @RequestMapping("/doCartAdd.goods")
	   public String doCartAdd(HttpServletRequest req) {
	      try {
	         int gnum = Integer.parseInt(req.getParameter("gnum"));
	         int gqty = Integer.parseInt(req.getParameter("gqty"));
	         String gname = req.getParameter("gname");
	         
	         //���ǿ� �־�� ��ٱ��� ��������
	         HttpSession session = req.getSession();
	         List<GoodsDTO> list = (List)session.getAttribute("cart");
	         GoodsDTO dto = null;
	         
	         //��ٱ��ϰ� ���������� ��ٱ��� ����
	         if(list == null || list.size() == 0) {
	            list = new ArrayList<GoodsDTO>();
	            dto = goodsMapper.getGoods(gnum);
	         }else {
	            //��ٱ��Ͽ� ��ǰ�� ����ִ��� Ȯ��
	            Iterator<GoodsDTO> it = list.iterator();
	            while(it.hasNext()) {
	               GoodsDTO gdto = it.next();
	               if(gdto.getGnum() == gnum) {//���� ��ǰ �ߺ����� ��ٱ��Ͽ� ������   
	                  dto = gdto;
	                  it.remove();
	                  gqty=gdto.getGqty()+gqty;
	                  gdto.setGqty(gqty);
	                  
	                  
	               }
	            }
	            //���ٸ� �����ͺ��̽����� ������ -> ���ǿ� ������ ��ǰ��� ������ �ű⼭ �����°ɷ� ����
	            if(dto == null) {
	               //dto = (GoodsDTO)session.getAttribute("goodsView");
	               dto = goodsMapper.getGoods(gnum);
	            }
	         }
	         
	         //��ǰ ���� ���� �� ��ٱ��Ͽ� ���
	         dto.setGqty(gqty);
	         list.add(dto);
	         session.setAttribute("cart", list);
	         
	         //�޼����߰�
	         req.setAttribute("msg", "��ǰ�� ��ٱ��Ͽ� ��ҽ��ϴ�. ��ٱ��ϸ� Ȯ���Ͻðڽ��ϱ�?");
	         req.setAttribute("url", "goCart.goods");
	         
	         return "forward:WEB-INF/views/confirm.jsp";
	               
	      }catch(Exception e) {
	         e.printStackTrace();
	         req.setAttribute("msg", "������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���.");
	         req.setAttribute("url", "Goods/GoodsCartInsert_No");
	         
	         return "forward:WEB-INF/views/message.jsp";
	      } 
	   }
	
	//��ǰ ��� ��� �庼��
	@RequestMapping("/GoodsCartInsert_No")
	public String GoodsCartInsert_No() {
		return "Goods/GoodsCartInsert_No";
	}
	
	//��ǰ ��� ��ٱ��Ϸ� ����
	@RequestMapping("/GoodsCartInsert_Yes")
	public String GoodsCartInsert_Yes() {
		return "Goods/GoodsCartInsert_Yes";
	}
	
	//��ǰ�󼼺��⿡�� �ٷα��� ��ư
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
	
	//��ٱ��Ͽ��� �ֹ��ϱ� ��ư
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

	
	 //�����ϱ�
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
               System.out.println("������ ��ǰ ������ŭ ������ ����Ǿ����ϴ�");
            }else {
               System.out.println("������ ��ǰ ������ŭ ������ �������!!!!!!");
            }
         }else {
            System.out.println("īƮ��ٱ��Ϸ� �Ѿ��");
            
            
            String order=values.get("ordergoods");
            String order1[]=order.split("/");
            System.out.println(Arrays.toString(order1));
            
            String gnum1=null,gqty=null;
            
               if(order1.length<=2) {//��ٱ��Ͽ��� �ϳ��� ����� �� 
                  gnum1=order1[0];
                  gqty=order1[1];
                  
                  int res1 = goodsMapper.M_updategqty(Integer.parseInt(gnum1),Integer.parseInt(gqty));
                  if(res1>0) {
                     System.out.println("������ ��ǰ ������ŭ ������ ����Ǿ����ϴ�");
                  }else {
                     System.out.println("������ ��ǰ ������ŭ ������ �������!!!!!!");
                  }
                  
               }else {//��ٱ��Ͽ��� �ΰ��̻� ����� ��
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
                           System.out.println("������ ��ǰ ������ŭ ������ ����Ǿ����ϴ�");
                        }else {
                           System.out.println("������ ��ǰ ������ŭ ������ �������!!!!!!");
                        }
                     }   
                  }
               }
         }
         
         
         //īƮ ����
         if(gnum == null) session.removeAttribute("cart");

         String odate[] = formatedNow.split("-");
         
         req.setAttribute("orderdate", odate);
         req.setAttribute("ordercode", ordercode);
         session.removeAttribute("mode");
         
         return "Goods/GoodsOrderConfirm";
      }else {
         String msg = "���� �߻�! �ֹ��� �����߽��ϴ�. ��� �� �ٽ� �õ��� �ּ���.";
         String url = "doOrder.goods";

         req.setAttribute("msg", msg);
         req.setAttribute("url", url);
         return "forward:WEB-INF/views/message.jsp";
      }
   }
	   
	   
	
	//�ֹ����� �ҷ�����
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
					gname = num[0] + " ����� ����";
				}else {
					gname = goodsMapper.getGoods(Integer.parseInt(num[0])).getGname();
				}
			}else {
				gname = goodsMapper.getGoods(Integer.parseInt(num[0])).getGname()
						+ " �� "+ String.valueOf(olist.length / 2) + "��";
			}
			tmpdto.setOrdergoods(gname);
		}
		
		req.setAttribute("orderlist", list);
		return "Goods/GoodsOrderListView";
	}
	
	
	
	//�� : ������ || �Ʒ� : �̾Ƹ�
	
	
	
	//���� ī�װ��� �з� ������ �̵�
	@RequestMapping("/goGoodsCategory.goods")
	public String goGoodsCategory() {//�����ͺ��̽����� �� ���͵� �ǳ�?
		return "Goods/GoodsCategory";
	}
	
	//ī�װ� ���� �� �̵�?
	@RequestMapping("/goGoodsCateList.goods")
	public String goGoodsCateList(HttpServletRequest req) {
		String gcategory  = req.getParameter("gcategory");
		List<GoodsDTO> list = goodsMapper.cateList(gcategory);
		req.setAttribute("cateList", list);
		req.setAttribute("gcategory", gcategory);
		return "Goods/GoodsCateList";
	}
	
	
	
	//�� : �̾Ƹ� || �Ʒ� : ������
	
	
	//�������������?
	@RequestMapping("/goods.goods")
	   public String goGoods(HttpServletRequest req) {
	      HttpSession session = req.getSession();
	      session.setAttribute("upPath", upPath);
	      
	      List<GoodsDTO> glist=adminMapper.listGoods(); //adminMapper���� goods ����Ʈ �ҷ����� �޼ҵ� �̸� ���߱�
	      session.setAttribute("listGoods", glist);
	      
	      List<GoodsDTO> plist1 = goodsMapper.selectByspec("best");//���� �̸� �߰�
	      List<GoodsDTO> plist2 = goodsMapper.selectByspec("newest");
	      List<GoodsDTO> plist3 = goodsMapper.selectByspec("season");
	      List<GoodsDTO> plist4 = goodsMapper.selectByspec("normal");
	      
	      //�����ϱ�
	      session.setAttribute("best", plist1);
	      session.setAttribute("newest", plist2);
	      session.setAttribute("season", plist3);
	      session.setAttribute("normal", plist4);
	      
	      return "Goods/GoodsMainPage";
	   }
	
	//����󼼺���?
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
	
	//��ٱ��� ����
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
	
	//���� �˻�
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
	
	
	
	//�� : ������ || �Ʒ� : �赿��
	
	
	
	//īƮ ����Ʈ ���� ����
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
	        		req.setAttribute("msg", "������ �ʹ� �����ϴ�.");
	   		      	req.setAttribute("url", "goCart.goods");
	        		break;
	        	 }else {
	        	 dto.setGqty(Integer.parseInt(gqty));
	        	 session.setAttribute("cart", list);
			      
			     req.setAttribute("msg", "������ �����߽��ϴ�.");
			     req.setAttribute("url", "goCart.goods");
	        	 break;
	        	 }
	         }
	      }
	      
	      return "forward:/WEB-INF/views/message.jsp";
	   }
	   
	 //īƮ ����Ʈ ��ǰ����
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
	      
	      req.setAttribute("msg", "��ǰ�� �����߽��ϴ�.");
	      req.setAttribute("url", "goCart.goods");
	      
	      return "forward:/WEB-INF/views/message.jsp";
	   }
	   
	 //���ϱ�
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
			
			req.setAttribute("msg", "�� ��Ͽ� �߰��߽��ϴ�!");
			req.setAttribute("url", "go.goodsView.goods?gnum="+gnum + "&gspec="+gspec + "&gmovie="+gmovie);
			return "forward:WEB-INF/views/message.jsp";
			
		}else {
			msg="���� �߻�! �� ��� �߰��� �����߽��ϴ�.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "go.goodsView.goods?gnum="+gnum + "&gspec="+gspec + "&gmovie="+gmovie);
		return "forward:WEB-INF/views/message.jsp";
	}
	
	//���ǰ ����
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
			msg="�� ��Ͽ��� �����߽��ϴ�!";
		}else {
			msg="���� �߻�! �� ��Ͽ��� ������ �����߽��ϴ�.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "go.goodsView.goods?gnum="+gnum + "&gspec="+gspec + "&gmovie="+gmovie);
		return "forward:WEB-INF/views/message.jsp";
	}
	   
	 //���� ��ǰ ��� �������� �̵�
	@RequestMapping("/goWishList.goods")
	public String goWishList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginData");
		
		String goodswish = dto.getGoodsWish();
		
		if(goodswish == null) {
			req.setAttribute("msg", "���� ��ǰ�� �����ϴ�.");
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
	
	//���� ��ǰ ����Ʈ ���������� ����
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
			msg="�� ��Ͽ��� �����߽��ϴ�!";
		}else {
			msg="���� �߻�! �� ��Ͽ��� ������ �����߽��ϴ�.";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", "goWishList.goods?gnum="+gnum);
		return "forward:WEB-INF/views/message.jsp";
	}

	   
	   
	   
	   //�� : �赿�� || �Ʒ� : �̽���
	   
	   
	
	
	//�ֹ����� �󼼺��� ������
	@RequestMapping("/goOrderlistDetail.goods")
	public String orderlistdetail(HttpServletRequest req,@RequestParam int ordernum) {
		OrderlistDTO dto = goodsMapper.getOrder(ordernum);
				
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
		
		req.setAttribute("ordergoodsList", list);
		req.setAttribute("orderlistDTO", dto);
	   
	   return "Goods/GoodsOrderListDetail";
	}
}
