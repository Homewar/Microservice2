<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes"/>

    <!-- корневой элемент -->
    <xsl:template match="/person">
            <person>
                <xsl:attribute name="name"       ><xsl:value-of select="name"/></xsl:attribute>
                <xsl:attribute name="surname"    ><xsl:value-of select="surname"/></xsl:attribute>
                <xsl:attribute name="patronymic" ><xsl:value-of select="patronymic"/></xsl:attribute>
                <xsl:attribute name="birthDate">
                    <xsl:value-of select="concat(substring(birthDate,9,2), '.', substring(birthDate,6,2), '.', substring(birthDate,1,4))"/>
                </xsl:attribute>
                <xsl:attribute name="gender"     ><xsl:value-of select="gender"/></xsl:attribute>

                <xsl:apply-templates select="document"/>
            </person>
    </xsl:template>

    <!-- документ -->
    <xsl:template match="document">
        <document>
            <xsl:attribute name="series"    ><xsl:value-of select="series"/></xsl:attribute>
            <xsl:attribute name="number"    ><xsl:value-of select="number"/></xsl:attribute>
            <xsl:attribute name="type"      ><xsl:value-of select="type"/></xsl:attribute>
            <xsl:attribute name="issueDate">
                <xsl:value-of select="concat(substring(issueDate,9,2), '.', substring(issueDate,6,2), '.', substring(issueDate,1,4))"/>
            </xsl:attribute>
        </document>
    </xsl:template>

</xsl:stylesheet>
