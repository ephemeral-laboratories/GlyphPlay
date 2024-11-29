package garden.ephemeral.glyphplay.unicode.unihan

import garden.ephemeral.glyphplay.unicode.UnicodeVersion
import garden.ephemeral.glyphs.glyphplay.generated.resources.Res
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kAccountingNumeric
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kAlternateTotalStrokes
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kBigFive
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCCCII
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCNS1986
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCNS1992
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCangjie
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCantonese
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCheungBauer
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCheungBauerIndex
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCihaiT
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCompatibilityVariant
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kCowles
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kDaeJaweon
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kDefinition
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kEACC
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kFanqie
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kFenn
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kFennIndex
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kFourCornerCode
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kGB0
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kGB1
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kGB3
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kGB5
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kGB7
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kGB8
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kGSR
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kGradeLevel
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kHDZRadBreak
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kHKGlyph
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kHanYu
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kHangul
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kHanyuPinlu
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kHanyuPinyin
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIBMJapan
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIICore
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRGDaeJaweon
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRGHanyuDaZidian
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRGKangXi
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_GSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_HSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_JSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_KPSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_KSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_MSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_SSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_TSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_UKSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_USource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kIRG_VSource
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kJIS0213
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kJa
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kJapanese
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kJapaneseKun
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kJapaneseOn
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kJinmeiyoKanji
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kJis0
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kJis1
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kJoyoKanji
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kKangXi
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kKarlgren
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kKorean
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kKoreanEducationHanja
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kKoreanName
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kLau
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kMainlandTelegraph
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kMandarin
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kMatthews
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kMeyerWempe
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kMojiJoho
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kMorohashi
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kNelson
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kOtherNumeric
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kPhonetic
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kPrimaryNumeric
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kPseudoGB1
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kRSAdobe_Japan1_6
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kRSUnicode
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kSBGY
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kSMSZD2003Index
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kSMSZD2003Readings
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kSemanticVariant
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kSimplifiedVariant
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kSpecializedSemanticVariant
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kSpoofingVariant
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kStrange
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kTGH
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kTGHZ2013
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kTaiwanTelegraph
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kTang
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kTotalStrokes
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kTraditionalVariant
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kUnihanCore2020
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kVietnamese
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kVietnameseNumeric
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kXHC1983
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kXerox
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kZVariant
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kZhuang
import garden.ephemeral.glyphs.glyphplay.generated.resources.name_kZhuangNumeric
import org.jetbrains.compose.resources.StringResource

/**
 * Enumeration of properties in the Unihan database.
 *
 * For detailed definitions, see
 * [Unicode® Standard Annex #38 - Unicode Han Database (Unihan)](https://www.unicode.org/reports/tr38/).
 */
