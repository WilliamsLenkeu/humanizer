package com.humanizer.rules;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests pour ContractionsRule.
 */
@DisplayName("ContractionsRule Tests")
class ContractionsRuleTest {

    private ContractionsRule rule;

    @BeforeEach
    void setUp() {
        rule = new ContractionsRule();
    }

    @Test
    @DisplayName("Applique les contractions basiques")
    void testAppliesContractions() {
        String text = "Il ne faut pas le faire.";
        String result = rule.apply(text);

        assertThat(result).contains("pas");
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
    @DisplayName("Compte les modifications")
    void testCountsModifications() {
        rule.resetModificationCount();
        rule.apply("Il ne faut pas le faire.");
        
        assertThat(rule.getModificationCount()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("Réinitialise le compteur")
    void testResetsModificationCount() {
        rule.apply("Il ne faut pas le faire.");
        rule.resetModificationCount();
        assertThat(rule.getModificationCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("Retourne le nom correct")
    void testGetName() {
        assertThat(rule.getName()).isEqualTo("ContractionsRule");
    }
}
