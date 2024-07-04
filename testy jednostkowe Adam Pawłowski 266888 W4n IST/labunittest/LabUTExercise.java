package labunittest;

public class LabUTExercise {
    /*
    1. Dla przygotowanych klas związanych z symulatorem aplikacji pogodowej przygotuj odpowiednie testy jednostkowe:
      - Przetestuj klasę URL Parser w taki sposób, aby pokryć wszystkie możliwe ścieżki. Czy kod zawiera błędy? Czy wszystkie
      możliwości zostały wzięte pod uwagę?
      - Przetestuj klasę Weather Downloader w taki sposób, aby uzyskać pełne pokrycie metod getByCityName oraz getByCoordinates.
      Jakie problemy musiałeś rozwiązać aby to było możliwe? Jakie błędy popełnił twórca tej klasy?

      Jakie problemy musiałeś rozwiązać aby to było możliwe?
      Trzeba porównać typ zwracanego obiektu zamiast jego konkretne pola.
      Jakie błędy popełnił twórca tej klasy?
      Nie ma możliwości mockowania metod, które zwracają w domyśle losowe wartości więc nie ma możliwości porównania pól zwracanego obiektu.
      Zad. * Jak można zmienić klasę WeatherDownloader, aby dało się przetestować pola obiektu WeatherObject?
      Można na przykład zmienić dostęp metod getTemperature(), getPressure(), getHumidity() i getWeather() na public, aby można było je zmockować w testach.

      Uwaga:
      Przy testowaniu ścieżki, która zwraca poprawny WeatherObject, sprawdź tylko czy zwrócony został poprawny typ, nie musisz
      weryfikować pól zwracanego obiektu typu WeatherObject.
      Zad. * Jak można zmienić klasę WeatherDownloader, aby dało się przetestować pola obiektu WeatherObject?

     */
}
