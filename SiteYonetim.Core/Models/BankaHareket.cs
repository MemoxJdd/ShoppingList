using SiteYonetim.Common;

namespace SiteYonetim.Core.Models;

public class BankaHareket : BaseEntity
{
    public int BankaHesapId { get; set; }
    public TransactionType Type { get; set; }
    public decimal Tutar { get; set; }
    public decimal OncekiBakiye { get; set; }
    public decimal SonrakiBakiye { get; set; }
    public string Aciklama { get; set; } = string.Empty;
    public DateTime HareketTarihi { get; set; }
    public string? ReferansNo { get; set; }

    // Navigation properties
    public virtual BankaHesap BankaHesap { get; set; } = null!;
}