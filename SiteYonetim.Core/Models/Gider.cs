namespace SiteYonetim.Core.Models;

public class Gider : BaseEntity
{
    public string Ad { get; set; } = string.Empty;
    public string Kategori { get; set; } = string.Empty;
    public string Aciklama { get; set; } = string.Empty;
    public decimal Tutar { get; set; }
    public DateTime GiderTarihi { get; set; }
    public int? BankaHesapId { get; set; }
    public string? FisNo { get; set; }
    public int KaydedenUserId { get; set; }

    // Navigation properties
    public virtual BankaHesap? BankaHesap { get; set; }
    public virtual User KaydedenUser { get; set; } = null!;
}