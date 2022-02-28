using Microsoft.AspNetCore.Mvc;

namespace Buy;

public class Buy
{
    public static void Main(String[] args)
    {
        var builder = WebApplication.CreateBuilder(args);

        // Add services to the container.
        // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
        builder.Services.AddEndpointsApiExplorer();
        builder.Services.AddSwaggerGen();
        builder.Services.AddCors(p => p.AddPolicy("cors", builder =>
        {
            builder.WithOrigins("*").AllowAnyMethod().AllowAnyHeader();
        }));

        var app = builder.Build();

        // Configure the HTTP request pipeline.
        if (app.Environment.IsDevelopment())
        {
            app.UseSwagger();
            app.UseSwaggerUI();
        }
        app.UseCors("cors");

        var mongo = new Mongo(new MongoDB.Driver.MongoClient(builder.Configuration.GetValue<string>("Mongo:ConnectionString")),
            builder.Configuration.GetValue<string>("Mongo:DbName"),
            builder.Configuration.GetValue<string>("Mongo:CollectionName"));

        app.MapPost("/buy", ([FromBody] int id, [FromBody] int amount) =>
        {
            if (mongo.GetProduct(id) == null)
            {
                return Results.NotFound();
            }
            if (mongo.ReduceStockProduct(id, amount))
            {
                return Results.Conflict();
            }
            return Results.Ok();
        });

        //var summaries = new[]
        //{
        //    "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        //};

        //app.MapGet("/weatherforecast", () =>
        //{
        //    var forecast = Enumerable.Range(1, 5).Select(index =>
        //       new WeatherForecast
        //       (
        //           DateTime.Now.AddDays(index),
        //           Random.Shared.Next(-20, 55),
        //           summaries[Random.Shared.Next(summaries.Length)]
        //       ))
        //        .ToArray();
        //    return forecast;
        //})
        //.WithName("GetWeatherForecast");

        app.Run();

        //internal record WeatherForecast(DateTime Date, int TemperatureC, string? Summary)
        //{
        //    public int TemperatureF => 32 + (int)(TemperatureC / 0.5556);
        //}
    }
}
