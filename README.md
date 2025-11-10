# Programming Languages and Paradigms Practice No. 3 Object Oriented Programming
**Estudiantes:** 

Luis Fernando Bernal Ramirez

Ana Sofía Bermúdez Orozco

Video Explicativo: 

---

## Challenge 1: Database
Se nos pide implementar una base de datos con almenos 50 entradas.

### Facts
```
% Hecho principal: platform/9
% platform(Marca, ID, Año, RAM_GB, CPU_Fabricante, Núcleos, Disco_GB, Tipo, GPU, VRAM_GB)

platform(asus, p001, 2020, 8, amd,   4, 256, laptop, nvidia, 4).
platform(asus, p002, 2022, 16, amd,  8, 512, pc,     amd,    6).
platform(asus, p003, 2021, 4, intel, 2, 128, tablet, intel,  2).
platform(asus, p004, 2019, 32, intel, 8, 1024, pc,   nvidia, 8).
platform(asus, p005, 2023, 8, amd,   6, 512, laptop, amd,    4).

platform(msi, p006, 2020, 16, intel, 4, 512, laptop, nvidia, 6).
platform(msi, p007, 2022, 32, amd,   8, 1024, pc,    amd,    8).
platform(msi, p008, 2021, 8, amd,    6, 256, laptop, nvidia, 4).
platform(msi, p009, 2018, 4, intel,  2, 128, tablet, intel,  2).
platform(msi, p010, 2023, 64, amd,  12, 2048, pc,    nvidia, 12).

platform(dell, p011, 2021, 8, intel, 4, 256, laptop, intel,  4).
platform(dell, p012, 2019, 16, amd,  6, 512, pc,     amd,    6).
platform(dell, p013, 2022, 32, amd,  8, 1024, pc,    nvidia, 8).
platform(dell, p014, 2020, 4, intel, 2, 128, tablet, intel,  2).
platform(dell, p015, 2023, 16, amd,  6, 512, laptop, nvidia, 4).

platform(lenovo, p016, 2020, 8, amd,   4, 256, laptop, amd,    4).
platform(lenovo, p017, 2022, 16, intel, 8, 512, pc,     nvidia, 6).
platform(lenovo, p018, 2019, 4, intel,  2, 64,  tablet, intel,  2).
platform(lenovo, p019, 2021, 32, amd,   8, 1024, pc,    nvidia, 8).
platform(lenovo, p020, 2023, 64, intel, 12, 2048, pc,  nvidia, 12).

platform(hp, p021, 2021, 8, amd,   4, 256, laptop, amd,    4).
platform(hp, p022, 2019, 16, intel, 6, 512, pc,     nvidia, 6).
platform(hp, p023, 2022, 32, amd,   8, 1024, pc,    nvidia, 8).
platform(hp, p024, 2020, 4, intel,  2, 128, tablet, intel,  2).
platform(hp, p025, 2023, 16, amd,   6, 512, laptop, amd,    4).

platform(acer, p026, 2020, 8, amd,   4, 256, laptop, nvidia, 4).
platform(acer, p027, 2022, 16, intel, 8, 512, pc,    amd,    6).
platform(acer, p028, 2019, 4, intel,  2, 64,  tablet, intel,  2).
platform(acer, p029, 2021, 32, amd,   8, 1024, pc,   nvidia, 8).
platform(acer, p030, 2023, 64, intel, 12, 2048, pc,  nvidia, 12).

platform(apple, p031, 2021, 8, apple,  4, 256, laptop, apple, 4).
platform(apple, p032, 2022, 16, apple, 8, 512, laptop, apple, 6).
platform(apple, p033, 2020, 32, apple, 8, 1024, pc,   apple, 8).
platform(apple, p034, 2023, 64, apple, 12, 2048, pc,  apple, 12).
platform(apple, p035, 2019, 4, apple,  2, 128, tablet, apple, 2).

platform(samsung, p036, 2021, 8, amd,   4, 256, laptop, nvidia, 4).
platform(samsung, p037, 2019, 16, intel, 6, 512, pc,     amd,    6).
platform(samsung, p038, 2022, 32, amd,   8, 1024, pc,    nvidia, 8).
platform(samsung, p039, 2020, 4, intel,  2, 128, tablet, intel,  2).
platform(samsung, p040, 2023, 16, amd,   6, 512, laptop, amd,    4).

platform(huawei, p041, 2020, 8, amd,   4, 256, laptop, nvidia, 4).
platform(huawei, p042, 2022, 16, intel, 8, 512, pc,     amd,    6).
platform(huawei, p043, 2019, 4, intel,  2, 64,  tablet, intel,  2).
platform(huawei, p044, 2021, 32, amd,   8, 1024, pc,    nvidia, 8).
platform(huawei, p045, 2023, 64, intel, 12, 2048, pc,   nvidia, 12).

platform(razer, p046, 2021, 8, amd,   4, 256, laptop, amd,    4).
platform(razer, p047, 2019, 16, intel, 6, 512, pc,     nvidia, 6).
platform(razer, p048, 2022, 32, amd,   8, 1024, pc,    nvidia, 8).
platform(razer, p049, 2020, 4, intel,  2, 128, tablet, intel,  2).
platform(razer, p050, 2023, 16, amd,   6, 512, laptop, nvidia, 4).
```

