namespace SiteYonetim.Core.Models;

public class YoneticiGecmis : BaseEntity
{
    public int UserId { get; set; }
    public DateTime BaslangicTarihi { get; set; }
    public DateTime? BitisTarihi { get; set; }
    public string Aciklama { get; set; } = string.Empty;
    public bool IsActive { get; set; } = true;

    // Navigation properties
    public virtual User User { get; set; } = null!;

    public bool IsCurrentManager => IsActive && BitisTarihi == null;
    public TimeSpan? GorevSuresi => BitisTarihi?.Subtract(BaslangicTarihi) ?? DateTime.Now.Subtract(BaslangicTarihi);
}