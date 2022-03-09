using LiteDB;
using Telegram.Bot;
using Telegram.Bot.Exceptions;
using Telegram.Bot.Extensions.Polling;
using Telegram.Bot.Types;
using Telegram.Bot.Types.Enums;
using Telegram.Bot.Types.InlineQueryResults;
using Telegram.Bot.Types.ReplyMarkups;

namespace TelegramBot
{
    class MainClass
    {
        static async Task Main()
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
            Directory.CreateDirectory("/data/db");

            bot.StartReceiving(MainClass.HandleUpdate, MainClass.HandleErrorAsync, receiverOptions, cancellationToken: cts.Token);
            await Task.Delay(Timeout.Infinite, cts.Token);
        }

        static async Task HandleUpdate(ITelegramBotClient bot, Update update, CancellationToken cancellationToken)
        {
            using var db = new LiteDatabase(@"/data/db/status.db");
            var collection = db.GetCollection<Status>("status");
            switch (update.Type)
            {
                case UpdateType.Message:
                    if (update.Message!.Chat.Type == ChatType.Private)
                    {
                        switch (update.Message!.Type)
                        {
                            case MessageType.Text:
                                if (!collection!.Exists(s => s.UserId == update.Message.From!.Id) && update.Message.Text!.ToLower() == "microserviciosmiw"
                                    || collection!.Exists(s => s.UserId == update.Message.From!.Id) && update.Message.Text!.ToLower().StartsWith("/start"))
                                {
                                    collection.Upsert(new Status { UserId = update.Message.From!.Id, Type = StatusType.MENU });
                                    await bot.SendTextMessageAsync(update.Message.Chat.Id, "¡Bienvenido a WallaMIW, tu tienda de ropa favorita!", replyMarkup: new InlineKeyboardMarkup(new[]
                                    {
                                            new []
                                            {
                                                InlineKeyboardButton.WithSwitchInlineQueryCurrentChat("Buscar productos")
                                            }
                                        }), cancellationToken: cancellationToken);
                                }
                                else if (collection!.Exists(s => s.UserId == update.Message.From!.Id))
                                {
                                    var status = collection!.FindOne(s => s.UserId == update.Message.From!.Id);
                                    if (status.Type == StatusType.PURCHASING)
                                    {
                                        var isNumeric = int.TryParse(update.Message.Text, out int amount);
                                        if (isNumeric)
                                        {
                                            var response = BuyHandler.BuyProduct(int.Parse(status.Addtional!), amount);
                                            await bot.SendTextMessageAsync(update.Message.Chat.Id, response, cancellationToken: cancellationToken);
                                            await GetInfoAndSend(bot, update.Message.Chat.Id, int.Parse(status.Addtional!), cancellationToken);
                                            collection.Upsert(new Status { UserId = update.Message.From!.Id, Type = StatusType.MENU });
                                        }
                                        else
                                        {
                                            await bot.SendTextMessageAsync(update.Message.Chat.Id, "Envíame, por favor, el número de unidades que quieres comprar. 0 para salir", cancellationToken: cancellationToken);
                                        }
                                    }
                                }
                                else
                                {
                                    await bot.SendTextMessageAsync(update.Message.Chat.Id, "No te tengo registrado. Envíame la contraseña", cancellationToken: cancellationToken);
                                }
                                break;
                            case MessageType.Photo:
                                if (update.Message.ViaBot != null && int.TryParse(update.Message.Caption, out int id) && update.Message.ViaBot.Equals(await bot.GetMeAsync(cancellationToken: cancellationToken)))
                                {
                                    await GetInfoAndSend(bot, update.Message.Chat.Id, id, cancellationToken);
                                }
                                break;
                        }
                    }
                    break;
                case UpdateType.CallbackQuery:
                    await bot.EditMessageReplyMarkupAsync(update.CallbackQuery!.InlineMessageId!, InlineKeyboardMarkup.Empty(), cancellationToken: cancellationToken);
                    if (update.CallbackQuery.Message == null)
                    {
                        await bot.AnswerCallbackQueryAsync(update.CallbackQuery.Id, "Ha habido un error. Prueba a empezar de nuevo usando /start", cancellationToken: cancellationToken);
                    }
                    else
                    {
                        if (update.CallbackQuery.Data!.StartsWith("b."))
                        {
                            var r = int.TryParse(update.CallbackQuery.Data.Replace("b.", ""), out var id);
                            if (r)
                            {
                                await bot.SendTextMessageAsync(update.CallbackQuery.Message.Chat.Id, "¿Cuántas unidades quieres?", cancellationToken: cancellationToken);
                                collection.Upsert(new Status { UserId = update.CallbackQuery.Message.From!.Id, Type = StatusType.PURCHASING, Addtional = id.ToString() });
                            }
                        }
                        else if (update.CallbackQuery.Data.StartsWith("f."))
                        {
                            var r = int.TryParse(update.CallbackQuery.Data.Replace("b.", ""), out var id);
                            if (r)
                            {
                                await GetInfoAndSend(bot, update.CallbackQuery.Message!.Chat.Id, id, cancellationToken);
                            }
                        }
                    }
                    break;
                case UpdateType.InlineQuery:
                    // Obtener lista, filtrar y answerInlineQuery
                    InlineQueryResultPhoto[] results = Array.Empty<InlineQueryResultPhoto>();
                    //foreach (var p in products)
                    //if ("a".ToLower().Contains(update.InlineQuery!.Query.ToLower()))
                    await bot.AnswerInlineQueryAsync(update.InlineQuery!.Id, results, 30, true, cancellationToken: cancellationToken);
                    break;
            }
        }

        static Task HandleErrorAsync(ITelegramBotClient botClient, Exception exception, CancellationToken cancellationToken)
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

        static async Task GetInfoAndSend(ITelegramBotClient bot, long chatId, int productId, CancellationToken cts)
        {
            // Obtener lista y filtrar
            await bot.SendPhotoAsync(chatId, "URL", cancellationToken: cts);
            string message = $"*{"Camiseta"}*\n{"Camiseta reshulona"}\n_{23.99 + "€"}\n{"Men"}\n{3} unidades\n{18}favoritos";
            await bot.SendTextMessageAsync(chatId, message, ParseMode.MarkdownV2, cancellationToken: cts, replyMarkup: new InlineKeyboardMarkup(new[]
            {
                new[]
                {
                    InlineKeyboardButton.WithCallbackData("Comprar este producto", $"b.{1}")
                },
                new[]
                {
                    InlineKeyboardButton.WithCallbackData("Marcar como favorito", $"f.{1}")
                },
                new[]
                {
                    InlineKeyboardButton.WithSwitchInlineQueryCurrentChat("Buscar productos")
                }
            }));
        }
    }
}
