package com.humanizer.core;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests pour TextAnalyzer.
 */
@DisplayName("TextAnalyzer Tests")
class TextAnalyzerTest {

    private TextAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new TextAnalyzer();
    }

    @Test
    @DisplayName("Analyse les phrases correctement")
    void testAnalyzeSentences() {
        String text = "Première phrase. Deuxième phrase!";
        var sentences = analyzer.analyzeSentences(text);

        assertThat(sentences).hasSize(2);
        assertThat(sentences.get(0)).contains("Première");
        assertThat(sentences.get(1)).contains("Deuxième");
    }

    @Test
    @DisplayName("Extrait les mots correctement")
    void testExtractWords() {
        String text = "Voici un texte avec des mots.";
        var words = analyzer.extractWords(text);

        assertThat(words).contains("voici", "texte", "mots");
    }

    @Test
    @DisplayName("Calcule la longueur moyenne des phrases")
    void testCalculateAveragePhraseLengthWords() {
        String text = "Phrase un. Ceci est une phrase deux.";
        double average = analyzer.calculateAveragePhraseLengthWords(text);

        assertThat(average).isGreaterThan(0);
    }

    @Test
    @DisplayName("Détecte les patterns robotiques")
    void testDetectRoboticPatterns() {
        String text = "Je dois vous informer que suite à notre examen, par conséquent.";
        var patterns = analyzer.detectRoboticPatterns(text);

        assertThat(patterns).isNotEmpty();
        assertThat(patterns.toString()).contains("robotique");
    }

    @Test
    @DisplayName("Évalue le score de formalité")
    void testEvaluateFormalityScore() {
        String text = "Je vous informe que cependant conformément aux dispositions.";
        int score = analyzer.evaluateFormalityScore(text);

        assertThat(score).isGreaterThan(0);
        assertThat(score).isLessThanOrEqualTo(100);
    }

    @Test
    @DisplayName("Retourne 0 pour texte vide")
    void testEmptyText() {
        var sentences = analyzer.analyzeSentences("");
        assertThat(sentences).isEmpty();

        var words = analyzer.extractWords("");
        assertThat(words).isEmpty();
    }

    @Test
    @DisplayName("Gère le texte avec accents")
    void testAccentedText() {
        String text = "Voici un texte avec des accénts: éàâêôù.";
        var words = analyzer.extractWords(text);

        assertThat(words).contains("accénts", "accents");
    }
}
