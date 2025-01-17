package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.text.Normalizer
import com.ibm.icu.text.Normalizer.QuickCheckResult

enum class UnicodeQuickCheckResult(override val icuValue: Int, val icuValueObject: QuickCheckResult) :
    IcuUnicodeValueEnum<UnicodeQuickCheckResult> {

    // Infuriatingly, ICU doesn't seem to have given us a way to get the value out,
    // or to look up the result by value!
    NO(0, Normalizer.NO),
    YES(1, Normalizer.YES),
    MAYBE(2, Normalizer.MAYBE),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeQuickCheckResult>(
        enumType = UnicodeQuickCheckResult::class,
    )
}
