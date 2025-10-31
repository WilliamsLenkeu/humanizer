#!/usr/bin/env bash
# 📖 GUIDE INTERACTIF - Text Humanizer v1.0.0

set -e

PROJECT_DIR="/home/williams/Documents/dev_/humanizer"

display_menu() {
    echo ""
    echo "════════════════════════════════════════════════════════════════"
    echo "  TEXT HUMANIZER v1.0.0 - GUIDE DE NAVIGATION"
    echo "════════════════════════════════════════════════════════════════"
    echo ""
    echo "  📍 Localisation: $PROJECT_DIR"
    echo ""
    echo "  Vous êtes:"
    echo ""
    echo "  1️⃣  UN UTILISATEUR - Je veux humaniser un texte MAINTENANT"
    echo "  2️⃣  UN DÉVELOPPEUR - Je veux comprendre le code"
    echo "  3️⃣  UN CONTRIBUTER - Je veux créer une nouvelle règle"
    echo "  4️⃣  UN COMPRÉHENSION - Je veux comprendre l'architecture"
    echo "  5️⃣  AFFICHER - Tous les fichiers du projet"
    echo "  6️⃣  LANCER - La démo"
    echo "  0️⃣  QUITTER"
    echo ""
    echo "════════════════════════════════════════════════════════════════"
    echo ""
}

show_user_path() {
    echo ""
    echo "  👤 CHEMIN UTILISATEUR (5 minutes)"
    echo "  ─────────────────────────────"
    echo ""
    echo "  Vous avez un texte formel que vous voulez humaniser."
    echo ""
    echo "  📝 Étapes:"
    echo "     1. Lancer l'application: cd $PROJECT_DIR"
    echo "     2. Exécuter: java -jar target/text-humanizer-1.0.0-jar-with-dependencies.jar"
    echo "     3. Voir le résultat instantanément"
    echo ""
    echo "  📚 Pour plus d'info:"
    echo "     $ cat README.md"
    echo "     $ cat QUICKSTART.md"
    echo ""
}

show_developer_path() {
    echo ""
    echo "  🧑‍💻 CHEMIN DÉVELOPPEUR (30 minutes)"
    echo "  ──────────────────────────────"
    echo ""
    echo "  Vous voulez l'intégrer dans votre code Java."
    echo ""
    echo "  📝 Étapes:"
    echo "     1. Lire l'organisation: cat STRUCTURE.md"
    echo "     2. Examiner l'API: cat src/main/java/com/humanizer/TextHumanizer.java"
    echo "     3. Copier le JAR: cp target/text-humanizer-1.0.0-jar-with-dependencies.jar /votre/projet"
    echo "     4. Utiliser en code Java"
    echo ""
    echo "  📚 Pour plus d'info:"
    echo "     $ cat GETTING_STARTED.md"
    echo "     $ cat src/main/java/com/humanizer/rules/HumanizerRule.java"
    echo ""
}

show_contributor_path() {
    echo ""
    echo "  🤝 CHEMIN CONTRIBUTEUR (1-2 heures)"
    echo "  ──────────────────────────────────"
    echo ""
    echo "  Vous voulez créer une nouvelle règle d'humanisation."
    echo ""
    echo "  📝 Étapes:"
    echo "     1. Lire le template: cat src/main/java/com/humanizer/rules/CustomRuleTemplate.java"
    echo "     2. Créer votre règle en implémentant HumanizerRule"
    echo "     3. Ajouter un test: src/test/java/com/humanizer/rules/VotreRuleTest.java"
    echo "     4. Tester: mvn test"
    echo "     5. Valider: mvn verify"
    echo ""
    echo "  📚 Pour plus d'info:"
    echo "     $ cat GETTING_STARTED.md (Section 'Créer Votre Première Règle')"
    echo "     $ ls src/main/java/com/humanizer/rules/"
    echo ""
}

show_architect_path() {
    echo ""
    echo "  🏗️  CHEMIN ARCHITECTE (1 heure)"
    echo "  ──────────────────────────────"
    echo ""
    echo "  Vous vous demandez comment c'est construit."
    echo ""
    echo "  📝 Étapes:"
    echo "     1. Lire les décisions: cat docs/decisions.md"
    echo "     2. Examiner l'architecture: cat STRUCTURE.md"
    echo "     3. Voir le flux: cat FINAL_SUMMARY.md"
    echo "     4. Vérifier l'extensibilité: cat src/main/java/com/humanizer/rules/HumanizerRule.java"
    echo ""
    echo "  📚 Pour plus d'info:"
    echo "     $ cat project_context.md"
    echo "     $ cat CHECKLIST.md"
    echo ""
}

show_all_files() {
    echo ""
    echo "  📂 STRUCTURE COMPLÈTE DU PROJET"
    echo "  ──────────────────────────────"
    echo ""
    cd "$PROJECT_DIR"
    tree -L 3 -I 'target' 2>/dev/null || find . -not -path './target*' -type f | head -30
    echo ""
}

show_demo() {
    echo ""
    echo "  🚀 LANCEMENT DE LA DÉMO"
    echo "  ──────────────────────"
    echo ""
    cd "$PROJECT_DIR"
    
    # Vérifier que le JAR existe
    if [ ! -f "target/text-humanizer-1.0.0-jar-with-dependencies.jar" ]; then
        echo "  ⚠️  JAR non trouvé. Compilation en cours..."
        mvn package -DskipTests -q
    fi
    
    echo "  Exécution de la démo..."
    echo ""
    java -jar target/text-humanizer-1.0.0-jar-with-dependencies.jar
    echo ""
}

# Main loop
while true; do
    display_menu
    read -p "  Choisissez une option (0-6): " choice
    
    case $choice in
        1) show_user_path ;;
        2) show_developer_path ;;
        3) show_contributor_path ;;
        4) show_architect_path ;;
        5) show_all_files ;;
        6) show_demo ;;
        0) 
            echo ""
            echo "  À bientôt! 👋"
            echo ""
            exit 0
            ;;
        *)
            echo ""
            echo "  ❌ Option invalide. Essayez à nouveau."
            ;;
    esac
done