### Rules
````
 CONSULTAS OBLIGATORIAS

% 1. Plataformas con CPU AMD adquiridas después de 2021
query1(ID) :-
    platform(_, ID, Year, _, amd, _, _, _, _, _),
    Year > 2021.

% 2. Tablets con más de 2GB de RAM
query2(ID) :-
    platform(_, ID, _, RAM, _, _, _, tablet, _, _),
    RAM > 2.

% 3. Plataformas con disco duro entre 32GB y 512GB
query3(ID) :-
    platform(_, ID, _, _, _, _, Disk, _, _, _),
    Disk >= 32,
    Disk =< 512.

% 4. Cuántas plataformas son de ASUS
query4(Count) :-
    findall(ID, platform(asus, ID, _, _, _, _, _, _, _, _), List),
    length(List, Count).

% 5. Cuántas laptops tienen más de 4GB de RAM y menos de 512GB de disco
query5(Count) :-
    findall(ID, (platform(_, ID, _, RAM, _, _, Disk, laptop, _, _),
                 RAM > 4, Disk < 512), List),
    length(List, Count).
````
#### query 1
    % 1. Plataformas con CPU AMD adquiridas después de 2021
    query1(ID) :-
        platform(_, ID, Year, _, amd, _, _, _, _, _), Year > 2021.

- Esta regla busca en la base de datos todas las plataformas (platform/10) que:

    - Tienen como fabricante de CPU el valor amd.
    - Fueron adquiridas después del año 2021.
- El parámetro ID devuelve el número de serie (platform ID) de cada equipo que cumple esas condiciones.

**Resultado test**
````
?- query1(ID).
ID = p002 ;
ID = p005 ;
ID = p007 ;
ID = p010 ;
ID = p013 ;
ID = p015 ;
ID = p023 ;
ID = p025 ;
ID = p038 ;
ID = p040 ;
ID = p048 ;
ID = p050.
````


---

#### query 2
    % 2. Tablets con más de 2GB de RAM
    query2(ID) :-
        platform(_, ID, _, RAM, _, _, _, tablet, _, _), RAM > 2.

- Busca las plataformas que son de tipo tablet y que tienen más de 2GB de memoria RAM.
- Retorna el identificador (ID) de cada tablet que cumple con esas condiciones.

**Resultado Test**
````
?- query2(ID).
ID = p003 ;
ID = p009 ;
ID = p014 ;
ID = p018 ;
ID = p024 ;
ID = p028 ;
ID = p035 ;
ID = p039 ;
ID = p043 ;
ID = p049.
````


---


#### query 3
    % 3. Plataformas con disco duro entre 32GB y 512GB
    query3(ID) :-
        platform(_, ID, _, _, _, _, Disk, _, _, _), Disk >= 32, Disk =< 512.

**Resultado Test**
````
?- query3(ID).
ID = p001 ;
ID = p002 ;
ID = p003 ;
ID = p005 ;
ID = p006 ;
ID = p008 ;
ID = p009 ;
ID = p011 ;
ID = p012 ;
ID = p014 ;
ID = p015 ;
ID = p016 ;
ID = p017 ;
ID = p018 ;
ID = p021 ;
ID = p022 ;
ID = p024 ;
ID = p025 ;
ID = p026 ;
ID = p027 ;
ID = p028 ;
ID = p031 ;
ID = p032 ;
ID = p035 ;
ID = p036 ;
ID = p037 ;
ID = p039 ;
ID = p040 ;
ID = p041 ;
ID = p042 ;
ID = p043 ;
ID = p046 ;
ID = p047 ;
ID = p049 ;
ID = p050.
````


---


#### query 4
    % 4. Cuántas plataformas son de ASUS
    query4(Count) :-
        findall(ID, platform(asus, ID, _, _, _, _, _, _, _, _), List), length(List, Count).

Esta consulta cuenta cuántos equipos pertenecen a la marca ASUS.

