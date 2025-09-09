using SiteYonetim.Core.Models;

namespace SiteYonetim.Core.Services;

public interface IAidatService
{
    Task<AidatTahakkuk> CreateAidatTahakkukAsync(int userId, int aidatTanimId, int donemYil, int donemAy);
    Task<bool> ProcessAidatPaymentAsync(int aidatTahakkukId, decimal tutar, int? bankaHesapId, string aciklama);
    Task<IEnumerable<AidatTahakkuk>> GetUnpaidAidatlarAsync(int userId);
    Task<IEnumerable<AidatTahakkuk>> GetAidatlarByPeriodAsync(int yil, int ay);
    Task GenerateMonthlyAidatTahakkukAsync(int yil, int ay);
}