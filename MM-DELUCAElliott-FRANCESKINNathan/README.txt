Master Mind créé par Elliott DE LUCA et Nathan FRANCESKIN
//Nous avons profité du déplacement de l'horaire de rendu afin de corriger quelques légères erreurs//
______________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________

Dossier "MM-DELUCAElliott-FRANCESKINNathan" réparti en plusieurs sous dossiers (les ".class" ont été enlevés et les fichiers doivent être recompilés avant exécution) :

______________________________________________________________________________________________________________________________

- base (MasterMind de base) :
Fichiers dans le dossier :
	- MMBase_DELUCAElliott_FRANCESKINNathan.java (fichier code de base)
	- MM.java (copie du fichier code de base servant aux tests)

Extensions appliquées : 
	- Affichage du plateau
	- Affichage des erreurs de réponse
	- Quelques statistiques pour évaluer et comparer les stratégies

A savoir : 
	- La partie est guidée, il suffit de suivre ce qui est affiché sur le terminal.
	- Au début : Peut choisir entre l'extension Statistiques ou Jouer.
	- Ne peut que faire une partie Ordinateur ou Joueur à la fois avec un nombre de manche déterminé au début par l'utilisateur.
	- Les statistiques ne montrent pas la totalité des codes pour éviter de surcharger le terminal.
	- "TestsAutoPublics.java" passé avec succès.

______________________________________________________________________________________________________________________________

- POO (Version objet du Master Mind):
Extensions Appliqués :
	- Affichage du plateau
	- Affichage des erreurs de réponse

Les différents fichiers ".java" servent tous au fichier "MainMasterMind.java"

A savoir : 
	- La partie est guidée, il suffit de suivre ce qui est affiché sur le terminal.
	- La partie est formée de telle sorte que le joueur joue en premier et que l'ordi soit le second.
	- Le code est une reconstruction de celui de MMBase de façon à être "orienté objets", donc peu de commentaires puisque les fonctions ont déjà été expliquées

Compilation --> javac *.java
Exécution   --> java MainMasterMind

______________________________________________________________________________________________________________________________

- CSJO (Contre-Stratégie du joueur et de l'ordinateur) :
Fichiers dans le dossier :
	- MMCSJO_DELUCAElliott_FRANCESKINNathan(fichier code de base)
	- MM.java (copie du fichier code de base servant à de potentiels nouveaux tests)

Extensions Appliqués :
	- Affichage du plateau
	- Affichage des erreurs de réponse
	- Quelques statistiques pour évaluer et comparer les stratégies (possible de l'utiliser avec la contre-stratégie)

A savoir :
	- La partie est guidée, il suffit de suivre ce qui est affiché sur le terminal.
	- Plusieurs méthodes aléatoires utilisées pour plus d'aléatoire dans la recherche des réponses de l'IA

______________________________________________________________________________________________________________________________

- CFC (Stratégie CFC de l'ordinateur):
Fichiers dans le dossier :
	- MMCFC_DELUCAElliott_FRANCESKINNathan (fichier code de base)
	- MM.java (copie du fichier code de base servant à de potentiels nouveaux tests)

Similaire au MasterMind de base mais avec une IA différente

______________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________

Participation :

- Elliott : A surtout participé à la programmation du code du Master Mind de base avec les extensions et aidé au développement de la POO et de la CSJO
- Nathan : A surtout participé à la programmation de la version objet et de la CSJO et aidé au développement du code du Master Mind de base et des extensions

Seule extension non programmée : Interface Graphique.