- `findall(ID, platform(asus, ID, _, _, _, _, _, _, _, _), List)` busca todas las plataformas cuya marca (Trademark) es asus, y almacena sus IDs en una lista (List).
- `length(List, Count)` calcula la cantidad de elementos en la lista, es decir, cuántos ASUS hay en total.

**Resultados Test**
````
?- query4(Count).
Count = 5.
````


---


#### query 5
    % 5. Cuántas laptops tienen más de 4GB de RAM y menos de 512GB de disco
    query5(Count) :-
        findall(ID, (platform(_, ID, _, RAM, _, _, Disk, laptop, _, _), RAM > 4, Disk < 512), List), length(List, Count).

Cuenta cuántas plataformas son laptops y además cumplen dos condiciones:
- Tienen más de 4GB de RAM.
- Tienen menos de 512GB de disco duro.

- `findall(ID, (...), List)` busca todos los equipos que cumplan simultáneamente las tres condiciones:
    - Type = laptop
    - RAM > 4
    - Disk < 512
    y guarda sus IDs en List.
- `length(List, Count)` cuenta cuántas laptops cumplen con esas especificaciones.

**Resultados Test**
```
?- query5(Count).
Count = 10.
```


---


### Problemas encontrados en el desarrollo
Durante el desarrollo, el mayor reto fue garantizar que la base de datos tuviera exactamente 50 entradas válidas y que cada una cumpliera con todos los atributos (marca, ID, año, RAM, CPU, núcleos, disco, tipo, GPU y VRAM). Al principio, se presentaban errores de inconsistencia en los hechos, como olvidar un parámetro o usar diferentes cantidades de argumentos, lo que hacía que Prolog no pudiera reconocer bien los predicados platform/9.

**Solución**

Se resolvió estandarizando el formato de cada entrada en la base de datos (platform/9) y verificando que todos los hechos tuvieran 9 argumentos exactos. Además, se numeraron los identificadores (p001 a p050) para llevar un control y evitar duplicados. Finalmente, se probó cada consulta en la consola de SWI-Prolog para asegurarse de que todas devolvieran resultados correctos.


---


### Conclusiones


---
#### 1. Organización y consistencia son claves en Prolog
Mantener el mismo formato en los hechos de la base de datos evita errores y facilita que las consultas funcionen correctamente.

#### 2. Las consultas permiten validar la base de datos
Gracias a las consultas pedidas (ejemplo: buscar por marca, por rango de memoria o por características específicas), se comprobó que los datos estaban bien estructurados y que el sistema responde de forma correcta a distintos criterios.

#### 3. Prolog es útil para bases de datos lógicas
Aunque no maneja grandes volúmenes de datos como un sistema SQL, Prolog resulta muy práctico para representar conocimiento y realizar consultas complejas de manera clara y directa.


---


## 2. Travel planning software
Se nos pide desarrollar un sistema de planeación de viajes en Prolog que permita encontrar rutas entre ciudades considerando múltiples factores.

### Facts
```
ruta(bogota, medellin, avion, 8, 9, 100, si).
ruta(bogota, medellin, bus, 6, 16, 40, si).
ruta(medellin, cartagena, avion, 10, 11.5, 120, no).
ruta(cartagena, santa_marta, bus, 14, 18, 30, si).
ruta(medellin, santa_marta, avion, 9.5, 11, 150, si).
ruta(bogota, cali, bus, 5, 13, 35, si).
ruta(cali, medellin, bus, 14, 22, 40, si).
```

### Rules
---
#### direct_route
```
direct_route(Origen, Destino, Medio, Salida, Llegada, Costo) :-
    ruta(Origen, Destino, Medio, Salida, Llegada, Costo, si).
```
Encuentra conexiones directas disponibles sin escalas.

---

#### route
```
route(Origen, Destino, Camino, TotalCosto, TotalTiempo) :-
    travel(Origen, Destino, [Origen], CaminoInvertido, 0, 0 TotalCosto, TotalTiempo), reverse(CaminoInvertido, Camino).
```
Encuentra todos los posibles caminos entre "Origen" y "Destino", incluso con ciudades intermedias, mediante una regla auxiliar "travel" que realiza la recursión.

"[Origen]" actua como una lista que lleva el registro de las ciudades visitadas, para evitar ciclos.

También inicializa los contadores de tiempo y costo.

Finalmente reversa el camino, debido a que por la recursión este es construido de forma inversa.

---

