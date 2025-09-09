using SiteYonetim.Core.Models;
using System.Security.Cryptography;
using System.Text;

namespace SiteYonetim.Core.Services;

public class AuthenticationService : IAuthenticationService
{
    private readonly IUserRepository _userRepository;

    public AuthenticationService(IUserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    public async Task<User?> LoginAsync(string username, string password)
    {
        var user = await _userRepository.GetByUsernameAsync(username);
        if (user != null && VerifyPassword(password, user.Password))
        {
            return user;
        }
        return null;
    }

    public async Task<bool> ChangePasswordAsync(int userId, string oldPassword, string newPassword)
    {
        var user = await _userRepository.GetByIdAsync(userId);
        if (user != null && VerifyPassword(oldPassword, user.Password))
        {
            user.Password = HashPassword(newPassword);
            await _userRepository.UpdateAsync(user);
            return true;
        }
        return false;
    }

    public string HashPassword(string password)
    {
        // Simple hash implementation - in production, use proper password hashing like BCrypt
        using var sha256 = SHA256.Create();
        var hashedBytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(password + "SiteYonetimSalt"));
        return Convert.ToBase64String(hashedBytes);
    }

    public bool VerifyPassword(string password, string hashedPassword)
    {
        return HashPassword(password) == hashedPassword;
    }
}