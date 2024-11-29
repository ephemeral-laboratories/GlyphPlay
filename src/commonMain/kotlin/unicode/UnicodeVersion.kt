package garden.ephemeral.glyphplay.unicode

/**
 * Enumeration of known Unicode versions. Fill out the missing info as we go.
 *
 * Separate from ICU4j's because we wanted it in multiplatform code.
 * I guess we'll end up with a mapping somewhere as a transitional thing.
 */
enum class UnicodeVersion(val stringRepresentation: String) {
    VERSION_16_0_0("16.0.0"),
    VERSION_15_1_0("15.1.0"),
    VERSION_15_0_0("15.0.0"),
    VERSION_14_0_0("14.0.0"),
    VERSION_13_0_0("13.0.0"),
    VERSION_11_0_0("11.0.0"),
    VERSION_8_0("8.0"),
    VERSION_5_2("5.2"),
    VERSION_5_1("5.1"),
    VERSION_5_0("5.0"),
    VERSION_4_1("4.1"),
    VERSION_4_0_1("4.0.1"),
    VERSION_4_0("4.0"),
    VERSION_3_2("3.2"),
    VERSION_3_1_1("3.1.1"),
    VERSION_3_1("3.1"),
    VERSION_3_0("3.0"),
    VERSION_2_0("2.0"),
    ;

}
