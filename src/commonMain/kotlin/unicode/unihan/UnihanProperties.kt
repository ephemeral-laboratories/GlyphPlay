package garden.ephemeral.glyphplay.unicode.unihan

import garden.ephemeral.glyphplay.unicode.CodePointProperty
import garden.ephemeral.glyphplay.unicode.CodePointPropertyCollection

/**
 * Unihan properties, organised in a slightly more app-friendly fashion than the raw enum.
 */
object UnihanProperties {
    private fun unihanProperty(
        dbProperty: UnihanDatabaseProperty,
    ): CodePointProperty<String?> {
        return CodePointProperty(
            propertyNameGetter = { dbProperty.displayName },
            propertyValueGetter = { codePoint -> UnihanData.lookup(codePoint, dbProperty) },
            propertyValueDescriber = { value: String? -> value ?: "" },
        )
    }

    object IRGSources : CodePointPropertyCollection<String?> {
        val CompatibilityVariant = unihanProperty(UnihanDatabaseProperty.kCompatibilityVariant)
        val IICore = unihanProperty(UnihanDatabaseProperty.kIICore)
        val IRG_GSource = unihanProperty(UnihanDatabaseProperty.kIRG_GSource)
        val IRG_HSource = unihanProperty(UnihanDatabaseProperty.kIRG_HSource)
        val IRG_JSource = unihanProperty(UnihanDatabaseProperty.kIRG_JSource)
        val IRG_KPSource = unihanProperty(UnihanDatabaseProperty.kIRG_KPSource)
        val IRG_KSource = unihanProperty(UnihanDatabaseProperty.kIRG_KSource)
        val IRG_MSource = unihanProperty(UnihanDatabaseProperty.kIRG_MSource)
        val IRG_SSource = unihanProperty(UnihanDatabaseProperty.kIRG_SSource)
        val IRG_TSource = unihanProperty(UnihanDatabaseProperty.kIRG_TSource)
        val IRG_UKSource = unihanProperty(UnihanDatabaseProperty.kIRG_UKSource)
        val IRG_USource = unihanProperty(UnihanDatabaseProperty.kIRG_USource)
        val IRG_VSource = unihanProperty(UnihanDatabaseProperty.kIRG_VSource)
        val RSUnicode = unihanProperty(UnihanDatabaseProperty.kRSUnicode)
        val TotalStrokes = unihanProperty(UnihanDatabaseProperty.kTotalStrokes)

        override fun all() = sequenceOf(
            CompatibilityVariant,
            IICore,
            IRG_GSource,
            IRG_HSource,
            IRG_JSource,
            IRG_KPSource,
            IRG_KSource,
            IRG_MSource,
            IRG_SSource,
            IRG_TSource,
            IRG_UKSource,
            IRG_USource,
            IRG_VSource,
            RSUnicode,
            TotalStrokes,
        )
    }

    object OtherMappings : CodePointPropertyCollection<String?> {
        val BigFive = unihanProperty(UnihanDatabaseProperty.kBigFive)
        val CCCII = unihanProperty(UnihanDatabaseProperty.kCCCII)
        val CNS1986 = unihanProperty(UnihanDatabaseProperty.kCNS1986)
        val CNS1992 = unihanProperty(UnihanDatabaseProperty.kCNS1992)
        val EACC = unihanProperty(UnihanDatabaseProperty.kEACC)
        val GB0 = unihanProperty(UnihanDatabaseProperty.kGB0)
        val GB1 = unihanProperty(UnihanDatabaseProperty.kGB1)
        val GB3 = unihanProperty(UnihanDatabaseProperty.kGB3)
        val GB5 = unihanProperty(UnihanDatabaseProperty.kGB5)
        val GB7 = unihanProperty(UnihanDatabaseProperty.kGB7)
        val GB8 = unihanProperty(UnihanDatabaseProperty.kGB8)
        val IBMJapan = unihanProperty(UnihanDatabaseProperty.kIBMJapan)
        val Ja = unihanProperty(UnihanDatabaseProperty.kJa)
        val JinmeiyoKanji = unihanProperty(UnihanDatabaseProperty.kJinmeiyoKanji)
        val Jis0 = unihanProperty(UnihanDatabaseProperty.kJis0)
        val Jis1 = unihanProperty(UnihanDatabaseProperty.kJis1)
        val JIS0213 = unihanProperty(UnihanDatabaseProperty.kJIS0213)
        val JoyoKanji = unihanProperty(UnihanDatabaseProperty.kJoyoKanji)
        val KoreanEducationHanja = unihanProperty(UnihanDatabaseProperty.kKoreanEducationHanja)
        val KoreanName = unihanProperty(UnihanDatabaseProperty.kKoreanName)
        val MainlandTelegraph = unihanProperty(UnihanDatabaseProperty.kMainlandTelegraph)
        val PseudoGB1 = unihanProperty(UnihanDatabaseProperty.kPseudoGB1)
        val TaiwanTelegraph = unihanProperty(UnihanDatabaseProperty.kTaiwanTelegraph)
        val TGH = unihanProperty(UnihanDatabaseProperty.kTGH)
        val Xerox = unihanProperty(UnihanDatabaseProperty.kXerox)

