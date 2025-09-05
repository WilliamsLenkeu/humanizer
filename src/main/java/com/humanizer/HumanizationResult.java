package com.humanizer;

import java.util.List;

/**
 * Interface pour le résultat d'humanisation d'un texte.
 */
public interface HumanizationResult {
    String getOriginalText();
    String getHumanizedText();
    int getHumanizationScore();
    int getModifications();
    List<String> getAppliedRules();
}
