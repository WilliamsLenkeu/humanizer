package com.humanizer.rules;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests pour SentenceStructureRule.
 */
@DisplayName("SentenceStructureRule Tests")
class SentenceStructureRuleTest {

    private SentenceStructureRule rule;

    @BeforeEach
    void setUp() {
        rule = new SentenceStructureRule();
    }

    @Test
    @DisplayName("Divise les phrases longues sur les conjonctions")
    void testSplitsLongSentences() {
        String text = "Ceci est une phrase très longue qui contient beaucoup de mots " +
                     "et pourrait être divisée, car elle est difficile à lire, " +
                     "donc elle devrait être restructurée";
        String result = rule.apply(text);

        // Devrait contenir plus de points
        assertThat(result.split("\\.").length).isGreaterThan(2);
    }

    @Test
    @DisplayName("Remplace les conjonctions par des séparateurs de phrases")
    void testReplacesConjunctions() {
        String text = "Première partie, et deuxième partie.";
        String result = rule.apply(text);

        assertThat(result).contains(". ");
    }

    @Test
    @DisplayName("Gère le texte vide")
    void testHandlesEmptyText() {
        String result = rule.apply("");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Gère le texte null")
    void testHandlesNullText() {
        String result = rule.apply(null);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Préserve les phrases courtes")
    void testPreserveShortSentences() {
        String text = "Phrase courte.";
        String result = rule.apply(text);

        assertThat(result).contains("Phrase courte");
    }

    @Test
    @DisplayName("Réinitialise le compteur")
    void testResetsModificationCount() {
        rule.apply("Ceci est une phrase très longue avec beaucoup de mots, " +
                  "donc elle devrait être divisée");
        assertThat(rule.getModificationCount()).isGreaterThanOrEqualTo(0);
        
        rule.resetModificationCount();
        assertThat(rule.getModificationCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("Retourne le nom correct")
    void testGetName() {
        assertThat(rule.getName()).isEqualTo("SentenceStructureRule");
    }
}