        override fun all() = sequenceOf(
            BigFive,
            CCCII,
            CNS1986,
            CNS1992,
            EACC,
            GB0,
            GB1,
            GB3,
            GB5,
            GB7,
            GB8,
            IBMJapan,
            Ja,
            JinmeiyoKanji,
            Jis0,
            Jis1,
            JIS0213,
            JoyoKanji,
            KoreanEducationHanja,
            KoreanName,
            MainlandTelegraph,
            PseudoGB1,
            TaiwanTelegraph,
            TGH,
            Xerox,
        )
    }

    object DictionaryIndices : CodePointPropertyCollection<String?> {
        val CheungBauerIndex = unihanProperty(UnihanDatabaseProperty.kCheungBauerIndex)
        val CihaiT = unihanProperty(UnihanDatabaseProperty.kCihaiT)
        val Cowles = unihanProperty(UnihanDatabaseProperty.kCowles)
        val DaeJaweon = unihanProperty(UnihanDatabaseProperty.kDaeJaweon)
        val FennIndex = unihanProperty(UnihanDatabaseProperty.kFennIndex)
        val GSR = unihanProperty(UnihanDatabaseProperty.kGSR)
        val HanYu = unihanProperty(UnihanDatabaseProperty.kHanYu)
        val IRGDaeJaweon = unihanProperty(UnihanDatabaseProperty.kIRGDaeJaweon)
        val IRGHanyuDaZidian = unihanProperty(UnihanDatabaseProperty.kIRGHanyuDaZidian)
        val IRGKangXi = unihanProperty(UnihanDatabaseProperty.kIRGKangXi)
        val KangXi = unihanProperty(UnihanDatabaseProperty.kKangXi)
        val Karlgren = unihanProperty(UnihanDatabaseProperty.kKarlgren)
        val Lau = unihanProperty(UnihanDatabaseProperty.kLau)
        val Matthews = unihanProperty(UnihanDatabaseProperty.kMatthews)
        val MeyerWempe = unihanProperty(UnihanDatabaseProperty.kMeyerWempe)
        val Morohashi = unihanProperty(UnihanDatabaseProperty.kMorohashi)
        val Nelson = unihanProperty(UnihanDatabaseProperty.kNelson)
        val SBGY = unihanProperty(UnihanDatabaseProperty.kSBGY)
        val SMSZD2003Index = unihanProperty(UnihanDatabaseProperty.kSMSZD2003Index)

        override fun all() = sequenceOf(
            CheungBauerIndex,
            CihaiT,
            Cowles,
            DaeJaweon,
            FennIndex,
            GSR,
            HanYu,
            IRGDaeJaweon,
            IRGHanyuDaZidian,
            IRGKangXi,
            KangXi,
            Karlgren,
            Lau,
            Matthews,
            MeyerWempe,
            Morohashi,
            Nelson,
            SBGY,
            SMSZD2003Index,
        )
    }

    object Readings : CodePointPropertyCollection<String?> {
        val Cantonese = unihanProperty(UnihanDatabaseProperty.kCantonese)
        val Definition = unihanProperty(UnihanDatabaseProperty.kDefinition)
        val Fanqie = unihanProperty(UnihanDatabaseProperty.kFanqie)
        val Hangul = unihanProperty(UnihanDatabaseProperty.kHangul)
        val HanyuPinlu = unihanProperty(UnihanDatabaseProperty.kHanyuPinlu)
        val HanyuPinyin = unihanProperty(UnihanDatabaseProperty.kHanyuPinyin)
        val Japanese = unihanProperty(UnihanDatabaseProperty.kJapanese)
        val JapaneseKun = unihanProperty(UnihanDatabaseProperty.kJapaneseKun)
        val JapaneseOn = unihanProperty(UnihanDatabaseProperty.kJapaneseOn)
        val Korean = unihanProperty(UnihanDatabaseProperty.kKorean)
        val Mandarin = unihanProperty(UnihanDatabaseProperty.kMandarin)
        val SMSZD2003Readings = unihanProperty(UnihanDatabaseProperty.kSMSZD2003Readings)
        val Tang = unihanProperty(UnihanDatabaseProperty.kTang)
        val TGHZ2013 = unihanProperty(UnihanDatabaseProperty.kTGHZ2013)
        val Vietnamese = unihanProperty(UnihanDatabaseProperty.kVietnamese)
        val XHC1983 = unihanProperty(UnihanDatabaseProperty.kXHC1983)
        val Zhuang = unihanProperty(UnihanDatabaseProperty.kZhuang)

