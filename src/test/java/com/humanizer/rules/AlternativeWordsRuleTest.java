package com.humanizer.rules;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests pour AlternativeWordsRule.
 */
@DisplayName("AlternativeWordsRule Tests")
class AlternativeWordsRuleTest {

    private AlternativeWordsRule rule;

    @BeforeEach
    void setUp() {
        rule = new AlternativeWordsRule();
    }

    @Test
    @DisplayName("Remplace les formules formelles")
    void testReplacesFormalPhrases() {
        String text = "Je dois vous informer que par conséquent cependant.";
        String result = rule.apply(text);

        assertThat(result).doesNotContain("Je dois vous informer que");
        assertThat(result).doesNotContain("Par conséquent");
        assertThat(result).doesNotContain("Cependant");
    }

    @Test
    @DisplayName("Remplace les alternatives de vocabulaire")
    void testReplacesVocabulary() {
        String text = "conformément aux dispositions légales applicables";
        String result = rule.apply(text);

        assertThat(result).contains("selon les");
        assertThat(result).contains("en vigueur");
    }

    @Test
    @DisplayName("Compte les modifications")
    void testCountsModifications() {
        rule.resetModificationCount();
        String text = "Je dois vous informer que par conséquent.";
        rule.apply(text);

        assertThat(rule.getModificationCount()).isGreaterThan(0);
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
    @DisplayName("Réinitialise le compteur")
    void testResetsModificationCount() {
        rule.apply("Je dois vous informer que par conséquent.");
        assertThat(rule.getModificationCount()).isGreaterThan(0);
        
        rule.resetModificationCount();
        assertThat(rule.getModificationCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("Retourne le nom correct")
    void testGetName() {
        assertThat(rule.getName()).isEqualTo("AlternativeWordsRule");
    }
}
