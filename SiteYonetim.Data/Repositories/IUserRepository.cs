using SiteYonetim.Core.Models;
using SiteYonetim.Core.Services;
using SiteYonetim.Common;

namespace SiteYonetim.Data.Repositories;

public interface IUserRepositoryData : IRepository<User>, IUserRepository
{
}