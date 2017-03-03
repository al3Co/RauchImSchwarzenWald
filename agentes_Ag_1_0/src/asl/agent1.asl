// Agent agent1 in project agentes_Ag_1_0.mas2j

/* Initial beliefs and rules */
lose(camino).

/* Initial goals */

!encontrar(camino).

/* Plans */
+!encontrar(camino):lose(camino)<- .print("buscando camino camino");pick(garb);.send([agent2], achieve,empezar(camino));!empezar(camino).
+!empezar(camino): true <- next(punto);next(punto);next(punto);next(punto);next(punto);next(punto);next(punto);next(punto);next(punto).