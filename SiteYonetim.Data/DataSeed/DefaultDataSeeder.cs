using Microsoft.EntityFrameworkCore;
using SiteYonetim.Core.Models;
using SiteYonetim.Core.Services;
using SiteYonetim.Common;
using SiteYonetim.Data.Context;

namespace SiteYonetim.Data.DataSeed;

public class DefaultDataSeeder
{
    private readonly SiteYonetimDbContext _context;
    private readonly IAuthenticationService _authenticationService;

    public DefaultDataSeeder(SiteYonetimDbContext context, IAuthenticationService authenticationService)
    {
        _context = context;
        _authenticationService = authenticationService;
    }

    public async Task SeedAsync()
    {
        await _context.Database.EnsureCreatedAsync();

        // Check if admin user exists
        var adminExists = await _context.Users.AnyAsync(u => u.Username == "admin");
        if (!adminExists)
        {
            await SeedDefaultAdmin();
        }

        // Seed default bank account
        var bankAccountExists = await _context.BankaHesaplari.AnyAsync();
        if (!bankAccountExists)
        {
            await SeedDefaultBankAccount();
        }

        // Seed default aidat definitions
        var aidatExists = await _context.AidatTanimlari.AnyAsync();
        if (!aidatExists)
        {
            await SeedDefaultAidatTanimlari();
        }
    }

    private async Task SeedDefaultAdmin()
    {
        var admin = new User
        {
            FirstName = "Sistem",
            LastName = "Yöneticisi",
            Username = "admin",
            Password = _authenticationService.HashPassword("123456"),
            Email = "admin@siteyonetim.com",
            Phone = "0555 123 4567",
            Role = UserRole.Yonetici,
            IsActive = true,
            CreatedAt = DateTime.Now
        };

        await _context.Users.AddAsync(admin);
        await _context.SaveChangesAsync();

        // Add manager history record
        var yoneticiGecmis = new YoneticiGecmis
        {
            UserId = admin.Id,
            BaslangicTarihi = DateTime.Now,
            Aciklama = "Sistem kurulum yöneticisi",
            IsActive = true,
            CreatedAt = DateTime.Now
        };

        await _context.YoneticiGecmisleri.AddAsync(yoneticiGecmis);
        await _context.SaveChangesAsync();

        Console.WriteLine("Varsayılan yönetici oluşturuldu:");
        Console.WriteLine("Kullanıcı Adı: admin");
        Console.WriteLine("Parola: 123456");
    }

    private async Task SeedDefaultBankAccount()
    {
        var bankAccount = new BankaHesap
        {
            Ad = "Ana Hesap",
            BankaAdi = "Ziraat Bankası",
            HesapNo = "12345678",
            IBAN = "TR123456789012345678901234",
            Currency = Currency.TRY,
            Bakiye = 0,
            IsActive = true,
            CreatedAt = DateTime.Now
        };

        await _context.BankaHesaplari.AddAsync(bankAccount);
        await _context.SaveChangesAsync();
    }

    private async Task SeedDefaultAidatTanimlari()
    {
        var aidatTanimlari = new List<AidatTanim>
        {
            new AidatTanim
            {
                Ad = "Aylık Aidat - 1+1 Daire",
                Aciklama = "1+1 daireler için aylık aidat",
                Tutar = 300,
                DaireType = DaireType.Bir,
                BaslangicTarihi = new DateTime(DateTime.Now.Year, 1, 1),
                BitisTarihi = new DateTime(DateTime.Now.Year, 12, 31),
                IsActive = true,
                CreatedAt = DateTime.Now
            },
            new AidatTanim
            {
                Ad = "Aylık Aidat - 2+1 Daire",
                Aciklama = "2+1 daireler için aylık aidat",
                Tutar = 400,
                DaireType = DaireType.Iki,
                BaslangicTarihi = new DateTime(DateTime.Now.Year, 1, 1),
                BitisTarihi = new DateTime(DateTime.Now.Year, 12, 31),
                IsActive = true,
                CreatedAt = DateTime.Now
            },
            new AidatTanim
            {
                Ad = "Aylık Aidat - 3+1 Daire",
                Aciklama = "3+1 daireler için aylık aidat",
                Tutar = 500,
                DaireType = DaireType.Uc,
                BaslangicTarihi = new DateTime(DateTime.Now.Year, 1, 1),
                BitisTarihi = new DateTime(DateTime.Now.Year, 12, 31),
                IsActive = true,
                CreatedAt = DateTime.Now
            }
        };

        await _context.AidatTanimlari.AddRangeAsync(aidatTanimlari);
        await _context.SaveChangesAsync();
    }
}