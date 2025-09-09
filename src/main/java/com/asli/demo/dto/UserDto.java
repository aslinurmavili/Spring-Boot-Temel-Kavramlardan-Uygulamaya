package com.asli.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    // getter & setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}




// Manuel Mapping:
// - Entity'den DTO'ya veya DTO'dan Entity'ye dönüşümleri elle yazarız.
// - Her alan için getter ve setter kullanılır.
// - Küçük projelerde basit ve anlaşılırdır.
// - Ancak çok sayıda alan veya sık değişen yapılar olduğunda kod tekrarı ve hata riski artar.

// ModelMapper:
// - Dönüşümler otomatik olarak yapılır, kütüphane alan isimlerini eşleştirir.
// - Karmaşık veya iç içe geçmiş nesneler için bile kolay kullanım sağlar.
// - Kod daha temiz ve bakımı daha kolaydır.
// - Konfigürasyon yapılabilir, özel eşlemeler tanımlanabilir.
// - Büyük projelerde manuel mapping'e göre zaman kazandırır ve hata olasılığını azaltır.


// ///////////////////////////////////////////////////////////////////////////////////////////////////////

// Manuel Mapping Metotları:
// convertToDTO()   -> Entity'den DTO'ya dönüşüm yapan metot
// convertToEntity() -> DTO'dan Entity'ye dönüşüm yapan metot

// ModelMapper Metotları:
// map(source, DestinationClass.class) -> Kaynak nesneyi hedef sınıfa otomatik olarak dönüştürür
// addMappings(PropertyMap)             -> Özelleştirilmiş alan eşlemeleri yapmak için kullanılır

// Diğer Yaklaşımlar:
// MapStruct -> Compile-time mapping sağlar, hızlı ve hatasız
// Dozer     -> Runtime mapping yapar, alanları otomatik eşler
// Builder/Setter kullanımı -> Küçük ve basit projelerde manuel dönüşümü kolaylaştırır

// ////////////////////////////////////////////////////////////////////////////////////////////////////////

// Validation
// @NotBlank → Boş bırakılamaz
// @Size(min=2, max=50) → Karakter uzunluğu sınırı
// @Email → Geçerli email formatı

// //////////////////////////////////////////////////////////////////////////////////////////////////////////