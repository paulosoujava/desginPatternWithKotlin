package com.example.desginpatterkotlin.structural.flyweight
// A classe flyweight contém uma parte do estado de uma árvore
// Esses campos armazenam valores que são únicos para cada
// árvore em particular. Por exemplo, você não vai encontrar
// coordenadas da árvore aqui. Já que esses dados geralmente são
// GRANDES, você gastaria muita memória mantendo-os em cada
// objeto árvore. Ao invés disso, nós podemos extrair a textura,
// cor e outros dados repetitivos em um objeto separado os quais
// muitas árvores individuais podem referenciar.
/*
class TreeType is
field name
field color
field texture
constructor TreeType(name, color, texture) { ... }
method draw(canvas, x, y) is
// 1. Cria um bitmap de certo tipo, cor e textura.
// 2. Desenha o bitmap em uma tela nas coordenadas X e
// Y.

// A fábrica flyweight decide se reutiliza o flyweight existente
// ou cria um novo objeto.
class TreeFactory is
static field treeTypes: collection of tree types
static method getTreeType(name, color, texture) is
type = treeTypes.find(name, color, texture)
if (type == null)
type = new TreeType(name, color, texture)
treeTypes.add(type)
return type

// O objeto contextual contém a parte extrínseca do estado da
// árvore. Uma aplicação pode criar bilhões desses estados, já
// que são muito pequenos:
// apenas dois números inteiros para coordenadas e um campo de
// referência.
class Tree is
field x,y
field type: TreeType
constructor Tree(x, y, type) { ... }
method draw(canvas) is
type.draw(canvas, this.x, this.y)

// As classes Tree (Árvore) e Forest (Floresta) são os clientes
// flyweight. Você pode uni-las se não planeja desenvolver mais
// a classe Tree.
class Forest is
field trees: collection of Trees

method plantTree(x, y, name, color, texture) is
type = TreeFactory.getTreeType(name, color, texture)
tree = new Tree(x, y, type)
trees.add(tree)

method draw(canvas) is
foreach (tree in trees) do
tree.draw(canvas)

*/