#### travel
```
travel(Destino, Destino, Camino, Camino, Costo, Tiempo, Costo, Tiempo).
travel(Origen, Destino, Visitados, Camino, CostoAcum, TiempoAcum, CostoTotal, TiempoTotal) :-
    ruta(Origen, Siguiente, _, Salida, Llegada, Costo, si),
    \+ pertenece(Siguiente, Visitados),
    TiempoViaje is Llegada - Salida,
    NuevoCosto is CostoAcum + Costo,
    NuevoTiempo is TiempoAcum + TiempoViaje,
    travel(Siguiente, Destino, [Siguiente|Visitados], Camino, NuevoCosto, NuevoTiempo, CostoTotal, TiempoTotal).
```
- Caso base: 

        travel(Destino, Destino, Camino, Camino, Costo, Tiempo, Costo, Tiempo).
    
    Si ya estamos en el destino, es decir, "Origen"(el primer parametro de travel) es igual a "Destino", entonces:
    
    El camino está completo, el costo y tiempo total son iguales a los valores acumulados, y la recursión se detiene.

- Caso recursivo: 

        ruta(Origen, Siguiente, _, Salida, Llegada, Costo, si)

    Esta parte encuentra una ruta valida desde la ciudad actual hacia otra ("Siguiente") que esté disponible.

        \+ pertenece(Siguiente, Visitados)
        
    Aqui, usando la regla auxiliar "pertenece", nos aseguramos que la "Siguiente" ciudad no esté dentro de la lista de ciudades que ya visitamos.

        TiempoViaje is Llegada - Salida
    
    Se calcula cuanto tiempo ese tramo toma.

        NuevoCosto is CostoAcum + Costo,
        NuevoTiempo is TiempoAcum + TiempoViaje

    Se actualizan los totales de costo y tiempo para todo el camino.

        travel(Siguiente, Destino, [Siguiente|Visitados], Camino, NuevoCosto, NuevoTiempo, CostoTotal, TiempoTotal).

    Se hace recursión usando "Siguiente" como el nuevo "Origen" de travel, e insertandolo al inicio de la lista de ciudades visitadas.
    

---


#### pertenece
```
pertenece(X, [X|_]).
pertenece(X, [_|T]):- pertenece(X, T).
```
Verifica si un X está dentro de una lista.
Mira si el primer elemento de la lista es igual al valor de X, y si no, hace recursión con el resto de la lista


---


#### gather_routes
```
gather_routes(Origen, Destino, Lista) :-
    gather_routes_aux(Origen, Destino, [], Lista).
```
Inicializa una lista vacia para colectar las rutas y llama a la regla auxiliar "gather_routes_aux" para la recursión.


---


#### gather_routes_aux
```
gather_routes_aux(Origen, Destino, Parcial, ListaFinal) :-
    route(Origen, Destino, Camino, Costo, Tiempo),
    \+ pertenece([Camino, Costo, Tiempo], Parcial),
    gather_routes_aux(Origen, Destino, [[Camino, Costo, Tiempo]|Parcial], ListaFinal).
gather_routes_aux(_, _, Lista, Lista).
```
- `route(Origen, Destino, Camino, Costo, Tiempo)`: Llama a "route" para generar una posible ruta.
- `\+ pertenece([Camino, Costo, Tiempo], Parcial)`: Asegura que no se añadan duplicados, usando el auxiliar "pertenece".
- `gather_routes_aux(Origen, Destino, [[Camino, Costo, Tiempo]|Parcial], ListaFinal)`: Se añada la ruta al acumulador "Parcial" y se hacer recursión para encontrar más rutas.
- `gather_routes_aux(_, _, Lista, Lista).`: Cuando no existan más rutas, se detiene y retorna la lista final.


---


#### min_by_cost
```
min_by_cost([[Path, C, _]], Path, C).
min_by_cost([[Path, C, _]|Resto], BestPath, BestCost) :-
    min_by_cost(Resto, TempPath, TempCost),
    (C < TempCost -> BestPath = Path, BestCost = C ; BestPath = TempPath, BestCost = TempCost).
```
- Caso base: Si solo hay una ruta, esa es la más barata.
- Caso recursivo: Se compara el costo de la ruta actual(C) con el del resto(TempCost). Si es menor, se actualiza la ruta más barata, sino, no cambia.

#### min_by_time
````
min_by_time([[Path, _, T]], Path, T).
min_by_time([[Path, _, T]|Resto], BestPath, BestTime) :-
    min_by_time(Resto, TempPath, TempTime),
    (T < TempTime -> BestPath = Path, BestTime = T ; BestPath = TempPath, BestTime = TempTime).
````
- Caso base: Si solo hay una ruta, esa es la más corta.
- Caso recursivo: Se compara el tiempo de la ruta actual(T) con el del resto(TempTime). Si es menor, se actualiza la ruta más corta, sino, no cambia.


---


#### cheapest_route
````
cheapest_route(Origen, Destino, Camino, CostoMin) :-
    gather_routes(Origen, Destino, Lista),
    Lista \= [],
    min_by_cost(Lista, Camino, CostoMin).
