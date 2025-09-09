using SiteYonetim.Common;

namespace SiteYonetim.Core.Models;

public class AidatTahakkuk : BaseEntity
{
    public int UserId { get; set; }
    public int AidatTanimId { get; set; }
    public int DonemYil { get; set; }
    public int DonemAy { get; set; }
    public decimal Tutar { get; set; }
    public decimal OdenenTutar { get; set; } = 0;
    public PaymentStatus Status { get; set; } = PaymentStatus.Odenmedi;
    public DateTime VadeTarihi { get; set; }

    // Navigation properties
    public virtual User User { get; set; } = null!;
    public virtual AidatTanim AidatTanim { get; set; } = null!;
    public virtual ICollection<AidatOdeme> AidatOdemeleri { get; set; } = new List<AidatOdeme>();

    public decimal KalanBorc => Tutar - OdenenTutar;
    public bool IsFullyPaid => OdenenTutar >= Tutar;
}