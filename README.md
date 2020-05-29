# pracadyplomowa: wersja do weryfikacji/oceny:

Witryna sklepu muzycznego:
Strona powitalna -> grafika w tle, nazwa sklepu, przycisk wejdź,
Po wejściu do sklepu: layout 3 przyciski, domek -> link do strony powitalnej, oferta -> lista z produktami, koszyk
Oferta -> lista z produktami: zdjęcie, nazwa, cena dostępna ilość sztuk, przycisk „zobacz szczegóły”.
 
Widok szczegółów: durze zdjęcie + szczegółowy opis produktu, cena, ilość dostępnych sztuk, przycisk inkrementacji w celu dodania żądanej ilości sztuk, przycisk „dodaj do koszyka” i „powrót”. Danego wyświetlani (zdjęcia + szczegółowy opis) ładowane są z bazy danych. zdjęcia jako bit Array -> BLOB w bazie danych, opis sformatowany jako tekst w HTML’u. W przypadku gdyby produktu zabrakło w magazynie (ilość dostępnych sztuk = 0) szczegóły produktu nadal są widoczne, w miejscu przycisku „dodaj do koszyka” wyświetlana jest informacja że „produkt jest chwilowo niedostępny” -> nie da się wykonać akcji „dodaj do koszyka” 
Równie nie da się wykonać akcji dodaj do koszyka 0 sztuk,

Koszyk: widok miniatury zamawianego produktu, nazwa, cena jednostkowa, ilość zamawianych sztuk, cena całkowita za wszystkie zamawiane sztuki (jeżeli więcej niż jeden) produktu danej kategorii, przycisk „zmień” umożliwia modyfikowanie ilości zamawianych produktów danej kategorii, przycisk „usuń”, W dolnej części wyświetlana jest całkowita wartość koszyka oraz sumaryczna ilość zamawianych sztuk produktów. Przycisk „wyczyść koszyk” usuwa wszystkie produkty z koszyka, przycisk „kup”, usuwa wszystkie produkty z koszyka dodatkowo aktualizuje stan bazy danych, przenosi do listy produktów (która powinna być już zaktualizowana). W przypadku gdy koszyk jest pusty w miejscu przycisku kup wyświetlana jest informacja „Twój koszyk jest pusty”

Testy: 
Pokryte testami jednostkowymi zostały pokryte prawie wszystkie metody serwisów, nie pokryta została tylko metoda ProductService.fillDB() której zadaniem jest wypełnienie bazy danymi.
Metody w kontrolerze zostały pokryte wszystkie, tylko w tym przypadku to już chyba są testy integracyjne, wsumie wszystkich testów jest 31.
Coverage Breakdown
Package 
Class, % 
Method, % 
Line, % 
pl.edu.wszib.pracadyplomowa
100% (1/ 1) 	50% (1/ 2) 	33,3% (1/ 3) 
pl.edu.wszib.pracadyplomowa.controller
100% (1/ 1) 	100% (10/ 10) 	100% (37/ 37) 
pl.edu.wszib.pracadyplomowa.dto
100% (7/ 7) 	69,2% (36/ 52) 	75,6% (90/ 119) 
pl.edu.wszib.pracadyplomowa.model
100% (1/ 1) 	64,7% (11/ 17) 	70% (28/ 40) 
pl.edu.wszib.pracadyplomowa.service
100% (3/ 3) 	95,2% (20/ 21) 	96% (72/ 75) 

Baza danych:
W czasie realizacji projektu korzystałem z lokalnej bazy mariaDB, z którą głównie komunikowałem się za pomocą hiebrnet’a, do ładowania danych do bazy w serwisie ProductService metoda ProductService.fillDB() jest wywoływana w konstruktorze i w czasie uruchamiania serwera baza jest jednorazowo wypełniana danymi. Wspominam o tym ponieważ w trakcie pisania query sql’owego które miało generować bazę natrafiłem na trudności z ładowaniem plików binarnych (JPG, gif ..etc) do bazy. Znalazłem obejście robiąc zrzut z bazy ale dane binarne już tam istnieją załadowane prze hiberneta, dlatego na wszelki wypadek na potrzeby weryfikacji pracy dyplomowej zostawiam zakomentowaną metodę ProductService.fillDB() oraz dane do wypełnienia bazy:
\src\main\java\pl\edu\wszib\pracadyplomowa\service\ProductService.java
\src\main\java\database_data\*

Sql query w dialekcie mariaDB
\src\main\resources\database_shop_items.sql -> po załadowaniu danych do bazy i ustawienu spring.jpa.hibernate.ddl-auto=none  
aplikacja zachowuje się poprawnie, natomiast dwa testy które operują bezpośrednio na bazie przestają przechodzić z braku uprawnień (testy integracyjne)
\src\test\java\pl\edu\wszib\pracadyplomowa\dao\dbtest.java


# pracadyplomowa założenia wstępne
Wstępne założenia:
Witryna z produktami: zdjęcie, opis, cena, ilość dostępnych sztuk, przycisk dodania do koszyka, przycisk dodania kolejnych sztuk (opcja dodania kolejnych sztuk być może będzie zrealizowana za pomocą przycisku dodaj -> ponowne kliknięcie), być może przycisk „szczegóły” otwierający dodatkowy panel ze zdjęciem/ami i bardziej szczegółowym opisem.

Witryna koszyka: miniatura zdjęcia, nazwa produktu, ilość dodanych sztuk danego produktu cena jednostkowa/i lub sumaryczna za dany produkt, przycisk usuń produkt, przycisk kup, przycisk usuń wszystkie, pole ceny całkowitej, pole sumarycznej ilości zamawianych sztuk produktów.

Dodatkowe funkcjonalności: weryfikacja dostępności produktu w bazie danych,
Aktualizacja stanu magazynowego/ilości sztuk w bazie danych.

Technologie:
Springboot
Thymeleaf
UIkit
DB w moim przypadku MariaDB
i być może inne. 



