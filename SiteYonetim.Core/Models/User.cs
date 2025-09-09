using SiteYonetim.Common;

namespace SiteYonetim.Core.Models;

public class User : BaseEntity
{
    public string FirstName { get; set; } = string.Empty;
    public string LastName { get; set; } = string.Empty;
    public string Username { get; set; } = string.Empty;
    public string Password { get; set; } = string.Empty; // Should be hashed
    public string Email { get; set; } = string.Empty;
    public string Phone { get; set; } = string.Empty;
    public UserRole Role { get; set; }
    public bool IsActive { get; set; } = true;

    // Navigation properties
    public virtual ICollection<Daire> Daireler { get; set; } = new List<Daire>();
    public virtual ICollection<YoneticiGecmis> YoneticiGecmisleri { get; set; } = new List<YoneticiGecmis>();
    public virtual ICollection<AidatTahakkuk> AidatTahakkuklari { get; set; } = new List<AidatTahakkuk>();

    public string FullName => $"{FirstName} {LastName}";
}