<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<xml id="v_xml" style="display:none;">
	<rows>
		<xsl:for-each select="list/Visit">
		     <row id="{position()}">
			     <cell><xsl:value-of select="visitID"/></cell>
			     <cell><xsl:value-of select="date"/></cell>
			     <cell><xsl:value-of select="visitNum"/></cell>
			     <cell><xsl:value-of select="clinicNum"/></cell>
			     <cell><xsl:value-of select="provider"/></cell>
			     <cell><xsl:value-of select="kinesiologist"/></cell>
			     <cell><xsl:value-of select="dateProcessingComplete"/></cell>
			     <cell><xsl:value-of select="physicalTherapist"/></cell>
			     <cell><xsl:value-of select="dateAnalysisComplete"/></cell>
		     </row>
		</xsl:for-each>

	</rows>
</xml>
</xsl:template>
</xsl:stylesheet>