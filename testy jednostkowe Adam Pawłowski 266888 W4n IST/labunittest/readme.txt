Rozwiązanie zad 1. - plik TestUrlParser.java
Rozwiązanie zad 2. - plik TestWeatherDownloader.java

Jakie problemy musiałeś rozwiązać aby to było możliwe?
Aby sprawdzić wynik testu, trzeba porównać typ zwracanego obiektu zamiast jego konkretne pola. 

Jakie błędy popełnił twórca tej klasy?
Nie ma możliwości mockowania metod, które zwracają w domyśle losowe wartości więc nie ma możliwości porównania pól zwracanego obiektu.

Zad. * Jak można zmienić klasę WeatherDownloader, aby dało się przetestować pola obiektu WeatherObject?
Można na przykład zmienić dostęp metod getTemperature(), getPressure(), getHumidity() i getWeather() na public, aby można było je zmockować w testach.