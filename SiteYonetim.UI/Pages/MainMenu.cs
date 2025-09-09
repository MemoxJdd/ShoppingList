using SiteYonetim.Core.Models;
using SiteYonetim.Common;

namespace SiteYonetim.UI.Pages;

public class MainMenu
{
    private readonly LoginPage _loginPage;
    private User? _currentUser;

    public MainMenu(LoginPage loginPage)
    {
        _loginPage = loginPage;
    }

    public async Task ShowAsync()
    {
        Console.Clear();
        ShowWelcome();

        while (true)
        {
            if (_currentUser == null)
            {
                _currentUser = await _loginPage.ShowAsync();
                if (_currentUser == null)
                {
                    Console.WriteLine("Çıkış yapılıyor...");
                    break;
                }
            }

            Console.Clear();
            ShowUserInfo();
            ShowMenuByRole();

            var choice = Console.ReadLine();
            await HandleMenuChoice(choice);
        }
    }

    private void ShowWelcome()
    {
        Console.WriteLine("═══════════════════════════════════════");
        Console.WriteLine("     SİTE YÖNETİM PROGRAMI v1.0");
        Console.WriteLine("═══════════════════════════════════════");
        Console.WriteLine();
    }

    private void ShowUserInfo()
    {
        if (_currentUser != null)
        {
            Console.WriteLine("═══════════════════════════════════════");
            Console.WriteLine($" Hoşgeldiniz: {_currentUser.FullName}");
            Console.WriteLine($" Rol: {GetRoleDisplayName(_currentUser.Role)}");
            Console.WriteLine($" Giriş: {DateTime.Now:dd.MM.yyyy HH:mm}");
            Console.WriteLine("═══════════════════════════════════════");
            Console.WriteLine();
        }
    }

    private void ShowMenuByRole()
    {
        if (_currentUser == null) return;

        Console.WriteLine("MENÜ:");
        Console.WriteLine("─────────────────────────────────────");

        switch (_currentUser.Role)
        {
            case UserRole.Yonetici:
                ShowManagerMenu();
                break;
            case UserRole.Yardimci:
                ShowAssistantMenu();
                break;
            case UserRole.Denetleyici:
                ShowAuditorMenu();
                break;
            case UserRole.Sakin:
                ShowResidentMenu();
                break;
        }

        Console.WriteLine();
        Console.WriteLine("0. Çıkış");
        Console.WriteLine();
        Console.Write("Seçiminiz: ");
    }

    private void ShowManagerMenu()
    {
        Console.WriteLine("1. Kullanıcı ve Daire Yönetimi");
        Console.WriteLine("2. Aidat Tanımları");
        Console.WriteLine("3. Aidat Tahakkuk");
        Console.WriteLine("4. Tahsilat İşlemleri");
        Console.WriteLine("5. Gider Yönetimi");
        Console.WriteLine("6. Banka Hesap Yönetimi");
        Console.WriteLine("7. Yönetici Geçmişi");
        Console.WriteLine("8. Raporlar");
        Console.WriteLine("9. Yedekleme ve Geri Yükleme");
    }

    private void ShowAssistantMenu()
    {
        Console.WriteLine("1. Tahsilat İşlemleri");
        Console.WriteLine("2. Gider Yönetimi");
        Console.WriteLine("3. Raporlar (Kısıtlı)");
    }

    private void ShowAuditorMenu()
    {
        Console.WriteLine("1. Raporlar");
        Console.WriteLine("2. Banka Hesap Görüntüleme");
        Console.WriteLine("3. Aidat Durumu Görüntüleme");
    }

    private void ShowResidentMenu()
    {
        Console.WriteLine("1. Aidat Borç Durumu");
        Console.WriteLine("2. Ödeme Geçmişi");
        Console.WriteLine("3. İletişim Bilgilerini Güncelle");
    }

    private async Task HandleMenuChoice(string? choice)
    {
        if (string.IsNullOrEmpty(choice))
            return;

        switch (choice)
        {
            case "0":
                Console.WriteLine("Çıkış yapılıyor...");
                Environment.Exit(0);
                break;
            case "1":
                await HandleFirstMenuOption();
                break;
            case "2":
                await HandleSecondMenuOption();
                break;
            default:
                Console.WriteLine("Geçersiz seçim! Herhangi bir tuşa basın...");
                Console.ReadKey();
                break;
        }
    }

    private async Task HandleFirstMenuOption()
    {
        if (_currentUser == null) return;

        switch (_currentUser.Role)
        {
            case UserRole.Yonetici:
                Console.WriteLine("Kullanıcı ve Daire Yönetimi - Yakında...");
                break;
            case UserRole.Yardimci:
                Console.WriteLine("Tahsilat İşlemleri - Yakında...");
                break;
            case UserRole.Denetleyici:
                Console.WriteLine("Raporlar - Yakında...");
                break;
            case UserRole.Sakin:
                Console.WriteLine("Aidat Borç Durumu - Yakında...");
                break;
        }
        
        Console.WriteLine("Herhangi bir tuşa basın...");
        Console.ReadKey();
    }

    private async Task HandleSecondMenuOption()
    {
        if (_currentUser == null) return;

        switch (_currentUser.Role)
        {
            case UserRole.Yonetici:
                Console.WriteLine("Aidat Tanımları - Yakında...");
                break;
            case UserRole.Yardimci:
                Console.WriteLine("Gider Yönetimi - Yakında...");
                break;
            case UserRole.Denetleyici:
                Console.WriteLine("Banka Hesap Görüntüleme - Yakında...");
                break;
            case UserRole.Sakin:
                Console.WriteLine("Ödeme Geçmişi - Yakında...");
                break;
        }
        
        Console.WriteLine("Herhangi bir tuşa basın...");
        Console.ReadKey();
    }

    private string GetRoleDisplayName(UserRole role)
    {
        return role switch
        {
            UserRole.Yonetici => "Yönetici",
            UserRole.Yardimci => "Yardımcı",
            UserRole.Denetleyici => "Denetleyici",
            UserRole.Sakin => "Sakin",
            _ => "Bilinmeyen"
        };
    }
}