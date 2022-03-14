using RestSharp;

namespace TelegramBot
{
    public class BuyHandler
    {
        public static string BuyProduct(string id, int amount)
        {
            var client = new RestClient("http://buyproducts");
            var request = new RestRequest("/products/" + id + "/buy", Method.Post)
            {
                RequestFormat = DataFormat.Json
            };
            request.AddJsonBody(new BuyJson() { Amount = amount });
            var response = client.ExecuteAsync(request).Result;
            return response.StatusCode switch
            {
                System.Net.HttpStatusCode.NoContent => "Producto comprado con éxito",
                System.Net.HttpStatusCode.Conflict => "No había suficientes unidades",
                System.Net.HttpStatusCode.NotFound => "No hemos podido localizar el producto que intentabas comprar",
                _ => "Ha ocurrido un error inesperado",
            };
        }
    }

    public class BuyJson
    {
        public int Amount { get; set; }
    }
}
