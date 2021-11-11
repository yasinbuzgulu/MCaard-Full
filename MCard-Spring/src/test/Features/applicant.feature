Feature: Başvuran CRUD işlemleri test özellikleri

  Background:
    Given "U-1" kullanıcısı kayıtlı iken
    And "U-1" kullanıcısı ile giriş yapılı iken


  Scenario: Bir başvuran başarıyla kaydedilmelidir.
    Given Başvuranın tüm bilgileri var iken
    When  Bu bilgiler post edildiğinde
    Then Başvuran başarıyla kaydedilir

  Scenario: Tüm kayıtlı başvuranlar başarıyla listelenmelidir.
    When Tüm başvuranlar için Get request'i atıldığında
    Then Tüm başvuranlar başarıyla listelenir

  Scenario: Başvuran başarıyla silinmeldir.
    Given "P-1" isminde bir başvuran var iken
    When "P-1" başvurana silme isteği atıldığında
    Then Başvuran başarıyla silinir.

  Scenario: Başvuran başarıyla güncellenmelidir.
    Given "P-1" isminde bir başvuran var iken
    When "P-1" başvuranına güncellemeler yapıldığında
    Then Başvuran başarı ile güncellenir

  Scenario: Başvuranın ismi boşken varken hata vermelidir .
    Given Başvuranın isim bilgisi boş olarak girilmiş olmalı
    When  Bu bilgiler post edildiğinde
    Then  Hata verilir

  Scenario: Başvuranın soyismi boş olduğunda hata verilmelidir.
    Given "P-1" başvuranı var iken
    When "P-1" soyismi boş olarak post edildiğinde
    Then Hata verilir

  Scenario: Başvuranın doğum günü  bilgisi boş olduğunda hata verilmelidir.
    Given "P-1" başvuranı var iken
    When "P-1" doğum günü boş olarak post edildiğinde
    Then Hata verilir

  Scenario: Başvuranın tc kimlik numarası  bilgisi boş olduğunda hata verilmelidir.
    Given "P-1" başvuranı var iken
    When "P-1" tc kimlik numarası boş olarak post edildiğinde
    Then Hata verilir

  Scenario: Başvuranın tc kimlik numarası  bilgisi gerçek olmadığında hata verilmelidir.
    Given "P-1" başvuranı var iken
    When "P-1" (15869658208)tc kimlik numarası gerçek dışı olarak post edildiğinde
    Then Hata verilir

  Scenario: Başvuranın tc kimlik numarası  bilgisi gerçek olduğunda başarıyla kaydetmelidir.
    Given "P-1" başvuranı var iken
    When "P-1" (15869658200)tc kimlik numarası gerçek olarak post edildiğinde
    Then Başvuran başarıyla kaydedilir

  Scenario: Başvuranın tc kimlik numarası  11 hane olmadığında hata verilmelidir.
    Given "P-1" başvuranı var iken
    When "P-1" (158696582)tc kimlik numarası dokuz hane olarak post edildiğinde
    Then Hata verilir

  Scenario: Başvuranın yaş grubu boş olduğunda hata verilmelidir.
    Given "P-1" başvuranı var iken
    When "P-1" başvuranı yaş grubu boş olarak post edildiğinde
    Then Hata verilir

  Scenario: Başvuranın eğitim durumu boş olduğunda hata verilmelidir.
    Given "P-1" başvuranı var iken
    When "P-1" başvuranı eğitim durumu boş olarak post edildiğinde
    Then Hata verilir

  Scenario: Başvuranın doğum günü  bilgisi formatı yanlış olduğunda hata verilmelidir.
    Given "P-1" başvuranı var iken
    When "P-1" doğum günü yanlış formatta post edildiğinde
    Then Hata verilir
