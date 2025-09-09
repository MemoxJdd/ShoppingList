namespace SiteYonetim.Common.Extensions;

public static class DateTimeExtensions
{
    public static string ToTurkishDateString(this DateTime date)
    {
        return date.ToString("dd.MM.yyyy");
    }

    public static string ToTurkishDateTimeString(this DateTime dateTime)
    {
        return dateTime.ToString("dd.MM.yyyy HH:mm:ss");
    }

    public static bool IsInMonth(this DateTime date, int year, int month)
    {
        return date.Year == year && date.Month == month;
    }

    public static DateTime StartOfMonth(this DateTime date)
    {
        return new DateTime(date.Year, date.Month, 1);
    }

    public static DateTime EndOfMonth(this DateTime date)
    {
        return new DateTime(date.Year, date.Month, DateTime.DaysInMonth(date.Year, date.Month));
    }
}