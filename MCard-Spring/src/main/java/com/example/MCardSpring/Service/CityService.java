package com.example.MCardSpring.Service;

import com.example.MCardSpring.Exception.CityNotFoundException;
import com.example.MCardSpring.MainModel.City;
import com.example.MCardSpring.Repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * CRUD işlemlerini yapan servis sınıfı
 */
@Service
@Transactional
public class CityService {
    /**
     * Service içinde kullanılacak city repository için instance oluşturmadan constructor ile çağrılır
     */
    CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Yeni şehir oluşturma metodu
     *
     * @param city: yeni şehir nesnesi
     * @return: depoya kayıt döner
     */
    public City createCity(City city) {
        return cityRepository.save(city);
    }

    /**
     * Şehir silme metodu
     *
     * @param id: silinecek şehir id si
     */
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    /**
     * Belirli şehir alma metodu
     *
     * @param id: istenen şehrin id si
     * @return: 'id' li şehri döner
     */
    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    /**
     * Şehir güncelleme metodu
     *
     * @param newCity: güncel şehir verisi
     * @param id:      güncellencek şehrin id si
     */
    public void updateCity(City newCity, Long id) {
        City city = cityRepository.findById(id).get();
        city.setCityName(newCity.getCityName());
        cityRepository.save(city);
    }

    /**
     * Tüm şehirleri listeleme metodu
     *
     * @return: tüm şehirleri döner
     */
    public List<City> listCities() {
        return new ArrayList<>(cityRepository.findAll());
    }

}
