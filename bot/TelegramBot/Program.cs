using LiteDB;
using Telegram.Bot;
using Telegram.Bot.Exceptions;
using Telegram.Bot.Extensions.Polling;
using Telegram.Bot.Types;
using Telegram.Bot.Types.Enums;

namespace TelegramBot
{
    class MainClass
    {
        static async Task Main(string[] args)
        {
            string? API_KEY = Environment.GetEnvironmentVariable("TELEGRAM_API_KEY");
            if (API_KEY == null)
            {
                API_KEY = "5145750519:AAH_4ZCd0JjbhQRoEajjPxXi4eJcX6cr248";
            }
            var bot = new TelegramBotClient(API_KEY);
            using var cts = new CancellationTokenSource();

            var receiverOptions = new ReceiverOptions
            {
                AllowedUpdates = { }
            };
            var me = await bot.GetMeAsync();
            Console.WriteLine("Encendido bot de Telegram. https://t.me/" + me.Username);
            using var db = new LiteDatabase("/data/db/status.db");
            var collection = db.GetCollection<Status>("status");

            async Task HandleUpdate(ITelegramBotClient bot, Update update, CancellationToken cancellationToken)
            {

            }

            Task HandleErrorAsync(ITelegramBotClient botClient, Exception exception, CancellationToken cancellationToken)
            {
                var ErrorMessage = exception switch
                {
                    ApiRequestException apiRequestException
                        => $"Telegram API Error:\n[{apiRequestException.ErrorCode}]\n{apiRequestException.Message}",
                    _ => exception.ToString()
                };

                Console.WriteLine(ErrorMessage);
                return Task.CompletedTask;
            }
        }
    }
}