````
- Usa "gather_routes" para recolectar todos los posibles caminos. 
- Chequea que la lista no este vacia(que exista una ruta).
- Usa el auxiliar "min_by_cost" pra encontrar el menor costo.


---


#### fastest_route
````
fastest_route(Origen, Destino, Camino, TiempoMin) :-
    gather_routes(Origen, Destino, Lista),
    Lista \= [],
    min_by_time(Lista, Camino, TiempoMin).
````
- Usa "gather_routes" para recolectar todos los posibles caminos. 
- Chequea que la lista no este vacia(que exista una ruta).
- Usa el auxiliar "min_by_time" pra encontrar el menor tiempo.


---


#### available_between
````
available_between(HoraInicio, HoraFin, Origen, Destino, Medio, Salida, Llegada, Costo) :-
    ruta(Origen, Destino, Medio, Salida, Llegada, Costo, si),
    Salida >= HoraInicio,
    Salida =< HoraFin.
````
Lista todas las rutas disponibles que partan dentro de un intérvalo de tiempo específico.


---



#### suggest_route

```
suggest_route(Origen, Destino, Camino, Costo, Tiempo, Mensaje) :-
    (   direct_route(Origen, Destino, _, _, _, _) ->
        % Direct route exists
        route(Origen, Destino, Camino, Costo, Tiempo),
        length(Camino, Longitud),
        Longitud =:= 2,
        Mensaje = 'Ruta directa disponible'
    ;   % No direct route, find route with stopovers
        route(Origen, Destino, Camino, Costo, Tiempo),
        length(Camino, Longitud),
        Longitud > 2,
        NumParadas is Longitud - 2,
        atom_concat('Ruta con ', NumParadas, Temp),
        atom_concat(Temp, ' parada(s)', Mensaje)
    ).
```

Sugiere inteligentemente la mejor ruta disponible con mecanismo de respaldo automático.

- **Primera opción**: Intenta encontrar una ruta directa usando `direct_route(Origen, Destino, _, _, _, _)`.
  - Si existe una ruta directa disponible, verifica que el camino tenga longitud 2 (origen → destino).
  - Retorna el mensaje `'Ruta directa disponible'`.

- **Respaldo automático**: Si no hay ruta directa (`; else`):
  - Busca cualquier ruta posible con escalas usando `route(Origen, Destino, Camino, Costo, Tiempo)`.
  - Calcula el número de paradas intermedias: `NumParadas is Longitud - 2`.
  - Construye un mensaje descriptivo indicando cuántas paradas tiene la ruta.

Esta regla prioriza rutas directas pero automáticamente ofrece alternativas con escalas cuando sea necesario.

---

#### remove_cheapest

```
remove_cheapest(Lista, ListaSinMin) :-
    min_by_cost(Lista, CaminoMin, CostoMin),
    remove_route([CaminoMin, CostoMin, _], Lista, ListaSinMin).
```

Elimina la ruta más económica de una lista de rutas.

- Utiliza `min_by_cost` para identificar la ruta con el menor costo.
- Llama a `remove_route` para eliminar esa ruta específica de la lista original.
- Retorna la lista sin la ruta más barata en `ListaSinMin`.

Esta función auxiliar es crucial para encontrar la segunda mejor opción por costo.

---

#### remove_route

```
remove_route(_, [], []).
remove_route([C, P, T], [[C, P, T]|Resto], Resto) :- !.
remove_route(Ruta, [H|Resto], [H|NuevaResto]) :-
    remove_route(Ruta, Resto, NuevaResto).
```

Elimina una ruta específica de una lista.

- **Caso base**: `remove_route(_, [], [])` - Si la lista está vacía, retorna lista vacía.

- **Caso de coincidencia**: `remove_route([C, P, T], [[C, P, T]|Resto], Resto) :- !`
  - Si encuentra la ruta exacta (mismo costo C, camino P, y tiempo T), la elimina.
  - El operador `!` (cut) evita backtracking innecesario una vez encontrada.

- **Caso recursivo**: `remove_route(Ruta, [H|Resto], [H|NuevaResto])`
  - Si el primer elemento no coincide, lo mantiene en la nueva lista.
  - Continúa buscando recursivamente en el resto de la lista.

---

#### next_best_by_cost

```
next_best_by_cost(Origen, Destino, Camino, Costo) :-
    gather_routes(Origen, Destino, Lista),
    Lista \= [],
    remove_cheapest(Lista, ListaSinMin),
    ListaSinMin \= [],
    min_by_cost(ListaSinMin, Camino, Costo).
```

Encuentra la segunda ruta más económica entre dos ciudades.

