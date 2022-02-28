using MongoDB.Driver;

namespace Buy
{
    public class Mongo
    {
        private readonly IMongoCollection<Product> _products;

        public Mongo(MongoClient mongo, string database, string collection)
        {
            _products = mongo.GetDatabase(database).GetCollection<Product>(collection);
        }
        public Product GetProduct(int id)
        {
            return _products.Find(product => product.Id == id).FirstOrDefault();
        }

        public bool ReduceStockProduct(int id, int amountReduced)
        {
            return _products.UpdateOne(product => product.Id == id && product.Stock >= amountReduced,
                Builders<Product>.Update.Inc(product => product.Stock, -amountReduced)).MatchedCount > 0;
        }
    }
}
