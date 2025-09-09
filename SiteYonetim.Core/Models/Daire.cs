using SiteYonetim.Common;

namespace SiteYonetim.Core.Models;

public class Daire : BaseEntity
{
    public string DaireNo { get; set; } = string.Empty;
    public int Kat { get; set; }
    public DaireType Type { get; set; }
    public decimal Alan { get; set; } // m²
    public int UserId { get; set; }
    public bool IsEmpty { get; set; } = false;

    // Navigation properties
    public virtual User User { get; set; } = null!;
    public virtual ICollection<AidatTanim> AidatTanimlari { get; set; } = new List<AidatTanim>();
}