package com.humanizer;

import com.humanizer.core.*;
import com.humanizer.core.HumanizationResultImpl;
import com.humanizer.rules.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Moteur principal d'humanisation de texte.
 * Orchestre l'application des règles et génère le rapport d'humanisation.
 */
public class TextHumanizer {
    private static final Logger logger = LoggerFactory.getLogger(TextHumanizer.class);

    private final TextAnalyzer analyzer;
    private final PatternDetector patternDetector;
    private final List<HumanizerRule> rules;

    /**
     * Constructeur avec configuration par défaut.
     */
    public TextHumanizer() {
        this.analyzer = new TextAnalyzer();
        this.patternDetector = new PatternDetector();
        this.rules = new ArrayList<>();
        initializeDefaultRules();
    }

    /**
     * Constructeur avec règles personnalisées.
     */
    public TextHumanizer(List<HumanizerRule> customRules) {
        this.analyzer = new TextAnalyzer();
        this.patternDetector = new PatternDetector();
        this.rules = new ArrayList<>(customRules);
    }

    /**
     * Initialise les règles par défaut.
     */
    private void initializeDefaultRules() {
        rules.add(new AlternativeWordsRule());
        rules.add(new SentenceStructureRule());
        rules.add(new ContractionsRule());
    }

    /**
     * Humanise le texte fourni en appliquant toutes les règles.
     * @param text le texte à humaniser
     * @return le résultat d'humanisation
     */
    public HumanizationResult humanize(String text) {
        if (text == null || text.trim().isEmpty()) {
            logger.warn("Texte vide fourni à l'humanisation");
            return createEmptyResult(text);
        }

        logger.info("Démarrage de l'humanisation du texte");

        // Analyse initiale
        List<PatternDetector.DetectedPattern> detectedPatterns = patternDetector.detect(text);
        logger.debug("Patterns détectés: {}", detectedPatterns.size());

        // Application des règles
        String humanized = text;
        List<String> appliedRules = new ArrayList<>();
        int totalModifications = 0;

        for (HumanizerRule rule : rules) {
            rule.resetModificationCount();
            humanized = rule.apply(humanized);
            int mods = rule.getModificationCount();

            // Enregistrer le nom de la règle même si elle n'a pas modifié le texte
            appliedRules.add(rule.getName() + " (" + mods + ")");
            totalModifications += mods;
            if (mods > 0) {
                logger.debug("Règle appliquée: {}, modifications: {}", rule.getName(), mods);
            } else {
                logger.debug("Règle exécutée sans modification: {}", rule.getName());
            }
        }

        // Calcul du score
        int originalScore = analyzer.evaluateFormalityScore(text);
        int humanizedScore = analyzer.evaluateFormalityScore(humanized);
        int improvementScore = Math.min(100, Math.max(0, originalScore - humanizedScore));

        logger.info("Humanisation complétée. Score original: {}, amélioré: {}", 
                  originalScore, improvementScore);

        HumanizationResultImpl result = new HumanizationResultImpl(
            text, 
            humanized,
            improvementScore,
            totalModifications,
            appliedRules
        );

        result.setMetadata("originalFormalityScore", originalScore);
        result.setMetadata("detectedPatternsCount", detectedPatterns.size());
        result.setMetadata("appliedRulesCount", appliedRules.size());

        return result;
    }

    /**
     * Analyse simplement le texte sans l'humaniser.
     * @param text le texte à analyser
     * @return une map contenant les informations d'analyse
     */
    public Map<String, Object> analyze(String text) {
        if (text == null || text.trim().isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Object> analysis = new HashMap<>();

        // Analyse structurelle
        List<String> sentences = analyzer.analyzeSentences(text);
        analysis.put("sentenceCount", sentences.size());
        analysis.put("averageWordPerSentence", analyzer.calculateAveragePhraseLengthWords(text));
        analysis.put("formalityScore", analyzer.evaluateFormalityScore(text));

        // Patterns détectés
        List<PatternDetector.DetectedPattern> patterns = patternDetector.detect(text);
        analysis.put("detectedPatterns", patterns);
        analysis.put("patternCount", patterns.size());

        logger.debug("Analyse complétée: {}", analysis);
        return analysis;
    }

    /**
     * Ajoute une règle personnalisée.
     */
    public void addRule(HumanizerRule rule) {
        if (rule != null) {
            rules.add(rule);
            logger.info("Règle ajoutée: {}", rule.getName());
        }
    }

    /**
     * Crée un résultat vide pour texte nul/vide.
     */
    private HumanizationResult createEmptyResult(String text) {
        return new HumanizationResultImpl(
            text != null ? text : "",
            text != null ? text : "",
            0,
            0,
            Collections.emptyList()
        );
    }
}
