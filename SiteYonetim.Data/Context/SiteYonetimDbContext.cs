using Microsoft.EntityFrameworkCore;
using SiteYonetim.Core.Models;

namespace SiteYonetim.Data.Context;

public class SiteYonetimDbContext : DbContext
{
    public SiteYonetimDbContext(DbContextOptions<SiteYonetimDbContext> options) : base(options)
    {
    }

    // DbSets
    public DbSet<User> Users { get; set; }
    public DbSet<Daire> Daireler { get; set; }
    public DbSet<AidatTanim> AidatTanimlari { get; set; }
    public DbSet<AidatTahakkuk> AidatTahakkuklari { get; set; }
    public DbSet<AidatOdeme> AidatOdemeleri { get; set; }
    public DbSet<BankaHesap> BankaHesaplari { get; set; }
    public DbSet<BankaHareket> BankaHareketleri { get; set; }
    public DbSet<Gider> Giderler { get; set; }
    public DbSet<YoneticiGecmis> YoneticiGecmisleri { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        if (!optionsBuilder.IsConfigured)
        {
            optionsBuilder.UseSqlite("Data Source=SiteYonetim.db");
        }
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        // Configure entities
        ConfigureUser(modelBuilder);
        ConfigureDaire(modelBuilder);
        ConfigureAidatTanim(modelBuilder);
        ConfigureAidatTahakkuk(modelBuilder);
        ConfigureAidatOdeme(modelBuilder);
        ConfigureBankaHesap(modelBuilder);
        ConfigureBankaHareket(modelBuilder);
        ConfigureGider(modelBuilder);
        ConfigureYoneticiGecmis(modelBuilder);

        // Add query filters for soft delete
        modelBuilder.Entity<User>().HasQueryFilter(u => !u.IsDeleted);
        modelBuilder.Entity<Daire>().HasQueryFilter(d => !d.IsDeleted);
        modelBuilder.Entity<AidatTanim>().HasQueryFilter(at => !at.IsDeleted);
        modelBuilder.Entity<AidatTahakkuk>().HasQueryFilter(ath => !ath.IsDeleted);
        modelBuilder.Entity<AidatOdeme>().HasQueryFilter(ao => !ao.IsDeleted);
        modelBuilder.Entity<BankaHesap>().HasQueryFilter(bh => !bh.IsDeleted);
        modelBuilder.Entity<BankaHareket>().HasQueryFilter(bhar => !bhar.IsDeleted);
        modelBuilder.Entity<Gider>().HasQueryFilter(g => !g.IsDeleted);
        modelBuilder.Entity<YoneticiGecmis>().HasQueryFilter(yg => !yg.IsDeleted);
    }

    private void ConfigureUser(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<User>(entity =>
        {
            entity.HasKey(e => e.Id);
            entity.Property(e => e.FirstName).HasMaxLength(100).IsRequired();
            entity.Property(e => e.LastName).HasMaxLength(100).IsRequired();
            entity.Property(e => e.Username).HasMaxLength(50).IsRequired();
            entity.Property(e => e.Password).HasMaxLength(500).IsRequired();
            entity.Property(e => e.Email).HasMaxLength(200);
            entity.Property(e => e.Phone).HasMaxLength(20);
            entity.HasIndex(e => e.Username).IsUnique();
        });
    }

    private void ConfigureDaire(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Daire>(entity =>
        {
            entity.HasKey(e => e.Id);
            entity.Property(e => e.DaireNo).HasMaxLength(10).IsRequired();
            entity.Property(e => e.Alan).HasPrecision(10, 2);
            entity.HasOne(e => e.User)
                  .WithMany(u => u.Daireler)
                  .HasForeignKey(e => e.UserId)
                  .OnDelete(DeleteBehavior.Restrict);
            entity.HasIndex(e => e.DaireNo).IsUnique();
        });
    }

    private void ConfigureAidatTanim(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<AidatTanim>(entity =>
        {
            entity.HasKey(e => e.Id);
            entity.Property(e => e.Ad).HasMaxLength(200).IsRequired();
            entity.Property(e => e.Aciklama).HasMaxLength(500);
            entity.Property(e => e.Tutar).HasPrecision(18, 2);
        });
    }

