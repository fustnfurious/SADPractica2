 Xat broadcast client textual
L'aplicació consta d'un servidor multithread i un client amb interfície de consola. El client inicialitza dos threads: Reader i Writer.
El primer llegeix del teclat els missatges de l'usuari i els envia al servidor mitjançant el Output Stream que proporciona el Socket connectat al servidor.
El segon escolta constanment el Input Stream i desfà el parsing dels strings rebuts per presentar-los. Incorpora un mapa de colors per presentar els missatges rebuts de diferents users amb el seu corresponent color.

