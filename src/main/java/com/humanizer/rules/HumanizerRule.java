package com.humanizer.rules;

/**
 * Interface de base pour les règles d'humanisation de texte.
 * Chaque règle encapsule une transformation spécifique pour rendre le texte plus naturel.
 */
public interface HumanizerRule {
    /**
     * Applique la règle au texte fourni.
     * @param text le texte à transformer
     * @return le texte transformé
     */
    String apply(String text);

    /**
     * Retourne le nom unique de cette règle.
     * @return le nom de la règle
     */
    String getName();

    /**
     * Retourne le nombre de modifications apportées par cette règle.
     * @return le nombre de modifications
     */
    int getModificationCount();

    /**
     * Réinitialise le compteur de modifications.
     */
    void resetModificationCount();
}
