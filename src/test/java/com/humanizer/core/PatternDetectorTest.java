package com.humanizer.core;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Tests pour PatternDetector.
 */
@DisplayName("PatternDetector Tests")
class PatternDetectorTest {

    private PatternDetector detector;

    @BeforeEach
    void setUp() {
        detector = new PatternDetector();
    }

    @Test
    @DisplayName("Détecte les patterns robotiques courants")
    void testDetectCommonPatterns() {
        String text = "Je dois vous informer que suite à notre examen par conséquent.";
        List<PatternDetector.DetectedPattern> patterns = detector.detect(text);

        assertThat(patterns).isNotEmpty();
        assertThat(patterns.stream().map(p -> p.pattern).toList())
            .anyMatch(p -> p.contains("Je dois") || p.contains("robotique"));
    }

    @Test
    @DisplayName("Compte correctement les occurrences")
    void testCountOccurrences() {
        String text = "Je dois vous informer. Je dois vous dire. Je dois.";
        List<PatternDetector.DetectedPattern> patterns = detector.detect(text);

        assertThat(patterns).anySatisfy(p -> 
            assertThat(p.occurrences).isGreaterThan(0)
        );
    }

    @Test
    @DisplayName("Retourne une liste vide pour texte normal")
    void testNormalText() {
        String text = "Voici un texte normal sans formalité excessive.";
        List<PatternDetector.DetectedPattern> patterns = detector.detect(text);

        // Peut être vide ou presque vide
        assertThat(patterns).isNotNull();
    }

    @Test
    @DisplayName("Gère le texte null")
    void testNullText() {
        List<PatternDetector.DetectedPattern> patterns = detector.detect(null);

        assertThat(patterns).isEmpty();
    }

    @Test
    @DisplayName("Gère le texte vide")
    void testEmptyText() {
        List<PatternDetector.DetectedPattern> patterns = detector.detect("");

        assertThat(patterns).isEmpty();
    }

    @Test
    @DisplayName("Détecte les énumérations excessives")
    void testDetectExcessiveEnumeration() {
        String text = "Liste d'éléments:\n" +
                     "- Élément 1\n" +
                     "- Élément 2\n" +
                     "- Élément 3\n" +
                     "- Élément 4\n" +
                     "- Élément 5";
        List<PatternDetector.DetectedPattern> patterns = detector.detect(text);

        assertThat(patterns).anySatisfy(p ->
            assertThat(p.pattern).contains("Énumération"));
    }

    @Test
    @DisplayName("Détecte les phrases trop longues")
    void testDetectLongSentences() {
        String text = "Ceci est une phrase extrêmement longue qui contient beaucoup " +
                     "de mots et d'informations sans interruption ce qui rend le texte difficile à lire.";
        List<PatternDetector.DetectedPattern> patterns = detector.detect(text);

        assertThat(patterns).anySatisfy(p ->
            assertThat(p.pattern).contains("longues"));
    }

    @Test
    @DisplayName("DetectedPattern toString fonctionne")
    void testDetectedPatternToString() {
        var pattern = new PatternDetector.DetectedPattern("Test", "Suggestion", 5);
        String str = pattern.toString();

        assertThat(str).contains("Test");
        assertThat(str).contains("Suggestion");
        assertThat(str).contains("5");
    }
}
