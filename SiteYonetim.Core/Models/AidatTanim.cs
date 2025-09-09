using SiteYonetim.Common;

namespace SiteYonetim.Core.Models;

public class AidatTanim : BaseEntity
{
    public string Ad { get; set; } = string.Empty;
    public string Aciklama { get; set; } = string.Empty;
    public decimal Tutar { get; set; }
    public DaireType? DaireType { get; set; } // Null means applies to all types
    public UserRole? UserRole { get; set; } // Null means applies to all roles
    public DateTime BaslangicTarihi { get; set; }
    public DateTime BitisTarihi { get; set; }
    public bool IsActive { get; set; } = true;

    // Navigation properties
    public virtual ICollection<AidatTahakkuk> AidatTahakkuklari { get; set; } = new List<AidatTahakkuk>();
}