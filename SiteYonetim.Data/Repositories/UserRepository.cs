using Microsoft.EntityFrameworkCore;
using SiteYonetim.Core.Models;
using SiteYonetim.Common;
using SiteYonetim.Data.Context;

namespace SiteYonetim.Data.Repositories;

public class UserRepository : Repository<User>, IUserRepositoryData
{
    public UserRepository(SiteYonetimDbContext context) : base(context)
    {
    }

    public async Task<User?> GetByUsernameAsync(string username)
    {
        return await _dbSet
            .FirstOrDefaultAsync(u => u.Username == username && u.IsActive);
    }

    public async Task<User?> ValidateUserAsync(string username, string password)
    {
        // Note: In a real application, you should hash the password
        return await _dbSet
            .FirstOrDefaultAsync(u => u.Username == username && u.Password == password && u.IsActive);
    }

    public async Task<IEnumerable<User>> GetUsersByRoleAsync(UserRole role)
    {
        return await _dbSet
            .Where(u => u.Role == role && u.IsActive)
            .ToListAsync();
    }

    public async Task<bool> IsUsernameExistsAsync(string username)
    {
        return await _dbSet
            .AnyAsync(u => u.Username == username);
    }

    public async Task<User?> GetCurrentManagerAsync()
    {
        return await _dbSet
            .Include(u => u.YoneticiGecmisleri)
            .FirstOrDefaultAsync(u => u.Role == UserRole.Yonetici && 
                                     u.IsActive &&
                                     u.YoneticiGecmisleri.Any(yg => yg.IsActive && yg.BitisTarihi == null));
    }
}