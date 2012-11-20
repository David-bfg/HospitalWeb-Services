<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<xml id="v_xml" style="display:none;">
	<rows>
		<xsl:for-each select="list/Tape">
		     <row id="{position()}">
			       <xsl:attribute name="id">
			       				  <xsl:value-of select="tapeID"/>
				  </xsl:attribute>

			     <cell><xsl:value-of select="clinicNum"/></cell>
			     <cell><xsl:value-of select="visitID"/></cell>
			     <cell><xsl:value-of select="tapeNum"/></cell>
			     <cell><xsl:value-of select="studyID"/></cell>
			     <cell><xsl:value-of select="backup"/></cell>
		     </row>
		</xsl:for-each>

	</rows>
</xml>
</xsl:template>
</xsl:stylesheet>