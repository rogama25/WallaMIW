using LiteDB;

namespace TelegramBot
{
    internal class Status
    {
        [BsonId]
        public long UserId { get; set; }
        public string? Addtional { get; set; }
        public StatusType Type { get; set; }
    }

    internal enum StatusType
    {
        MENU,
        PURCHASING
    }
}
