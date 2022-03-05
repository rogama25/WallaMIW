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

        var app = builder.Build();

        // Configure the HTTP request pipeline.
        if (app.Environment.IsDevelopment())
        {
            app.UseSwagger();
            app.UseSwaggerUI();
        }

        Mongo mongo = new Mongo(new MongoDB.Driver.MongoClient(builder.Configuration.GetValue<string>("Mongo:ConnectionString")),
            builder.Configuration.GetValue<string>("Mongo:DbName"),
            builder.Configuration.GetValue<string>("Mongo:CollectionName"));

        app.MapPost("/products/{id}/buy", ([FromBody] BuyJson body, int id) =>
        {
            if (mongo.GetProduct(id) == null)
            {
                return Results.NotFound();
            }
            if (!mongo.ReduceStockProduct(id, body.amount))
            {
                return Results.Conflict();
            }
            return Results.Ok();
        }).Produces(StatusCodes.Status204NoContent).Produces(StatusCodes.Status404NotFound).Produces(StatusCodes.Status409Conflict);

        app.Run();
    }
}

public class BuyJson
{
    public int amount { get; set; }
}
