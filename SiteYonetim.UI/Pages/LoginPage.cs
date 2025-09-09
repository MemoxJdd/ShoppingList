using SiteYonetim.Core.Models;
using SiteYonetim.Core.Services;

namespace SiteYonetim.UI.Pages;

public class LoginPage
{
    private readonly IAuthenticationService _authenticationService;

    public LoginPage(IAuthenticationService authenticationService)
    {
        _authenticationService = authenticationService;
    }

    public async Task<User?> ShowAsync()
    {
        Console.Clear();
        ShowLoginHeader();

        while (true)
        {
            Console.Write("Kullanıcı Adı: ");
            var username = Console.ReadLine();

            if (string.IsNullOrWhiteSpace(username))
            {
                Console.WriteLine("Kullanıcı adı boş olamaz!");
                continue;
            }

            Console.Write("Parola: ");
            var password = ReadPassword();
            Console.WriteLine();

            if (string.IsNullOrWhiteSpace(password))
            {
                Console.WriteLine("Parola boş olamaz!");
                continue;
            }

            try
            {
                var user = await _authenticationService.LoginAsync(username, password);
                if (user != null)
                {
                    Console.WriteLine($"\nGiriş başarılı! Hoşgeldiniz {user.FullName}");
                    Console.WriteLine("Devam etmek için herhangi bir tuşa basın...");
                    Console.ReadKey();
                    return user;
                }
                else
                {
                    Console.WriteLine("\nHatalı kullanıcı adı veya parola!");
                    Console.WriteLine("Tekrar denemek için herhangi bir tuşa basın veya çıkış için ESC...");
                    
                    var key = Console.ReadKey();
                    if (key.Key == ConsoleKey.Escape)
                    {
                        return null;
                    }
                    
                    Console.Clear();
                    ShowLoginHeader();
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"\nGiriş sırasında hata oluştu: {ex.Message}");
                Console.WriteLine("Tekrar denemek için herhangi bir tuşa basın...");
                Console.ReadKey();
                Console.Clear();
                ShowLoginHeader();
            }
        }
    }

    private void ShowLoginHeader()
    {
        Console.WriteLine("═══════════════════════════════════════");
        Console.WriteLine("           KULLANICI GİRİŞİ");
        Console.WriteLine("═══════════════════════════════════════");
        Console.WriteLine();
        Console.WriteLine("Çıkış için ESC tuşuna basın");
        Console.WriteLine();
    }

    private string ReadPassword()
    {
        string password = "";
        ConsoleKeyInfo key;

        do
        {
            key = Console.ReadKey(intercept: true);

            if (key.Key == ConsoleKey.Backspace && password.Length > 0)
            {
                password = password.Substring(0, password.Length - 1);
                Console.Write("\b \b");
            }
            else if (!char.IsControl(key.KeyChar))
            {
                password += key.KeyChar;
                Console.Write("*");
            }
        }
        while (key.Key != ConsoleKey.Enter);

        return password;
    }
}