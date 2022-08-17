<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  
<%@ include file="AdminManagerTop.jsp"%>

<style>
  #page-wrapper {
    padding-left: 250px;
  }
  #sidebar-wrapper {
    position: fixed;
    width: 250px;
    height: 100%;
    margin-left: -250px;
    background: #000;
    overflow-x: hidden;
    overflow-y: auto;
  }
  #page-content-wrapper {
    width: 100%;
    padding: 20px;
  }
  .sidebar-nav {
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
  }
  .sidebar-nav li {
    text-indent: 1.5em;
    line-height: 2.8em;
  }
  .sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #999;
  }
  .sidebar-nav li a:hover {
    color: #fff;
    background: rgba(255, 255, 255, 0.2);
  }
  .sidebar-nav > .sidebar-brand {
    font-size: 1.3em;
    line-height: 3em;
  }
</style>  

<div class="page-wrapper">
	<div id="sidebar-wrapper">
	    <ul class="sidebar-nav">
	      <li class="sidebar-brand">
	        <a href="#">매출 관리</a>
	      </li>
	      <li><a href="goYearSalelist.admin">연단위 매출</a></li><br>
	      <li><a href="goMonthSalelist.admin">월단위 매출</a></li><br>
	      <li><a href="goDaySalelist.admin">일단위 매출</a></li>
	    </ul>
	  </div>
</div>