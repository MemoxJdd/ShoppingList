using SiteYonetim.Core.Models;

namespace SiteYonetim.Core.Services;

public interface IAuthenticationService
{
    Task<User?> LoginAsync(string username, string password);
    Task<bool> ChangePasswordAsync(int userId, string oldPassword, string newPassword);
    string HashPassword(string password);
    bool VerifyPassword(string password, string hashedPassword);
}