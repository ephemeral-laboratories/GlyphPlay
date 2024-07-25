package garden.ephemeral.glyphplay

import com.ibm.icu.text.DateFormat
import com.ibm.icu.util.GregorianCalendar
import com.ibm.icu.util.VersionInfo

data class VersionInfoSummary(
    val versionString: String,
    val releaseYear: Int,
    val releaseMonth: Int,
    val releaseDayOfMonth: Int? = null
) {
    val versionDateString: String get() = if (releaseDayOfMonth != null) {
        dateFormatWithDayOfMonth.format(GregorianCalendar(releaseYear, releaseMonth, releaseDayOfMonth))
    } else {
        dateFormatWithoutDayOfMonth.format(GregorianCalendar(releaseYear, releaseMonth, 1))
    }

    val fullDescription: String get() = "$versionString ($versionDateString)"

    companion object {
        private val dateFormatWithoutDayOfMonth: DateFormat = DateFormat.getInstanceForSkeleton("MMM yyyy")
        private val dateFormatWithDayOfMonth: DateFormat = DateFormat.getInstanceForSkeleton("d MMM yyyy")

        private val versionInfoMap = mapOf(
            VersionInfo.UNICODE_1_0 to VersionInfoSummary("1.0.0", 1991, GregorianCalendar.OCTOBER),
            VersionInfo.UNICODE_1_0_1 to VersionInfoSummary("1.0.1", 1992, GregorianCalendar.JUNE),
            VersionInfo.UNICODE_1_1_0 to VersionInfoSummary("1.1.0", 1993, GregorianCalendar.JUNE),
            VersionInfo.UNICODE_1_1_5 to VersionInfoSummary("1.1.5", 1995, GregorianCalendar.JULY),
            VersionInfo.UNICODE_2_0 to VersionInfoSummary("2.0.0", 1996, GregorianCalendar.JULY),
            VersionInfo.UNICODE_2_1_2 to VersionInfoSummary("2.1.2", 1998, GregorianCalendar.MAY),
            VersionInfo.UNICODE_2_1_5 to VersionInfoSummary("2.1.5", 1998, GregorianCalendar.AUGUST),
            VersionInfo.UNICODE_2_1_8 to VersionInfoSummary("2.1.8", 1998, GregorianCalendar.DECEMBER),
            VersionInfo.UNICODE_2_1_9 to VersionInfoSummary("2.1.9", 1999, GregorianCalendar.APRIL),

            // https://www.unicode.org/mail-arch/unicode-ml/Archives-Old/UML018/0792.html
            VersionInfo.UNICODE_3_0 to VersionInfoSummary("3.0.0", 1999, GregorianCalendar.SEPTEMBER, 13),

            // https://www.unicode.org/mail-arch/unicode-ml/Archives-Old/UML024/0082.html
            VersionInfo.UNICODE_3_0_1 to VersionInfoSummary("3.0.1", 2000, GregorianCalendar.SEPTEMBER, 2),

            // https://unicode.org/mail-arch/unicode-ml/y2001-m03/0195.html
            VersionInfo.UNICODE_3_1_0 to VersionInfoSummary("3.1.0", 2001, GregorianCalendar.MARCH, 30),

            // Date of last edit: https://www.unicode.org/Public/3.1-Update1/ReadMe-3.1.1.txt
            // TODO: Released when? https://unicode.org/mail-arch/unicode-ml/y2001-m08/0115.html
            VersionInfo.UNICODE_3_1_1 to VersionInfoSummary("3.1.1", 2001, GregorianCalendar.AUGUST, 10),

            // https://unicode.org/mail-arch/unicode-ml/y2002-m03/0692.html
            VersionInfo.UNICODE_3_2 to VersionInfoSummary("3.2.0", 2002, GregorianCalendar.MARCH, 27),

            // https://unicode.org/mail-arch/unicode-ml/y2003-m04/0229.html
            VersionInfo.UNICODE_4_0 to VersionInfoSummary("4.0.0", 2003, GregorianCalendar.APRIL, 18),

            // https://unicode.org/mail-arch/unicode-ml/y2004-m04/0059.html
            VersionInfo.UNICODE_4_0_1 to VersionInfoSummary("4.0.1", 2004, GregorianCalendar.MARCH, 30),

            // Dates from here on are all well-recorded
            VersionInfo.UNICODE_4_1 to VersionInfoSummary("4.1.0", 2005, GregorianCalendar.MARCH, 31),
            VersionInfo.UNICODE_5_0 to VersionInfoSummary("5.0.0", 2006, GregorianCalendar.JULY, 14),
            VersionInfo.UNICODE_5_1 to VersionInfoSummary("5.1.0", 2008, GregorianCalendar.APRIL, 4),
            VersionInfo.UNICODE_5_2 to VersionInfoSummary("5.2.0", 2009, GregorianCalendar.OCTOBER, 1),
            VersionInfo.UNICODE_6_0 to VersionInfoSummary("6.0.0", 2010, GregorianCalendar.OCTOBER, 11),
            VersionInfo.UNICODE_6_1 to VersionInfoSummary("6.1.0", 2012, GregorianCalendar.JANUARY, 31),
            VersionInfo.UNICODE_6_2 to VersionInfoSummary("6.2.0", 2012, GregorianCalendar.SEPTEMBER, 26),
            VersionInfo.UNICODE_6_3 to VersionInfoSummary("6.3.0", 2013, GregorianCalendar.SEPTEMBER, 30),
            VersionInfo.UNICODE_7_0 to VersionInfoSummary("7.0.0", 2014, GregorianCalendar.JUNE, 16),
            VersionInfo.UNICODE_8_0 to VersionInfoSummary("8.0.0", 2015, GregorianCalendar.JUNE, 17),
            VersionInfo.UNICODE_9_0 to VersionInfoSummary("9.0.0", 2016, GregorianCalendar.JUNE, 21),
            VersionInfo.UNICODE_10_0 to VersionInfoSummary("10.0.0", 2017, GregorianCalendar.JUNE, 20),
            VersionInfo.UNICODE_11_0 to VersionInfoSummary("11.0.0", 2018, GregorianCalendar.JUNE, 5),
            VersionInfo.UNICODE_12_0 to VersionInfoSummary("12.0.0", 2019, GregorianCalendar.MARCH, 5),
            VersionInfo.UNICODE_12_1 to VersionInfoSummary("12.1.0", 2019, GregorianCalendar.MAY, 7),
            VersionInfo.UNICODE_13_0 to VersionInfoSummary("13.0.0", 2020, GregorianCalendar.MARCH, 10),
            VersionInfo.UNICODE_14_0 to VersionInfoSummary("14.0.0", 2021, GregorianCalendar.SEPTEMBER, 14),
            VersionInfo.UNICODE_15_0 to VersionInfoSummary("15.0.0", 2022, GregorianCalendar.SEPTEMBER, 13),
            VersionInfo.UNICODE_15_1 to VersionInfoSummary("15.1.0", 2023, GregorianCalendar.SEPTEMBER, 12),
        )

        fun of(versionInfo: VersionInfo) = versionInfoMap[versionInfo]
            ?: throw IllegalArgumentException("Unknown Unicode version: $versionInfo")
    }
}

