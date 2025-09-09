using SiteYonetim.Core.Models;
using SiteYonetim.Common;

namespace SiteYonetim.Core.Services;

public interface IUserRepository
{
    Task<User?> GetByIdAsync(int id);
    Task<IEnumerable<User>> GetAllAsync();
    Task<User> AddAsync(User entity);
    Task UpdateAsync(User entity);
    Task DeleteAsync(int id);
    Task<User?> GetByUsernameAsync(string username);
    Task<User?> ValidateUserAsync(string username, string password);
    Task<IEnumerable<User>> GetUsersByRoleAsync(UserRole role);
    Task<bool> IsUsernameExistsAsync(string username);
    Task<User?> GetCurrentManagerAsync();
}