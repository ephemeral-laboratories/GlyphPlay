package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeBlock(override val icuValue: Int) : IcuUnicodeValueEnum<UnicodeBlock> {
    NO_BLOCK(0),

    BASIC_LATIN(UCharacter.UnicodeBlock.BASIC_LATIN_ID),
    LATIN_1_SUPPLEMENT(UCharacter.UnicodeBlock.LATIN_1_SUPPLEMENT_ID),
    LATIN_EXTENDED_A(UCharacter.UnicodeBlock.LATIN_EXTENDED_A_ID),
    LATIN_EXTENDED_B(UCharacter.UnicodeBlock.LATIN_EXTENDED_B_ID),
    IPA_EXTENSIONS(UCharacter.UnicodeBlock.IPA_EXTENSIONS_ID),
    SPACING_MODIFIER_LETTERS(UCharacter.UnicodeBlock.SPACING_MODIFIER_LETTERS_ID),
    COMBINING_DIACRITICAL_MARKS(UCharacter.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_ID),
    GREEK(UCharacter.UnicodeBlock.GREEK_ID),
    CYRILLIC(UCharacter.UnicodeBlock.CYRILLIC_ID),
    ARMENIAN(UCharacter.UnicodeBlock.ARMENIAN_ID),
    HEBREW(UCharacter.UnicodeBlock.HEBREW_ID),
    ARABIC(UCharacter.UnicodeBlock.ARABIC_ID),
    SYRIAC(UCharacter.UnicodeBlock.SYRIAC_ID),
    THAANA(UCharacter.UnicodeBlock.THAANA_ID),
    DEVANAGARI(UCharacter.UnicodeBlock.DEVANAGARI_ID),
    BENGALI(UCharacter.UnicodeBlock.BENGALI_ID),
    GURMUKHI(UCharacter.UnicodeBlock.GURMUKHI_ID),
    GUJARATI(UCharacter.UnicodeBlock.GUJARATI_ID),
    ORIYA(UCharacter.UnicodeBlock.ORIYA_ID),
    TAMIL(UCharacter.UnicodeBlock.TAMIL_ID),
    TELUGU(UCharacter.UnicodeBlock.TELUGU_ID),
    KANNADA(UCharacter.UnicodeBlock.KANNADA_ID),
    MALAYALAM(UCharacter.UnicodeBlock.MALAYALAM_ID),
    SINHALA(UCharacter.UnicodeBlock.SINHALA_ID),
    THAI(UCharacter.UnicodeBlock.THAI_ID),
    LAO(UCharacter.UnicodeBlock.LAO_ID),
    TIBETAN(UCharacter.UnicodeBlock.TIBETAN_ID),
    MYANMAR(UCharacter.UnicodeBlock.MYANMAR_ID),
    GEORGIAN(UCharacter.UnicodeBlock.GEORGIAN_ID),
    HANGUL_JAMO(UCharacter.UnicodeBlock.HANGUL_JAMO_ID),
    ETHIOPIC(UCharacter.UnicodeBlock.ETHIOPIC_ID),
    CHEROKEE(UCharacter.UnicodeBlock.CHEROKEE_ID),
    UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS(UCharacter.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_ID),
    OGHAM(UCharacter.UnicodeBlock.OGHAM_ID),
    RUNIC(UCharacter.UnicodeBlock.RUNIC_ID),
    KHMER(UCharacter.UnicodeBlock.KHMER_ID),
    MONGOLIAN(UCharacter.UnicodeBlock.MONGOLIAN_ID),
    LATIN_EXTENDED_ADDITIONAL(UCharacter.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL_ID),
    GREEK_EXTENDED(UCharacter.UnicodeBlock.GREEK_EXTENDED_ID),
    GENERAL_PUNCTUATION(UCharacter.UnicodeBlock.GENERAL_PUNCTUATION_ID),
    SUPERSCRIPTS_AND_SUBSCRIPTS(UCharacter.UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS_ID),
    CURRENCY_SYMBOLS(UCharacter.UnicodeBlock.CURRENCY_SYMBOLS_ID),
    COMBINING_MARKS_FOR_SYMBOLS(UCharacter.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS_ID),
    LETTERLIKE_SYMBOLS(UCharacter.UnicodeBlock.LETTERLIKE_SYMBOLS_ID),
    NUMBER_FORMS(UCharacter.UnicodeBlock.NUMBER_FORMS_ID),
    ARROWS(UCharacter.UnicodeBlock.ARROWS_ID),
    MATHEMATICAL_OPERATORS(UCharacter.UnicodeBlock.MATHEMATICAL_OPERATORS_ID),
    MISCELLANEOUS_TECHNICAL(UCharacter.UnicodeBlock.MISCELLANEOUS_TECHNICAL_ID),
    CONTROL_PICTURES(UCharacter.UnicodeBlock.CONTROL_PICTURES_ID),
    OPTICAL_CHARACTER_RECOGNITION(UCharacter.UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION_ID),
    ENCLOSED_ALPHANUMERICS(UCharacter.UnicodeBlock.ENCLOSED_ALPHANUMERICS_ID),
    BOX_DRAWING(UCharacter.UnicodeBlock.BOX_DRAWING_ID),
    BLOCK_ELEMENTS(UCharacter.UnicodeBlock.BLOCK_ELEMENTS_ID),
    GEOMETRIC_SHAPES(UCharacter.UnicodeBlock.GEOMETRIC_SHAPES_ID),
    MISCELLANEOUS_SYMBOLS(UCharacter.UnicodeBlock.MISCELLANEOUS_SYMBOLS_ID),
    DINGBATS(UCharacter.UnicodeBlock.DINGBATS_ID),
    BRAILLE_PATTERNS(UCharacter.UnicodeBlock.BRAILLE_PATTERNS_ID),
    CJK_RADICALS_SUPPLEMENT(UCharacter.UnicodeBlock.CJK_RADICALS_SUPPLEMENT_ID),
    KANGXI_RADICALS(UCharacter.UnicodeBlock.KANGXI_RADICALS_ID),
    IDEOGRAPHIC_DESCRIPTION_CHARACTERS(UCharacter.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS_ID),
    CJK_SYMBOLS_AND_PUNCTUATION(UCharacter.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION_ID),
    HIRAGANA(UCharacter.UnicodeBlock.HIRAGANA_ID),
    KATAKANA(UCharacter.UnicodeBlock.KATAKANA_ID),
    BOPOMOFO(UCharacter.UnicodeBlock.BOPOMOFO_ID),
    HANGUL_COMPATIBILITY_JAMO(UCharacter.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO_ID),
    KANBUN(UCharacter.UnicodeBlock.KANBUN_ID),
    BOPOMOFO_EXTENDED(UCharacter.UnicodeBlock.BOPOMOFO_EXTENDED_ID),
    ENCLOSED_CJK_LETTERS_AND_MONTHS(UCharacter.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS_ID),
    CJK_COMPATIBILITY(UCharacter.UnicodeBlock.CJK_COMPATIBILITY_ID),
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A_ID),
    CJK_UNIFIED_IDEOGRAPHS(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_ID),
    YI_SYLLABLES(UCharacter.UnicodeBlock.YI_SYLLABLES_ID),
    YI_RADICALS(UCharacter.UnicodeBlock.YI_RADICALS_ID),
    HANGUL_SYLLABLES(UCharacter.UnicodeBlock.HANGUL_SYLLABLES_ID),
    HIGH_SURROGATES(UCharacter.UnicodeBlock.HIGH_SURROGATES_ID),
    HIGH_PRIVATE_USE_SURROGATES(UCharacter.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES_ID),
    LOW_SURROGATES(UCharacter.UnicodeBlock.LOW_SURROGATES_ID),
    PRIVATE_USE_AREA(UCharacter.UnicodeBlock.PRIVATE_USE_AREA_ID),
    CJK_COMPATIBILITY_IDEOGRAPHS(UCharacter.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_ID),
    ALPHABETIC_PRESENTATION_FORMS(UCharacter.UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS_ID),
    ARABIC_PRESENTATION_FORMS_A(UCharacter.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A_ID),
    COMBINING_HALF_MARKS(UCharacter.UnicodeBlock.COMBINING_HALF_MARKS_ID),
    CJK_COMPATIBILITY_FORMS(UCharacter.UnicodeBlock.CJK_COMPATIBILITY_FORMS_ID),
    SMALL_FORM_VARIANTS(UCharacter.UnicodeBlock.SMALL_FORM_VARIANTS_ID),
    ARABIC_PRESENTATION_FORMS_B(UCharacter.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B_ID),
    SPECIALS(UCharacter.UnicodeBlock.SPECIALS_ID),
    HALFWIDTH_AND_FULLWIDTH_FORMS(UCharacter.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS_ID),
    OLD_ITALIC(UCharacter.UnicodeBlock.OLD_ITALIC_ID),
    GOTHIC(UCharacter.UnicodeBlock.GOTHIC_ID),
    DESERET(UCharacter.UnicodeBlock.DESERET_ID),
    BYZANTINE_MUSICAL_SYMBOLS(UCharacter.UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS_ID),
    MUSICAL_SYMBOLS(UCharacter.UnicodeBlock.MUSICAL_SYMBOLS_ID),
    MATHEMATICAL_ALPHANUMERIC_SYMBOLS(UCharacter.UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS_ID),
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B_ID),
    CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT(UCharacter.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT_ID),
    TAGS(UCharacter.UnicodeBlock.TAGS_ID),
    CYRILLIC_SUPPLEMENTARY(UCharacter.UnicodeBlock.CYRILLIC_SUPPLEMENTARY_ID),
    CYRILLIC_SUPPLEMENT(UCharacter.UnicodeBlock.CYRILLIC_SUPPLEMENT_ID),
    TAGALOG(UCharacter.UnicodeBlock.TAGALOG_ID),
    HANUNOO(UCharacter.UnicodeBlock.HANUNOO_ID),
    BUHID(UCharacter.UnicodeBlock.BUHID_ID),
    TAGBANWA(UCharacter.UnicodeBlock.TAGBANWA_ID),
    MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A(UCharacter.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A_ID),
    SUPPLEMENTAL_ARROWS_A(UCharacter.UnicodeBlock.SUPPLEMENTAL_ARROWS_A_ID),
    SUPPLEMENTAL_ARROWS_B(UCharacter.UnicodeBlock.SUPPLEMENTAL_ARROWS_B_ID),
    MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B(UCharacter.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_ID),
    SUPPLEMENTAL_MATHEMATICAL_OPERATORS(UCharacter.UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS_ID),
    KATAKANA_PHONETIC_EXTENSIONS(UCharacter.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS_ID),
    VARIATION_SELECTORS(UCharacter.UnicodeBlock.VARIATION_SELECTORS_ID),
    SUPPLEMENTARY_PRIVATE_USE_AREA_A(UCharacter.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A_ID),
    SUPPLEMENTARY_PRIVATE_USE_AREA_B(UCharacter.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B_ID),
    LIMBU(UCharacter.UnicodeBlock.LIMBU_ID),
    TAI_LE(UCharacter.UnicodeBlock.TAI_LE_ID),
    KHMER_SYMBOLS(UCharacter.UnicodeBlock.KHMER_SYMBOLS_ID),
    PHONETIC_EXTENSIONS(UCharacter.UnicodeBlock.PHONETIC_EXTENSIONS_ID),
    MISCELLANEOUS_SYMBOLS_AND_ARROWS(UCharacter.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS_ID),
    YIJING_HEXAGRAM_SYMBOLS(UCharacter.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS_ID),
    LINEAR_B_SYLLABARY(UCharacter.UnicodeBlock.LINEAR_B_SYLLABARY_ID),
    LINEAR_B_IDEOGRAMS(UCharacter.UnicodeBlock.LINEAR_B_IDEOGRAMS_ID),
    AEGEAN_NUMBERS(UCharacter.UnicodeBlock.AEGEAN_NUMBERS_ID),
    UGARITIC(UCharacter.UnicodeBlock.UGARITIC_ID),
    SHAVIAN(UCharacter.UnicodeBlock.SHAVIAN_ID),
    OSMANYA(UCharacter.UnicodeBlock.OSMANYA_ID),
    CYPRIOT_SYLLABARY(UCharacter.UnicodeBlock.CYPRIOT_SYLLABARY_ID),
    TAI_XUAN_JING_SYMBOLS(UCharacter.UnicodeBlock.TAI_XUAN_JING_SYMBOLS_ID),
    VARIATION_SELECTORS_SUPPLEMENT(UCharacter.UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT_ID),
    ANCIENT_GREEK_MUSICAL_NOTATION(UCharacter.UnicodeBlock.ANCIENT_GREEK_MUSICAL_NOTATION_ID), /*[1D200]*/
    ANCIENT_GREEK_NUMBERS(UCharacter.UnicodeBlock.ANCIENT_GREEK_NUMBERS_ID), /*[10140]*/
    ARABIC_SUPPLEMENT(UCharacter.UnicodeBlock.ARABIC_SUPPLEMENT_ID), /*[0750]*/
    BUGINESE(UCharacter.UnicodeBlock.BUGINESE_ID), /*[1A00]*/
    CJK_STROKES(UCharacter.UnicodeBlock.CJK_STROKES_ID), /*[31C0]*/
    COMBINING_DIACRITICAL_MARKS_SUPPLEMENT(UCharacter.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_SUPPLEMENT_ID), /*[1DC0]*/
    COPTIC(UCharacter.UnicodeBlock.COPTIC_ID), /*[2C80]*/
    ETHIOPIC_EXTENDED(UCharacter.UnicodeBlock.ETHIOPIC_EXTENDED_ID), /*[2D80]*/
    ETHIOPIC_SUPPLEMENT(UCharacter.UnicodeBlock.ETHIOPIC_SUPPLEMENT_ID), /*[1380]*/
    GEORGIAN_SUPPLEMENT(UCharacter.UnicodeBlock.GEORGIAN_SUPPLEMENT_ID), /*[2D00]*/
    GLAGOLITIC(UCharacter.UnicodeBlock.GLAGOLITIC_ID), /*[2C00]*/
    KHAROSHTHI(UCharacter.UnicodeBlock.KHAROSHTHI_ID), /*[10A00]*/
    MODIFIER_TONE_LETTERS(UCharacter.UnicodeBlock.MODIFIER_TONE_LETTERS_ID), /*[A700]*/
    NEW_TAI_LUE(UCharacter.UnicodeBlock.NEW_TAI_LUE_ID), /*[1980]*/
    OLD_PERSIAN(UCharacter.UnicodeBlock.OLD_PERSIAN_ID), /*[103A0]*/
    PHONETIC_EXTENSIONS_SUPPLEMENT(UCharacter.UnicodeBlock.PHONETIC_EXTENSIONS_SUPPLEMENT_ID), /*[1D80]*/
    SUPPLEMENTAL_PUNCTUATION(UCharacter.UnicodeBlock.SUPPLEMENTAL_PUNCTUATION_ID), /*[2E00]*/
    SYLOTI_NAGRI(UCharacter.UnicodeBlock.SYLOTI_NAGRI_ID), /*[A800]*/
    TIFINAGH(UCharacter.UnicodeBlock.TIFINAGH_ID), /*[2D30]*/
    VERTICAL_FORMS(UCharacter.UnicodeBlock.VERTICAL_FORMS_ID), /*[FE10]*/
    NKO(UCharacter.UnicodeBlock.NKO_ID), /*[07C0]*/
    BALINESE(UCharacter.UnicodeBlock.BALINESE_ID), /*[1B00]*/
    LATIN_EXTENDED_C(UCharacter.UnicodeBlock.LATIN_EXTENDED_C_ID), /*[2C60]*/
    LATIN_EXTENDED_D(UCharacter.UnicodeBlock.LATIN_EXTENDED_D_ID), /*[A720]*/
    PHAGS_PA(UCharacter.UnicodeBlock.PHAGS_PA_ID), /*[A840]*/
    PHOENICIAN(UCharacter.UnicodeBlock.PHOENICIAN_ID), /*[10900]*/
    CUNEIFORM(UCharacter.UnicodeBlock.CUNEIFORM_ID), /*[12000]*/
    CUNEIFORM_NUMBERS_AND_PUNCTUATION(UCharacter.UnicodeBlock.CUNEIFORM_NUMBERS_AND_PUNCTUATION_ID), /*[12400]*/
    COUNTING_ROD_NUMERALS(UCharacter.UnicodeBlock.COUNTING_ROD_NUMERALS_ID), /*[1D360]*/
    SUNDANESE(UCharacter.UnicodeBlock.SUNDANESE_ID), /* [1B80] */
    LEPCHA(UCharacter.UnicodeBlock.LEPCHA_ID), /* [1C00] */
    OL_CHIKI(UCharacter.UnicodeBlock.OL_CHIKI_ID), /* [1C50] */
    CYRILLIC_EXTENDED_A(UCharacter.UnicodeBlock.CYRILLIC_EXTENDED_A_ID), /* [2DE0] */
    VAI(UCharacter.UnicodeBlock.VAI_ID), /* [A500] */
    CYRILLIC_EXTENDED_B(UCharacter.UnicodeBlock.CYRILLIC_EXTENDED_B_ID), /* [A640] */
    SAURASHTRA(UCharacter.UnicodeBlock.SAURASHTRA_ID), /* [A880] */
    KAYAH_LI(UCharacter.UnicodeBlock.KAYAH_LI_ID), /* [A900] */
    REJANG(UCharacter.UnicodeBlock.REJANG_ID), /* [A930] */
    CHAM(UCharacter.UnicodeBlock.CHAM_ID), /* [AA00] */
    ANCIENT_SYMBOLS(UCharacter.UnicodeBlock.ANCIENT_SYMBOLS_ID), /* [10190] */
    PHAISTOS_DISC(UCharacter.UnicodeBlock.PHAISTOS_DISC_ID), /* [101D0] */
    LYCIAN(UCharacter.UnicodeBlock.LYCIAN_ID), /* [10280] */
    CARIAN(UCharacter.UnicodeBlock.CARIAN_ID), /* [102A0] */
    LYDIAN(UCharacter.UnicodeBlock.LYDIAN_ID), /* [10920] */
    MAHJONG_TILES(UCharacter.UnicodeBlock.MAHJONG_TILES_ID), /* [1F000] */
    DOMINO_TILES(UCharacter.UnicodeBlock.DOMINO_TILES_ID), /* [1F030] */
    SAMARITAN(UCharacter.UnicodeBlock.SAMARITAN_ID), /*[0800]*/
    UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED(UCharacter.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_ID), /*[18B0]*/
    TAI_THAM(UCharacter.UnicodeBlock.TAI_THAM_ID), /*[1A20]*/
    VEDIC_EXTENSIONS(UCharacter.UnicodeBlock.VEDIC_EXTENSIONS_ID), /*[1CD0]*/
    LISU(UCharacter.UnicodeBlock.LISU_ID), /*[A4D0]*/
    BAMUM(UCharacter.UnicodeBlock.BAMUM_ID), /*[A6A0]*/
    COMMON_INDIC_NUMBER_FORMS(UCharacter.UnicodeBlock.COMMON_INDIC_NUMBER_FORMS_ID), /*[A830]*/
    DEVANAGARI_EXTENDED(UCharacter.UnicodeBlock.DEVANAGARI_EXTENDED_ID), /*[A8E0]*/
    HANGUL_JAMO_EXTENDED_A(UCharacter.UnicodeBlock.HANGUL_JAMO_EXTENDED_A_ID), /*[A960]*/
    JAVANESE(UCharacter.UnicodeBlock.JAVANESE_ID), /*[A980]*/
    MYANMAR_EXTENDED_A(UCharacter.UnicodeBlock.MYANMAR_EXTENDED_A_ID), /*[AA60]*/
    TAI_VIET(UCharacter.UnicodeBlock.TAI_VIET_ID), /*[AA80]*/
    MEETEI_MAYEK(UCharacter.UnicodeBlock.MEETEI_MAYEK_ID), /*[ABC0]*/
    HANGUL_JAMO_EXTENDED_B(UCharacter.UnicodeBlock.HANGUL_JAMO_EXTENDED_B_ID), /*[D7B0]*/
    IMPERIAL_ARAMAIC(UCharacter.UnicodeBlock.IMPERIAL_ARAMAIC_ID), /*[10840]*/
    OLD_SOUTH_ARABIAN(UCharacter.UnicodeBlock.OLD_SOUTH_ARABIAN_ID), /*[10A60]*/
    AVESTAN(UCharacter.UnicodeBlock.AVESTAN_ID), /*[10B00]*/
    INSCRIPTIONAL_PARTHIAN(UCharacter.UnicodeBlock.INSCRIPTIONAL_PARTHIAN_ID), /*[10B40]*/
    INSCRIPTIONAL_PAHLAVI(UCharacter.UnicodeBlock.INSCRIPTIONAL_PAHLAVI_ID), /*[10B60]*/
    OLD_TURKIC(UCharacter.UnicodeBlock.OLD_TURKIC_ID), /*[10C00]*/
    RUMI_NUMERAL_SYMBOLS(UCharacter.UnicodeBlock.RUMI_NUMERAL_SYMBOLS_ID), /*[10E60]*/
    KAITHI(UCharacter.UnicodeBlock.KAITHI_ID), /*[11080]*/
    EGYPTIAN_HIEROGLYPHS(UCharacter.UnicodeBlock.EGYPTIAN_HIEROGLYPHS_ID), /*[13000]*/
    ENCLOSED_ALPHANUMERIC_SUPPLEMENT(UCharacter.UnicodeBlock.ENCLOSED_ALPHANUMERIC_SUPPLEMENT_ID), /*[1F100]*/
    ENCLOSED_IDEOGRAPHIC_SUPPLEMENT(UCharacter.UnicodeBlock.ENCLOSED_IDEOGRAPHIC_SUPPLEMENT_ID), /*[1F200]*/
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C_ID), /*[2A700]*/
    MANDAIC(UCharacter.UnicodeBlock.MANDAIC_ID), /*[0840]*/
    BATAK(UCharacter.UnicodeBlock.BATAK_ID), /*[1BC0]*/
    ETHIOPIC_EXTENDED_A(UCharacter.UnicodeBlock.ETHIOPIC_EXTENDED_A_ID), /*[AB00]*/
    BRAHMI(UCharacter.UnicodeBlock.BRAHMI_ID), /*[11000]*/
    BAMUM_SUPPLEMENT(UCharacter.UnicodeBlock.BAMUM_SUPPLEMENT_ID), /*[16800]*/
    KANA_SUPPLEMENT(UCharacter.UnicodeBlock.KANA_SUPPLEMENT_ID), /*[1B000]*/
    PLAYING_CARDS(UCharacter.UnicodeBlock.PLAYING_CARDS_ID), /*[1F0A0]*/
    MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS(UCharacter.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS_ID), /*[1F300]*/
    EMOTICONS(UCharacter.UnicodeBlock.EMOTICONS_ID), /*[1F600]*/
    TRANSPORT_AND_MAP_SYMBOLS(UCharacter.UnicodeBlock.TRANSPORT_AND_MAP_SYMBOLS_ID), /*[1F680]*/
    ALCHEMICAL_SYMBOLS(UCharacter.UnicodeBlock.ALCHEMICAL_SYMBOLS_ID), /*[1F700]*/
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D_ID), /*[2B740]*/
    ARABIC_EXTENDED_A(UCharacter.UnicodeBlock.ARABIC_EXTENDED_A_ID), /*[08A0]*/
    ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS(UCharacter.UnicodeBlock.ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS_ID), /*[1EE00]*/
    CHAKMA(UCharacter.UnicodeBlock.CHAKMA_ID), /*[11100]*/
    MEETEI_MAYEK_EXTENSIONS(UCharacter.UnicodeBlock.MEETEI_MAYEK_EXTENSIONS_ID), /*[AAE0]*/
    MEROITIC_CURSIVE(UCharacter.UnicodeBlock.MEROITIC_CURSIVE_ID), /*[109A0]*/
    MEROITIC_HIEROGLYPHS(UCharacter.UnicodeBlock.MEROITIC_HIEROGLYPHS_ID), /*[10980]*/
    MIAO(UCharacter.UnicodeBlock.MIAO_ID), /*[16F00]*/
    SHARADA(UCharacter.UnicodeBlock.SHARADA_ID), /*[11180]*/
    SORA_SOMPENG(UCharacter.UnicodeBlock.SORA_SOMPENG_ID), /*[110D0]*/
    SUNDANESE_SUPPLEMENT(UCharacter.UnicodeBlock.SUNDANESE_SUPPLEMENT_ID), /*[1CC0]*/
    TAKRI(UCharacter.UnicodeBlock.TAKRI_ID), /*[11680]*/
    BASSA_VAH(UCharacter.UnicodeBlock.BASSA_VAH_ID), /*[16AD0]*/
    CAUCASIAN_ALBANIAN(UCharacter.UnicodeBlock.CAUCASIAN_ALBANIAN_ID), /*[10530]*/
    COPTIC_EPACT_NUMBERS(UCharacter.UnicodeBlock.COPTIC_EPACT_NUMBERS_ID), /*[102E0]*/
    COMBINING_DIACRITICAL_MARKS_EXTENDED(UCharacter.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_EXTENDED_ID), /*[1AB0]*/
    DUPLOYAN(UCharacter.UnicodeBlock.DUPLOYAN_ID), /*[1BC00]*/
    ELBASAN(UCharacter.UnicodeBlock.ELBASAN_ID), /*[10500]*/
    GEOMETRIC_SHAPES_EXTENDED(UCharacter.UnicodeBlock.GEOMETRIC_SHAPES_EXTENDED_ID), /*[1F780]*/
    GRANTHA(UCharacter.UnicodeBlock.GRANTHA_ID), /*[11300]*/
    KHOJKI(UCharacter.UnicodeBlock.KHOJKI_ID), /*[11200]*/
    KHUDAWADI(UCharacter.UnicodeBlock.KHUDAWADI_ID), /*[112B0]*/
    LATIN_EXTENDED_E(UCharacter.UnicodeBlock.LATIN_EXTENDED_E_ID), /*[AB30]*/
    LINEAR_A(UCharacter.UnicodeBlock.LINEAR_A_ID), /*[10600]*/
    MAHAJANI(UCharacter.UnicodeBlock.MAHAJANI_ID), /*[11150]*/
    MANICHAEAN(UCharacter.UnicodeBlock.MANICHAEAN_ID), /*[10AC0]*/
    MENDE_KIKAKUI(UCharacter.UnicodeBlock.MENDE_KIKAKUI_ID), /*[1E800]*/
    MODI(UCharacter.UnicodeBlock.MODI_ID), /*[11600]*/
    MRO(UCharacter.UnicodeBlock.MRO_ID), /*[16A40]*/
    MYANMAR_EXTENDED_B(UCharacter.UnicodeBlock.MYANMAR_EXTENDED_B_ID), /*[A9E0]*/
    NABATAEAN(UCharacter.UnicodeBlock.NABATAEAN_ID), /*[10880]*/
    OLD_NORTH_ARABIAN(UCharacter.UnicodeBlock.OLD_NORTH_ARABIAN_ID), /*[10A80]*/
    OLD_PERMIC(UCharacter.UnicodeBlock.OLD_PERMIC_ID), /*[10350]*/
    ORNAMENTAL_DINGBATS(UCharacter.UnicodeBlock.ORNAMENTAL_DINGBATS_ID), /*[1F650]*/
    PAHAWH_HMONG(UCharacter.UnicodeBlock.PAHAWH_HMONG_ID), /*[16B00]*/
    PALMYRENE(UCharacter.UnicodeBlock.PALMYRENE_ID), /*[10860]*/
    PAU_CIN_HAU(UCharacter.UnicodeBlock.PAU_CIN_HAU_ID), /*[11AC0]*/
    PSALTER_PAHLAVI(UCharacter.UnicodeBlock.PSALTER_PAHLAVI_ID), /*[10B80]*/
    SHORTHAND_FORMAT_CONTROLS(UCharacter.UnicodeBlock.SHORTHAND_FORMAT_CONTROLS_ID), /*[1BCA0]*/
    SIDDHAM(UCharacter.UnicodeBlock.SIDDHAM_ID), /*[11580]*/
    SINHALA_ARCHAIC_NUMBERS(UCharacter.UnicodeBlock.SINHALA_ARCHAIC_NUMBERS_ID), /*[111E0]*/
    SUPPLEMENTAL_ARROWS_C(UCharacter.UnicodeBlock.SUPPLEMENTAL_ARROWS_C_ID), /*[1F800]*/
    TIRHUTA(UCharacter.UnicodeBlock.TIRHUTA_ID), /*[11480]*/
    WARANG_CITI(UCharacter.UnicodeBlock.WARANG_CITI_ID), /*[118A0]*/
    AHOM(UCharacter.UnicodeBlock.AHOM_ID), /*[11700]*/
    ANATOLIAN_HIEROGLYPHS(UCharacter.UnicodeBlock.ANATOLIAN_HIEROGLYPHS_ID), /*[14400]*/
    CHEROKEE_SUPPLEMENT(UCharacter.UnicodeBlock.CHEROKEE_SUPPLEMENT_ID), /*[AB70]*/
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E_ID), /*[2B820]*/
    EARLY_DYNASTIC_CUNEIFORM(UCharacter.UnicodeBlock.EARLY_DYNASTIC_CUNEIFORM_ID), /*[12480]*/
    HATRAN(UCharacter.UnicodeBlock.HATRAN_ID), /*[108E0]*/
    MULTANI(UCharacter.UnicodeBlock.MULTANI_ID), /*[11280]*/
    OLD_HUNGARIAN(UCharacter.UnicodeBlock.OLD_HUNGARIAN_ID), /*[10C80]*/
    SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS(UCharacter.UnicodeBlock.SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS_ID), /*[1F900]*/
    SUTTON_SIGNWRITING(UCharacter.UnicodeBlock.SUTTON_SIGNWRITING_ID), /*[1D800]*/
    ADLAM(UCharacter.UnicodeBlock.ADLAM_ID), /*[1E900]*/
    BHAIKSUKI(UCharacter.UnicodeBlock.BHAIKSUKI_ID), /*[11C00]*/
    CYRILLIC_EXTENDED_C(UCharacter.UnicodeBlock.CYRILLIC_EXTENDED_C_ID), /*[1C80]*/
    GLAGOLITIC_SUPPLEMENT(UCharacter.UnicodeBlock.GLAGOLITIC_SUPPLEMENT_ID), /*[1E000]*/
    IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION(UCharacter.UnicodeBlock.IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION_ID), /*[16FE0]*/
    MARCHEN(UCharacter.UnicodeBlock.MARCHEN_ID), /*[11C70]*/
    MONGOLIAN_SUPPLEMENT(UCharacter.UnicodeBlock.MONGOLIAN_SUPPLEMENT_ID), /*[11660]*/
    NEWA(UCharacter.UnicodeBlock.NEWA_ID), /*[11400]*/
    OSAGE(UCharacter.UnicodeBlock.OSAGE_ID), /*[104B0]*/
    TANGUT(UCharacter.UnicodeBlock.TANGUT_ID), /*[17000]*/
    TANGUT_COMPONENTS(UCharacter.UnicodeBlock.TANGUT_COMPONENTS_ID), /*[18800]*/
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F_ID), /*[2CEB0]*/
    KANA_EXTENDED_A(UCharacter.UnicodeBlock.KANA_EXTENDED_A_ID), /*[1B100]*/
    MASARAM_GONDI(UCharacter.UnicodeBlock.MASARAM_GONDI_ID), /*[11D00]*/
    NUSHU(UCharacter.UnicodeBlock.NUSHU_ID), /*[1B170]*/
    SOYOMBO(UCharacter.UnicodeBlock.SOYOMBO_ID), /*[11A50]*/
    SYRIAC_SUPPLEMENT(UCharacter.UnicodeBlock.SYRIAC_SUPPLEMENT_ID), /*[0860]*/
    ZANABAZAR_SQUARE(UCharacter.UnicodeBlock.ZANABAZAR_SQUARE_ID), /*[11A00]*/
    CHESS_SYMBOLS(UCharacter.UnicodeBlock.CHESS_SYMBOLS_ID), /*[1FA00]*/
    DOGRA(UCharacter.UnicodeBlock.DOGRA_ID), /*[11800]*/
    GEORGIAN_EXTENDED(UCharacter.UnicodeBlock.GEORGIAN_EXTENDED_ID), /*[1C90]*/
    GUNJALA_GONDI(UCharacter.UnicodeBlock.GUNJALA_GONDI_ID), /*[11D60]*/
    HANIFI_ROHINGYA(UCharacter.UnicodeBlock.HANIFI_ROHINGYA_ID), /*[10D00]*/
    INDIC_SIYAQ_NUMBERS(UCharacter.UnicodeBlock.INDIC_SIYAQ_NUMBERS_ID), /*[1EC70]*/
    MAKASAR(UCharacter.UnicodeBlock.MAKASAR_ID), /*[11EE0]*/
    MAYAN_NUMERALS(UCharacter.UnicodeBlock.MAYAN_NUMERALS_ID), /*[1D2E0]*/
    MEDEFAIDRIN(UCharacter.UnicodeBlock.MEDEFAIDRIN_ID), /*[16E40]*/
    OLD_SOGDIAN(UCharacter.UnicodeBlock.OLD_SOGDIAN_ID), /*[10F00]*/
    SOGDIAN(UCharacter.UnicodeBlock.SOGDIAN_ID), /*[10F30]*/
    EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS(UCharacter.UnicodeBlock.EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS_ID), /*[13430]*/
    ELYMAIC(UCharacter.UnicodeBlock.ELYMAIC_ID), /*[10FE0]*/
    NANDINAGARI(UCharacter.UnicodeBlock.NANDINAGARI_ID), /*[119A0]*/
    NYIAKENG_PUACHUE_HMONG(UCharacter.UnicodeBlock.NYIAKENG_PUACHUE_HMONG_ID), /*[1E100]*/
    OTTOMAN_SIYAQ_NUMBERS(UCharacter.UnicodeBlock.OTTOMAN_SIYAQ_NUMBERS_ID), /*[1ED00]*/
    SMALL_KANA_EXTENSION(UCharacter.UnicodeBlock.SMALL_KANA_EXTENSION_ID), /*[1B130]*/
    SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A(UCharacter.UnicodeBlock.SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A_ID), /*[1FA70]*/
    TAMIL_SUPPLEMENT(UCharacter.UnicodeBlock.TAMIL_SUPPLEMENT_ID), /*[11FC0]*/
    WANCHO(UCharacter.UnicodeBlock.WANCHO_ID), /*[1E2C0]*/
    CHORASMIAN(UCharacter.UnicodeBlock.CHORASMIAN_ID), /*[10FB0]*/
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G_ID), /*[30000]*/
    DIVES_AKURU(UCharacter.UnicodeBlock.DIVES_AKURU_ID), /*[11900]*/
    KHITAN_SMALL_SCRIPT(UCharacter.UnicodeBlock.KHITAN_SMALL_SCRIPT_ID), /*[18B00]*/
    LISU_SUPPLEMENT(UCharacter.UnicodeBlock.LISU_SUPPLEMENT_ID), /*[11FB0]*/
    SYMBOLS_FOR_LEGACY_COMPUTING(UCharacter.UnicodeBlock.SYMBOLS_FOR_LEGACY_COMPUTING_ID), /*[1FB00]*/
    TANGUT_SUPPLEMENT(UCharacter.UnicodeBlock.TANGUT_SUPPLEMENT_ID), /*[18D00]*/
    YEZIDI(UCharacter.UnicodeBlock.YEZIDI_ID), /*[10E80]*/
    ARABIC_EXTENDED_B(UCharacter.UnicodeBlock.ARABIC_EXTENDED_B_ID), /*[0870]*/
    CYPRO_MINOAN(UCharacter.UnicodeBlock.CYPRO_MINOAN_ID), /*[12F90]*/
    ETHIOPIC_EXTENDED_B(UCharacter.UnicodeBlock.ETHIOPIC_EXTENDED_B_ID), /*[1E7E0]*/
    KANA_EXTENDED_B(UCharacter.UnicodeBlock.KANA_EXTENDED_B_ID), /*[1AFF0]*/
    LATIN_EXTENDED_F(UCharacter.UnicodeBlock.LATIN_EXTENDED_F_ID), /*[10780]*/
    LATIN_EXTENDED_G(UCharacter.UnicodeBlock.LATIN_EXTENDED_G_ID), /*[1DF00]*/
    OLD_UYGHUR(UCharacter.UnicodeBlock.OLD_UYGHUR_ID), /*[10F70]*/
    TANGSA(UCharacter.UnicodeBlock.TANGSA_ID), /*[16A70]*/
    TOTO(UCharacter.UnicodeBlock.TOTO_ID), /*[1E290]*/
    UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_A(UCharacter.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_A_ID), /*[11AB0]*/
    VITHKUQI(UCharacter.UnicodeBlock.VITHKUQI_ID), /*[10570]*/
    ZNAMENNY_MUSICAL_NOTATION(UCharacter.UnicodeBlock.ZNAMENNY_MUSICAL_NOTATION_ID), /*[1CF00]*/
    ARABIC_EXTENDED_C(UCharacter.UnicodeBlock.ARABIC_EXTENDED_C_ID), /*[10EC0]*/
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_H(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_H_ID), /*[31350]*/
    CYRILLIC_EXTENDED_D(UCharacter.UnicodeBlock.CYRILLIC_EXTENDED_D_ID), /*[1E030]*/
    DEVANAGARI_EXTENDED_A(UCharacter.UnicodeBlock.DEVANAGARI_EXTENDED_A_ID), /*[11B00]*/
    KAKTOVIK_NUMERALS(UCharacter.UnicodeBlock.KAKTOVIK_NUMERALS_ID), /*[1D2C0]*/
    KAWI(UCharacter.UnicodeBlock.KAWI_ID), /*[11F00]*/
    NAG_MUNDARI(UCharacter.UnicodeBlock.NAG_MUNDARI_ID), /*[1E4D0]*/
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_I(UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_I_ID), /*[2EBF0]*/
    INVALID_CODE(UCharacter.UnicodeBlock.INVALID_CODE_ID),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeBlock>(
        enumType = UnicodeBlock::class,
    )
}
