Feature: Kart CRUD işlemleri test özellikleri

  Background:
    Given "U-1" kullanıcısı kart kaydı için kayıtlı iken
    And "U-1" kullanıcısı ile kart kaydı için giriş yapılı iken

  Scenario: Bir kart başarıyla kaydedilmelidir.
    Given "C-1" kartı var iken
    When  "C-1" kartı post edildiğinde
    Then Kart başarıyla kaydedilir

  Scenario: Tüm kayıtlı kartlar başarıyla listelenmelidir.
    When Tüm kartlar için Get request'i atıldığında
    Then Tüm kartlar başarıyla listelenir

  Scenario: Kart başarıyla silinmeldir.
    Given "C-1" kartı var iken
    When "C-1" kartına silme isteği atıldığında
    Then Kart başarıyla silinir.

  Scenario: Kart başarıyla güncellenmelidir.
    Given "C-1" kartı var iken
    When "C-1" kartında güncellemeler yapıldığında
    Then Kart başarı ile güncellenir

  Scenario: Kart kaydında hata vermelidir.
    Given "C-1" kartı var iken
    When "C-1" kartının başvuran'ı boş post edildiğinde
    Then Kart postu hata verir

  Scenario: Kart kaydında hata vermelidir.
    Given "C-1" kartı var iken
    When "C-1" kartının şehir-olanak'ı boş post edildiğinde
    Then Kart postu hata verir

  Scenario: Kart kaydında hata vermelidir.
    Given "C-1" kartı var iken
    When "C-1" kartının son kullanma tarihi boş post edildiğinde
    Then Kart postu hata verir

  Scenario: Kart kaydında hata vermelidir.
    Given "C-1" kartı var iken
    When "C-1" kartının fiyatı boş post edildiğinde
    Then Kart postu hata verir

  Scenario: Kart kaydında hata vermelidir.
    Given "C-1" kartı var iken
    When "C-1" kartının olanak tanımlanma yılı boş post edildiğinde
    Then Kart postu hata verir
