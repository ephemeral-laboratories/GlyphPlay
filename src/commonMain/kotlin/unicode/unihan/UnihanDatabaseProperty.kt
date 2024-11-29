package garden.ephemeral.glyphplay.unicode.unihan

import garden.ephemeral.glyphplay.unicode.UnicodeVersion

/**
 * Enumeration of properties in the Unihan database.
 *
 * For detailed definitions, see
 * [Unicode® Standard Annex #38 - Unicode Han Database (Unihan)](https://www.unicode.org/reports/tr38/).
 */
enum class UnihanDatabaseProperty(
    val displayName: String,
    val category: UnihanDatabaseCategory,
    val sinceUnicode: UnicodeVersion,
) {
    kAccountingNumeric(
        displayName = "Accounting Numeric",
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kAlternateTotalStrokes(
        displayName = "Alternate Total Strokes",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_15_0_0
    ),
    kBigFive(
        displayName = "Big Five",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCangjie(
        displayName = "Cangjie",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kCantonese(
        displayName = "Cantonese",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCCCII(
        displayName = "CCCII",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCheungBauer(
        displayName = "Cheung Bauer",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_5_0
    ),
    kCheungBauerIndex(
        displayName = "Cheung Bauer Index",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_5_0
    ),
    kCihaiT(
        displayName = "Cihai T",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kCNS1986(
        displayName = "CNS 11643-1986",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCNS1992(
        displayName = "CNS 11643-1992",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCompatibilityVariant(
        displayName = "Compatibility Variant",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kCowles(
        displayName = "Cowles",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kDaeJaweon(
        displayName = "Dae Jaweon",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kDefinition(
        displayName = "Definition",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kEACC(
        displayName = "EACC",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kFanqie(
        displayName = "Fanqie",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_16_0_0
    ),
    kFenn(
        displayName = "Fenn",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kFennIndex(
        displayName = "Fenn Index",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_4_1
    ),
    kFourCornerCode(
        displayName = "FourCornerCode",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_5_0
    ),
    kGB0(
        displayName = "GB/T 2312-1980",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB1(
        displayName = "GB/T 12345-1990",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB3(
        displayName = "GB/T 13131",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB5(
        displayName = "GB/T 13132",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB7(
        displayName = "General Purpose Hanzi List",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB8(
        displayName = "GB/T 8565.2-1988",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGradeLevel(
        displayName = "Grade Level",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kGSR(
        displayName = "GSR",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_4_0_1
    ),
    kHangul(
        displayName = "Hangul",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_5_0
    ),
    kHanYu(
        displayName = "Han Yu",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kHanyuPinlu(
        displayName = "Hanyu Pinlu",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_4_0_1
    ),
    kHanyuPinyin(
        displayName = "Hanyu Pinyin",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_5_2
    ),
    kHDZRadBreak(
        displayName = "HDZ Rad Break",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_4_1
    ),
    kHKGlyph(
        displayName = "HK Glyph",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kIBMJapan(
        displayName = "IBM Japan",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kIICore(
        displayName = "II Core",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_4_1
    ),
    kIRGDaeJaweon(
        displayName = "IRG Dae Jaweon",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRGHanyuDaZidian(
        displayName = "IRG Hanyu Da Zidian",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRGKangXi(
        displayName = "IRG Kang Xi",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_GSource(
        displayName = "IRG \"G\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_HSource(
        displayName = "IRG \"H\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_1
    ),
    kIRG_JSource(
        displayName = "IRG \"J\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_KPSource(
        displayName = "IRG \"KP\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kIRG_KSource(
        displayName = "IRG \"K\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_MSource(
        displayName = "IRG \"M\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_5_2
    ),
    kIRG_SSource(
        displayName = "IRG \"S\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kIRG_TSource(
        displayName = "IRG \"T\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_UKSource(
        displayName = "IRG \"UK\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kIRG_USource(
        displayName = "IRG \"U\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_4_0_1
    ),
    kIRG_VSource(
        displayName = "IRG \"V\" Source",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kJa(
        displayName = "Ja",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_8_0
    ),
    kJapanese(
        displayName = "Japanese",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kJapaneseKun(
        displayName = "Japanese Kun",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kJapaneseOn(
        displayName = "Japanese On",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kJinmeiyoKanji(
        displayName = "Jinmeiyo Kanji",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kJis0(
        displayName = "JIS X 0208-1990",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kJis1(
        displayName = "JIS X 0212-1990",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kJIS0213(
        displayName = "JIS X 0213:2004",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kJoyoKanji(
        displayName = "Joyo Kanji",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kKangXi(
        displayName = "Kang Xi",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kKarlgren(
        displayName = "Karlgren",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kKorean(
        displayName = "Korean",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kKoreanEducationHanja(
        displayName = "Korean Education Hanja",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kKoreanName(
        displayName = "Korean Name",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kLau(
        displayName = "Lau",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kMainlandTelegraph(
        displayName = "Mainland Telegraph",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kMandarin(
        displayName = "Mandarin",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kMatthews(
        displayName = "Matthews",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kMeyerWempe(
        displayName = "Meyer Wempe",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_1
    ),
    kMojiJoho(
        displayName = "Moji Joho",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kMorohashi(
        displayName = "Morohashi",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kNelson(
        displayName = "Nelson",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kOtherNumeric(
        displayName = "Other Numeric",
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kPhonetic(
        displayName = "Phonetic",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_1
    ),
    kPrimaryNumeric(
        displayName = "Primary Numeric",
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kPseudoGB1(
        displayName = "Pseudo-GB1",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kRSAdobe_Japan1_6(
        displayName = "Radical Stroke Count (Adobe-Japan1-6)",
        category = UnihanDatabaseCategory.RadicalStrokeCounts,
        sinceUnicode = UnicodeVersion.VERSION_4_1
    ),
    kRSUnicode(
        displayName = "Radical Stroke Count (Unicode)",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kSBGY(
        displayName = "SBGY",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kSemanticVariant(
        displayName = "Semantic Variant",
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kSimplifiedVariant(
        displayName = "Simplified Variant",
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kSMSZD2003Index(
        displayName = "SMSZD 2003 Index",
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kSMSZD2003Readings(
        displayName = "SMSZD 2003 Readings",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kSpecializedSemanticVariant(
        displayName = "Specialized Semantic Variant",
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kSpoofingVariant(
        displayName = "Spoofing Variant",
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kStrange(
        displayName = "Strange",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_14_0_0
    ),
    kTaiwanTelegraph(
        displayName = "Taiwan Telegraph",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kTang(
        displayName = "Tang",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kTGH(
        displayName = "TGH",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kTGHZ2013(
        displayName = "TGHZ 2013",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kTotalStrokes(
        displayName = "Total Strokes",
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_1
    ),
    kTraditionalVariant(
        displayName = "Traditional Variant",
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kUnihanCore2020(
        displayName = "Unihan Core 2020",
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kVietnamese(
        displayName = "Vietnamese",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kVietnameseNumeric(
        displayName = "Vietnamese Numeric",
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kXerox(
        displayName = "Xerox",
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kXHC1983(
        displayName = "XHC 1983",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_5_1
    ),
    kZhuang(
        displayName = "Zhuang",
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_16_0_0
    ),
    kZhuangNumeric(
        displayName = "Zhuang Numeric",
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kZVariant(
        displayName = "Z-Variant",
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    ;

    init {
        require(name.startsWith("k")) { "We expect Unihan properties to always start with k, got: $name" }
    }

    companion object {
        /**
         * Looks up a Unihan property by name.
         *
         * @param name the name to look up.
         * @return the property.
         * @throws IllegalArgumentException if the name does not match any of the names in this enum.
         */
        fun forName(name: String) = UnihanDatabaseProperty.valueOf(name)
    }
}