- `gather_routes(Origen, Destino, Lista)`: Recolecta todas las rutas posibles.
- `Lista \= []`: Verifica que exista al menos una ruta.
- `remove_cheapest(Lista, ListaSinMin)`: Elimina la ruta más barata de la lista.
- `ListaSinMin \= []`: Asegura que quede al menos una ruta alternativa.
- `min_by_cost(ListaSinMin, Camino, Costo)`: Encuentra la más barata de las restantes.

Útil cuando la ruta óptima no está disponible y se necesita la siguiente mejor opción económica.

---

#### remove_fastest

```
remove_fastest(Lista, ListaSinMin) :-
    min_by_time(Lista, CaminoMin, TiempoMin),
    remove_route([_, CaminoMin, TiempoMin], Lista, ListaSinMin).
```

Elimina la ruta más rápida de una lista de rutas.

- Utiliza `min_by_time` para identificar la ruta con el menor tiempo de viaje.
- Llama a `remove_route` para eliminar esa ruta específica de la lista original.
- Retorna la lista sin la ruta más rápida en `ListaSinMin`.

Funciona análogamente a `remove_cheapest` pero optimizando por tiempo en lugar de costo.

---

#### next_best_by_time

```
next_best_by_time(Origen, Destino, Camino, Tiempo) :-
    gather_routes(Origen, Destino, Lista),
    Lista \= [],
    remove_fastest(Lista, ListaSinMin),
    ListaSinMin \= [],
    min_by_time(ListaSinMin, Camino, Tiempo).
```

Encuentra la segunda ruta más rápida entre dos ciudades.

- `gather_routes(Origen, Destino, Lista)`: Recolecta todas las rutas posibles.
- `Lista \= []`: Verifica que exista al menos una ruta.
- `remove_fastest(Lista, ListaSinMin)`: Elimina la ruta más rápida de la lista.
- `ListaSinMin \= []`: Asegura que quede al menos una ruta alternativa.
- `min_by_time(ListaSinMin, Camino, Tiempo)`: Encuentra la más rápida de las restantes.

Proporciona una opción de respaldo cuando la ruta más rápida no está disponible.

---

#### verificar_disponibilidad_completa

```
verificar_disponibilidad_completa([_]).
verificar_disponibilidad_completa([Origen, Siguiente|Resto]) :-
    ruta(Origen, Siguiente, _, _, _, _, si),
    verificar_disponibilidad_completa([Siguiente|Resto]).
```

Valida que todos los segmentos de una ruta multi-ciudad estén disponibles.

- **Caso base**: `verificar_disponibilidad_completa([_])` - Una sola ciudad siempre es válida (ya llegamos al destino).

- **Caso recursivo**: `verificar_disponibilidad_completa([Origen, Siguiente|Resto])`
  - Verifica que exista una ruta disponible (`si`) entre `Origen` y `Siguiente`.
  - Recursivamente verifica el resto del camino usando `Siguiente` como nuevo origen.
  - Solo tiene éxito si **todos** los segmentos están disponibles.

Esta validación es crítica para evitar sugerir rutas donde algún tramo no esté operativo.

---

#### recommend_route

```
recommend_route(Origen, Destino, by_cost, Camino, Costo, Mensaje) :-
    (   cheapest_route(Origen, Destino, CaminoBarato, CostoBarato),
        verificar_disponibilidad_completa(CaminoBarato) ->
        Camino = CaminoBarato,
        Costo = CostoBarato,
        Mensaje = 'Ruta mas economica disponible'
    ;   next_best_by_cost(Origen, Destino, Camino, Costo),
        verificar_disponibilidad_completa(Camino),
        Mensaje = 'Ruta economica alternativa (la optima no esta disponible)'
    ).

recommend_route(Origen, Destino, by_time, Camino, Tiempo, Mensaje) :-
    (   fastest_route(Origen, Destino, CaminoRapido, TiempoRapido),
        verificar_disponibilidad_completa(CaminoRapido) ->
        Camino = CaminoRapido,
        Tiempo = TiempoRapido,
        Mensaje = 'Ruta mas rapida disponible'
    ;   next_best_by_time(Origen, Destino, Camino, Tiempo),
        verificar_disponibilidad_completa(Camino),
        Mensaje = 'Ruta rapida alternativa (la optima no esta disponible)'
    ).
```

Sistema inteligente de recomendación que proporciona la mejor ruta disponible con respaldo automático.

**Para optimización por costo** (`by_cost`):
- **Intento primario**: 
  - Busca la ruta más económica con `cheapest_route`.
  - Verifica disponibilidad completa con `verificar_disponibilidad_completa`.
  - Si está disponible, retorna con mensaje `'Ruta mas economica disponible'`.