enum class UnihanDatabaseProperty(
    val displayNameResource: StringResource,
    val category: UnihanDatabaseCategory,
    val sinceUnicode: UnicodeVersion,
) {
    kAccountingNumeric(
        displayNameResource = Res.string.name_kAccountingNumeric,
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kAlternateTotalStrokes(
        displayNameResource = Res.string.name_kAlternateTotalStrokes,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_15_0_0
    ),
    kBigFive(
        displayNameResource = Res.string.name_kBigFive,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCangjie(
        displayNameResource = Res.string.name_kCangjie,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kCantonese(
        displayNameResource = Res.string.name_kCantonese,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCCCII(
        displayNameResource = Res.string.name_kCCCII,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCheungBauer(
        displayNameResource = Res.string.name_kCheungBauer,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_5_0
    ),
    kCheungBauerIndex(
        displayNameResource = Res.string.name_kCheungBauerIndex,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_5_0
    ),
    kCihaiT(
        displayNameResource = Res.string.name_kCihaiT,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kCNS1986(
        displayNameResource = Res.string.name_kCNS1986,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCNS1992(
        displayNameResource = Res.string.name_kCNS1992,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kCompatibilityVariant(
        displayNameResource = Res.string.name_kCompatibilityVariant,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kCowles(
        displayNameResource = Res.string.name_kCowles,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kDaeJaweon(
        displayNameResource = Res.string.name_kDaeJaweon,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kDefinition(
        displayNameResource = Res.string.name_kDefinition,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kEACC(
        displayNameResource = Res.string.name_kEACC,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kFanqie(
        displayNameResource = Res.string.name_kFanqie,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_16_0_0
    ),
    kFenn(
        displayNameResource = Res.string.name_kFenn,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kFennIndex(
        displayNameResource = Res.string.name_kFennIndex,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_4_1
    ),
    kFourCornerCode(
        displayNameResource = Res.string.name_kFourCornerCode,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_5_0
    ),
    kGB0(
        displayNameResource = Res.string.name_kGB0,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB1(
        displayNameResource = Res.string.name_kGB1,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB3(
        displayNameResource = Res.string.name_kGB3,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB5(
        displayNameResource = Res.string.name_kGB5,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB7(
        displayNameResource = Res.string.name_kGB7,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGB8(
        displayNameResource = Res.string.name_kGB8,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kGradeLevel(
        displayNameResource = Res.string.name_kGradeLevel,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kGSR(
        displayNameResource = Res.string.name_kGSR,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_4_0_1
    ),
    kHangul(
        displayNameResource = Res.string.name_kHangul,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_5_0
    ),
    kHanYu(
        displayNameResource = Res.string.name_kHanYu,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kHanyuPinlu(
        displayNameResource = Res.string.name_kHanyuPinlu,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_4_0_1
    ),
    kHanyuPinyin(
        displayNameResource = Res.string.name_kHanyuPinyin,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_5_2
    ),
    kHDZRadBreak(
        displayNameResource = Res.string.name_kHDZRadBreak,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_4_1
    ),
    kHKGlyph(
        displayNameResource = Res.string.name_kHKGlyph,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kIBMJapan(
        displayNameResource = Res.string.name_kIBMJapan,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kIICore(
        displayNameResource = Res.string.name_kIICore,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_4_1
    ),
    kIRGDaeJaweon(
        displayNameResource = Res.string.name_kIRGDaeJaweon,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRGHanyuDaZidian(
        displayNameResource = Res.string.name_kIRGHanyuDaZidian,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRGKangXi(
        displayNameResource = Res.string.name_kIRGKangXi,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_GSource(
        displayNameResource = Res.string.name_kIRG_GSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_HSource(
        displayNameResource = Res.string.name_kIRG_HSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_1
    ),
    kIRG_JSource(
        displayNameResource = Res.string.name_kIRG_JSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_KPSource(
        displayNameResource = Res.string.name_kIRG_KPSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kIRG_KSource(
        displayNameResource = Res.string.name_kIRG_KSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_MSource(
        displayNameResource = Res.string.name_kIRG_MSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_5_2
    ),
    kIRG_SSource(
        displayNameResource = Res.string.name_kIRG_SSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kIRG_TSource(
        displayNameResource = Res.string.name_kIRG_TSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kIRG_UKSource(
        displayNameResource = Res.string.name_kIRG_UKSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kIRG_USource(
        displayNameResource = Res.string.name_kIRG_USource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_4_0_1
    ),
    kIRG_VSource(
        displayNameResource = Res.string.name_kIRG_VSource,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_0
    ),
    kJa(
        displayNameResource = Res.string.name_kJa,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_8_0
    ),
    kJapanese(
        displayNameResource = Res.string.name_kJapanese,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kJapaneseKun(
        displayNameResource = Res.string.name_kJapaneseKun,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kJapaneseOn(
        displayNameResource = Res.string.name_kJapaneseOn,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kJinmeiyoKanji(
        displayNameResource = Res.string.name_kJinmeiyoKanji,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kJis0(
        displayNameResource = Res.string.name_kJis0,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kJis1(
        displayNameResource = Res.string.name_kJis1,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kJIS0213(
        displayNameResource = Res.string.name_kJIS0213,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kJoyoKanji(
        displayNameResource = Res.string.name_kJoyoKanji,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kKangXi(
        displayNameResource = Res.string.name_kKangXi,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kKarlgren(
        displayNameResource = Res.string.name_kKarlgren,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kKorean(
        displayNameResource = Res.string.name_kKorean,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kKoreanEducationHanja(
        displayNameResource = Res.string.name_kKoreanEducationHanja,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kKoreanName(
        displayNameResource = Res.string.name_kKoreanName,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kLau(
        displayNameResource = Res.string.name_kLau,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kMainlandTelegraph(
        displayNameResource = Res.string.name_kMainlandTelegraph,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kMandarin(
        displayNameResource = Res.string.name_kMandarin,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kMatthews(
        displayNameResource = Res.string.name_kMatthews,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kMeyerWempe(
        displayNameResource = Res.string.name_kMeyerWempe,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_1
    ),
    kMojiJoho(
        displayNameResource = Res.string.name_kMojiJoho,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kMorohashi(
        displayNameResource = Res.string.name_kMorohashi,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kNelson(
        displayNameResource = Res.string.name_kNelson,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kOtherNumeric(
        displayNameResource = Res.string.name_kOtherNumeric,
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kPhonetic(
        displayNameResource = Res.string.name_kPhonetic,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_3_1
    ),
    kPrimaryNumeric(
        displayNameResource = Res.string.name_kPrimaryNumeric,
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kPseudoGB1(
        displayNameResource = Res.string.name_kPseudoGB1,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kRSAdobe_Japan1_6(
        displayNameResource = Res.string.name_kRSAdobe_Japan1_6,
        category = UnihanDatabaseCategory.RadicalStrokeCounts,
        sinceUnicode = UnicodeVersion.VERSION_4_1
    ),
    kRSUnicode(
        displayNameResource = Res.string.name_kRSUnicode,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kSBGY(
        displayNameResource = Res.string.name_kSBGY,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_3_2
    ),
    kSemanticVariant(
        displayNameResource = Res.string.name_kSemanticVariant,
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kSimplifiedVariant(
        displayNameResource = Res.string.name_kSimplifiedVariant,
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kSMSZD2003Index(
        displayNameResource = Res.string.name_kSMSZD2003Index,
        category = UnihanDatabaseCategory.DictionaryIndices,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kSMSZD2003Readings(
        displayNameResource = Res.string.name_kSMSZD2003Readings,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kSpecializedSemanticVariant(
        displayNameResource = Res.string.name_kSpecializedSemanticVariant,
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kSpoofingVariant(
        displayNameResource = Res.string.name_kSpoofingVariant,
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kStrange(
        displayNameResource = Res.string.name_kStrange,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_14_0_0
    ),
    kTaiwanTelegraph(
        displayNameResource = Res.string.name_kTaiwanTelegraph,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kTang(
        displayNameResource = Res.string.name_kTang,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kTGH(
        displayNameResource = Res.string.name_kTGH,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_11_0_0
    ),
    kTGHZ2013(
        displayNameResource = Res.string.name_kTGHZ2013,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kTotalStrokes(
        displayNameResource = Res.string.name_kTotalStrokes,
        category = UnihanDatabaseCategory.IRGSources,
        sinceUnicode = UnicodeVersion.VERSION_3_1
    ),
    kTraditionalVariant(
        displayNameResource = Res.string.name_kTraditionalVariant,
        category = UnihanDatabaseCategory.Variants,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kUnihanCore2020(
        displayNameResource = Res.string.name_kUnihanCore2020,
        category = UnihanDatabaseCategory.DictionaryLikeData,
        sinceUnicode = UnicodeVersion.VERSION_13_0_0
    ),
    kVietnamese(
        displayNameResource = Res.string.name_kVietnamese,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_3_1_1
    ),
    kVietnameseNumeric(
        displayNameResource = Res.string.name_kVietnameseNumeric,
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kXerox(
        displayNameResource = Res.string.name_kXerox,
        category = UnihanDatabaseCategory.OtherMappings,
        sinceUnicode = UnicodeVersion.VERSION_2_0
    ),
    kXHC1983(
        displayNameResource = Res.string.name_kXHC1983,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_5_1
    ),
    kZhuang(
        displayNameResource = Res.string.name_kZhuang,
        category = UnihanDatabaseCategory.Readings,
        sinceUnicode = UnicodeVersion.VERSION_16_0_0
    ),
    kZhuangNumeric(
        displayNameResource = Res.string.name_kZhuangNumeric,
        category = UnihanDatabaseCategory.NumericValues,
        sinceUnicode = UnicodeVersion.VERSION_15_1_0
    ),
    kZVariant(
        displayNameResource = Res.string.name_kZVariant,
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
