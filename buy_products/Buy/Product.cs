using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace Buy
{
    public class Product
    {
        [BsonId]
        public int Id;

        [BsonElement("title")]
        public string Title = string.Empty;

        [BsonElement("price")]
        public float Price;

        [BsonElement("description")]
        public string Description = string.Empty;

        [BsonElement("category")]
        public string Category = string.Empty;

        [BsonElement("image")]
        public string Image = string.Empty;

        [BsonElement("manual")]
        public bool Manual;

        [BsonElement("stock")]
        public int Stock;

        [BsonElement("favorites")]
        public int Favorites;
    }
}
