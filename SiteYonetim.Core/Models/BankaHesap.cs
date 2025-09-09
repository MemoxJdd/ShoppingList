using SiteYonetim.Common;

namespace SiteYonetim.Core.Models;

public class BankaHesap : BaseEntity
{
    public string Ad { get; set; } = string.Empty;
    public string BankaAdi { get; set; } = string.Empty;
    public string HesapNo { get; set; } = string.Empty;
    public string IBAN { get; set; } = string.Empty;
    public Currency Currency { get; set; } = Currency.TRY;
    public decimal Bakiye { get; set; } = 0;
    public bool IsActive { get; set; } = true;

    // Navigation properties
    public virtual ICollection<BankaHareket> BankaHareketleri { get; set; } = new List<BankaHareket>();
    public virtual ICollection<AidatOdeme> AidatOdemeleri { get; set; } = new List<AidatOdeme>();
    public virtual ICollection<Gider> Giderler { get; set; } = new List<Gider>();
}