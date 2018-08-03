<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>拜访记录列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
	<!-- 日期插件，使用jquery -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery-1.4.2.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery/jquery.datepick.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery.datepick.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery.datepick-zh-CN.js"></script>





<SCRIPT language=javascript>
	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		document.customerForm.submit();
	}

	$(function () {
        $("#visit_time").datepick({dateFormat:'yy-mm-dd'});
        $("#visit_end_time").datepick({dateFormat:'yy-mm-dd'});
    })

</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/clientVisit_findByPage.action"
		method=post>
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>拜访时间：</TD>
													<TD>
													<INPUT class=textbox id="visit_time"
														style="WIDTH: 80px" maxLength=50 name="visit_time" readonly="readonly" 
														value="<s:date name="visit_time" format="yy-MM-dd"></s:date>">

													</TD>
													<TD>至</TD>
													<TD>
													<INPUT class=textbox id="visit_end_time"
														style="WIDTH: 80px" maxLength=50 name="visit_end_time" readonly="readonly" 
														value="<s:date name="visit_end_time" format="yy-MM-dd"></s:date>">
													</TD>
													<TD><INPUT class=button id=sButton2 type=submit value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>业务员名称</TD>
													<TD>拜访客户名称</TD>
													<TD>拜访时间</TD>
													<TD>拜访地点</TD>
													<TD>拜访详情</TD>
													<TD>下次拜访时间</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="list">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD><s:property value="user.user_name"></s:property></TD>
													<TD><s:property value="customer.cust_name"></s:property></TD>
													<TD><s:property value="visit_time"></s:property></TD>
													<TD><s:property value="visit_location"></s:property></TD>
													<TD><s:property value="visit_detail"></s:property></TD>
													<TD><s:property value="visit_nexttime"></s:property></TD>
													<TD>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath }/clientVisit_delete.action?visit_id=<s:property value="visit_id"/>">删除</a>
													</TD>
												</TR>
												</s:iterator>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="totalCount"/></B>]条记录,[<B><s:property value="totalPage"></s:property></B>]页
												,每页显示
												
												<select name="pageSize" onchange="to_page()">
												<option value="3" <s:if test="pageSize==3">selected</s:if>>3</option>
												<option value="5" <s:if test="pageSize==5">selected</s:if>>5</option>
												<option value="10" <s:if test="pageSize==10">selected</s:if>>10</option>
												</select>
												条

												<s:if test="currentPage>1">
													[<A href="javascript:to_page(<s:property value="currentPage-1"></s:property> )">前一页</A>]
												</s:if>
												
												<B>
													<s:iterator begin="1" end="totalPage" var="i">
														<s:if test="currentPage==#i">
															<s:property value="#i"></s:property>
														</s:if>
														<s:else>
															<a href="javascript:to_page(<s:property value="#i"/>)"><s:property value="#i"/></a>
														</s:else>
													</s:iterator>

												</B>
												
												<s:if test="currentPage<totalPage">
													[<A href="javascript:to_page(<s:property value="currentPage+1"></s:property>)">后一页</A>]
												</s:if>
												
												到
												<input type="text" size="3" id="page" name="currentPage"  value="<s:property value="currentPage"/>"/>
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
