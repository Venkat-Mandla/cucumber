<!-- Identity template for empty elements -->
<xsl:template match="*[not(node())]">
    <xsl:copy>
        <xsl:apply-templates select="@* | node()" />
        <xsl:value-of select="$empty"/>
    </xsl:copy>
</xsl:template>