- **Respaldo automático** (si la óptima no está disponible):
  - Busca la segunda mejor opción con `next_best_by_cost`.
  - Verifica su disponibilidad completa.
  - Retorna con mensaje explicativo de que es una alternativa.

**Para optimización por tiempo** (`by_time`):
- Funciona idénticamente pero usando `fastest_route` y `next_best_by_time`.
- Prioriza velocidad sobre costo.

Esta regla garantiza siempre ofrecer una solución viable al usuario, incluso cuando las rutas óptimas no estén operativas.

---

#### clasificar_rutas

```
clasificar_rutas([], []).
clasificar_rutas([[Camino, Costo, Tiempo]|Resto], [clasificada(Tipo, Camino, Costo, Tiempo)|RestoClasificado]) :-
    length(Camino, Longitud),
    (Longitud =:= 2 -> Tipo = directa ; Tipo = con_paradas),
    clasificar_rutas(Resto, RestoClasificado).
```

Clasifica rutas como directas o con paradas intermedias.

- **Caso base**: `clasificar_rutas([], [])` - Lista vacía resulta en lista vacía.

- **Caso recursivo**: `clasificar_rutas([[Camino, Costo, Tiempo]|Resto], ...)`
  - Calcula la longitud del camino con `length(Camino, Longitud)`.
  - **Clasificación**:
    - Si `Longitud =:= 2` → es una ruta `directa` (solo origen y destino).
    - Si `Longitud > 2` → es una ruta `con_paradas` (tiene ciudades intermedias).
  - Crea un término estructurado: `clasificada(Tipo, Camino, Costo, Tiempo)`.
  - Recursivamente clasifica el resto de las rutas.

Esta función facilita al usuario identificar rápidamente qué rutas son directas y cuáles requieren escalas.

---

#### list_alternatives

```
list_alternatives(Origen, Destino, Alternativas) :-
    gather_routes(Origen, Destino, TodasRutas),
    clasificar_rutas(TodasRutas, Alternativas).
```

Lista todas las alternativas disponibles entre dos ciudades, clasificadas por tipo.

- `gather_routes(Origen, Destino, TodasRutas)`: Recolecta todas las rutas posibles.
- `clasificar_rutas(TodasRutas, Alternativas)`: Clasifica cada ruta como directa o con paradas.

Retorna una lista estructurada donde cada elemento tiene la forma:
```
clasificada(Tipo, Camino, Costo, Tiempo)
```

Permite al usuario visualizar todas las opciones disponibles de un vistazo, organizadas por tipo de ruta.


### Resultados Pruebas por Items
#### 1. Display direct and stopover routes


**Consulta:**
```
?- direct_route(bogota, medellin, Medio, Salida, Llegada, Costo).
```

**Resultados:**
```
Medio = avion,
Salida = 8,
Llegada = 9,
Costo = 100 ;

Medio = bus,
Salida = 6,
Llegada = 16,
Costo = 40.
```

**Análisis:**
-  El sistema encontró correctamente las **2 rutas directas** disponibles entre Bogotá y Medellín.
-  La primera opción es por avión (8:00-9:00, $100 USD).
-  La segunda opción es por bus (6:00-16:00, $40 USD).
-  Ambas rutas están marcadas como disponibles en la base de conocimiento.

---

#### 2. Calculate optimal routes based on time or cost

**Consulta:**
```
?- cheapest_route(bogota, medellin, Camino, CostoMin).
```

**Resultados:**
```
Camino = [bogota, medellin],
CostoMin = 40.
```

**Análisis:**
- El sistema identificó correctamente la ruta **más económica**: bus directo por $40 USD.
- Descartó la opción de avión ($100) y rutas con escalas más costosas.
- El algoritmo `min_by_cost` funcionó correctamente comparando todas las rutas recolectadas.

---

## 3. Filter routes based on time availability

Filtrar Rutas por Horario de Mañana (6:00 - 10:00)

**Consulta:**
```
?- available_between(6, 10, bogota, medellin, Medio, Salida, Llegada, Costo).
```

**Resultados:**
```
Medio = avion,
Salida = 8,
Llegada = 9,
Costo = 100 ;

Medio = bus,
Salida = 6,
Llegada = 16,
Costo = 40.
```

**Análisis:**
- El sistema filtró correctamente las rutas que **salen entre las 6:00 y 10:00**.
- Encontró 2 opciones:
  - Avión saliendo a las 8:00 
  - Bus saliendo a las 6:00 
- Ambas cumplen con la condición: `Salida >= 6` y `Salida =< 10`.

---

## 4. Suggest alternative routes if the main route is unavailable

Sugerencia Inteligente - Ruta Directa Disponible

