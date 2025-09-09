# Site Yönetimi Programı

Bu proje, apartman ve site yönetimi için geliştirilmiş kapsamlı bir C# desktop uygulamasıdır.

## Proje Yapısı

```
SiteYonetim/
├── SiteYonetim.Core/          # Temel iş mantığı, modeller, servisler
│   ├── Models/                # Veri modelleri (User, Daire, Aidat, vb.)
│   ├── Services/              # İş mantığı servisleri (Authentication, Aidat, vb.)
│   └── Utils/                 # Yardımcı sınıflar
├── SiteYonetim.Data/          # Veri erişim katmanı (SQLite, repository, migrasyon)
│   ├── Context/               # Entity Framework DbContext
│   ├── Repositories/          # Repository pattern implementasyonu
│   └── DataSeed/              # Varsayılan veri oluşturma
├── SiteYonetim.UI/            # Console tabanlı kullanıcı arayüzü
│   ├── Pages/                 # UI sayfaları (Login, MainMenu, vb.)
│   ├── Controls/              # Özel UI kontrolleri
│   └── Resources/             # Kaynaklar
├── SiteYonetim.Common/        # Ortak tipler, yardımcılar, extensionlar, enums
│   └── Extensions/            # Extension metodları
└── README.md
```

## Özellikler

### Kullanıcı Rolleri
- **Yönetici**: Tüm sistem işlemlerine erişim
- **Yardımcı**: Tahsilat, gider ve kısıtlı raporlama
- **Denetleyici**: Sadece görüntüleme ve raporlama
- **Sakin**: Kendi aidat durumu ve ödeme işlemleri

### Ana Modüller

1. **Kullanıcı ve Daire Yönetimi**
   - Sakin kaydı ve düzenleme
   - Daire tanımları ve atamaları
   - Rol bazlı yetkilendirme

2. **Aidat Sistemi**
   - Aidat tanımları (daire tipine göre)
   - Otomatik tahakkuk oluşturma
   - Ödeme takibi ve raporlama

3. **Finansal Yönetim**
   - Banka hesap yönetimi (çoklu para birimi)
   - Gelir/gider takibi
   - Otomatik bakiye güncelleme

4. **Raporlama**
   - Rol bazlı rapor erişimi
   - Dönemsel gelir/gider raporları
   - Borç durumu raporları

5. **Yönetici Geçmişi**
   - Yönetici değişiklik takibi
   - Görev süreleri kayıtları

6. **Yedekleme/Geri Yükleme**
   - Veritabanı yedeği alma
   - Sistem geri yükleme

## Teknoloji Stack

- **Backend**: .NET 8, C#
- **Veritabanı**: SQLite, Entity Framework Core
- **UI**: Console Application (Konsol tabanlı)
- **Dependency Injection**: Microsoft.Extensions.DependencyInjection
- **Hosting**: Microsoft.Extensions.Hosting

## Kurulum ve Çalıştırma

### Gereksinimler
- .NET 8 SDK
- SQLite (otomatik kurulur)

### Kurulum Adımları

1. Projeyi klonlayın:
```bash
git clone [repository-url]
cd ShoppingList
```

2. Bağımlılıkları yükleyin:
```bash
dotnet restore
```

3. Veritabanını oluşturun ve varsayılan verileri yükleyin:
```bash
dotnet run --project SiteYonetim.UI
```

### İlk Çalıştırma

Uygulama ilk çalıştırıldığında:
- SQLite veritabanı otomatik oluşturulur
- Varsayılan yönetici hesabı oluşturulur:
  - **Kullanıcı Adı**: admin
  - **Parola**: 123456

## Veritabanı Modelleri

### Temel Entitiler

- **User**: Kullanıcı bilgileri ve rolleri
- **Daire**: Daire bilgileri ve sakin atamaları
- **AidatTanim**: Aidat tanımları ve tutarları
- **AidatTahakkuk**: Aylık aidat borçları
- **AidatOdeme**: Aidat ödemeleri
- **BankaHesap**: Banka hesap bilgileri
- **BankaHareket**: Hesap hareketleri
- **Gider**: Gider kayıtları
- **YoneticiGecmis**: Yönetici geçmişi

### İlişkiler

- Bir kullanıcının birden fazla dairesi olabilir
- Her aidat tahakkuku bir kullanıcı ve aidat tanımına bağlıdır
- Ödemeler banka hesapları ile ilişkilendirilir
- Soft delete pattern kullanılır

## Geliştirme

### Yeni Özellik Ekleme

1. Model değişiklikleri `SiteYonetim.Core/Models/` içinde
2. Veri erişim katmanı `SiteYonetim.Data/Repositories/` içinde
3. İş mantığı `SiteYonetim.Core/Services/` içinde
4. UI `SiteYonetim.UI/Pages/` içinde

### Veritabanı Migrasyonları

```bash
# Migration oluştur
dotnet ef migrations add MigrationName --project SiteYonetim.Data --startup-project SiteYonetim.UI

# Veritabanını güncelle
dotnet ef database update --project SiteYonetim.Data --startup-project SiteYonetim.UI
```

## Güvenlik

- Parolalar SHA256 ile hashlenir (production'da BCrypt önerilir)
- Rol bazlı erişim kontrolü
- Soft delete ile veri güvenliği
- SQL injection koruması (Entity Framework)

## Katkıda Bulunma

1. Fork edin
2. Feature branch oluşturun (`git checkout -b feature/AmazingFeature`)
3. Commit edin (`git commit -m 'Add some AmazingFeature'`)
4. Branch'e push edin (`git push origin feature/AmazingFeature`)
5. Pull Request açın

## Lisans

Bu proje [MIT License](LICENSE) altında lisanslanmıştır.

## İletişim

Sorularınız için: [email@example.com]