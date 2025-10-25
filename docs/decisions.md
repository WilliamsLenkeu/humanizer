# Décisions Techniques et Architecturales

## DEC-001: Choix du Langage et Framework
**Date**: 11 mars 2026
**Décision**: Java 17 + Maven
**Justification**: 
- Java offre une excellente performance pour le traitement de texte
- Maven assure la reproductibilité des builds
- JUnit 5 fournit un cadre de test solide
- Facilité d'intégration dans des écosystèmes d'entreprise

---

## DEC-002: Architecture Modulaire des Règles
**Date**: 11 mars 2026
**Décision**: Interface `HumanizerRule` avec implémentations spécifiques
**Justification**:
- Permet l'ajout facile de nouvelles règles sans modifier le cœur
- Chaque règle est testable indépendamment
- Pattern Strategy facilite la composition et la priorité des règles

---

## DEC-003: Pas de Dépendances Externes Lourdes
**Date**: 11 mars 2026
**Décision**: Utilisation minimale de dépendances externes
**Justification**:
- SLF4J pour le logging (standard industriel)
- JUnit 5 + AssertJ pour tester (essentiels)
- Évite la bloatware et maintient la maintenabilité
- Donne plus de flexibilité pour l'intégration futur

---

## DEC-004: Approche de Détection par Patterns
**Date**: 11 mars 2026
**Décision**: Détection basée sur patterns et heuristiques (pas de ML)
**Justification**:
- Plus rapide et prévisible que le ML
- Pas de dépendances de modèles externes
- Plus facile à debuguer et à maintenir
- Suffisant pour un MVP

---

## DEC-005: Format de Configuration
**Date**: 11 mars 2026
**Décision**: Configuration en code Java avec possibilité d'extension
**Justification**:
- Type-safe
- Facile à version dans Git
- Pas de fichier de config supplémentaire au démarrage

---

## Notes Évolutives
- Évaluer l'ajout de NLP (Natural Language Processing) en Phase 2
- Considérer une API REST si besoin d'intégration en backend
- Potentiel de machine learning pour améliorer la détection en Phase 3
