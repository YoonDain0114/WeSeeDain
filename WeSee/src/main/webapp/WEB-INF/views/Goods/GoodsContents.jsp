<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
	
	<!-- GoodsContents.jsp -->
	
<%@ include file="GoodsMainTop.jsp"%>

 <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
    <script>
      $( document ).ready( function() {
        $( '.slider' ).slick( {
          //autoplay: true,
          //autoplaySpeed: 1000,
           arrows : true,
           prevArrow : "<button type='button' class='slick-prev'>Previous</button>",  
           nextArrow : "<button type='button' class='slick-next'>Next</button>",
          slidesToShow: 4,
          slidesToScroll: 2,
        } );
      } );
    </script>
    <style>
      img {
        max-width: 100%;
        height: auto;    
      }
      .slider {
        width: 470px;
        margin: 0px auto;
      }
      .slider .slick-slide {
        margin: 10px;
      }
      .slick-prev:before, .slick-next:before {
        color:  red;
      }
    </style>

	<script type="text/javascript">
	function goCart(){
		var text1 = document.getElementById('result').value;
		if(text1 > "${getAGoods.gqty}"){
			
		alert('구매 가능한 수량보다 입력하신 수량이 많습니다.');
		document.getElementById('result').focus();
		return false;
		}else{
		
		var popupWidth = 600;
		var popupHeight = 200;
		var popupX = (window.screen.width / 2) - (popupWidth / 2);		
		var popupY= (window.screen.height / 2) - (popupHeight / 2);	
		
		document.getElementById('gqty').value = document.getElementById('result').value;
		window.open("", "value", "scrollbars=1, menubar=1, resizable=0, status=no, height=" + popupHeight  + ", width=" + popupWidth  + ", left="+ popupX + ", top="+ popupY); 
         //새창의 타겟과 크기 같은 옵션을 지정
		document.goCartGo.target ="value";             //새창에서 지정한 value옵션으로 타겟을 지정
		document.goCartGo.submit();
		}
		return true;
	}
	
	function goOrder(){
		var text1 = document.getElementById('result').value;
		if(text1 > "${getAGoods.gqty}"){
			
		alert('구매 가능한 수량보다 입력하신 수량이 많습니다.');
		document.getElementById('result').focus();

		return false;
		}else{
		var gnum = document.getElementById('gnum').value;
		var gqty = document.getElementById('result').value;
		location.href='goOrderPage.goods?gnum='+gnum+'&gqty='+gqty;
		}
		return true;
	}
		
		function onlyNumber(obj) {
			obj.value = obj.value.replace(/[^0-9]/g, "");
		}
		
		function count(type)  {
		  // 결과를 표시할 element
		  const resultElement = document.getElementById('result');
		  // 현재 화면에 표시된 값
		  let number = resultElement.value;
		  //X눌러서 지운 상태면 기본값은 1
		  if(number === '') number = 0;
		  // 더하기/빼기
		  if(type === 'plus') {
		    number = parseInt(number) + 1;
		  }else if(type === 'minus')  {
		    number = parseInt(number) - 1;
		  }
		  //최소값은 1
		  if(number < 1) number = 1;
		  // 결과 출력
		  resultElement.value = number;
		}
		function openImage(url){
			var popupWidth = 10;
			var popupHeight = 10;
			var popupX = (window.screen.width / 2) - (popupWidth / 2);		
			var popupY= (window.screen.height / 2) - (popupHeight / 2);		
			window.open(url, '이미지 확대', 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
		}
		function openWish(gnum){
			var popupWidth = 10;
			var popupHeight = 10;
			var popupX = (window.screen.width / 2) - (popupWidth / 2);		
			var popupY= (window.screen.height / 2) - (popupHeight / 2);		
			window.open('doGoodsWish.goods?gnum='+gnum, '찜 목록 추가!', 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
		}
		
		function overImg(img){
			var big = document.getElementById("bigImg");
			big.src = img.src;
		}
		
	</script>
	
	<br>
	<div class="row">
		<div class="col-md-offset-2 col-md-4">
			<div class="row">
				<div class="col-md-12">
					<a href="#" onclick="window.open('filePath1/${getGoods.gimage}','새창열기','resizable=no width=800 height=800');return false">
				    <img id ="bigImg" src="filePath1/${getGoods.gimage}" style="width:500px;height:500px"/>
					</a>
					<div class="col-md-offset-1 slider"style="height:130px;">
					<p><a href="#" onclick="window.open('filePath1/${getGoods.gimage}','새창열기','resizable=no width=800 height=800');return false">
				    <img onmouseover="overImg(this);" src="filePath1/${getGoods.gimage}" style="width:100px;height:100px"/>
                    </a>
                    <c:if test="${not empty getGoods.gimage2}">
				    <p><a href="#" onclick="window.open('filePath1/${getGoods.gimage2}','새창열기','resizable=no width=800 height=800');return false">
				    <img  onmouseover="overImg(this);" src="filePath1/${getGoods.gimage2}" style="width:100px;height:100px"/>
				    </a>
				    </c:if>
				    <c:if test="${not empty getGoods.gimage3}">
				    <p><a href="#" onclick="window.open('filePath1/${getGoods.gimage3}','새창열기','resizable=no width=800 height=800');return false">
				    <img onmouseover="overImg(this);" src="filePath1/${getGoods.gimage3}" style="width:100px;height:100px"/>
				    </a>
				    </p>
				    </c:if>
				    <c:if test="${not empty getGoods.gimage4}">
				    <p><a href="#" onclick="window.open('filePath1/${getGoods.gimage4}','새창열기','resizable=no width=800 height=800');return false">
				    <img onmouseover="overImg(this);" src="filePath1/${getGoods.gimage4}" style="width:100px;height:100px"/>
				    </a>
				    </p></c:if>
				    <c:if test="${not empty getGoods.gimage5 }">
				    <p><a href="#" onclick="window.open('filePath1/${getGoods.gimage5}','새창열기','resizable=no width=800 height=800');return false">
				    <img onmouseover="overImg(this);" src="filePath1/${getGoods.gimage5}" style="width:100px;height:100px"/>
				    </a>
				    </p></c:if>
				    </div>
				</div>
				
				<div class="col-md-12">
					<p style="text-align:center;">클릭시 원본 이미지 보기</p>
				</div>
			</div>
		</div>
		
		<div class="col-md-4">
			<div class="row" style="height:125px">
				<div class="col-md-12">
					<p style="text-align:center;">${getGoods.gname}</p>
				</div>
			</div>
			
			<div class="row" style="height:125px">
				<div class="col-md-6">
					<p style="text-align:center;">${getmMovie.title} 관련 굿즈</p>
				</div>
				<div class="col-md-6">
					<p style="text-align:center;">카테고리<br>${getGoods.gcategory}</p>
				</div>
			</div>
			
			<div class="row" style="height:125px">
				<div class="col-md-offset-3 col-md-4">
					<p style="text-align:center;font-size:15pt;">재고량</p>
				</div>
				<div class="col-md-4" >
					<p style="font-size:15pt;"> ${getAGoods.gqty}</p>
				</div>
			</div>
			
			<div class="row" style="height:125px">
				<div class="col-md-5">
					<p style="text-align:center;">
						단가 : <fmt:formatNumber value="${getGoods.gprice}" pattern="###,###"/> 원
					</p>
				</div>
			
				<div class="col-md-1">
					<input type='button' class="btn" onclick='count("minus")' value='-'/>
				</div>
				<div class="col-md-2">
					<p style="text-align:center;">수량</p>
				</div>
				<div class="col-md-2">
					<input type="text" class="form-control" name="qty" value="1"
					minlength="1" maxlength="3" size = "3"
					onKeyup="onlyNumber(this)" id="result"/>
				</div>
				<div class="col-md-1">
					<input type='button' class="btn" onclick='count("plus")' value='+'/>
				</div>
				
				<form name="goCartGo" method="post" action="doCartAdd.goods">
					<input type="hidden" id="gnum" name="gnum" value="${getGoods.gnum}"/>
					<input type="hidden" id="gname" name="gname" value="${getGoods.gname}"/>
					<input type="hidden" id="gqty" name="gqty" value="${getGoods.gqty}"/>
				</form>
				
				
			</div>
			<br>
			
			<div class="row" style="height:80px">
				<div class="col-md-12">
					<input type="button" class="btn btn-primary btn-block" value="바로구매" onclick="javascript:goOrder()">
				</div>
			</div>
			
			<div class="row" style="height:80px">
				<div class="col-md-6">
					<input type="button" class="btn btn-secondary btn-block" value="장바구니" onclick="javascript:goCart()" size="20"/>
				</div>
				
				<div class="row" style="height:80px">
					<div class="col-md-6">
						<c:choose>
							<c:when test="${empty checkwish}">
								<input type="button" class="btn btn-success btn-fluid" value="찜하기" onclick="location.href='doGoodsWish.goods?gnum=${getGoods.gnum}&gspec=${getGoods.gspec}&gmovie=${getGoods.gmovie}'">
							</c:when>
							<c:otherwise>
								<input type="button" class="btn btn-danger btn-fluid" value="찜 제거" onclick="location.href='doGoodsWishDelete.goods?gnum=${getGoods.gnum}&gspec=${getGoods.gspec}&gmovie=${getGoods.gmovie}'">
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				
			</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8" style="height:400px;">
			<p style="text-align:center;">상품 상세 설명</p><br>
			<hr color="green" width="100%"><p>
			
			<div class="col-md-offset-1">
				<br>
				${getGoods.gcontents}
			</div>
			
		</div>
	</div>
	<hr color="green" width="100%">
	<br>
	
	<div class="row">
		<div class="col-md-offset-6 col-md-1">
			<button class="btn btn-success btn-fluid" onclick="history.back()" >돌아가기</button>
		</div>
	</div>
	<br><br>
	
	