<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/links">
    <ul>
      <xsl:for-each select="link">

        <li><a href="{url}"><span><xsl:value-of select="name"/></span></a></li>
      </xsl:for-each>
    </ul>
</xsl:template>
</xsl:stylesheet>