        override fun all() = sequenceOf(
            Cantonese,
            Definition,
            Fanqie,
            Hangul,
            HanyuPinlu,
            HanyuPinyin,
            Japanese,
            JapaneseKun,
            JapaneseOn,
            Korean,
            Mandarin,
            SMSZD2003Readings,
            Tang,
            TGHZ2013,
            Vietnamese,
            XHC1983,
            Zhuang,
        )
    }

    object DictionaryLikeData : CodePointPropertyCollection<String?> {
        val AlternateTotalStrokes = unihanProperty(UnihanDatabaseProperty.kAlternateTotalStrokes)
        val Cangjie = unihanProperty(UnihanDatabaseProperty.kCangjie)
        val CheungBauer = unihanProperty(UnihanDatabaseProperty.kCheungBauer)
        val Fenn = unihanProperty(UnihanDatabaseProperty.kFenn)
        val FourCornerCode = unihanProperty(UnihanDatabaseProperty.kFourCornerCode)
        val GradeLevel = unihanProperty(UnihanDatabaseProperty.kGradeLevel)
        val HDZRadBreak = unihanProperty(UnihanDatabaseProperty.kHDZRadBreak)
        val HKGlyph = unihanProperty(UnihanDatabaseProperty.kHKGlyph)
        val MojiJoho = unihanProperty(UnihanDatabaseProperty.kMojiJoho)
        val Phonetic = unihanProperty(UnihanDatabaseProperty.kPhonetic)
        val Strange = unihanProperty(UnihanDatabaseProperty.kStrange)
        val UnihanCore2020 = unihanProperty(UnihanDatabaseProperty.kUnihanCore2020)

        override fun all() = sequenceOf(
            AlternateTotalStrokes,
            Cangjie,
            CheungBauer,
            Fenn,
            FourCornerCode,
            GradeLevel,
            HDZRadBreak,
            HKGlyph,
            MojiJoho,
            Phonetic,
            Strange,
            UnihanCore2020,
        )
    }

    object RadicalStrokeCounts : CodePointPropertyCollection<String?> {
        val RSAdobe_Japan1_6 = unihanProperty(UnihanDatabaseProperty.kRSAdobe_Japan1_6)

        override fun all() = sequenceOf(RSAdobe_Japan1_6)
    }

    object Variants : CodePointPropertyCollection<String?> {
        val SemanticVariant = unihanProperty(UnihanDatabaseProperty.kSemanticVariant)
        val SimplifiedVariant = unihanProperty(UnihanDatabaseProperty.kSimplifiedVariant)
        val SpecializedSemanticVariant = unihanProperty(UnihanDatabaseProperty.kSpecializedSemanticVariant)
        val SpoofingVariant = unihanProperty(UnihanDatabaseProperty.kSpoofingVariant)
        val TraditionalVariant = unihanProperty(UnihanDatabaseProperty.kTraditionalVariant)
        val ZVariant = unihanProperty(UnihanDatabaseProperty.kZVariant)

        override fun all() = sequenceOf(
            SemanticVariant,
            SimplifiedVariant,
            SpecializedSemanticVariant,
            SpoofingVariant,
            TraditionalVariant,
            ZVariant,
        )
    }

    object NumericValues : CodePointPropertyCollection<String?> {
        val AccountingNumeric = unihanProperty(UnihanDatabaseProperty.kAccountingNumeric)
        val OtherNumeric = unihanProperty(UnihanDatabaseProperty.kOtherNumeric)
        val PrimaryNumeric = unihanProperty(UnihanDatabaseProperty.kPrimaryNumeric)
        val VietnameseNumeric = unihanProperty(UnihanDatabaseProperty.kVietnameseNumeric)
        val ZhuangNumeric = unihanProperty(UnihanDatabaseProperty.kZhuangNumeric)

        override fun all() = sequenceOf(
            AccountingNumeric,
            OtherNumeric,
            PrimaryNumeric,
            VietnameseNumeric,
            ZhuangNumeric,
        )
    }

    fun allCollections(): List<CodePointPropertyCollection<String?>> = listOf(
        IRGSources,
        OtherMappings,
        DictionaryIndices,
        Readings,
        DictionaryLikeData,
        RadicalStrokeCounts,
        Variants,
        NumericValues,
    )
}
