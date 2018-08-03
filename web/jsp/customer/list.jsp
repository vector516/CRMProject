<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		$("#customerForm").submit();
	}
	function search(){
        $("#page").val(1);
        $("#customerForm").submit();
	}



    $(function () {
        $.getJSON("${pageContext.request.contextPath}/baseDict_findByTypeCode.action", {"dict_type_code": "006"}, function (result) {
            $(result).each(function (index, element) {
                $("#cust_level").append("<option value='"+element.dict_id+"'>" + element.dict_item_name + "</option>")
            })
            $("#cust_level option[value='${baseDictLevel.dict_id}']").prop("selected",true);
        });
        $.getJSON("${pageContext.request.contextPath}/baseDict_findByTypeCode.action", {"dict_type_code": "002"}, function (result) {
            $(result).each(function (index, element) {
                $("#cust_source").append("<option value='"+element.dict_id+"'>" + element.dict_item_name + "</option>")
            })
            $("#cust_source option[value='${baseDictSource.dict_id}']").prop("selected",true);
        });
        $.getJSON("${pageContext.request.contextPath}/baseDict_findByTypeCode.action", {"dict_type_code": "001"}, function (result) {
            $(result).each(function (index, element) {
                $("#cust_industry").append("<option value='"+element.dict_id+"'>" + element.dict_item_name + "</option>")
            })
            $("#cust_industry option[value='${baseDictIndustry.dict_id}']").prop("selected",true);

        });

    })



</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_findByPage.action"
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
													<TD>客户名称：</TD>
													<TD>
													<s:textfield cssStyle="WIDTH: 80px" maxLength="50" name="cust_name"></s:textfield>
													</TD>
													<TD>客户来源：</TD>
													<TD>
														<select id="cust_source" name="baseDictSource.dict_id">
															<option value="">-请选择-</option>
														</select>
													</TD>
													<TD>客户级别：</TD>
													<TD>
														<select id="cust_level" name="baseDictLevel.dict_id">
															<option value="">-请选择-</option>
														</select>
													</TD>
													<TD>客户行业：</TD>
													<TD>
														<select id="cust_industry" name="baseDictIndustry.dict_id">
															<option value="">-请选择-</option>
														</select>
													</TD>

													
													<TD><INPUT class=button id=sButton2 type=button
														value=" 筛选 " name=sButton2  onclick="search()"></TD>
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
													<TD>客户名称</TD>
													<TD>图片下载</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>客户行业</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="list">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD><s:property value="cust_name"/></TD>
													<%--<TD><s:a href="{pageContext.request.ContextPath}/download.action?cust_image=E:/upload/8/2/b73ef070776f4e85b063e70587133915.jpg>图片下载</s:a></TD>--%>
													<TD><a href="{pageContext.request.ContextPath}/download_download.action?cust_image=<s:property value="cust_image"/>">图片下载</a></TD>
													<TD><s:property value="baseDictLevel.dict_item_name"/></TD>
													<TD><s:property value="baseDictSource.dict_item_name"/></TD>
													<TD><s:property value="baseDictIndustry.dict_item_name"/></TD>
													<TD><s:property value="cust_phone"/></TD>
													<TD><s:property value="cust_mobile"/></TD>
													<TD>
													<a href="${pageContext.request.contextPath }/customer_update.action?cust_id=<s:property value="cust_id"/>">修改</a>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath }/customer_delete.action?cust_id=<s:property value="cust_id"/>">删除</a>
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
												共[<B>${totalCount}</B>]条记录,[<B>${totalPage}</B>]页
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
												<s:if test="currentPage<totalPage">
													[<A href="javascript:to_page(<s:property value="currentPage+1"></s:property>)">后一页</A>]
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
