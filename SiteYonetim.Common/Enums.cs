namespace SiteYonetim.Common;

public enum UserRole
{
    Yonetici = 1,      // Yönetici (Manager)
    Yardimci = 2,      // Yardımcı (Assistant)
    Denetleyici = 3,   // Denetleyici (Auditor)
    Sakin = 4          // Sakin (Resident)
}

public enum DaireType
{
    Tek = 1,           // 1+0
    Bir = 2,           // 1+1
    Iki = 3,           // 2+1
    Uc = 4,            // 3+1
    Dort = 5,          // 4+1
    Dubleks = 6,       // Dupleks
    Penthouse = 7      // Penthouse
}

public enum TransactionType
{
    Gelir = 1,         // Income
    Gider = 2          // Expense
}

public enum PaymentStatus
{
    Odendi = 1,        // Paid
    Odenmedi = 2,      // Unpaid
    Gecikti = 3,       // Overdue
    KismiOdendi = 4    // Partially paid
}

public enum Currency
{
    TRY = 1,           // Turkish Lira
    USD = 2,           // US Dollar
    EUR = 3            // Euro
}