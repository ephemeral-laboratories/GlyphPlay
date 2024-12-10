package garden.ephemeral.glyphplay.util

import org.junit.platform.commons.annotation.Testable

/**
 * Annotation to put on a test when it is specifically around testing a certain
 * class or object.
 *
 * This is useful for navigation but also marks the class as testable, which removes the ugly
 * "unused class" warning for the test class. Why this is not a standard annotation of JUnit or
 * Kotlin Test is anybody's guess.
 *
 * XXX: It would be nice if this annotation could trigger a check that the test actually
 *      references the specified type, to catch copy/paste errors.
 *
 * @param <T> the subject of the test.
 */
// Useful for IDE navigation
@Suppress("unused")
@Testable
annotation class Subject<S>
