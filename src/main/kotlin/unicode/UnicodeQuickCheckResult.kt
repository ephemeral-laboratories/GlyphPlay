package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.text.Normalizer
import com.ibm.icu.text.Normalizer.QuickCheckResult
enum class UnicodeQuickCheckResult {
    NO(Normalizer.NO.),
    YES(1)
    MAYBE(2)
    ;

    val x = Normalizer.QuickCheckResult
}

