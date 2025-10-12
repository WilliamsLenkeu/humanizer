package com.humanizer;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests pour le TextHumanizer principal.
 */
@DisplayName("TextHumanizer Tests")
class TextHumanizerTest {

    private TextHumanizer humanizer;

    @BeforeEach
    void setUp() {
        humanizer = new TextHumanizer();
    }

    @Test
    @DisplayName("Humanise un texte formel simple")
    void testHumanizeSimpleFormalText() {
        String text = "Je dois vous informer que votre demande a été acceptée.";
        HumanizationResult result = humanizer.humanize(text);

        assertThat(result).isNotNull();
        assertThat(result.getOriginalText()).isEqualTo(text);
        assertThat(result.getHumanizedText()).isNotEqualTo(text);
        assertThat(result.getHumanizationScore()).isGreaterThanOrEqualTo(0);
        assertThat(result.getModifications()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Gère le texte vide")
    void testHumanizeEmptyText() {
        HumanizationResult result = humanizer.humanize("");

        assertThat(result).isNotNull();
        assertThat(result.getHumanizationScore()).isEqualTo(0);
        assertThat(result.getModifications()).isEqualTo(0);
    }

    @Test
    @DisplayName("Gère le texte null")
    void testHumanizeNullText() {
        HumanizationResult result = humanizer.humanize(null);

        assertThat(result).isNotNull();
        assertThat(result.getHumanizationScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("Détecte et remplace les formules robotiques")
    void testReplaceRoboticPhrases() {
        String text = "Je dois vous informer que suite à notre examen, par conséquent nous vous confirmions.";
        HumanizationResult result = humanizer.humanize(text);

        String humanized = result.getHumanizedText();
        assertThat(humanized.toLowerCase()).doesNotContain("je dois vous informer que");
        assertThat(humanized.toLowerCase()).doesNotContain("suite à notre examen");
        assertThat(result.getAppliedRules()).isNotEmpty();
    }

    @Test
    @DisplayName("Préserve le sens du texte")
    void testPreservesMeaning() {
        String text = "Par conséquent, vous pouvez procéder.";
        HumanizationResult result = humanizer.humanize(text);

        // Les mots clés doivent rester présents
        String humanized = result.getHumanizedText().toLowerCase();
        assertThat(humanized).contains("pouvez");
        assertThat(humanized).contains("procéder");
    }

    @Test
    @DisplayName("Applique la règle AlternativeWords")
    void testAlternativeWordsRuleApplication() {
        String text = "l'ensemble des critères ont été satisfaits.";
        HumanizationResult result = humanizer.humanize(text);

        String humanized = result.getHumanizedText();
        assertThat(humanized).contains("tous les");
    }

    @Test
    @DisplayName("Analyse un texte sans humanisation")
    void testAnalyzeText() {
        String text = "Ceci est une phrase. Voici une autre phrase.";
        var analysis = humanizer.analyze(text);

        assertThat(analysis).isNotEmpty();
        assertThat(analysis).containsKey("sentenceCount");
        assertThat(analysis.get("sentenceCount")).isEqualTo(2);
    }

    @Test
    @DisplayName("Ajoute une règle personnalisée")
    void testAddCustomRule() {
        var rule = new MockHumanizerRule();
        humanizer.addRule(rule);

        String text = "Test text";
        HumanizationResult result = humanizer.humanize(text);

        assertThat(result.getAppliedRules()).anySatisfy(r -> r.contains("MockRule"));
    }

    /**
     * Mock d'une règle pour les tests.
     */
    private static class MockHumanizerRule implements com.humanizer.rules.HumanizerRule {
        @Override
        public String apply(String text) {
            return text;
        }

        @Override
        public String getName() {
            return "MockRule";
        }

        @Override
        public int getModificationCount() {
            return 0;
        }

        @Override
        public void resetModificationCount() {
        }
    }
}
