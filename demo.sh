#!/bin/bash
# Script de démonstration du Text Humanizer

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║           TEXT HUMANIZER - Démonstration Complète             ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# Vérifier que Maven et Java sont installés
if ! command -v java &> /dev/null; then
    echo "❌ Java n'est pas installé. Veuillez installer Java 17+."
    exit 1
fi

if ! command -v mvn &> /dev/null; then
    echo "❌ Maven n'est pas installé. Veuillez installer Maven 3.8.0+."
    exit 1
fi

PROJECT_DIR="/home/williams/Documents/dev_/humanizer"

echo "📦 Compilation du projet..."
cd "$PROJECT_DIR"
mvn clean package -q -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la compilation."
    exit 1
fi

echo "✅ Compilation réussie!"
echo ""
echo "🚀 Exécution du humanizer..."
echo ""

java -jar target/text-humanizer-1.0.0-jar-with-dependencies.jar

echo ""
echo "✅ Démonstration terminée avec succès!"
echo ""
echo "📚 Pour plus d'informations:"
echo "   - README.md: Vue d'ensemble du projet"
echo "   - docs/mvp.md: Spécifications"
echo "   - docs/procedures.md: Guide d'utilisation"
echo ""
