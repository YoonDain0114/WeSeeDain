<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ include file="GoodsMainTop.jsp"%>

<style>
	th, td {
	padding: 15px;
    }
    *{ padding: 0; margin: 0; }
li{ list-style: none; }

.category{
  width: 1400px; margin:  auto;
 
  overflow: hidden;
}
.category h3{
  width: 100%; height: 50px;
  line-height: 50px; text-align: center;
}
.category li{
  float: left;
  width: 300px; height: 200px;
  margin-right: 20px;
}
.category li:last-child{ margin-right: 0; }

.category a{
  display: block; /* 영역적용위해 사용 */
  width: 100%; height: 100%;

  overflow: hidden;

  position: relative; /* absolute의 기본기준은 body로 처리 - 현재 요소로 기준변경 */
}

.category figure{
  width: 100%; height: 100%;
}
.category figcaption{
  width: 100%; height: 100%;
  background-color: rgba(0,0,0,0.7);

  position: absolute; /* 이미지와 겹치게 처리 */
  top: 0; left: 0;

  color: #fff; text-align: center;
  line-height: 200px;

  opacity: 0; /* 처음엔 안보이고 */

  transition: 0.3s;
}

.category a:hover figcaption, .category a:focus figcaption{
  /* 마우스를 올리면 보이게 처리 */
  opacity: 1;
}
</style>
	
	<div class="row">
		<form name="f" action="doSearch.goods" method="post">
			<div class="col-md-offset-4 col-md-3">			
				<input type="text" class="form-control input-lg" name="search" size="50"
				placeholder="상품명을 입력해주세요." >
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-primary btn-lg" value="검색">
			</div>
		</form>
	</div>
	<br><br>
	
	<div class="category">
		<div class="col-md-offset-2">
			<table>
				<tr style="height:500px;">
					<th>
						<a href="goGoodsCateList.goods?gcategory=ecobag">
						<figure>
							<img src="resources/ecobag.jpg" style="width:250px;height:250px;">
						<figcaption>자세히보기</figcaption>
						</figure>
						</a><br><br>
						<p style="text-align:center;">에코백</p>
					</th>
					
					<th>
						<a href="goGoodsCateList.goods?gcategory=cup">
						<figure>
							<img src="resources/다운로드6.jpg" style="width:250px;height:250px;">
						<figcaption>자세히보기</figcaption>
                        </figure>
						</a><br><br>
						<p style="text-align:center;">컵</p>
					</th>
					
					<th>
						<a href="goGoodsCateList.goods?gcategory=poster">
						<figure>
							<img src="resources/poster.jpg" style="width:250px;height:250px;">
						<figcaption>자세히보기</figcaption>
                        </figure>
						</a><br><br>
						<p style="text-align:center;">포스터</p>
					</th>
				</tr>
				
				<tr style="height:500px;">
					<th>
						<a href="goGoodsCateList.goods?gcategory=griptok">
						<figure>
								<img src="resources/다운로드3.jpg" style="width:250px;height:250px;">
						<figcaption>자세히보기</figcaption>
                        </figure>
						</a><br><br>
						<p style="text-align:center;">그립톡</p>
					</th>
					
					<th>
						<a href="goGoodsCateList.goods?gcategory=keyring">
						<figure>
							<img src="resources/다운로드1.jpg" style="width:250px;height:250px;">
						<figcaption>자세히보기</figcaption>
                        </figure>
						</a><br><br>
						<p style="text-align:center;">키링</p>
					</th>
					
					<th>
						<a href="goGoodsCateList.goods?gcategory=filmmark">
						<figure>
							<img src="resources/filmmark.jpg" style="width:250px;height:250px;">
						<figcaption>자세히보기</figcaption>
                        </figure>
						</a><br><br>
						<p style="text-align:center;">필름마크</p>
					</th>
				</tr>
				
				<tr style="height:500px;">
					<th>
						<a href="goGoodsCateList.goods?gcategory=stationery">
						<figure>
							<img src="resources/다운로드2.jpg" style="width:250px;height:250px;">
						<figcaption>자세히보기</figcaption>
                        </figure>
						</a><br><br>
						<p style="text-align:center;">문구류</p>
					</th>
					
					<th>
						<a href="goGoodsCateList.goods?gcategory=etc">
						<figure>
							<img src="resources/etc.jpg" style="width:250px;height:250px;">
						<figcaption>자세히보기</figcaption>
                        </figure> 
						</a><br><br>
						<p style="text-align:center;">기타</p>
					</th>
				</tr>
			</table>
		</div>
	</div>
	
	