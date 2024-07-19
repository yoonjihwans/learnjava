<?xml version="1.0" encoding="UTF-8"?>
<!-- XSL 문서 : XSLT 데이타를 저장한 XML 문서 -->
<!-- XSLT(eXtensible Stylesheet Language Template) : XSL를 사용해 변환정보를 제공하는 파서(Parser) -->
<!-- => XML 데이타를 제공받아 XML 문서 또는 HTML 문서로 변환 -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- output : XML 데이타를 제공받아 변환하기 위한 문서의 형식을 설정하는 엘리먼트 -->
	<!-- method 속성 : 변환 처리될 파일 형식을 속성값으로 설정 - 기본값 : xml -->
	<!-- encoding 속성 : 변환문서에서 사용하는 문자형태를 속성값으로 설정 - 기본값 : iso-8859-1 -->
	<xsl:output method="html" encoding="utf-8"/>
	<!-- template : 변환정보를 제공하는 엘리먼트 -->
	<!-- match 속성 : XML 문서의 최상위 엘리먼트의 이름을 속성값으로 설정 -->
	<xsl:template match="books">
		<ol>
			<!-- for-each : 반복 처리하기 위한 엘리먼트 -->
			<!-- select 속성 : 반복적으로 처리할 엘리먼트의 이름을 속성값으로 설정 -->
			<xsl:for-each select="book">
				<!-- value-of : 태그내용을 제공하기 위한 엘리먼트 -->
				<!-- select 속성 : 태그내용을 제공하기 위한 엘리먼트의 이름을 속성값으로 설정 -->
				<li><b><xsl:value-of select="title"/></b>[<xsl:value-of select="author"/>]</li>
			</xsl:for-each>
		</ol>
	</xsl:template>
</xsl:stylesheet>