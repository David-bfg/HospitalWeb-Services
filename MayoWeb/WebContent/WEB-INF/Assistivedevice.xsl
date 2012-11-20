<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<xml id="v_xml" style="display:none;">
	<rows>
		<xsl:for-each select="list/Assistivedevice">
		     <row id="{position()}">
			     <cell><xsl:value-of select="name"/></cell>
		     </row>
		</xsl:for-each>

	</rows>
</xml>
</xsl:template>
</xsl:stylesheet>