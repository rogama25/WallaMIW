using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TelegramBot
{
    internal class Status
    {
        string? Addtional { get; set; }
        StatusType Type { get; set; }
    }

    internal enum StatusType
    {
        MENU,
        PURCHASING
    }
}
