<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<xml id="p_xml" style="display:none;">
	<rows>
		<xsl:for-each select="list/Patient">
		     <row id="{position()}">
			     <cell><xsl:value-of select="clinicNum"/></cell>
			     <cell><xsl:value-of select="lastname"/></cell>
			     <cell><xsl:value-of select="firstname"/></cell>
			     <cell><xsl:value-of select="dob"/></cell>
			     <cell><xsl:value-of select="gender"/></cell>
			     <cell><xsl:value-of select="height"/></cell>
			     <cell><xsl:value-of select="weight"/></cell>
			     <cell><xsl:value-of select="side"/></cell>
			     <cell><xsl:value-of select="extremity"/></cell>
			     <cell><xsl:value-of select="involved"/></cell>
			     <cell><xsl:value-of select="dominant"/></cell>
			     <cell><xsl:value-of select="measuredSide"/></cell>
			     <cell><xsl:value-of select="problemDescriptor"/></cell>
			 </row>
		</xsl:for-each>

	</rows>
</xml>
</xsl:template>
</xsl:stylesheet>