**Consulta:**
```
?- suggest_route(bogota, medellin, Camino, Costo, Tiempo, Mensaje).
```

**Resultados:**
```
Camino = [bogota, medellin],
Costo = 100,
Tiempo = 1,
Mensaje = 'Ruta directa disponible' ;

Camino = [bogota, medellin],
Costo = 40,
Tiempo = 10,
Mensaje = 'Ruta directa disponible'.
```

**Análisis:**
- El sistema **prioriza rutas directas** cuando están disponibles.
- Mensaje claro: `'Ruta directa disponible'`.
- Proporciona ambas opciones directas (avión y bus) para que el usuario elija.
- No sugiere rutas con escalas innecesarias cuando hay opciones directas.


---


### Problemas encontrados en el desarrollo

#### Problema 1: Duplicación de Rutas en gather_routes

**Descripción del problema:**
Durante las pruebas iniciales, `gather_routes` generaba rutas duplicadas debido al backtracking de Prolog. Una misma ruta podía aparecer múltiples veces con diferentes instanciaciones de variables.

**Causa raíz:**
El backtracking natural de Prolog hace que `route/5` genere múltiples soluciones para el mismo camino, especialmente cuando existen varias formas de llegar al mismo resultado.

**Solución implementada:**
Se implementó una verificación de duplicados en `gather_routes_aux/4`:
```
gather_routes_aux(Origen, Destino, Parcial, ListaFinal) :-
    route(Origen, Destino, Camino, Costo, Tiempo),
    \+ pertenece([Camino, Costo, Tiempo], Parcial),
    gather_routes_aux(Origen, Destino, [[Camino, Costo, Tiempo]|Parcial], ListaFinal).
```

La línea `\+ pertenece([Camino, Costo, Tiempo], Parcial)` asegura que cada combinación única de camino, costo y tiempo solo se agregue una vez a la lista. Esto previene duplicados y mejora la eficiencia del sistema.

---

#### Problema 2: Orden Inverso en la Construcción de Caminos

**Descripción del problema:**
La función `travel` construía los caminos en orden inverso (destino→origen) en lugar del orden esperado (origen→destino), haciendo difícil interpretar los resultados.

**Causa raíz:**
Por diseño recursivo, `travel` va agregando ciudades a medida que avanza, pero el proceso de recursión causa que la lista se construya desde el destino hacia el origen.

**Solución implementada:**
Se utilizó `reverse/2` en la función `route` para invertir el camino antes de retornarlo:
```
route(Origen, Destino, Camino, TotalCosto, TotalTiempo) :-
    travel(Origen, Destino, [Origen], CaminoInvertido, 0, 0, TotalCosto, TotalTiempo),
    reverse(CaminoInvertido, Camino).
```

Se aprovecho una función built-in de Prolog para corregir el orden sin modificar la lógica recursiva central.

---

### Conclusiones


#### 1. La Programación Lógica Requiere un Cambio de Mentalidad

El desarrollo de este sistema nos enseñó que programar en Prolog requiere pensar en términos de relaciones y unificación en lugar de secuencias de instrucciones. A diferencia de la programación imperativa donde se especifica cómo hacer algo paso a paso, en Prolog se define qué características debe cumplir una solución y el motor de inferencia encuentra los resultados.

Este cambio de mentalidad nos fue evidente al implementar funciones como `travel`, donde la recursión y el backtracking automático reemplazan estructuras iterativas tradicionales. 

---

#### 2. La Validación de Datos es Fundamental en Sistemas de Recomendación

Uno de los aprendizajes más importantes que tuvimos fue la necesidad crítica de validar la disponibilidad completa de las rutas antes de recomendarlas a los usuarios. Inicialmente, las funciones de optimización encontraban rutas "óptimas" que incluían segmentos no disponibles, lo cual habría resultado en recomendaciones inútiles en un sistema real.


La implementación de `verificar_disponibilidad_completa` y su integración con `recommend_route` transformó el sistema de uno que simplemente encuentra caminos teóricos a uno que proporciona recomendaciones prácticas. Este patrón de validación antes de recomendación es aplicable a cualquier sistema de planificación: no basta con encontrar la solución óptima, debe ser una solución viable.

---

#### 3. El Diseño Modular Facilita la Extensibilidad y el Mantenimiento

La arquitectura del sistema, construida con funciones pequeñas y especificas, demostró ser bastante beneficiosa. Funciones auxiliares como `pertenece`, `remove_route`, `min_by_cost`, y `clasificar_rutas` son reutilizables y fáciles de probar independientemente.

Este enfoque modular nos permitió enocntrar problemas más eficientemente y realizar extensiones sin tener que reemplazar gran parte del codigo ya realizado.
