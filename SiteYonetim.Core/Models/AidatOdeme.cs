namespace SiteYonetim.Core.Models;

public class AidatOdeme : BaseEntity
{
    public int UserId { get; set; }
    public int AidatTahakkukId { get; set; }
    public int? BankaHesapId { get; set; }
    public decimal Tutar { get; set; }
    public DateTime OdemeTarihi { get; set; }
    public string Aciklama { get; set; } = string.Empty;
    public string? FisNo { get; set; }

    // Navigation properties
    public virtual User User { get; set; } = null!;
    public virtual AidatTahakkuk AidatTahakkuk { get; set; } = null!;
    public virtual BankaHesap? BankaHesap { get; set; }
}