    private void ConfigureAidatTahakkuk(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<AidatTahakkuk>(entity =>
        {
            entity.HasKey(e => e.Id);
            entity.Property(e => e.Tutar).HasPrecision(18, 2);
            entity.Property(e => e.OdenenTutar).HasPrecision(18, 2);
            entity.HasOne(e => e.User)
                  .WithMany(u => u.AidatTahakkuklari)
                  .HasForeignKey(e => e.UserId)
                  .OnDelete(DeleteBehavior.Restrict);
            entity.HasOne(e => e.AidatTanim)
                  .WithMany(at => at.AidatTahakkuklari)
                  .HasForeignKey(e => e.AidatTanimId)
                  .OnDelete(DeleteBehavior.Restrict);
        });
    }

    private void ConfigureAidatOdeme(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<AidatOdeme>(entity =>
        {
            entity.HasKey(e => e.Id);
            entity.Property(e => e.Tutar).HasPrecision(18, 2);
            entity.Property(e => e.Aciklama).HasMaxLength(500);
            entity.Property(e => e.FisNo).HasMaxLength(50);
            entity.HasOne(e => e.User)
                  .WithMany()
                  .HasForeignKey(e => e.UserId)
                  .OnDelete(DeleteBehavior.Restrict);
            entity.HasOne(e => e.AidatTahakkuk)
                  .WithMany(ath => ath.AidatOdemeleri)
                  .HasForeignKey(e => e.AidatTahakkukId)
                  .OnDelete(DeleteBehavior.Restrict);
            entity.HasOne(e => e.BankaHesap)
                  .WithMany(bh => bh.AidatOdemeleri)
                  .HasForeignKey(e => e.BankaHesapId)
                  .OnDelete(DeleteBehavior.SetNull);
        });
    }

    private void ConfigureBankaHesap(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<BankaHesap>(entity =>
        {
            entity.HasKey(e => e.Id);
            entity.Property(e => e.Ad).HasMaxLength(200).IsRequired();
            entity.Property(e => e.BankaAdi).HasMaxLength(100).IsRequired();
            entity.Property(e => e.HesapNo).HasMaxLength(50);
            entity.Property(e => e.IBAN).HasMaxLength(34);
            entity.Property(e => e.Bakiye).HasPrecision(18, 2);
        });
    }

    private void ConfigureBankaHareket(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<BankaHareket>(entity =>
        {
            entity.HasKey(e => e.Id);
            entity.Property(e => e.Tutar).HasPrecision(18, 2);
            entity.Property(e => e.OncekiBakiye).HasPrecision(18, 2);
            entity.Property(e => e.SonrakiBakiye).HasPrecision(18, 2);
            entity.Property(e => e.Aciklama).HasMaxLength(500);
            entity.Property(e => e.ReferansNo).HasMaxLength(50);
            entity.HasOne(e => e.BankaHesap)
                  .WithMany(bh => bh.BankaHareketleri)
                  .HasForeignKey(e => e.BankaHesapId)
                  .OnDelete(DeleteBehavior.Cascade);
        });
    }

    private void ConfigureGider(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Gider>(entity =>
        {
            entity.HasKey(e => e.Id);
            entity.Property(e => e.Ad).HasMaxLength(200).IsRequired();
            entity.Property(e => e.Kategori).HasMaxLength(100);
            entity.Property(e => e.Aciklama).HasMaxLength(500);
            entity.Property(e => e.Tutar).HasPrecision(18, 2);
            entity.Property(e => e.FisNo).HasMaxLength(50);
            entity.HasOne(e => e.BankaHesap)
                  .WithMany(bh => bh.Giderler)
                  .HasForeignKey(e => e.BankaHesapId)
                  .OnDelete(DeleteBehavior.SetNull);
            entity.HasOne(e => e.KaydedenUser)
                  .WithMany()
                  .HasForeignKey(e => e.KaydedenUserId)
                  .OnDelete(DeleteBehavior.Restrict);
        });
    }

    private void ConfigureYoneticiGecmis(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<YoneticiGecmis>(entity =>
        {
            entity.HasKey(e => e.Id);
            entity.Property(e => e.Aciklama).HasMaxLength(500);
            entity.HasOne(e => e.User)
                  .WithMany(u => u.YoneticiGecmisleri)
                  .HasForeignKey(e => e.UserId)
                  .OnDelete(DeleteBehavior.Restrict);
        });
    }
}