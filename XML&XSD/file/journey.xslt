<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <table cellpadding="2" cellspacing="2" border="1">
            <tr>
                <th>ID</th>
                <th>Days</th>
                <th>Cost</th>
                <th>Country</th>
                <th>Name</th>
                <th>Stars</th>
            </tr>
            <xsl:apply-templates select="tourist-vouchers"/>

        </table>
    </xsl:template>

    <xsl:template match="tourist-vouchers">
        <xsl:apply-templates select="rest"></xsl:apply-templates>
    </xsl:template>

    <xsl:template match="rest">
        <tr>
            <td>
                <xsl:value-of select="id"></xsl:value-of>
            </td>
            <td>
                <xsl:value-of select="days"></xsl:value-of>
            </td>
            <td>
                <xsl:value-of select="cost"></xsl:value-of>
            </td>
            <td>
                <xsl:value-of select="country"></xsl:value-of>
            </td>
            <td>
                <xsl:value-of select="hotel/name"></xsl:value-of>
            </td>
            <td>
                <xsl:value-of select="hotel/star"></xsl:value-of>
            </td>
        </tr>
    </xsl:template>

</xsl:stylesheet>