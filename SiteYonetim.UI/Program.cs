using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using SiteYonetim.Core.Services;
using SiteYonetim.Data.Context;
using SiteYonetim.Data.Repositories;
using SiteYonetim.UI.Pages;

namespace SiteYonetim.UI;

class Program
{
    static async Task Main(string[] args)
    {
        var host = CreateHostBuilder(args).Build();
        
        // Ensure database is created and seeded
        using (var scope = host.Services.CreateScope())
        {
            var context = scope.ServiceProvider.GetRequiredService<SiteYonetimDbContext>();
            var authService = scope.ServiceProvider.GetRequiredService<IAuthenticationService>();
            
            await context.Database.EnsureCreatedAsync();
            
            var seeder = new SiteYonetim.Data.DataSeed.DefaultDataSeeder(context, authService);
            await seeder.SeedAsync();
        }

        var mainMenu = host.Services.GetRequiredService<MainMenu>();
        await mainMenu.ShowAsync();
    }

    static IHostBuilder CreateHostBuilder(string[] args) =>
        Host.CreateDefaultBuilder(args)
            .ConfigureServices((context, services) =>
            {
                // Database
                services.AddDbContext<SiteYonetimDbContext>(options =>
                    options.UseSqlite("Data Source=SiteYonetim.db"));

                // Repositories
                services.AddScoped<Core.Services.IUserRepository, UserRepository>();
                services.AddScoped<IUserRepositoryData, UserRepository>();
                services.AddScoped(typeof(IRepository<>), typeof(Repository<>));

                // Services
                services.AddScoped<IAuthenticationService, AuthenticationService>();

                // UI
                services.AddScoped<MainMenu>();
                services.AddScoped<LoginPage>();
            });
}
