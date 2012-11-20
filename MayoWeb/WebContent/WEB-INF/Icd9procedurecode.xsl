<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xml id="v_xml" style="display:none;">
			<rows>
				<xsl:for-each select="list/Icd9procedurecode">
					<row>
						<xsl:attribute name="id">
					     <xsl:value-of select="icd9ProcedureCodeID" />
				   </xsl:attribute>
						<cell><xsl:value-of select="icd9ProcedureCode" /></cell>
						<cell><xsl:value-of select="procedureDescriptor" /></cell>
					</row>
				</xsl:for-each>
			</rows>
		</xml>
	</xsl:template>
</xsl:stylesheet>